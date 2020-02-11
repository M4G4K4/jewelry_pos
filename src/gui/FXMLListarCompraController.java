
package gui;

import BLL.BLLCompra;
import BLL.BLLFornecedor;
import BLL.BLLFuncionario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import jpa.entities.Compra;
import jpa.entities.Fornecedor;
import jpa.entities.Utilizador;
import org.controlsfx.control.Notifications;


public class FXMLListarCompraController implements Initializable {
    
    private ObservableList<Compra> obsCompra;
    private ObservableList<Compra> obsCompra2;

    private ObservableList<Fornecedor> obsFornedeor;  
    private ObservableList<Utilizador> obsFuncionario;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        preencheTableViewCompra();
        preencheComboboxFornecedor();
        preencheComboboxFuncionario();
        
    }    
    
    
    @FXML
    private TableView<Compra> tabela;
    
    @FXML
    private TableColumn<Compra , Integer> IDcompra;

    @FXML
    private TableColumn<Compra, Integer> idfuncionario;

    @FXML
    private TableColumn<Compra, Integer> idfornecedor;

    @FXML
    private TableColumn<Compra, Date> data;

    @FXML
    private TableColumn<Compra, Long> valorTotal;

    @FXML
    private TableColumn<Compra, Long> numFatura;
    

    
    
    
    @FXML
    private JFXDatePicker dataInicio;

    @FXML
    private JFXComboBox<Utilizador> funcionario;

    @FXML
    private JFXComboBox<Fornecedor> fornecedores;

    @FXML
    private JFXDatePicker dataFim;

    @FXML
    private JFXButton botaofiltro;

    @FXML
    private JFXButton botaoadicionar;

    @FXML
    private JFXButton botaoElimina;

    @FXML
    private JFXButton botaoEditar;

    @FXML
    private JFXButton botaoGuardar;

    @FXML
    void adicionar(ActionEvent event) {
           try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLInserirCompra.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Inserir Compra");
                stage.setScene(scene);
                stage.setResizable(false);
                BLLCompra bLLCompra = new BLLCompra();
                List<Compra> i = bLLCompra.DevolveCompra();
                stage.showAndWait();
                preencheTableViewCompra();
                List<Compra> d = bLLCompra.DevolveCompra();
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
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditarCompra.fxml"));
            Parent root = loader.load();
            
            FXMLEditarCompraController compraController = loader.getController();
            compraController.transferCampos(
                tabela.getSelectionModel().getSelectedItem().getIdcompra(),
                tabela.getSelectionModel().getSelectedItem().getIdfuncionario(),
                tabela.getSelectionModel().getSelectedItem().getIdfornecedor(),
                tabela.getSelectionModel().getSelectedItem().getData(),
                tabela.getSelectionModel().getSelectedItem().getValortotal(),
                tabela.getSelectionModel().getSelectedItem().getNfatura()
            );
            
            
                   
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Compra");
            stage.setResizable(false);
            stage.showAndWait();
            tabela.refresh();
            
        } catch (IOException ex) {
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Tem que selecionar algum item");
            errorAlert.showAndWait();
        }

    }

   

    @FXML
    void filtro(ActionEvent event) {

       if(funcionario.getSelectionModel().isEmpty() && fornecedores.getSelectionModel().isEmpty()){
           preencheTableViewCompra();
       }
       if(!funcionario.getSelectionModel().isEmpty()){
           preencheTabelaFuncionario();
           funcionario.getSelectionModel().clearSelection();
       }
       if(!fornecedores.getSelectionModel().isEmpty()){
           preencheTabelaFornecedor();
           fornecedores.getSelectionModel().clearSelection();
       }
       
        
    }

    @FXML
    void guardar(ActionEvent event) {
         // Fechar janela
        Stage stage = (Stage) botaoGuardar.getScene().getWindow();
        stage.close();
    }
    
    
    public void preencheTableViewCompra(){
     
        BLLCompra bllcompra = new BLLCompra();
        
        IDcompra.setCellValueFactory(new PropertyValueFactory<>("idcompra"));
        idfuncionario.setCellValueFactory(new PropertyValueFactory<>("idfuncionario"));
        idfornecedor.setCellValueFactory(new PropertyValueFactory<>("idfornecedor"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        valorTotal.setCellValueFactory(new PropertyValueFactory<>("valortotal"));
        numFatura.setCellValueFactory(new PropertyValueFactory<>("nfatura"));
        
        
        obsCompra = FXCollections.observableArrayList(bllcompra.DevolveCompra() );
        tabela.setItems(obsCompra);
       
    }
    
    
    public void showNotification(){
          Image img = new Image("images/check2.png");
          Notifications notoficationBuilder = Notifications.create()
                        .title("Compra criada com sucesso")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.TOP_RIGHT);
          
          notoficationBuilder.show();
    }
        
        
        
    public void preencheComboboxFornecedor(){
        
        BLLFornecedor bLLFornecedor = new BLLFornecedor();
        
        obsFornedeor = FXCollections.observableArrayList(bLLFornecedor.DevolveFornecedor());
        
        fornecedores.setItems(obsFornedeor);
        
    }
    
    public void preencheComboboxFuncionario(){
        
        BLLFuncionario bLLFuncionario = new BLLFuncionario();
        
        obsFuncionario = FXCollections.observableArrayList(bLLFuncionario.DevolveFuncionario());
        
        funcionario.setItems(obsFuncionario);
        
    }
    
   
     public void preencheTabelaDatas(){
         try{
            BLLCompra bllcompra = new BLLCompra();

            IDcompra.setCellValueFactory(new PropertyValueFactory<>("idcompra"));
            idfuncionario.setCellValueFactory(new PropertyValueFactory<>("idfuncionario"));
            idfornecedor.setCellValueFactory(new PropertyValueFactory<>("idfornecedor"));
            data.setCellValueFactory(new PropertyValueFactory<>("data"));
            valorTotal.setCellValueFactory(new PropertyValueFactory<>("valortotal"));
            numFatura.setCellValueFactory(new PropertyValueFactory<>("nfatura"));

            // Inicio
           int anoi = dataInicio.getValue().getYear();
           int monthi = dataInicio.getValue().getMonthValue();
           int diai = dataInicio.getValue().getDayOfMonth();
//           System.out.println("anoi: " + anoi);
//           System.out.println("motnhi: " + monthi);
//           System.out.println("diai: " + diai);

           
           Date dateI = new Date();
           dateI.setMonth(monthi);
           dateI.setYear(anoi);
           dateI.setDate(diai);

           // Fim 
           int anof = dataFim.getValue().getYear();
           int monthf = dataFim.getValue().getMonthValue();
           int diaf = dataFim.getValue().getDayOfMonth();
//           System.out.println("anof: " + anof);
//           System.out.println("motnhf: " + monthf);
//           System.out.println("diaf: " + diaf);



           Date datef = new Date();
           datef.setMonth(monthf);
           datef.setYear(anof);
           datef.setDate(diaf);

            obsCompra = FXCollections.observableArrayList(bllcompra.DevolveCompra());
           // System.out.println("obs size " + obsCompra.size());
            
            for(int i = 0 ; i < obsCompra.size(); i++){
                if( (obsCompra.get(i).getData().getYear() + 1900) >= anoi
                     && obsCompra.get(i).getData().getDate() >= diai
                        && obsCompra.get(i).getData().getMonth() >= monthi  ){
                    if( (obsCompra.get(i).getData().getYear() + 1900 ) <= anof 
                            && obsCompra.get(i).getData().getDate() <= diaf
                            && obsCompra.get(i).getData().getMonth() <= monthf ){
                        obsCompra.remove(i);
                        System.out.println("" + obsCompra.get(i).getIdcompra());
                    }
                }
            }
            
            
            tabela.setItems(obsCompra);
            
        }catch(IndexOutOfBoundsException ex){
            System.out.println("" + ex.getMessage());
        }
    }
     
     public void preencheTabelaFuncionario(){
     
        BLLCompra bllcompra = new BLLCompra();
        
        IDcompra.setCellValueFactory(new PropertyValueFactory<>("idcompra"));
        idfuncionario.setCellValueFactory(new PropertyValueFactory<>("idfuncionario"));
        idfornecedor.setCellValueFactory(new PropertyValueFactory<>("idfornecedor"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        valorTotal.setCellValueFactory(new PropertyValueFactory<>("valortotal"));
        numFatura.setCellValueFactory(new PropertyValueFactory<>("nfatura"));
        
        
        obsCompra = FXCollections.observableArrayList(bllcompra.DevolveCompraFuncionario2(funcionario.getValue() ));
        tabela.setItems(obsCompra);
       
    }
     
     public void preencheTabelaFornecedor(){
     
        BLLCompra bllcompra = new BLLCompra();
        
        IDcompra.setCellValueFactory(new PropertyValueFactory<>("idcompra"));
        idfuncionario.setCellValueFactory(new PropertyValueFactory<>("idfuncionario"));
        idfornecedor.setCellValueFactory(new PropertyValueFactory<>("idfornecedor"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        valorTotal.setCellValueFactory(new PropertyValueFactory<>("valortotal"));
        numFatura.setCellValueFactory(new PropertyValueFactory<>("nfatura"));
        
        
        obsCompra = FXCollections.observableArrayList(bllcompra.DevolveCompraFornecedor2( fornecedores.getValue() ));
        tabela.setItems(obsCompra);
       
    }
    
}
