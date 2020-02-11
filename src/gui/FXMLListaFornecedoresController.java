package gui;

import BLL.BLLFornecedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import jpa.entities.Fornecedor;
import org.controlsfx.control.Notifications;

public class FXMLListaFornecedoresController implements Initializable {

    private ObservableList<Fornecedor> obsForneceores;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preenchTableViewForncedores();
    }    
    
        
    @FXML
    private TableView<Fornecedor> tabela;
      
    @FXML
    private TableColumn<Fornecedor, Integer> id;

    @FXML
    private TableColumn<Fornecedor, String> nomeT;

    @FXML
    private TableColumn<Fornecedor, String> moradaT;

    @FXML
    private TableColumn<Fornecedor, String> telemovelT;

    @FXML
    private TableColumn<Fornecedor, String> email;

   
    @FXML
    private TableColumn<Fornecedor, String> obs;

    @FXML
    private TableColumn<Fornecedor, String> cpostal;

   
    @FXML
    private JFXTextField nome;

    @FXML
    private JFXTextField morada;

    @FXML
    private JFXTextField telemovel;

    @FXML
    private JFXTextField NIF;

    @FXML
    private JFXButton botaoAdicionar;

    @FXML
    private JFXButton botaoElimina;

    @FXML
    private JFXButton botaoEdita;

    @FXML
    private JFXButton botaofiltro;

    @FXML
    private JFXButton botaoGuardar;

    @FXML
    void adicionar(ActionEvent event) {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLInserirFornecedores.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Inserir Fornecedor");
                stage.setScene(scene);
                stage.setResizable(false);
                
                BLLFornecedor bLLFornecedor = new BLLFornecedor();
                List<Fornecedor> i = bLLFornecedor.DevolveFornecedor();
                stage.showAndWait();
                preenchTableViewForncedores();
                List<Fornecedor> d = bLLFornecedor.DevolveFornecedor();
                if( d.size() > i.size()){
                    showNotification();
                }
                
        } catch (IOException e) {
            System.err.println(e);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Tem que selecionar um item da tabela para editar");
            errorAlert.showAndWait();
        }
        catch(RuntimeException ex){
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Verifique se os campos inseridos estão corretos");
            errorAlert.showAndWait();
        }
        
    }

    @FXML
    void editar(ActionEvent event) {
       try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditarFornecedores.fxml"));
            Parent root = loader.load();
            
            
            FXMLEditarFornecedoresController fornecedoresController = loader.getController();
            fornecedoresController.transferCampos(
                    tabela.getSelectionModel().getSelectedItem().getIdfornecedor(),
                    tabela.getSelectionModel().getSelectedItem().getNome(),
                    tabela.getSelectionModel().getSelectedItem().getMorada(),
                    tabela.getSelectionModel().getSelectedItem().getTelemovel(),
                    tabela.getSelectionModel().getSelectedItem().getEmail(),
                    tabela.getSelectionModel().getSelectedItem().getObs(),
                    tabela.getSelectionModel().getSelectedItem().getCpostal()
            );
            
                   
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Fornecedor");
            stage.setResizable(false);
            stage.showAndWait();
            preenchTableViewForncedores();
            tabela.refresh();
            showNotificationEdit();
        } catch (IOException ex) {
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
    void elimina(ActionEvent event) {
        try{
            BLLFornecedor bLLFornecedor = new BLLFornecedor();

            bLLFornecedor.removeFornecedor(tabela.getSelectionModel().getSelectedItem().getIdfornecedor());
            preenchTableViewForncedores();
            showNotificationDelete();
        }catch(RuntimeException ex){
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("É necessário selecionar algum item");
            errorAlert.showAndWait();
        }
    }

    @FXML
    void filtro(ActionEvent event) {
       
        if(nome.getText().trim().isEmpty() && morada.getText().trim().isEmpty() && telemovel.getText().trim().isEmpty()){
            preenchTableViewForncedores();
        }
       if(!nome.getText().trim().isEmpty()){
           preenchTableViewForncedoresNome();
           nome.clear();
       }
       if(!morada.getText().trim().isEmpty()){
           preenchTableViewForncedoresMorada();
           morada.clear();
       }
       if(!telemovel.getText().trim().isEmpty()){
           preenchTableViewForncedoresTelemovel();
           telemovel.clear();
       }
       
    }

    @FXML
    void guardar(ActionEvent event) {
         // Fechar janela
        Stage stage = (Stage) botaoGuardar.getScene().getWindow();
        stage.close();
    }

    public void preenchTableViewForncedores(){
        BLLFornecedor bLLFornecedor = new BLLFornecedor();
        
        
    id.setCellValueFactory(new PropertyValueFactory<>("idfornecedor"));
    nomeT.setCellValueFactory(new PropertyValueFactory<>("nome"));
    telemovelT.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    moradaT.setCellValueFactory(new PropertyValueFactory<>("morada"));
    obs.setCellValueFactory(new PropertyValueFactory<>("obs"));
    cpostal.setCellValueFactory(new PropertyValueFactory<>("cpostal"));
    
    obsForneceores = FXCollections.observableArrayList(bLLFornecedor.DevolveFornecedor());
    tabela.setItems(obsForneceores);
    
    
    } 
    
    public void preenchTableViewForncedoresNome(){
        BLLFornecedor bLLFornecedor = new BLLFornecedor();
        
        
    id.setCellValueFactory(new PropertyValueFactory<>("idfornecedor"));
    nomeT.setCellValueFactory(new PropertyValueFactory<>("nome"));
    telemovelT.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    moradaT.setCellValueFactory(new PropertyValueFactory<>("morada"));
    obs.setCellValueFactory(new PropertyValueFactory<>("obs"));
    cpostal.setCellValueFactory(new PropertyValueFactory<>("cpostal"));
    
    obsForneceores = FXCollections.observableArrayList(bLLFornecedor.DevolveFornecedorNome(nome.getText()));
    tabela.setItems(obsForneceores);
    
    
    } 
        
    public void preenchTableViewForncedoresMorada(){
        BLLFornecedor bLLFornecedor = new BLLFornecedor();
        
        
    id.setCellValueFactory(new PropertyValueFactory<>("idfornecedor"));
    nomeT.setCellValueFactory(new PropertyValueFactory<>("nome"));
    telemovelT.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    moradaT.setCellValueFactory(new PropertyValueFactory<>("morada"));
    obs.setCellValueFactory(new PropertyValueFactory<>("obs"));
    cpostal.setCellValueFactory(new PropertyValueFactory<>("cpostal"));
    
    obsForneceores = FXCollections.observableArrayList(bLLFornecedor.DevolveFornecedorMorada(morada.getText()));
    tabela.setItems(obsForneceores);
    
    
    } 
            
            
    public void preenchTableViewForncedoresTelemovel(){
        BLLFornecedor bLLFornecedor = new BLLFornecedor();
        
        
    id.setCellValueFactory(new PropertyValueFactory<>("idfornecedor"));
    nomeT.setCellValueFactory(new PropertyValueFactory<>("nome"));
    telemovelT.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    moradaT.setCellValueFactory(new PropertyValueFactory<>("morada"));
    obs.setCellValueFactory(new PropertyValueFactory<>("obs"));
    cpostal.setCellValueFactory(new PropertyValueFactory<>("cpostal"));
    
    obsForneceores = FXCollections.observableArrayList(bLLFornecedor.DevolveFornecedorTelemovel(telemovel.getText()));
    tabela.setItems(obsForneceores);
    
    
    } 
            
  
    public void showNotification(){
          Image img = new Image("images/check2.png");
          Notifications notoficationBuilder = Notifications.create()
                        .title("Fornecedor criado com sucesso")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.TOP_RIGHT);
          
          notoficationBuilder.show();
    }
    
    public void showNotificationEdit(){
          Image img = new Image("images/check2.png");
          Notifications notoficationBuilder = Notifications.create()
                        .title("Fornecedor editado com sucesso")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.TOP_RIGHT);
          
          notoficationBuilder.show();
    }
        
    public void showNotificationDelete(){
          Image img = new Image("images/check2.png");
          Notifications notoficationBuilder = Notifications.create()
                        .title("Fornecedor eliminado com sucesso")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.TOP_RIGHT);
          
          notoficationBuilder.show();
    }
            
      
    
}
