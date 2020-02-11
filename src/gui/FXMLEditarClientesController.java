package gui;

import BLL.BLLCliente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FXMLEditarClientesController implements Initializable {

    private int id = 0;
    
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
        BLLCliente bLLCliente = new BLLCliente();
        
        //int NIF,
        //int CC,int nContribuinte,Date datanasc,String obs,String codpostal,String Morada
        bLLCliente.editCliente(id, nome.getText(), telemovel.getText(), email.getText(),
                Integer.parseInt(NIF.getText()), Integer.parseInt(ncc.getText()),
                Integer.parseInt(nContribuinte.getText()), obs.getText(), codigoPostal.getText(), morada.getText());
        
        
    }
    
    public void transferCampos(int idcliente , String no, String tele, String mail,long cc,
            long ni,long nContri, String street,
            String local,String codPostal, String obser){
       
       id = idcliente;
        
       nome.setText(no);
       telemovel.setText(tele);
       email.setText(mail);
       ncc.setText(Long.toString(cc));
       NIF.setText(Long.toString(ni));
       nContribuinte.setText(Long.toString(nContri));
       morada.setText(street);
       localidade.setText(local);
       codigoPostal.setText(codPostal);
       obs.setText(obser);
    }
    
}
