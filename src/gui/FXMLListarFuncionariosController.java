package gui;

import BLL.BLLFuncionario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import jpa.entities.Utilizador;
import org.controlsfx.control.Notifications;

public class FXMLListarFuncionariosController implements Initializable {
    
    private ObservableList<Utilizador> obsUtilizador;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        preencheTableViewFuncionarios();
                
    }    
    
     @FXML
    private TableView<Utilizador> tabela;
     
    @FXML
    private TableColumn<Utilizador, Integer> idfuncionario;

    @FXML
    private TableColumn<Utilizador, String> login;

     

    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton botaoFiltro;

    @FXML
    private JFXButton botaoAdicionar;

    @FXML
    private JFXButton botaoEliminar;

    @FXML
    private JFXButton botaoEditar;

    @FXML
    private JFXButton botaoGuardar;

    @FXML
    void adicionar(ActionEvent event) {
         try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLInserirFuncionarios.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Inserir Funcionarios");
                stage.setScene(scene);
                stage.setResizable(false);
                
                BLLFuncionario bLLFuncionario = new BLLFuncionario();
                List<Utilizador> listai = bLLFuncionario.DevolveFuncionario();
                int i = listai.size();
                stage.showAndWait();
                preencheTableViewFuncionarios();
                List<Utilizador> listad = bLLFuncionario.DevolveFuncionario();
                int d = listad.size();
                if (d > i){
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
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditarFuncionarios.fxml"));
            Parent root = loader.load();
            
            FXMLEditarFuncionariosController funcionariosController = loader.getController();
            funcionariosController.transferMessage(tabela.getSelectionModel().getSelectedItem().getIdutilizador(),
                                                   tabela.getSelectionModel().getSelectedItem().getLogin(),
                                                   tabela.getSelectionModel().getSelectedItem().getPass());
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Funcionario");
            stage.setResizable(false);
            stage.showAndWait();
            preencheTableViewFuncionarios();
            showNotificationEdit();
        } catch (IOException | RuntimeException ex) {
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("É necessário slecionar algum item");
            errorAlert.showAndWait();
        }
       

    }

    @FXML
    void eliminar(ActionEvent event) {
        try {
            BLLFuncionario bLLFuncionario = new BLLFuncionario();
       
        bLLFuncionario.removefuncionarioID(tabela.getSelectionModel().getSelectedItem().getIdutilizador());
        preencheTableViewFuncionarios();
        showNotificationDelete();
        } catch (Exception e) {
            System.err.println(e);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("É necessário slecionar algum item");
            errorAlert.showAndWait();
        }
        
    }

    @FXML
    void filtro(ActionEvent event) {
        if(username.getText().trim().isEmpty()){
            preencheTableViewFuncionarios();
        }
        else{
            preencheTableViewFuncionarioslogin();
            username.clear();
        }
    }

    
    @FXML
    void guardar(ActionEvent event) {
          // Fechar janela
        Stage stage = (Stage) botaoGuardar.getScene().getWindow();
        stage.close();
    }
    
    public void preencheTableViewFuncionarios(){
        tabela.refresh();
        BLLFuncionario bLLFuncionario = new BLLFuncionario();
        
        idfuncionario.setCellValueFactory(new PropertyValueFactory<>("idutilizador"));
        login.setCellValueFactory(new PropertyValueFactory<>("login") );
        
        obsUtilizador = FXCollections.observableArrayList(bLLFuncionario.DevolveFuncionarioLessAdmin());
        tabela.setItems(obsUtilizador);
    }
    
     public void preencheTableViewFuncionarioslogin(){
        tabela.refresh();
        BLLFuncionario bLLFuncionario = new BLLFuncionario();
        
        idfuncionario.setCellValueFactory(new PropertyValueFactory<>("idutilizador"));
        login.setCellValueFactory(new PropertyValueFactory<>("login") );
        
        obsUtilizador = FXCollections.observableArrayList(bLLFuncionario.DevolveFuncionarioNome(username.getText()));
        tabela.setItems(obsUtilizador);
    }
    
    public void showNotification(){
        Image img = new Image("images/check2.png");
        Notifications notoficationBuilder = Notifications.create()
            .title("Funcionario criado com sucesso")
            .graphic(new ImageView(img))
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);

            notoficationBuilder.show();
    }
    public void showNotificationEdit(){
        Image img = new Image("images/check2.png");
        Notifications notoficationBuilder = Notifications.create()
            .title("Funcionario editado com sucesso")
            .graphic(new ImageView(img))
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);

            notoficationBuilder.show();
    }
    
    public void showNotificationDelete(){
        Image img = new Image("images/check2.png");
        Notifications notoficationBuilder = Notifications.create()
            .title("Funcionario eliminado com sucesso")
            .graphic(new ImageView(img))
            .hideAfter(Duration.seconds(4))
            .position(Pos.TOP_RIGHT);

            notoficationBuilder.show();
    }
    
    
}
