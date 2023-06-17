package layoutsControllers;

import changingClasses.Arguments;
import changingClasses.ServerReply;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Flat;

public class UpdateElementIdChecking {

    @FXML
    VBox parentBox;
    @FXML
    TextField enterId;
    @FXML
    Label textEnterId;
    @FXML
    Button submitButton;
    @FXML
    Button cancelButton;

    public void initialize(){
        textEnterId.setText(ScenesController.getInstance().properties.getProperty("EnterId"));
        submitButton.setText(ScenesController.getInstance().properties.getProperty("SubmitEntering"));
        cancelButton.setText(ScenesController.getInstance().properties.getProperty("Cancel"));
    }
    @FXML
    public void submitButtonClicked() {
        Long id = null;
        try{
            id = Long.parseLong(enterId.getText());
            if(ScenesController.getInstance().users_elements_id.contains(id)){
                for(Flat i: ScenesController.getInstance().cash){
                    if(i.getId() == id){
                        UpdateElementLayoutController.flat = i;
                        try {
                            FXMLLoader enter_element_loader = new FXMLLoader(getClass().getResource("/UpdateElementLayout.fxml"));
                            Parent enter_element_root = enter_element_loader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(enter_element_root));
                            stage.showAndWait();
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            showAlert(ScenesController.getInstance().properties.getProperty("WrongIdToUpdate"));
            return;
        }
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
