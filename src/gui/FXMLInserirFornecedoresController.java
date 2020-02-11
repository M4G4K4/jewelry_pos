
package gui;

import BLL.BLLCliente;
import BLL.BLLFornecedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import jpa.entities.Cliente;
import jpa.entities.Cpostal;
import jpa.entities.Fornecedor;


public class FXMLInserirFornecedoresController implements Initializable {

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
   @FXML
    private JFXTextField nome;

    @FXML
    private JFXTextField telemovel;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField morada;

    @FXML
    private JFXTextField localidade;

    @FXML
    private JFXTextField codPostal;

    @FXML
    private JFXTextArea obs;

    @FXML
    private JFXButton BotaoGuardar;

    @FXML
    void guardar(ActionEvent event) {
        
        BLLFornecedor bLLFornecedor = new BLLFornecedor();
        try{
            if(nome.getText() != null && telemovel.getText() != null){
                Fornecedor f = new Fornecedor();
                f.setNome(nome.getText());
                f.setTelemovel(telemovel.getText());
                f.setEmail(email.getText());
                f.setMorada(morada.getText());
                f.setObs(obs.getText());
                
                Cpostal cp = new Cpostal();
                cp.setLocalidade( localidade.getText() );
                cp.setCpostal(codPostal.getText());
                
                f.setCpostal(cp);
                
                
                bLLFornecedor.insereFornecedor(f);
                
                // Fechar janela
                Stage stage = (Stage) BotaoGuardar.getScene().getWindow();
                stage.close();
            }else{
                    System.out.println("Campos necesários não introduzidos");
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Dados Invalidos");
                    errorAlert.setContentText("Os campos nome e telemovel necessitam de estar preenchidos");
                    errorAlert.showAndWait();
            }
         
         
        } catch (NumberFormatException e) {
            
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Erro ao criar Fornecedor" , e);
        
        }
        
        
    }
    
}
