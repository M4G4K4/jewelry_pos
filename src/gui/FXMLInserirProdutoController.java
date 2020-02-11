package gui;

import BLL.BLLArtigo;
import BLL.BLLCompra;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
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
import jpa.entities.Artigo;
import jpa.entities.Compra;

public class FXMLInserirProdutoController implements Initializable {
    
    private ObservableList<Compra> obsCompra;  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheCombobox();
    }  
    
      @FXML
    private JFXTextField iniciais;

    @FXML
    private JFXTextField quantidade;

    @FXML
    private JFXTextField descricao;

    @FXML
    private JFXTextField preco;
    
    @FXML
    private JFXTextField peso;

    @FXML
    private JFXTextField toque;

    @FXML
    private JFXTextField material;


    @FXML
    private JFXTextField margen;

    @FXML
    private JFXTextField desconto;

    @FXML
    private JFXTextField IVA;
    

    @FXML
    private JFXComboBox<Compra> compra;

    @FXML
    private JFXTextField codFornecedor;

    

    @FXML
    private JFXButton BotaoGuardar;

    @FXML
    void guardar(ActionEvent event) {
        BLLArtigo bLLArtigo = new BLLArtigo();
        try{
            if(iniciais.getText() != null && descricao.getText() != null &&  Float.parseFloat(peso.getText()) > 0.0 ){
                Artigo a = new Artigo();
                a.setIniciais(iniciais.getText());
                a.setQtd( Short.parseShort(quantidade.getText()) );
                a.setDescricao(descricao.getText());
                
                // Preco
                    BigDecimal bigDecimalPreco=new BigDecimal(preco.getText());
                    a.setPrecoCompra( bigDecimalPreco );



                    a.setIva(Short.parseShort(IVA.getText()));
                    a.setDesconto(Short.parseShort(desconto.getText()));
                    a.setMargem(Short.parseShort(margen.getText()));

                    // Calculo preco final  - Iva , desconto , margem 
                        
                        // Desconto
                        float d = Float.parseFloat(desconto.getText());
                        float desc = d / 100;
                        
                        // Margem
                        float m = Float.parseFloat(margen.getText());
                        float marg = m / 100;
                       
                        // Preco
                        float p = Integer.parseInt(preco.getText()); // 100
                        float pfinal = 0;
                        
                        // IVA
                        float i = Integer.parseInt(IVA.getText()); // 20
                        float iv = i / 100;
                        
                        if(d > 0){
                            pfinal = p * iv + p;
                            pfinal = pfinal * marg + pfinal;
                            pfinal = pfinal - pfinal * desc;
                        }else{
                            pfinal = p * iv + p;
                            pfinal = pfinal * marg + pfinal;
                        }
                        
                        BigDecimal bgPFinal=new BigDecimal(pfinal);   
                        a.setPrecofinal(bgPFinal);
                
                // ---
                
                
                
                a.setMetal(material.getText());
                a.setToque(Short.parseShort(toque.getText()));
                
                // Peso 
                BigDecimal bigDecimalPeso =new BigDecimal(peso.getText());
                a.setPeso(bigDecimalPeso);
                
                // combobox compra 
                a.setIdcompra(compra.getValue());
                a.setIdartigofornecedor(Integer.parseInt(codFornecedor.getText()));
                
                
                //bll inserer artigo 
                bLLArtigo.insereArtigo(a);
                
                // Fechar janela
                Stage stage = (Stage) BotaoGuardar.getScene().getWindow();
                stage.close();
            }else{
                    System.out.println("Campos necesários não introduzidos");
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Dados Invalidos");
                    errorAlert.setContentText("Todos os campos necessitam de ser preenchidos");
                    errorAlert.showAndWait();
            }
         
         
        } catch (NumberFormatException e) {
            
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Erro ao criar Artigo" , e);
        
        }
    }
    
    // Metodo preenche Combobox Compra com as Compras efetuadas 
    public void preencheCombobox(){
        BLLCompra bLLCompra = new BLLCompra();
        
        obsCompra = FXCollections.observableArrayList(bLLCompra.DevolveCompraDESC());
        
        compra.setItems(obsCompra);
    }
    
    
}
