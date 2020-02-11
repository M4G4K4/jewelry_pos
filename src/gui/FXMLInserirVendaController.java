package gui;

import BLL.BLLCliente;
import BLL.BLLLinhaArtigo;
import BLL.BLLVenda;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jpa.entities.Artigo;
import jpa.entities.Cliente;
import jpa.entities.LinhaArtigo;
import jpa.entities.Venda;

public class FXMLInserirVendaController implements Initializable {

     private ObservableList<Cliente> obsCliente;
     private ObservableList<LinhaArtigo> obsLinhaArtigos;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Cria nova venda assim que o ecra é iniciado
        BLLVenda bLLVenda = new BLLVenda();
        Venda v = new Venda();
        BigDecimal q = new BigDecimal(BigInteger.ONE);
        v.setTotal(q);
        bLLVenda.InsereNovaVenda2(v);
        
    }    
    
    
      @FXML
    private JFXButton botaoGuardar;
      
        @FXML
    private JFXButton update;

    @FXML
    private TableView<LinhaArtigo> tabelaVenda;
    
    @FXML
    private TableColumn<Artigo,Integer> idcolum;

    @FXML
    private TableColumn<LinhaArtigo,Integer> qtd;

    @FXML
    private TableColumn<LinhaArtigo,Float> preco;
    

    @FXML
    private JFXTextField precoTotal;

    @FXML
    private JFXComboBox<Cliente> comboboxCliente;

    
     @FXML
    private JFXDatePicker comboboxData;

    @FXML
    private JFXButton botaoadicionar;
    
     @FXML
    private JFXButton btnfiltro;
     
     @FXML
    private JFXTextField searchCliente;


    
    @FXML
    void adicionar(ActionEvent event) {
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInserirProdutosVenda.fxml"));
            Parent root = loader.load();
            
                   
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Adicionar Produtos");
            stage.setResizable(false);
            stage.showAndWait();
            update(event);
            
        } catch (IOException ex) {
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("qualquer coisa");
            errorAlert.showAndWait();
        }
    }
    
    
        @FXML
    void update(ActionEvent event) {
        tabelaVenda.refresh();
        
        // Buscar as linhas artigos com o id venda que acabamos de criar 
        // e preencher a tabela 
        BLLVenda bLLVenda = new BLLVenda();
        List<Venda> listaVenda = bLLVenda.DevolveVendaTopID();
        int id = listaVenda.get(0).getIdvenda();
        
        BLLLinhaArtigo bLLLinhaArtigo = new BLLLinhaArtigo();
        List<LinhaArtigo> listaLinhaArtigos = bLLLinhaArtigo.DevolveLinhaArtigoID(id);
        
        
        idcolum.setCellValueFactory(new PropertyValueFactory<>("Artigo"));
        qtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        preco.setCellValueFactory(new PropertyValueFactory<>("valor"));
        
       
       obsLinhaArtigos = FXCollections.observableArrayList(bLLLinhaArtigo.DevolveLinhaArtigoID(id));
       tabelaVenda.setItems(obsLinhaArtigos);
        

        // Buscar as linhas artigos com o id venda que acabamos de criar
        // e somar o preco final e colocar no campo preco total 
        BigDecimal bgprecoTotal = new BigDecimal(0);
        for(int i=0; i<listaLinhaArtigos.size();i++){
            bgprecoTotal = bgprecoTotal.add(listaLinhaArtigos.get(i).getValor());
        }
        precoTotal.setText(bgprecoTotal.toString());
    }

   

    @FXML
    void guardar(ActionEvent event) {
        try{
        // buscar venda com top id e acrescentar o preco total , cliente e data
        BLLVenda bLLVenda = new BLLVenda();
        List<Venda> listaVenda = bLLVenda.DevolveVendaTopID();
        int i = listaVenda.get(0).getIdvenda();
        
       int ano = comboboxData.getValue().getYear() - 1900;
       int month = comboboxData.getValue().getMonthValue() - 1;
       int dia = comboboxData.getValue().getDayOfMonth();
       
       
       Date date = new Date();
       date.setMonth(month);
       date.setYear(ano);
       date.setDate(dia);
        
        BigDecimal bgtotal = new BigDecimal(precoTotal.getText());
        
        
        bLLVenda.editVenda(i,comboboxCliente.getValue(),bgtotal,date);
        
        // Fechar janela
        Stage stage = (Stage) botaoGuardar.getScene().getWindow();
        stage.close();
        }catch(RuntimeException ex){
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Todos os Campos necessitam de ser preenchidos");
            errorAlert.showAndWait();
        }
    }
    
    
     public void preencheComboboxCliente(){
        
        BLLCliente bLLCliente = new BLLCliente();
        
        obsCliente = FXCollections.observableArrayList(bLLCliente.DevolveCliente());
        
        comboboxCliente.setItems(obsCliente);
        
    }
     
     
      @FXML
    void filtro(ActionEvent event) {
        BLLCliente bLLCliente = new BLLCliente();
        
        obsCliente = FXCollections.observableArrayList(bLLCliente.DevolveCliente2(searchCliente.getText()));
        
        comboboxCliente.setItems(obsCliente);
    }
    
    
    
}
