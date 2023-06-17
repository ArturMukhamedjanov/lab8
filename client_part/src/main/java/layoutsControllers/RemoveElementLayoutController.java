package layoutsControllers;

import changingClasses.Arguments;
import changingClasses.ServerReply;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RemoveElementLayoutController {

    @FXML
    VBox parentBox;
    @FXML
    TextField enterKey;
    @FXML
    Label textEnterKey;
    @FXML
    Button submitButton;
    @FXML
    Button cancelButton;

    public void initialize(){
        textEnterKey.setText(ScenesController.getInstance().properties.getProperty("EnterKey"));
        submitButton.setText(ScenesController.getInstance().properties.getProperty("SubmitEntering"));
        cancelButton.setText(ScenesController.getInstance().properties.getProperty("Cancel"));
    }
    @FXML
    public void submitButtonClicked() {
        Integer key = null;
        try{
            key = Integer.parseInt(enterKey.getText());
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongKey"));
            return;
        }
        Arguments arguments = new Arguments("remove_key", key.toString(), null);
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
