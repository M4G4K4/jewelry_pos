package gui;

import BLL.BLLFuncionario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import jpa.entities.Utilizador;

public class FXMLDocumentController {

    @FXML
    private Label label;

    @FXML
    private JFXTextField usernameTextfield;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton LoginBotao;

    @FXML
    void Login(ActionEvent event) {
        try {
            boolean userEncontrado = false;
            BLLFuncionario bLLFuncionario = new BLLFuncionario();
            List<Utilizador> utilizadorList = bLLFuncionario.DevolveFuncionario();
               
               
            for(Utilizador u: utilizadorList ){
                if(u.getLogin().equals(usernameTextfield.getText())){
                    if(u.getPass().equals(password.getText())){
                        userEncontrado = true;
                    }
                }
            }
               
            if(userEncontrado){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("FXMLDashboard.fxml"));

                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Painel de Controlo");
                    stage.setResizable(false);
                    stage.show();
                    usernameTextfield.clear();
                    password.clear();
                    
               }else{
                    System.out.println("Login errado");
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setHeaderText("Dados Invalidos");
                    errorAlert.setContentText("Combinação de Username e Password errados");
                    errorAlert.showAndWait();
               }
               
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Erro ao criar nova janela", e);
        }

        
    }
    
    

}
