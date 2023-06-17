package layoutsControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RemoveFilterByIdController {


    @FXML
    VBox parentBox;
    @FXML
    TextField enterFilterId;
    @FXML
    Label textEnterFilterId;
    @FXML
    Button submitButton;
    @FXML
    Button cancelButton;

    public void initialize(){
        textEnterFilterId.setText(ScenesController.getInstance().properties.getProperty("EnterFilterId"));
        submitButton.setText(ScenesController.getInstance().properties.getProperty("SubmitEntering"));
        cancelButton.setText(ScenesController.getInstance().properties.getProperty("Cancel"));
    }
    @FXML
    public void submitButtonClicked() {
        Integer id= null;
        try{
            id = Integer.parseInt(enterFilterId.getText());
            if(id<0 || id >= ScenesController.getInstance().filters.size()){
                throw new Exception();
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongFilterId"));
            return;
        }
        ScenesController.getInstance().filters.remove((int)id);
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
