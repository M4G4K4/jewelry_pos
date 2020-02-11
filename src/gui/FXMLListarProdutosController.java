package gui;

import BLL.BLLArtigo;
import BLL.BLLFornecedor;
import BLL.BLLVenda;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import jpa.entities.Artigo;
import jpa.entities.Fornecedor;
import org.controlsfx.control.Notifications;

public class FXMLListarProdutosController implements Initializable {

    private ObservableList<Artigo> obsArtigos;
    private ObservableList<Fornecedor> obsFornecedor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheTableViewArtigos();
    }  
    
       @FXML
    private TableView<Artigo> tabela;

    @FXML
    private TableColumn<Artigo, Integer> id;

    @FXML
    private TableColumn<Artigo, String> iniciais;

    @FXML
    private TableColumn<Artigo, String> descricao;

    @FXML
    private TableColumn<Artigo, Integer> qtd;

    @FXML
    private TableColumn<Artigo, Float> peso;

    @FXML
    private TableColumn<Artigo, Integer> toque;

    @FXML
    private TableColumn<Artigo, String> metal;

    @FXML
    private TableColumn<Artigo, Integer> idartigofornecedor;

    @FXML
    private TableColumn<Artigo, Float> preco;
    
   
    @FXML
    private JFXTextField filtroIniciais;

    @FXML
    private JFXTextField filtroDescricao;

    @FXML
    private JFXTextField filtroIdartigoFornecedor;
    
    @FXML
    private JFXComboBox<Fornecedor> filtroFornecedor;
        
        
    @FXML
    private JFXButton botaoFiltro;

    @FXML
    private JFXButton botaoAdicionar;

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
                fxmlLoader.setLocation(getClass().getResource("FXMLInserirProduto.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Inserir Produto");
                stage.setScene(scene);
                stage.setResizable(false);
                BLLArtigo bLLArtigo = new BLLArtigo();
                List<Artigo> i = bLLArtigo.DevolveArtigo();
                stage.showAndWait();
                preencheTableViewArtigos();
                List<Artigo> d = bLLArtigo.DevolveArtigo();
                if(d.size() > i.size() ){
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditarProduto.fxml"));
            Parent root = loader.load();
            
            FXMLEditarProdutoController produtoController = loader.getController();
            produtoController.transfereCampos(
                    tabela.getSelectionModel().getSelectedItem().getIdartigo(),
                    tabela.getSelectionModel().getSelectedItem().getIniciais(),
                    tabela.getSelectionModel().getSelectedItem().getQtd(), // short
                    tabela.getSelectionModel().getSelectedItem().getDescricao(),
                    tabela.getSelectionModel().getSelectedItem().getPeso(), // Bigdecimal
                    tabela.getSelectionModel().getSelectedItem().getPrecoCompra(), // Bigdecimal
                    tabela.getSelectionModel().getSelectedItem().getIva(), // short
                    tabela.getSelectionModel().getSelectedItem().getDesconto(), // short 
                    tabela.getSelectionModel().getSelectedItem().getMargem() , //short 
                    tabela.getSelectionModel().getSelectedItem().getMetal() , // string 
                    tabela.getSelectionModel().getSelectedItem().getToque(), // short
                    tabela.getSelectionModel().getSelectedItem().getIdcompra(),
                    tabela.getSelectionModel().getSelectedItem().getIdartigofornecedor()
            );
            
                   
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Produto");
            stage.setResizable(false);
            stage.showAndWait();
            tabela.refresh();
            preencheTableViewArtigos();
        } catch (RuntimeException ex) {
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Tem que selecionar algum item");
            errorAlert.showAndWait();
        }
        catch(IOException ex){
            System.err.println(ex);
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Erro ao abrir janela");
            errorAlert.showAndWait();
        }
    
    
    
    }

    @FXML
    void eliminar(ActionEvent event) {
        BLLArtigo bLLArtigo = new BLLArtigo();
        
        
        bLLArtigo.removeArtigo(tabela.getSelectionModel().getSelectedItem().getIdartigo());
        preencheTableViewArtigos();
        showNotificationDelete();
    }

    @FXML
    void filtro(ActionEvent event) {
        if(filtroIniciais.getText().trim().isEmpty() && filtroDescricao.getText().trim().isEmpty() && filtroIdartigoFornecedor.getText().trim().isEmpty()){
            preencheTableViewArtigos();
            tabela.refresh();
        }
        if(!filtroIniciais.getText().trim().isEmpty()){
            preencheTabelaIniciais();
            filtroIniciais.clear();
        }
        if(!filtroDescricao.getText().trim().isEmpty()){
            preencheTabelaDescricao();
            filtroDescricao.clear();
        }
        if(!filtroIdartigoFornecedor.getText().trim().isEmpty()){
            preencheTabelaFIDartigoFornecedor();
            filtroIdartigoFornecedor.clear();
        }
        
    }

    @FXML
    void guardar(ActionEvent event) {
          // Fechar janela
        Stage stage = (Stage) botaoGuardar.getScene().getWindow();
        stage.close();
    }
    
    
    public void preencheTableViewArtigos(){
        
        BLLArtigo bLLArtigo = new BLLArtigo();
        
        id.setCellValueFactory(new PropertyValueFactory<>("idartigo"));
        iniciais.setCellValueFactory(new PropertyValueFactory<>("iniciais"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        qtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        peso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        toque.setCellValueFactory(new PropertyValueFactory<>("toque"));
        metal.setCellValueFactory(new PropertyValueFactory<>("metal"));
        idartigofornecedor.setCellValueFactory(new PropertyValueFactory<>("idartigofornecedor"));
        preco.setCellValueFactory(new PropertyValueFactory<>("precofinal"));
        
        
        obsArtigos = FXCollections.observableArrayList(bLLArtigo.DevolveArtigo());
        tabela.setItems(obsArtigos);
    }
    
    public void preencheTabelaIniciais(){
        
        BLLArtigo bLLArtigo = new BLLArtigo();
        
        id.setCellValueFactory(new PropertyValueFactory<>("idartigo"));
        iniciais.setCellValueFactory(new PropertyValueFactory<>("iniciais"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        qtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        peso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        toque.setCellValueFactory(new PropertyValueFactory<>("toque"));
        metal.setCellValueFactory(new PropertyValueFactory<>("metal"));
        idartigofornecedor.setCellValueFactory(new PropertyValueFactory<>("idartigofornecedor"));
        preco.setCellValueFactory(new PropertyValueFactory<>("precofinal"));
        
        
        obsArtigos = FXCollections.observableArrayList(bLLArtigo.DevolveArtigoIniciais(filtroIniciais.getText()));
        tabela.setItems(obsArtigos);
    }
    
    public void preencheTabelaDescricao(){
        
        BLLArtigo bLLArtigo = new BLLArtigo();
        
        id.setCellValueFactory(new PropertyValueFactory<>("idartigo"));
        iniciais.setCellValueFactory(new PropertyValueFactory<>("iniciais"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        qtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        peso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        toque.setCellValueFactory(new PropertyValueFactory<>("toque"));
        metal.setCellValueFactory(new PropertyValueFactory<>("metal"));
        idartigofornecedor.setCellValueFactory(new PropertyValueFactory<>("idartigofornecedor"));
        preco.setCellValueFactory(new PropertyValueFactory<>("precofinal"));
        
        
        obsArtigos = FXCollections.observableArrayList(bLLArtigo.DevolveArtigoDescricao(filtroDescricao.getText()));
        tabela.setItems(obsArtigos);
    }
    
    
    
    
        public void preencheTabelaFIDartigoFornecedor(){
        
        BLLArtigo bLLArtigo = new BLLArtigo();
        
        id.setCellValueFactory(new PropertyValueFactory<>("idartigo"));
        iniciais.setCellValueFactory(new PropertyValueFactory<>("iniciais"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        qtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        peso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        toque.setCellValueFactory(new PropertyValueFactory<>("toque"));
        metal.setCellValueFactory(new PropertyValueFactory<>("metal"));
        idartigofornecedor.setCellValueFactory(new PropertyValueFactory<>("idartigofornecedor"));
        preco.setCellValueFactory(new PropertyValueFactory<>("precofinal"));
        
        
        obsArtigos = FXCollections.observableArrayList(bLLArtigo.DevolveArtigoIDartigoFornecedor( Integer.parseInt(filtroIdartigoFornecedor.getText()) ) );
        tabela.setItems(obsArtigos);
    }
    
    
    
    public void showNotification(){
          Image img = new Image("images/check2.png");
          Notifications notoficationBuilder = Notifications.create()
                        .title("Produto criado com sucesso")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.TOP_RIGHT);
          
          notoficationBuilder.show();
    }
    
      public void showNotificationDelete(){
          Image img = new Image("images/check2.png");
          Notifications notoficationBuilder = Notifications.create()
                        .title("Produto eliminado com sucesso")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.TOP_RIGHT);
          
          notoficationBuilder.show();
    }
      
          public void showNotificationEdit(){
          Image img = new Image("images/edit (1).png");
          Notifications notoficationBuilder = Notifications.create()
                        .title("Produto editado com sucesso")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(4))
                        .position(Pos.TOP_RIGHT);
          
          notoficationBuilder.show();
    }
    
    
}
