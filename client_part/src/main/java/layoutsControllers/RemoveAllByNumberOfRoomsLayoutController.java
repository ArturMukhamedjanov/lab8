package layoutsControllers;

import changingClasses.Arguments;
import changingClasses.ServerReply;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RemoveAllByNumberOfRoomsLayoutController {

    @FXML
    VBox parentBox;
    @FXML
    TextField enterNumberOfRooms;
    @FXML
    Label textEnterNumberOfRooms;
    @FXML
    Button submitButton;
    @FXML
    Button cancelButton;

    public void initialize(){
        textEnterNumberOfRooms.setText(ScenesController.getInstance().properties.getProperty("EnterNumberOfRooms"));
        submitButton.setText(ScenesController.getInstance().properties.getProperty("SubmitEntering"));
        cancelButton.setText(ScenesController.getInstance().properties.getProperty("Cancel"));
    }
    @FXML
    public void submitButtonClicked() {
        Long number_of_rooms = null;
        try{
            number_of_rooms = Long.parseLong(enterNumberOfRooms.getText());
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongNumberOfRooms"));
            return;
        }
        Arguments arguments = new Arguments("remove_all_by_number_of_rooms", number_of_rooms.toString(), null);
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
