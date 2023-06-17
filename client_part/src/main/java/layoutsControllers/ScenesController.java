package layoutsControllers;

import changingClasses.Arguments;
import changingClasses.ServerReply;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.*;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;
import model.Flat;
import validator.Validator;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;


public class ScenesController extends Application{
    public  SocketChannel ch;
    private static ScenesController instance;
    public ArrayList<String> commands_with_elements;

    public ScenesController(){

    }
    public static ScenesController getInstance(){
        if(instance == null){
            instance = new ScenesController();
        }
        return instance;
    }
    protected  InputStream fis = ScenesController.class.getResourceAsStream("/russian_localisation.properties");
    protected  Properties properties = new Properties();
    protected  ObservableList<SimplifiedData> flats = FXCollections.observableArrayList();
    protected ArrayList<Flat> filters = new ArrayList<>();
    protected ArrayList<Flat> cash = new ArrayList<Flat>();
    protected  String login = null;
    protected ArrayList<Long> users_elements_id = new ArrayList<>();
    private  Scene register_scene;
    private Scene enter_scene;

    private Scene show_scene;
    private Canvas canvas;
    protected LinkedHashMap<Integer, ArrayList<Long>> user_id_flats_id_map = new LinkedHashMap<>();

    protected Stage primaryStage;
    public static void main(String[] args){
        Application.launch(args);
    }


