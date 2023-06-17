package layoutsControllers;

import changingClasses.Arguments;
import changingClasses.ServerReply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.*;
import validator.Validator;

import java.time.LocalDate;
import java.util.InputMismatchException;

public class UpdateElementLayoutController {
    public static Flat flat;
    @FXML
    VBox parentBox;
    @FXML
    TextField enterName;
    @FXML
    TextField enterId;
    @FXML
    TextField enterCoordinateX;
    @FXML
    TextField enterCoordinateY;
    @FXML
    TextField enterArea;
    @FXML
    TextField enterNumberOfRooms;
    @FXML
    TextField enterFurniture;
    @FXML
    TextField enterNameOfHouse;
    @FXML
    TextField enterYearOfHouse;
    @FXML
    TextField enterNumberOfFlats;

    @FXML
    Label textEnterElement;
    @FXML
    Label textEnterId;
    @FXML
    Label textEnterName;
    @FXML
    Label textEnterCoordinateX;
    @FXML
    Label textEnterCoordinateY;
    @FXML
    Label textEnterArea;
    @FXML
    Label textEnterNumberOfRooms;
    @FXML
    Label textEnterFurniture;
    @FXML
    Label textEnterFurnish;
    @FXML
    Label textEnterView;
    @FXML
    Label textEnterNameOfHouse;
    @FXML
    Label textEnterYearOfHouse;
    @FXML
    Label textEnterNumberOfFlats;

    @FXML
    Button submitEnter;

    @FXML
    Button cancel;
    @FXML
    ComboBox<String> furnishComboBox;
    @FXML
    ComboBox<String> viewComboBox;

