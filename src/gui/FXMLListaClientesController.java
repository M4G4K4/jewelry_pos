package gui;

import BLL.BLLCliente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.persistence.RollbackException;
import jpa.entities.Cliente;
import org.controlsfx.control.Notifications;

public class FXMLListaClientesController implements Initializable {

    private ObservableList<Cliente> obsCliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Preencher tabela com todos os clientes 
        preencheTableViewClientes();
        
    }  
    
    // -----------------------------------------------
    
       @FXML
    private TableView<Cliente> tabelaClientes;
       
    @FXML
    private TableColumn<Cliente, Integer> id;

    @FXML
    private TableColumn<Cliente, String> nomeT;

    @FXML
    private TableColumn<Cliente, String> telemovelT;

    @FXML
    private TableColumn<Cliente, Integer> nif;

    @FXML
    private TableColumn<Cliente, Integer> cc;

    @FXML
    private TableColumn<Cliente, Integer> nContribuinte;

    @FXML
    private TableColumn<Cliente, String> obs;

    @FXML
    private TableColumn<Cliente, String> email;  
    
    @FXML
    private TableColumn<Cliente, String> codpostal;
     
    @FXML
    private TableColumn<Cliente, String> moradaT;
     
       

    @FXML
    private JFXTextField nome;

    @FXML
    private JFXTextField morada;

    @FXML
    private JFXTextField telemovel;

    @FXML
    private JFXTextField NIF;

    @FXML
    private JFXButton BotaoAdicionar;

    @FXML
    private JFXButton BotaoEliminar;

    @FXML
    private JFXButton BotaoEditar;

    @FXML
    private JFXButton BotaoFiltro;

    @FXML
    private JFXButton botaoGuardar;

    @FXML
    void Filtro(ActionEvent event) {
        if( nome.getText().trim().isEmpty() && morada.getText().trim().isEmpty() 
                && telemovel.getText().trim().isEmpty() && NIF.getText().trim().isEmpty() ){
            preencheTableViewClientes();
        }
        
        if( !nome.getText().trim().isEmpty() ){
            preencheTableViewNome();
            nome.clear();
        }
        if( !morada.getText().trim().isEmpty() ){
            preencheTableViewMorada();
            morada.clear();
        }
        if( !telemovel.getText().trim().isEmpty() ){
           preencheTableViewTelemovel();
           telemovel.clear();
        }
        if( !NIF.getText().trim().isEmpty() ){
            preencheTableViewNIF();
            NIF.clear();
        }
        
        
    }

    @FXML
    void adicionar(ActionEvent event) {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLInserirClientes.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Inserir Cliente");
                stage.setScene(scene);
                stage.setResizable(false);
                BLLCliente bLLCliente = new BLLCliente();
                List<Cliente> i = bLLCliente.DevolveCliente();
                stage.showAndWait();
                preencheTableViewClientes();
                List<Cliente> d = bLLCliente.DevolveCliente();
                if(d.size() > i.size()){
                    showNotification();
                }

        } catch (IOException e) {
            
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        
        }
    }

    @FXML
    void editar(ActionEvent event) {
         try {
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditarClientes.fxml"));
            Parent root = loader.load();
            
            FXMLEditarClientesController clientesController = loader.getController();
            clientesController.transferCampos(
                    tabelaClientes.getSelectionModel().getSelectedItem().getIdcliente(),
                    tabelaClientes.getSelectionModel().getSelectedItem().getNome(),
                    tabelaClientes.getSelectionModel().getSelectedItem().getTelemovel(),
                    tabelaClientes.getSelectionModel().getSelectedItem().getEmail(),
                    tabelaClientes.getSelectionModel().getSelectedItem().getCc(),
                    tabelaClientes.getSelectionModel().getSelectedItem().getNif(),
                    tabelaClientes.getSelectionModel().getSelectedItem().getNcontribuinte(),
                    tabelaClientes.getSelectionModel().getSelectedItem().getMorada(),
                    tabelaClientes.getSelectionModel().getSelectedItem().getCpostal().getLocalidade(),
                    tabelaClientes.getSelectionModel().getSelectedItem().getCpostal().getCpostal(),
                    tabelaClientes.getSelectionModel().getSelectedItem().getObs()
            );
            
            
                   
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Fornecedor");
            stage.setResizable(false);
            stage.showAndWait();
            tabelaClientes.refresh();
            
            
        } catch (IOException ex) {
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Tem que selecionar algum item");
            errorAlert.showAndWait();
        }
        catch(RuntimeException ex){
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Erro ao editar cliente");
            errorAlert.showAndWait();
        }
       
           
    }

    @FXML
    void eliminar(ActionEvent event) {
        try {
        
            BLLCliente bLLCliente = new BLLCliente();
            bLLCliente.removeCliente(tabelaClientes.getSelectionModel().getSelectedItem().getIdcliente());
            showNotificationDelete();
 
        }catch(RollbackException ex){
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("É necessário selecionar algum item");
            errorAlert.showAndWait();
        }catch(RuntimeException ex){
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("É necessário selecionar algum item");
            errorAlert.showAndWait();
        }
    }

    
    @FXML
    void guardar(ActionEvent event) {
           // Fechar janela
            Stage stage = (Stage) botaoGuardar.getScene().getWindow();
            stage.close();
    }
    
    
    public void preencheTableViewClientes(){
        
        BLLCliente bLLCliente = new BLLCliente();
        
        id.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        nomeT.setCellValueFactory(new PropertyValueFactory<>("nome"));
        telemovelT.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
        nif.setCellValueFactory(new PropertyValueFactory<>("nif"));
        cc.setCellValueFactory(new PropertyValueFactory<>("cc"));
        nContribuinte.setCellValueFactory(new PropertyValueFactory<>("ncontribuinte"));
        obs.setCellValueFactory(new PropertyValueFactory<>("obs"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        moradaT.setCellValueFactory(new PropertyValueFactory<>("morada"));
        codpostal.setCellValueFactory(new PropertyValueFactory<>("cpostal"));
        
        
        obsCliente = FXCollections.observableArrayList(bLLCliente.DevolveCliente());
        tabelaClientes.setItems(obsCliente);
    }
    
    public void preencheTableViewNome(){
        
        BLLCliente bLLCliente = new BLLCliente();
        
        id.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        nomeT.setCellValueFactory(new PropertyValueFactory<>("nome"));
        telemovelT.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
        nif.setCellValueFactory(new PropertyValueFactory<>("nif"));
        cc.setCellValueFactory(new PropertyValueFactory<>("cc"));
        nContribuinte.setCellValueFactory(new PropertyValueFactory<>("ncontribuinte"));
        obs.setCellValueFactory(new PropertyValueFactory<>("obs"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        moradaT.setCellValueFactory(new PropertyValueFactory<>("morada"));
        codpostal.setCellValueFactory(new PropertyValueFactory<>("cpostal"));
        
        
        obsCliente = FXCollections.observableArrayList(bLLCliente.DevolveCliente2(nome.getText()));
        tabelaClientes.setItems(obsCliente);
    }
    
    public void preencheTableViewMorada(){
        
        BLLCliente bLLCliente = new BLLCliente();
        
        id.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        nomeT.setCellValueFactory(new PropertyValueFactory<>("nome"));
        telemovelT.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
        nif.setCellValueFactory(new PropertyValueFactory<>("nif"));
        cc.setCellValueFactory(new PropertyValueFactory<>("cc"));
        nContribuinte.setCellValueFactory(new PropertyValueFactory<>("ncontribuinte"));
        obs.setCellValueFactory(new PropertyValueFactory<>("obs"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        moradaT.setCellValueFactory(new PropertyValueFactory<>("morada"));
        codpostal.setCellValueFactory(new PropertyValueFactory<>("cpostal"));
        
        
        obsCliente = FXCollections.observableArrayList(bLLCliente.DevolveClienteMorada(morada.getText()));
        tabelaClientes.setItems(obsCliente);
    }
    
    
    public void preencheTableViewTelemovel(){
        
        BLLCliente bLLCliente = new BLLCliente();
        
        id.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        nomeT.setCellValueFactory(new PropertyValueFactory<>("nome"));
        telemovelT.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
        nif.setCellValueFactory(new PropertyValueFactory<>("nif"));
        cc.setCellValueFactory(new PropertyValueFactory<>("cc"));
        nContribuinte.setCellValueFactory(new PropertyValueFactory<>("ncontribuinte"));
        obs.setCellValueFactory(new PropertyValueFactory<>("obs"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        moradaT.setCellValueFactory(new PropertyValueFactory<>("morada"));
        codpostal.setCellValueFactory(new PropertyValueFactory<>("cpostal"));
        
        
        obsCliente = FXCollections.observableArrayList(bLLCliente.DevolveClienteTelemovel(telemovel.getText()));
        tabelaClientes.setItems(obsCliente);
    }
 
    
     public void preencheTableViewNIF(){
        
        BLLCliente bLLCliente = new BLLCliente();
        
        id.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        nomeT.setCellValueFactory(new PropertyValueFactory<>("nome"));
        telemovelT.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
        nif.setCellValueFactory(new PropertyValueFactory<>("nif"));
        cc.setCellValueFactory(new PropertyValueFactory<>("cc"));
        nContribuinte.setCellValueFactory(new PropertyValueFactory<>("ncontribuinte"));
        obs.setCellValueFactory(new PropertyValueFactory<>("obs"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        moradaT.setCellValueFactory(new PropertyValueFactory<>("morada"));
        codpostal.setCellValueFactory(new PropertyValueFactory<>("cpostal"));
        
        BigDecimal bgnif = new BigDecimal(NIF.getText());
        
        obsCliente = FXCollections.observableArrayList(bLLCliente.DevolveClienteNif(bgnif) );
        tabelaClientes.setItems(obsCliente);
    }
    
    
    public void showNotification(){
          Image img = new Image("images/check2.png");
          Notifications notoficationBuilder = Notifications.create()
                        .title("Cliente criado com sucesso")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.TOP_RIGHT);
          
          notoficationBuilder.show();
    }
    
    public void showNotificationDelete(){
          Image img = new Image("images/check2.png");
          Notifications notoficationBuilder = Notifications.create()
                        .title("Cliente eliminado com sucesso")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.TOP_RIGHT);
          
          notoficationBuilder.show();
    }
    
    public void showNotificationDeleteVenda(){
          Image img = new Image("images/remove2.png");
          Notifications notoficationBuilder = Notifications.create()
                        .title("Não é possivel eliminar cliente")
                        .text("Cliente está associada a uma venda , não é possivel eliminar")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(6))
                        .position(Pos.TOP_RIGHT);
          
          notoficationBuilder.show();
    }
    
    
    
    
    
}
