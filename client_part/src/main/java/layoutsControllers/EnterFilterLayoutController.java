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

public class EnterFilterLayoutController {
    @FXML
    VBox parentBox;
    @FXML
    TextField enterName;
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
        ObservableList<String> furnishes = FXCollections.observableArrayList("DESIGNER", "FINE", "BAD", "LITTLE", "Null");
        furnishComboBox.setItems(furnishes);
        ObservableList<String> views = FXCollections.observableArrayList("PARK", "BAD", "GOOD", "TERRIBLE", "Null");
        viewComboBox.setItems(views);
        textEnterElement.setText(ScenesController.getInstance().properties.getProperty("EnterFilterData"));
        textEnterName.setText(ScenesController.getInstance().properties.getProperty("EnterName"));
        textEnterCoordinateX.setText(ScenesController.getInstance().properties.getProperty("EnterCoordinateX"));
        textEnterCoordinateY.setText(ScenesController.getInstance().properties.getProperty("EnterCoordinateY"));
        textEnterArea.setText(ScenesController.getInstance().properties.getProperty("EnterArea"));
        textEnterNumberOfRooms.setText(ScenesController.getInstance().properties.getProperty("EnterNumberOfRooms"));
        textEnterFurniture.setText(ScenesController.getInstance().properties.getProperty("EnterFurniture"));
        textEnterFurnish.setText(ScenesController.getInstance().properties.getProperty("EnterFurnish"));
        textEnterView.setText(ScenesController.getInstance().properties.getProperty("EnterView"));
        textEnterNameOfHouse.setText(ScenesController.getInstance().properties.getProperty("EnterNameOfHouse"));
        textEnterYearOfHouse.setText(ScenesController.getInstance().properties.getProperty("EnterYear"));
        textEnterNumberOfFlats.setText(ScenesController.getInstance().properties.getProperty("EnterNumberOfFlats"));
        submitEnter.setText(ScenesController.getInstance().properties.getProperty("SubmitEntering"));
        cancel.setText(ScenesController.getInstance().properties.getProperty("Cancel"));
    }


    @FXML
    public void enterData(){
        String name = null;
        try{
            if(enterName.getText().equals("")){

            }else {
                name = enterName.getText();
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongName"));
            return;
        }
        float coordinate_x = -1;
        try{
            if(enterCoordinateX.getText().equals("")){
                coordinate_x = -1;
            }else {
                coordinate_x = Float.parseFloat(enterCoordinateX.getText());
                if (!Validator.checkCoordinateX(coordinate_x)) {
                    throw new Exception();
                }
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongCoordinateX"));
            return;
        }
        Integer coordinate_y = null;
        try{
            if(enterCoordinateY.getText().equals("")){
                coordinate_y = null;
            }else {
                coordinate_y = Integer.parseInt(enterCoordinateY.getText());
                if (!Validator.checkCoordinateY(coordinate_y)) {
                    throw new Exception();
                }
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongCoordinateY"));
            return;
        }
        Long area = null;
        try {
            if (!enterArea.getText().equals("")) {
                area = Long.parseLong(enterArea.getText());
            if (!Validator.checkArea(area)) {
                throw new Exception();
            }
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongArea"));
            return;
        }
        long number_of_rooms = -1;
        try{
            if(!enterNumberOfRooms.getText().equals("")) {
                number_of_rooms = Long.parseLong(enterNumberOfRooms.getText());
                if (!Validator.checkNumberOfRooms(number_of_rooms)) {
                    throw new Exception();
                }
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongNumberOfRooms"));
            return;
        }
        boolean furniture = false;
        try{
            if(!enterFurniture.getText().equals("")) {
                furniture = Boolean.parseBoolean(enterFurniture.getText());
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongFurniture"));
            return;
        }
        Furnish furnish = null;
        try{
            if(!furnishComboBox.getValue().equals("Null")) {
                String dummy = furnishComboBox.getValue();
                furnish = switch (dummy) {
                    case ("DESIGNER") -> Furnish.DESIGNER;
                    case ("FINE") -> Furnish.FINE;
                    case ("BAD") -> Furnish.BAD;
                    case ("LITTLE") -> Furnish.LITTLE;
                    default -> throw new InputMismatchException();
                };
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongFurnish"));
            return;
        }
        View view = null;
        try{
            if(!viewComboBox.getValue().equals("Null")) {
                String dummy = viewComboBox.getValue();
                view = switch (dummy) {
                    case ("BAD") -> View.BAD;
                    case ("GOOD") -> View.GOOD;
                    case ("PARK") -> View.PARK;
                    case ("TERRIBLE") -> View.TERRIBLE;
                    default -> throw new NumberFormatException();
                };
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongView"));
            return;
        }
        String nameOfHouse = null;
        try{
            if(!enterNameOfHouse.getText().equals("")) {
                nameOfHouse = enterNameOfHouse.getText();
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongNameOfHouse"));
            return;
        }
        long yearOfHouse = -1;
        try{
            if(!enterYearOfHouse.getText().equals("")) {
                yearOfHouse = Long.parseLong(enterYearOfHouse.getText());
                if (!Validator.checkYearOfHouse(yearOfHouse)) {
                    throw new Exception();
                }
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongYear"));
            return;
        }
        int numberOfFlats = -1;
        try{
            if(!enterNumberOfFlats.getText().equals("")) {
                numberOfFlats = Integer.parseInt(enterNumberOfFlats.getText());
                if (!Validator.checkNumberOfFlats(numberOfFlats)) {
                    throw new Exception();
                }
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongNumberOfFlats"));
            return;
        }
        Flat flat = new Flat(-1 ,name, new Coordinates(coordinate_x,coordinate_y), null, area, number_of_rooms, furniture, furnish, view, new House(nameOfHouse, yearOfHouse, numberOfFlats));
        ScenesController.getInstance().filters.add(flat);
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
