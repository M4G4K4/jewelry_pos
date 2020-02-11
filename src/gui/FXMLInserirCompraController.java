
package gui;

import BLL.BLLCompra;
import BLL.BLLFornecedor;
import BLL.BLLFuncionario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import jpa.entities.Compra;
import jpa.entities.Fornecedor;
import jpa.entities.Utilizador;



public class FXMLInserirCompraController implements Initializable {

    private ObservableList<Fornecedor> obsFornedeor;  
    private ObservableList<Utilizador> obsFuncionario;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        preencheComboboxFornecedor();
        preencheComboboxFuncionario();
        
    }    
    
    
    @FXML
    private JFXDatePicker data;

    @FXML
    private JFXTextField valorTotal;

    @FXML
    private JFXComboBox<Utilizador> funcionario;

    @FXML
    private JFXComboBox<Fornecedor> fornecedor;

    @FXML
    private JFXTextField numFatura;

    @FXML
    private JFXButton BotaoGuardar;

    @FXML
    void guardar(ActionEvent event) {
       BLLCompra bLLCompra = new BLLCompra();
        
       int ano = data.getValue().getYear() - 1900;
       int month = data.getValue().getMonthValue() - 1;
       int dia = data.getValue().getDayOfMonth();
       
       BigDecimal bg = new BigDecimal(valorTotal.getText());
       
       Date date = new Date();
       date.setMonth(month);
       date.setYear(ano);
       date.setDate(dia);
       
        try {
            if(valorTotal.getText() != null && numFatura.getText() != null){
                Compra c = new Compra();
                
                c.setData(date);
                c.setValortotal(bg);
                
                
                c.setIdfuncionario(  funcionario.getValue() );
                c.setIdfornecedor( fornecedor.getValue() );
                c.setNfatura( Integer.parseInt(numFatura.getText()) );

                bLLCompra.insereCompra(c);
                
                // Fechar janela
                Stage stage = (Stage) BotaoGuardar.getScene().getWindow();
                stage.close();
            }else{
                System.out.println("Campos necesários não introduzidos");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Dados Invalidos");
                errorAlert.setContentText("Todos os campos devem ser introduzidos");
                errorAlert.showAndWait();
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Erro ao criar Cliente" , e);
        }
    }
    
    
    public void preencheComboboxFornecedor(){
        
        BLLFornecedor bLLFornecedor = new BLLFornecedor();
        
        obsFornedeor = FXCollections.observableArrayList(bLLFornecedor.DevolveFornecedor());
        
        fornecedor.setItems(obsFornedeor);
        
    }
    
    public void preencheComboboxFuncionario(){
        
        BLLFuncionario bLLFuncionario = new BLLFuncionario();
        
        obsFuncionario = FXCollections.observableArrayList(bLLFuncionario.DevolveFuncionario());
        
        funcionario.setItems(obsFuncionario);
        
    }
    
}
