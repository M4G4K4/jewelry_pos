package gui;

import BLL.BLLCliente;
import BLL.BLLFuncionario;
import BLL.BLLVenda;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import jpa.entities.Cliente;
import jpa.entities.Venda;
import org.controlsfx.control.Notifications;

public class FXMLListarVendaController implements Initializable {

    private ObservableList<Venda> obsVenda;
    private ObservableList<Cliente> obsCliente;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheTableViewVendas();
    } 
    
    
     @FXML
    private TableView<Venda> tabelaVendas;

    @FXML
    private TableColumn<Venda, Integer> id;

    @FXML
    private TableColumn<Venda, Date> data;

    @FXML
    private TableColumn<Venda, Float> valortotal;

    @FXML
    private TableColumn<Venda, Integer> cliente;
    
    @FXML
    private JFXTextField filtronome;
   
    @FXML
    private JFXButton botaoFiltro;

    @FXML
    private JFXButton botaoAdicionar;

    @FXML
    private JFXButton botaoGuardar;
    
    @FXML
    private JFXComboBox<Cliente> comboboxCliente;

    @FXML
    void adicionar(ActionEvent event) {
          try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLInserirVenda.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Inserir Venda");
                stage.setScene(scene);
                stage.setResizable(false);
                BLLVenda bllVenda = new BLLVenda();
                List<Venda> i = bllVenda.DevolveVendas();
                stage.showAndWait();
                preencheTableViewVendas();
                List<Venda> d = bllVenda.DevolveVendas();
                if(d.size() > i.size()){
                    showNotification();
                }

        } catch (IOException e) {
            
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        
        }
    }


    @FXML
    void filtro(ActionEvent event) {
        
        if(filtronome.getText().trim().isEmpty()){
            preencheTableViewVendas();
            filtronome.clear();
        }else{
            preencheComboboxCliente();
            if(!comboboxCliente.getSelectionModel().isEmpty()){
                preencheTableViewVendaCliente();
            }
        }
    }

    @FXML
    void guardar(ActionEvent event) {
          // Fechar janela
        Stage stage = (Stage) botaoGuardar.getScene().getWindow();
        stage.close();
    }

    
    public void preencheTableViewVendas(){
        
        BLLVenda bLLVenda = new BLLVenda();
        
        
        id.setCellValueFactory(new PropertyValueFactory<>("idvenda"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        valortotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        cliente.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        
        obsVenda = FXCollections.observableArrayList(bLLVenda.DevolveVendas());
        tabelaVendas.setItems(obsVenda);
    }
    
    public void preencheTableViewVendaCliente(){
        
        BLLVenda bLLVenda = new BLLVenda();
        
        
        id.setCellValueFactory(new PropertyValueFactory<>("idvenda"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        valortotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        cliente.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        
        obsVenda = FXCollections.observableArrayList(bLLVenda.DevolveVendasClientes(comboboxCliente.getValue()));
        tabelaVendas.setItems(obsVenda);
    }
    
     public void preencheComboboxCliente(){
        
        BLLCliente bLLCliente = new BLLCliente();
        
        obsCliente = FXCollections.observableArrayList(bLLCliente.DevolveCliente2(filtronome.getText()));
        
        comboboxCliente.setItems(obsCliente);
        
    }
    
    
    
    public void showNotification(){
          Image img = new Image("images/check2.png");
          Notifications notoficationBuilder = Notifications.create()
                        .title("Venda criada com sucesso")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.TOP_RIGHT);
          
          notoficationBuilder.show();
    }
    
    
    
}
