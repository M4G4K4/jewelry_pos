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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import jpa.entities.Fornecedor;
import jpa.entities.Utilizador;

public class FXMLEditarCompraController implements Initializable {
    
    private ObservableList<Fornecedor> obsFornedeor;  
    private ObservableList<Utilizador> obsFuncionario;
    private int id=0;
    
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
        try {
            BLLCompra bLLCompra = new BLLCompra();
            
            int ano = data.getValue().getYear();
            int month = data.getValue().getMonthValue();
            int dia = data.getValue().getDayOfMonth();
       
            Date date = new Date();
            date.setMonth(month);
            date.setYear(ano);
            date.setDate(dia);
            
            float bg = new Float(valorTotal.getText());
             
            bLLCompra.editCompra(id, date, bg, funcionario.getValue(), fornecedor.getValue(), Integer.parseInt(numFatura.getText()) );
            
            // Fechar janela
            Stage stage = (Stage) BotaoGuardar.getScene().getWindow();
            stage.close();
            
        } catch (Exception e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "-- Erro ao guardar compra -- " , e); 
        }
        
    }
    
    
    
    
    public void transferCampos(int idcompra,Utilizador uti,Fornecedor forne,Date date, BigDecimal valor , int numFa){
        int dia = date.getDate();
        int month = date.getMonth() + 1;
        int year = date.getYear() + 1900;

        valorTotal.setText( valor.toString());
        data.setValue(LocalDate.of(year, month, dia));
        funcionario.setValue(uti);
        fornecedor.setValue(forne);
        numFatura.setText(Integer.toString(numFa));
        id=idcompra;
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
