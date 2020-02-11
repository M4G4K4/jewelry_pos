package gui;

import BLL.BLLFuncionario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class FXMLEditarFuncionariosController implements Initializable {

    private int idfuncionario = 0;
   
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
       
        BLLFuncionario bLLFuncionario = new BLLFuncionario();
        
        bLLFuncionario.editFuncionario(idfuncionario, username.getText(), password.getText());
        
        
        // Fechar janela
        Stage stage = (Stage) BotaoGuardar.getScene().getWindow();
        stage.close();
        
    }
    

    public void transferMessage(int i,String t1 , String t2){
        username.setText(t1);
        password.setText(t2);
        idfuncionario = i;
    }
    
}
