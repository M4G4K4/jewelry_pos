package gui;

import BLL.BLLFuncionario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import jpa.entities.Utilizador;
import org.controlsfx.control.Notifications;

public class FXMLInserirFuncionariosController implements Initializable {

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton BotaoGuardar;

    @FXML
    void guardar(ActionEvent event) {
        try{
            if( !username.getText().isEmpty() || !password.getText().isEmpty() ){
                BLLFuncionario bLLFuncionario = new BLLFuncionario();
                Utilizador u = new Utilizador();
                u.setLogin(username.getText());
                u.setPass(password.getText());
                
                bLLFuncionario.insereFuncionario(u);
                
                // Fechar janela
                Stage stage = (Stage) BotaoGuardar.getScene().getWindow();
                stage.close();
            }else{
                    System.out.println("Campos necesários não introduzidos");
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Dados Invalidos");
                    errorAlert.setContentText("Os campos Nome Utilizador e Palavra-Passe necessitam de estar preenchidos");
                    errorAlert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Erro ao criar Utilizador" , e);
        }
        
        
    }
    
    
    
    
}
