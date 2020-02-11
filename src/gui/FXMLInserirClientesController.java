
package gui;

import BLL.BLLCliente;
import BLL.BLLCodigoPostal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.persistence.RollbackException;
import jpa.entities.Cliente;
import jpa.entities.Cpostal;

public class FXMLInserirClientesController implements Initializable {

  
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
    private JFXTextField ncc;

    @FXML
    private JFXTextField NIF;
    
    @FXML
    private JFXTextField nContribuinte;

    @FXML
    private JFXTextField morada;

    @FXML
    private JFXTextField localidade;

    @FXML
    private JFXTextField codigoPostal;

    @FXML
    private JFXTextArea obs;

    @FXML
    private JFXButton BotaoGuardar;

    @FXML
    void guardar(ActionEvent event) {
        BLLCliente bllcliente = new BLLCliente();
        try{
        
            if(!nome.getText().trim().isEmpty() && !ncc.getText().trim().isEmpty() && 
                    !NIF.getText().trim().isEmpty() && !nContribuinte.getText().trim().isEmpty()){
                Cliente c = new Cliente();
                c.setNome(nome.getText());
                c.setTelemovel(telemovel.getText());
                c.setEmail(email.getText());
                c.setCc(Long.parseLong(ncc.getText()));
                c.setNif(Long.parseLong(NIF.getText()));
                c.setNcontribuinte(Long.parseLong(nContribuinte.getText()));
                c.setMorada(morada.getText());
                c.setObs(obs.getText());

                
                
                Cpostal cp = new Cpostal();
                cp.setLocalidade(localidade.getText());
                cp.setCpostal(codigoPostal.getText());
                
                BLLCodigoPostal bLLCodigoPostal = new BLLCodigoPostal();
                List<Cpostal> listaCpostal = bLLCodigoPostal.DevolveCpostal(codigoPostal.getText());
                if(listaCpostal.size() <= 0){
                    bLLCodigoPostal.insereCPostal(cp);
                }
                
                
                
                c.setCpostal(cp);

                bllcliente.insereCliente(c);
                
                // Fechar janela
                Stage stage = (Stage) BotaoGuardar.getScene().getWindow();
                stage.close();
            }else{
                    System.out.println("Campos necesários não introduzidos");
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Dados Invalidos");
                    errorAlert.setContentText("Os campos nome , Numero CC , NIF e Numero Contribuinte necessitam de estar preenchidos");
                    errorAlert.showAndWait();
            }
         
         
        } catch (NumberFormatException e) {
            System.out.println("Campos necesários não introduzidos");
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Dados Invalidos");
            errorAlert.setContentText("Os campos nome , Numero CC , NIF e Numero Contribuinte necessitam de estar preenchidos");
            errorAlert.showAndWait();
        
        }catch(RollbackException ex){
            System.out.println("Campos unícos já introduzidos");
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Dados Invalidos");
            errorAlert.setContentText("Os campos NIF , Numero CC e Numero de contribuinte já se encontram atribuidos a outro cliente");
            errorAlert.showAndWait();
        }
        

      
    }
    
    
}