    public void initialize() {
        ObservableList<String> furnishes = FXCollections.observableArrayList("DESIGNER", "FINE", "BAD", "LITTLE");
        furnishComboBox.setItems(furnishes);
        furnishComboBox.setValue(flat.getFurnish().toString());
        ObservableList<String> views = FXCollections.observableArrayList("PARK", "BAD", "GOOD", "TERRIBLE");
        viewComboBox.setItems(views);
        viewComboBox.setValue(flat.getView().toString());
        textEnterElement.setText(ScenesController.getInstance().properties.getProperty("EnterElementData"));
        textEnterId.setText(ScenesController.getInstance().properties.getProperty("EnterId"));
        enterId.setText(Long.toString(flat.getId()));
        textEnterName.setText(ScenesController.getInstance().properties.getProperty("EnterName"));
        enterName.setText(flat.getName());
        textEnterCoordinateX.setText(ScenesController.getInstance().properties.getProperty("EnterCoordinateX"));
        enterCoordinateX.setText(Float.toString(flat.getCoordinates().getX()));
        textEnterCoordinateY.setText(ScenesController.getInstance().properties.getProperty("EnterCoordinateY"));
        enterCoordinateY.setText(flat.getCoordinates().getY().toString());
        textEnterArea.setText(ScenesController.getInstance().properties.getProperty("EnterArea"));
        enterArea.setText(Long.toString(flat.getArea()));
        textEnterNumberOfRooms.setText(ScenesController.getInstance().properties.getProperty("EnterNumberOfRooms"));
        enterNumberOfRooms.setText(Long.toString(flat.getNumberOfRooms()));
        textEnterFurniture.setText(ScenesController.getInstance().properties.getProperty("EnterFurniture"));
        enterFurniture.setText(Boolean.toString(flat.isFurniture()));
        textEnterFurnish.setText(ScenesController.getInstance().properties.getProperty("EnterFurnish"));
        textEnterView.setText(ScenesController.getInstance().properties.getProperty("EnterView"));
        textEnterNameOfHouse.setText(ScenesController.getInstance().properties.getProperty("EnterNameOfHouse"));
        enterNameOfHouse.setText(flat.getHouse().getName());
        textEnterYearOfHouse.setText(ScenesController.getInstance().properties.getProperty("EnterYear"));
        enterYearOfHouse.setText(Long.toString(flat.getHouse().getYear()));
        textEnterNumberOfFlats.setText(ScenesController.getInstance().properties.getProperty("EnterNumberOfFlats"));
        enterNumberOfFlats.setText(Integer.toString(flat.getHouse().getNumberOfFlatsOnFloor()));
        submitEnter.setText(ScenesController.getInstance().properties.getProperty("SubmitEntering"));
        cancel.setText(ScenesController.getInstance().properties.getProperty("Cancel"));
    }
    @FXML
    public void enterData(){
        Long id = null;
        try {
            id = Long.parseLong(enterId.getText());
        }
        catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongId"));
            return;
        }
        String name = null;
        try{
            name = enterName.getText();
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongName"));
            return;
        }
        float coordinate_x = 0;
        try{
            coordinate_x = Float.parseFloat(enterCoordinateX.getText());
            if(!Validator.checkCoordinateX(coordinate_x)){
                throw  new Exception();
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongCoordinateX"));
            return;
        }
        Integer coordinate_y = null;
        try{
            coordinate_y = Integer.parseInt(enterCoordinateY.getText());
            if(!Validator.checkCoordinateY(coordinate_y)){
                throw  new Exception();
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongCoordinateY"));
            return;
        }
        Long area = null;
        try{
            area = Long.parseLong(enterArea.getText());
            if(!Validator.checkArea(area)){
                throw  new Exception();
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongArea"));
            return;
        }
        long number_of_rooms = 0;
        try{
            number_of_rooms = Long.parseLong(enterNumberOfRooms.getText());
            if(!Validator.checkNumberOfRooms(number_of_rooms)){
                throw  new Exception();
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongNumberOfRooms"));
            return;
        }
        boolean furniture = false;
        try{
            furniture = Boolean.parseBoolean(enterFurniture.getText());
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongFurniture"));
            return;
        }
        Furnish furnish = Furnish.DESIGNER;
        try{
            String dummy = furnishComboBox.getValue();
            furnish = switch (dummy) {
                case ("DESIGNER") -> Furnish.DESIGNER;
                case ("FINE") -> Furnish.FINE;
                case ("BAD") -> Furnish.BAD;
                case ("LITTLE") -> Furnish.LITTLE;
                default -> throw new InputMismatchException();
            };
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongFurnish"));
            return;
        }
        View view = View.BAD;
        try{
            String dummy = viewComboBox.getValue();
            view = switch (dummy) {
                case ("BAD") -> View.BAD;
                case ("GOOD") -> View.GOOD;
                case ("PARK") -> View.PARK;
                case ("TERRIBLE") -> View.TERRIBLE;
                default -> throw new NumberFormatException();
            };
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongView"));
            return;
        }
        String nameOfHouse = null;
        try{
            nameOfHouse = enterNameOfHouse.getText();
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongNameOfHouse"));
            return;
        }
        long yearOfHouse = 0;
        try{
            yearOfHouse = Long.parseLong(enterYearOfHouse.getText());
            if(!Validator.checkYearOfHouse(yearOfHouse)){
                throw  new Exception();
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongYearOfHouse"));
            return;
        }
        int numberOfFlats = 0;
        try{
            if(!Validator.checkNumberOfFlats(numberOfFlats)){
                throw  new Exception();
            }
            numberOfFlats = Integer.parseInt(enterNumberOfFlats.getText());
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongNumberOfFlats"));
            return;
        }
        LocalDate localDate = LocalDate.now();
        Flat flat = new Flat(-1 ,name, new Coordinates(coordinate_x,coordinate_y), localDate, area, number_of_rooms, furniture, furnish, view, new House(nameOfHouse, yearOfHouse, numberOfFlats));
        Arguments arguments = new Arguments("update", id.toString(), flat);
        ScenesController.getInstance().sendCommandToServer(arguments);
        ServerReply serverReply = ScenesController.getInstance().getServerReply();
        ScenesController.getInstance().update_simplified_data();
        parentBox.getScene().getWindow().hide();
    }
    private void showAlert(String massage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(massage);
        alert.showAndWait();
    }

    @FXML
    public void quit(){
        parentBox.getScene().getWindow().hide();
    }

}
