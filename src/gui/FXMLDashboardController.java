package gui;

import com.jfoenix.controls.JFXButton;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLDashboardController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   
    // -------------------
    
    
    
       @FXML
    private JFXButton ProdutosBotao;

    @FXML
    private JFXButton ClientesBotao;

    @FXML
    private JFXButton VendaBotao;

    @FXML
    private JFXButton FornecedoresBotao;

    @FXML
    private JFXButton FuncionarioBotao;

    @FXML
    private JFXButton CompraBotao;

   

    @FXML
    void OpenCompra(ActionEvent event) {
          try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLListarCompra.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Compra");
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);
              
          

        } catch (IOException e) {
            
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        
        }
    }

    @FXML
    void OpenFornecedores(ActionEvent event) {
          try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLListaFornecedores.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Fornecedores");
                stage.setScene(scene);
                stage.show();
               stage.setResizable(false);
                
             
        } catch (IOException e) {
            
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        
        }
    }

    @FXML
    void OpenFuncionario(ActionEvent event) {
          try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLListarFuncionarios.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Funcionarios");
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }


    @FXML
    void OpenVenda(ActionEvent event) {
          try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLListarVenda.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Venda");
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);
              

        } catch (IOException e) {
            
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        
        }
    }

    @FXML
    void OpenClientes(ActionEvent event) {
        
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLListaClientes.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Clientes");
                stage.setScene(scene);
                stage.show();
                 stage.setResizable(false);
              
              

        } catch (IOException e) {
            
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        
        }
    
        
    }

    @FXML
    void OpenProdutos(ActionEvent event) {
           try {
                // Abre ecra Dashboard
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLListarProdutos.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Produtos");
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);
              
          

        } catch (IOException e) {
            
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        
        }
    }
    
    
    

    // -------------------
    

}
