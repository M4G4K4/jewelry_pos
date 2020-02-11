package gui;

import BLL.BLLFornecedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import jpa.entities.Cpostal;

public class FXMLEditarFornecedoresController implements Initializable {

    private int idFor = 0;
    
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
        try {
            BLLFornecedor bLLFornecedor = new BLLFornecedor();
            
            
            
            bLLFornecedor.editFornecedor(idFor, nome.getText(), morada.getText(), telemovel.getText(), email.getText(), obs.getText(), codPostal.getText());
            
            // Fechar janela
            Stage stage = (Stage) BotaoGuardar.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        
        
        }
    }
    
    
    public void transferCampos(int id,String name,String street,String tele,String mail,String obser ,Cpostal cpostal){
        idFor = id;
        nome.setText(name);
        telemovel.setText(tele);
        email.setText(mail);
        morada.setText(street);
        localidade.setText(cpostal.getLocalidade());
        obs.setText(obser);
        codPostal.setText(cpostal.getCpostal());

    }
    
}
