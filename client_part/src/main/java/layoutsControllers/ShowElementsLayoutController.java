package layoutsControllers;


import changingClasses.Arguments;
import changingClasses.ServerReply;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import listenersOfCommands.CommandListener;
import model.Flat;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class ShowElementsLayoutController {

    @FXML
    public TableView<SimplifiedData> tableView;
    @FXML
    public TableColumn<SimplifiedData, SimpleStringProperty> keyColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleLongProperty> idColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleStringProperty> nameColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleFloatProperty> coordinateXColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleIntegerProperty> coordinateYColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleStringProperty> creationDateColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleLongProperty> areaColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleLongProperty> numberOfRoomsColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleBooleanProperty> furnitureColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleStringProperty> furnishColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleStringProperty> viewColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleStringProperty> nameOfHouseColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleLongProperty> yearColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleIntegerProperty> numberOfFlatsColumn;
    @FXML
    public TableColumn<SimplifiedData, SimpleStringProperty> isYourElementColumn;
    @FXML
    public MenuItem addFilterMenu;
    @FXML
    public Menu filterMenu;
    @FXML
    public MenuItem removeAllFiltersMenu;
    @FXML
    public MenuItem removeFilterMenu;
    @FXML
    public MenuItem showFiltersMenu;
    public Menu exitMenu;
    public MenuItem exitFromApp;
    public MenuItem exitFromAccount;
    public MenuItem executeScriptButton;
    @FXML
    MenuItem usersElementsInfo;
    @FXML
    MenuItem usersLoginInfo;
    @FXML
    Menu aboutUserMenu;
    @FXML
    Menu helpMenu;
    @FXML
    Menu Edit;
    @FXML
    MenuItem aboutCollectionMenu;
    @FXML
    MenuItem clearButton;
    @FXML
    MenuItem countLessThanNumberOfRoomsButton;
    @FXML
    MenuItem historyButton;
    @FXML
    MenuItem addElementButton;
    @FXML
    MenuItem showMinimalByCoordinatesButton;
    @FXML
    MenuItem removeAllByNumberOfRoomsButton;
    @FXML
    MenuItem removeElementButton;
    @FXML
    MenuItem removeLowerKeyElementButton;
    @FXML
    MenuItem updateElementButton;
    @FXML
    ImageView imageView;

    public void initialize(){
        keyColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleStringProperty>("key"));
        idColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleLongProperty>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleStringProperty>("name"));
        coordinateXColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleFloatProperty>("coordinate_x"));
        coordinateYColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleIntegerProperty>("coordinate_y"));
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleStringProperty>("creationDate"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleLongProperty>("area"));
        numberOfRoomsColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleLongProperty>("numberOfRooms"));
        furnitureColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleBooleanProperty>("furniture"));
        furnishColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleStringProperty>("furnish"));
        viewColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleStringProperty>("view"));
        nameOfHouseColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleStringProperty>("name_of_house"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleLongProperty>("year_of_house"));
        numberOfFlatsColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleIntegerProperty>("numberOfFlatsOnFloor"));
        isYourElementColumn.setCellValueFactory(new PropertyValueFactory<SimplifiedData, SimpleStringProperty>("isYourElement"));
        tableView.setItems(ScenesController.getInstance().flats);
        Image image = null;
        try {
            String imagePath = getClass().getClassLoader().getResource("Coordinate_plane.png").toExternalForm();
            image = new Image(imagePath);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        imageView.setImage(image);
        updateLocalisation();
    }

    @FXML
    public void addElementButtonClicked() {
        try {
            FXMLLoader enter_element_loader = new FXMLLoader(getClass().getResource("/EnterElementLayout.fxml"));
            Parent enter_element_root = enter_element_loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(enter_element_root));
            stage.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void addFilterButtonClicked() {
        try {
            FXMLLoader enter_element_loader = new FXMLLoader(getClass().getResource("/EnterFilterLayout.fxml"));
            Parent enter_element_root = enter_element_loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(enter_element_root));
            stage.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void clearButtonClicked(){
        try{
            Arguments arguments = new Arguments("clear", null, null);
            ScenesController.getInstance().sendCommandToServer(arguments);
            ServerReply serverReply = ScenesController.getInstance().getServerReply();
            ScenesController.getInstance().update_simplified_data();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void countLessThanNumberOfRoomsButtonClicked() {
        try {
            FXMLLoader enter_element_loader = new FXMLLoader(getClass().getResource("/CountLessThanNumberOfRoomsLayout.fxml"));
            Parent enter_element_root = enter_element_loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(enter_element_root));
            stage.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void historyButtonClicked() {
        Arguments arguments = new Arguments("history",null,null);
        ScenesController.getInstance().sendCommandToServer(arguments);
        ServerReply serverReply =ScenesController.getInstance().getServerReply();
        String alert_string = "";
        for(String i : serverReply.massages){
            alert_string +=i + "\n";
        }
        showAlert(alert_string);
    }

    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        TextArea textArea = new TextArea(message);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    public void showMinimalByCoordinatesButtonClicked() {
        Arguments arguments = new Arguments("min_by_coordinates",null,null);
        ScenesController.getInstance().sendCommandToServer(arguments);
        ServerReply serverReply =ScenesController.getInstance().getServerReply();
        String alert_string = "";
        for(Flat i : serverReply.flats){
            alert_string +=i.toString();
        }
        showAlert(alert_string);
    }

    public void removeAllByNumberOfRoomsButtonClicked() {
        try {
            FXMLLoader enter_element_loader = new FXMLLoader(getClass().getResource("/RemoveAllByNumberOfRoomsLayout.fxml"));
            Parent enter_element_root = enter_element_loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(enter_element_root));
            stage.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void removeElementButtonClicked() {
        try {
            FXMLLoader enter_element_loader = new FXMLLoader(getClass().getResource("/RemoveElementLayout.fxml"));
            Parent enter_element_root = enter_element_loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(enter_element_root));
            stage.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void removeLowerElementsButtonClicked() {
        try {
            FXMLLoader enter_element_loader = new FXMLLoader(getClass().getResource("/RemoveLowerElementsLayout.fxml"));
            Parent enter_element_root = enter_element_loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(enter_element_root));
            stage.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void removeLowerKeyElementsButtonClicked() {
        try {
            FXMLLoader enter_element_loader = new FXMLLoader(getClass().getResource("/RemoveLowerKeyElementsLayout.fxml"));
            Parent enter_element_root = enter_element_loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(enter_element_root));
            stage.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateElementButtonClicked() {
        try {
            FXMLLoader enter_element_loader = new FXMLLoader(getClass().getResource("/UpdateElementIdChecking.fxml"));
            Parent enter_element_root = enter_element_loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(enter_element_root));
            stage.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void aboutCollectionButtonClicked() {
        Arguments arguments = new Arguments("info", null, null);
        ScenesController.getInstance().sendCommandToServer(arguments);
        ServerReply serverReply = ScenesController.getInstance().getServerReply();
        showAlert(serverReply.massages.get(0));
    }
    @FXML
    public void setUkrainLocalisation(){
        try {
            ScenesController.getInstance().fis = ScenesController.class.getResourceAsStream("/ukrain_localisation.properties");
            ScenesController.getInstance().updateProperties();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        updateLocalisation();
    }
    @FXML
    public void setRussianLocalisation(){
        try {
            ScenesController.getInstance().fis = ScenesController.class.getResourceAsStream("/russian_localisation.properties");
            ScenesController.getInstance().updateProperties();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        updateLocalisation();
    }

    @FXML
    public void setSpainLocalisation(){
        try {
            ScenesController.getInstance().fis = ScenesController.class.getResourceAsStream("/spain_localisation.properties");
            ScenesController.getInstance().updateProperties();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        updateLocalisation();
    }
    @FXML
    public void setNetherlandsLocalisation(){
        try {
            ScenesController.getInstance().fis = ScenesController.class.getResourceAsStream("/netherlands_localisation.properties");
            ScenesController.getInstance().updateProperties();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        updateLocalisation();
    }

    public void showUsersLogin() {
        showAlert(ScenesController.getInstance().properties.getProperty("YourLogin") + ":" + ScenesController.getInstance().login);
    }
    public void showUsersElements() {
        showAlert(ScenesController.getInstance().properties.getProperty("YourElements") + ":" + ScenesController.getInstance().users_elements_id.toString());
    }
    public void clearFilters() {
        ScenesController.getInstance().filters.clear();
        ScenesController.getInstance().update_simplified_data();
    }

    public void showFilters() {
        String massage = ScenesController.getInstance().properties.getProperty("Annotation") + "\n";
        for(int i = 0; i < ScenesController.getInstance().filters.size(); i++){
            massage += "id: " + i + ScenesController.getInstance().filters.get(i).toString();
        }
        showAlert(massage);
    }

    public void Exit() {
        System.exit(0);
    }
    public void ExitFromAccount() {
        Arguments arguments = new Arguments("reregister", null,null);
        ScenesController.getInstance().sendCommandToServer(arguments);
        ScenesController.getInstance().setRegistrationScene();
    }

    public void removeFilterById() {
        try {
            FXMLLoader enter_element_loader = new FXMLLoader(getClass().getResource("/RemoveFilterById.fxml"));
            Parent enter_element_root = enter_element_loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(enter_element_root));
            stage.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void executeScriptButtonClicked() {
        ArrayList<String> result = new ArrayList<>();
        try {
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(ScenesController.getInstance().primaryStage);
            CommandListener commandListener = new CommandListener(ScenesController.getInstance().ch, new FileInputStream(file), ScenesController.getInstance().commands_with_elements);
            result = commandListener.start_listening();
        }catch (Exception e){
            showAlert(e.getMessage());
            return;
        }
        showAlert(ScenesController.getInstance().properties.getProperty("ScriptWasExecuted") + "\n" + result);
    }

    private void updateLocalisation(){
        Edit.setText(ScenesController.getInstance().properties.getProperty("Edit"));
        helpMenu.setText(ScenesController.getInstance().properties.getProperty("Help"));
        aboutCollectionMenu.setText(ScenesController.getInstance().properties.getProperty("AboutCollection"));
        keyColumn.setText(ScenesController.getInstance().properties.getProperty("Key"));
        nameColumn.setText(ScenesController.getInstance().properties.getProperty("Name"));
        coordinateXColumn.setText(ScenesController.getInstance().properties.getProperty("CoordinatesX"));
        coordinateYColumn.setText(ScenesController.getInstance().properties.getProperty("CoordinatesY"));
        creationDateColumn.setText(ScenesController.getInstance().properties.getProperty("CreationDate"));
        areaColumn.setText(ScenesController.getInstance().properties.getProperty("Area"));
        numberOfRoomsColumn.setText(ScenesController.getInstance().properties.getProperty("NumberOfRooms"));
        furnitureColumn.setText(ScenesController.getInstance().properties.getProperty("Furniture"));
        furnishColumn.setText(ScenesController.getInstance().properties.getProperty("Furnish"));
        viewColumn.setText(ScenesController.getInstance().properties.getProperty("View"));
        nameOfHouseColumn.setText(ScenesController.getInstance().properties.getProperty("NameOfHouse"));
        yearColumn.setText(ScenesController.getInstance().properties.getProperty("Year"));
        numberOfFlatsColumn.setText(ScenesController.getInstance().properties.getProperty("NumberOfFlats"));
        clearButton.setText(ScenesController.getInstance().properties.getProperty("Clear"));
        countLessThanNumberOfRoomsButton.setText(ScenesController.getInstance().properties.getProperty("CountLessThanNumberOfRooms"));
        historyButton.setText(ScenesController.getInstance().properties.getProperty("History"));
        addElementButton.setText(ScenesController.getInstance().properties.getProperty("AddElement"));;
        showMinimalByCoordinatesButton.setText(ScenesController.getInstance().properties.getProperty("ShowMinimalByCoordinates"));
        removeAllByNumberOfRoomsButton.setText(ScenesController.getInstance().properties.getProperty("RemoveAllByNumberOfRooms"));
        removeElementButton.setText(ScenesController.getInstance().properties.getProperty("RemoveElement"));
        removeLowerKeyElementButton.setText(ScenesController.getInstance().properties.getProperty("RemoveLowerKeyElement"));
        updateElementButton.setText(ScenesController.getInstance().properties.getProperty("UpdateElement"));
        aboutUserMenu.setText(ScenesController.getInstance().properties.getProperty("UsersInfo"));
        usersLoginInfo.setText(ScenesController.getInstance().properties.getProperty("LoginInfo"));
        usersElementsInfo.setText(ScenesController.getInstance().properties.getProperty("UsersElementsInformation"));
        addFilterMenu.setText(ScenesController.getInstance().properties.getProperty("AddFilter"));
        filterMenu.setText(ScenesController.getInstance().properties.getProperty("FiltersMenu"));
        removeAllFiltersMenu.setText(ScenesController.getInstance().properties.getProperty("RemoveAllFilters"));
        removeFilterMenu.setText(ScenesController.getInstance().properties.getProperty("RemoveFilter"));
        showFiltersMenu.setText(ScenesController.getInstance().properties.getProperty("ShowFilters"));
        exitMenu.setText(ScenesController.getInstance().properties.getProperty("Exit"));
        exitFromApp.setText(ScenesController.getInstance().properties.getProperty("ExitFromApp"));
        exitFromAccount.setText(ScenesController.getInstance().properties.getProperty("ExitFromAccount"));
        isYourElementColumn.setText(ScenesController.getInstance().properties.getProperty("IsYourElement"));
        executeScriptButton.setText(ScenesController.getInstance().properties.getProperty("ExecuteScript"));
    }
}
