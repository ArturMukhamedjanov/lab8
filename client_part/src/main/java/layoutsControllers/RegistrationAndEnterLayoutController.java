package layoutsControllers;

import changingClasses.Arguments;
import changingClasses.ServerReply;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileInputStream;


public class RegistrationAndEnterLayoutController {

    @FXML
    public TextField EnterLogin;
    @FXML
    public TextField EnterPassword;
    @FXML
    Label LoginText;
    @FXML
    Label PasswordText;
    @FXML
    Button SubmitRegistration;
    @FXML
    Button toEnter;

    @FXML
    Button SubmitEntering;
    @FXML
    Button Registrate;

    @FXML
    public void register_new_user(){
        String login = EnterLogin.getText();
        String password = EnterPassword.getText();
        if(login.equals("") || password.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText(ScenesController.getInstance().properties.getProperty("EmptyLoginOrPasswordException"));
            alert.showAndWait();
            return;
        }
        Arguments arguments = new Arguments("check_login", login, null);
        ScenesController.getInstance().sendCommandToServer(arguments);
        ServerReply about_login = ScenesController.getInstance().getServerReply();
        if(about_login.massages.get(0).equals("unknown_login")){
            arguments = new Arguments("register_new_user", password, null);
            ScenesController.getInstance().sendCommandToServer(arguments);
            ScenesController.getInstance().getServerReply();
            ScenesController.getInstance().login = login;
            ScenesController.getInstance().setShowScene();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText(ScenesController.getInstance().properties.getProperty("WrongLoginException"));
            alert.showAndWait();
        }
    }

    private void updateLocalisation(){
        if(LoginText != null) {
            LoginText.setText(ScenesController.getInstance().properties.getProperty("EnterLogin"));
        }
        if(PasswordText != null) {
            PasswordText.setText(ScenesController.getInstance().properties.getProperty("EnterPassword"));
        }
        if(SubmitRegistration != null) {
            SubmitRegistration.setText(ScenesController.getInstance().properties.getProperty("SubmitRegistration"));
        }
        if (toEnter != null) {
            toEnter.setText(ScenesController.getInstance().properties.getProperty("GoToEnterLayout"));
        }
        if(SubmitEntering != null) {
            SubmitEntering.setText(ScenesController.getInstance().properties.getProperty("SubmitEntering"));
        }
        if(Registrate != null) {
            Registrate.setText(ScenesController.getInstance().properties.getProperty("Registrate"));
        }
    }
    @FXML
    public void changeToEnter(){
        ScenesController.getInstance().setEnterScene();
        updateLocalisation();
    }

    @FXML
    public void changeToRegistration(){
        ScenesController.getInstance().setRegistrationScene();
        updateLocalisation();
    }

    @FXML
    public void enterLoginAndPassword(){
        String login = EnterLogin.getText();
        String password = EnterPassword.getText();
        if(login.equals("") || password.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText(ScenesController.getInstance().properties.getProperty("EmptyLoginOrPasswordException"));
            alert.showAndWait();
            return;
        }
        Arguments arguments = new Arguments("check_login", login, null);
        ScenesController.getInstance().sendCommandToServer(arguments);
        ServerReply about_login = ScenesController.getInstance().getServerReply();
        if(about_login.massages.get(0).equals("enter_password")){
            arguments = new Arguments("check_password", password, null);
            ScenesController.getInstance().sendCommandToServer(arguments);
            ServerReply about_password = ScenesController.getInstance().getServerReply();
            if(about_password.massages.get(0).equals("correct")){
                ScenesController.getInstance().login = login;
                ScenesController.getInstance().setShowScene();
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(ScenesController.getInstance().properties.getProperty("IncorrectLoginOrPassword"));
        alert.showAndWait();
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
}