    @Override
    public void start(Stage pS){
        instance.canvas = new Canvas(1408, 480);
        instance.primaryStage = pS;
        instance.properties = properties;
        try {
            instance.properties.load(instance.fis);
            FXMLLoader register_loader = new FXMLLoader(getClass().getResource("/RegistrationLayout.fxml"));
            Parent register_root = register_loader.load();
            instance.register_scene = new Scene(register_root);
            FXMLLoader enter_loader = new FXMLLoader(getClass().getResource("/EnteringLayout.fxml"));
            Parent enter_root = enter_loader.load();
            instance.enter_scene = new Scene(enter_root);
            instance.primaryStage.setScene(instance.register_scene);
            instance.primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    protected void update_simplified_data(){
        instance.flats.clear();
        instance.user_id_flats_id_map.clear();
        Arguments arguments = new Arguments("show", null, null);
        sendCommandToServer(arguments);
        ServerReply serverReply = getServerReply();
        instance.cash = serverReply.flats;
        instance.users_elements_id.clear();
        if(serverReply.massages.size() > 1) {
            if(serverReply.massages.get(1).length() > 2) {
                String chain = serverReply.massages.get(1).replace(" ", "");
                String chainWithOutBrackets = chain.substring(1, chain.length() - 1);
                String[] array = chainWithOutBrackets.split(",");
                for (String s : array) {
                    instance.users_elements_id.add(Long.parseLong(s));
                }
            }
        }
        for(String i : serverReply.massages){
            Integer User_id = null;
            ArrayList<Long> flats_id = new ArrayList<>();
            if(i.contains("::")){
                String[] sarray = i.split("::");
                if(sarray[1].equals("[]")){
                    continue;
                }
                User_id = Integer.parseInt(sarray[0]);
                String chain = sarray[1].replace(" ", "");
                String chainWithOutBrackets = chain.substring(1, chain.length() - 1);
                String[] array = chainWithOutBrackets.split(",");
                for(String s :array){
                    flats_id.add(Long.parseLong(s));
                }
                instance.user_id_flats_id_map.put(User_id, flats_id);
            }
        }
        for(int i = 0; i < serverReply.flats.size(); i++) {
            SimplifiedData simplifiedData = new SimplifiedData(serverReply.massages.get(i + 3), serverReply.flats.get(i));
            boolean correct = true;
            for (Flat a : filters) {
                if (!Validator.filterFlat(simplifiedData, a)) {
                    correct = false;
                    break;
                };
            }
            if (correct){
                instance.flats.add(simplifiedData);
            }
        }
        drawElements();
    }
    protected void setEnterScene(){
        instance.primaryStage.setScene(instance.enter_scene);
    }

    protected void setRegistrationScene(){
        instance.primaryStage.setScene(instance.register_scene);
    }
    protected void setShowScene(){
        try {
            update_simplified_data();
            FXMLLoader show_loader = new FXMLLoader(ScenesController.class.getResource("/ShowElementsLayout.fxml"));
            Group show_root = show_loader.load();
            instance.show_scene = new Scene(show_root);
            instance.primaryStage.setScene(instance.show_scene);
            instance.primaryStage.setResizable(false);

            VBox vBox = (VBox) show_root.getChildren().get(0);
            AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(1);

            instance.canvas = new Canvas(380, 450);
            instance.canvas.setLayoutX(1028);
            instance.canvas.setLayoutY(0);
            anchorPane.getChildren().add( instance.canvas);
            AnchorPane.setTopAnchor(instance.canvas, 0.0);
            AnchorPane.setRightAnchor(instance.canvas, 0.0);
            AnchorPane.setBottomAnchor(instance.canvas, 0.0);
            AnchorPane.setLeftAnchor(instance.canvas, 1028.0);
            instance.canvas.setOnMouseClicked(event -> {
                double mouseX = event.getX();
                double mouseY = event.getY();
                for(Flat a : instance.cash){
                    if (mouseX >= a.getCoordinates().getX() * 186 && mouseX <= a.getCoordinates().getX() * 186 + 10 * a.getNumberOfRooms()
                        && mouseY >= 450 - a.getCoordinates().getY() * 25 && mouseY <= 450 - a.getCoordinates().getY() * 25L + 10 * a.getNumberOfRooms()) {
                        showAlert(instance.properties.getProperty("YouClickedOnElement") + "\n" + a);
                    }

                }
            });
            drawElements();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    protected void updateProperties(){
        try {
            instance.properties.load(instance.fis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void drawElements(){
        GraphicsContext gc = instance.canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, instance.canvas.getWidth(), instance.canvas.getHeight());
        for(SimplifiedData a : instance.flats){
            if(instance.users_elements_id.contains(a.getId())){
                gc.setStroke(Color.GREEN);
                gc.setFill(Color.GREEN);
            }else {
                Integer user_id = null;
                for(Integer i : user_id_flats_id_map.keySet()){
                    if(user_id_flats_id_map.get(i).contains(a.getId())){
                        user_id = i;
                        break;
                    }
                }
                Color userColor = generateColor(user_id);
                gc.setStroke(userColor);
                gc.setFill(userColor);
            }
            double squareSize = 10 * a.getNumberOfRooms();
            double x = a.coordinate_x.doubleValue() * 180;
            double y = 450 - a.coordinate_y.doubleValue() * 25;

            // Рисование квадрата
            gc.setLineWidth(2);
            gc.fillRect(x, y, squareSize, squareSize);
            gc.strokeRect(x, y, squareSize, squareSize);
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), gc.getCanvas());
            translateTransition.setFromX(x-180);
            translateTransition.setToX(x-220); // Move 200 pixels to the right
            translateTransition.setCycleCount(2); // Move back and forth
            translateTransition.setAutoReverse(true);
            // Рисование равностороннего треугольника на верхней грани квадрата
            double triangleHeight = (Math.sqrt(3) / 2) * squareSize;
            double triangleTopX = x + squareSize / 2;
            double triangleTopY = y - triangleHeight;

            gc.beginPath();
            gc.moveTo(triangleTopX, triangleTopY);  // Верхняя точка треугольника
            gc.lineTo(x, y);  // Левая точка треугольника
            gc.lineTo(x + squareSize, y);  // Правая точка треугольника
            gc.lineTo(triangleTopX, triangleTopY);  // Замкнуть треугольник
            gc.closePath();
            gc.fill();
            gc.stroke();
            translateTransition.play();
        }
    }


    private Color generateColor(int user_id) {
        // Generate a color based on the user_id
        double hue = user_id * 137 % 360;  // Adjust the multiplier as needed
        double saturation = 0.8;           // Adjust the saturation and brightness as desired
        double brightness = 0.8;
        return Color.hsb(hue, saturation, brightness);
    }

    private void showAlert(String massage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(massage);
        alert.showAndWait();
    }

    protected void sendCommandToServer(Arguments arguments) {
        try {
            instance.ch.write(Arguments.toBuffer(arguments));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    protected ServerReply getServerReply(){
        ServerReply serverReply = null;
        int tries = 0;
        while (serverReply == null) {
            try {
                Thread.sleep(500);
                tries++;
                if (tries >= 30) {
                    System.out.println("Server doesn't reply for 15 seconds, disconnecting this client,try again later");
                    break;
                }
                ByteBuffer buffer = ByteBuffer.allocate(ch.socket().getReceiveBufferSize());
                instance.ch.read(buffer);
                serverReply = ServerReply.toServerReply(buffer);
            } catch (InterruptedException | IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return serverReply;
    }
}
