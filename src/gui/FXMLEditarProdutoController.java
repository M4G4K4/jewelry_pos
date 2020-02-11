package gui;

import BLL.BLLArtigo;
import BLL.BLLCompra;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import jpa.entities.Compra;

public class FXMLEditarProdutoController implements Initializable {

    private int id=0;
    private ObservableList<Compra> obsCompra; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheComboboxCompra();
    }   
    
    
     @FXML
    private JFXTextField iniciais;
    @FXML
    private JFXTextField quantidade;
    @FXML
    private JFXTextField descricao;
    @FXML
    private JFXTextField peso;
    @FXML
    private JFXTextField preco;
    @FXML
    private JFXTextField IVA;
    @FXML
    private JFXTextField desconto;
    @FXML
    private JFXTextField margen;
    @FXML
    private JFXTextField material;
    @FXML
    private JFXTextField toque;
    @FXML
    private JFXComboBox<Compra> compra;
    @FXML
    private JFXTextField codFornecedor;
    @FXML
    private JFXButton BotaoGuardar;
    
    
    @FXML
    void guardar(ActionEvent event) {
        BLLArtigo bLLArtigo = new BLLArtigo();
        
        
        BigDecimal bgpeso = new BigDecimal(peso.getText());
        BigDecimal bgpreco = new BigDecimal(preco.getText());
        
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
        
        BigDecimal bgprecoTotal = new BigDecimal(pfinal);
        
        bLLArtigo.editArtigo(id,iniciais.getText(),Short.parseShort(quantidade.getText()),
                descricao.getText(), bgpeso, bgpreco,Short.parseShort(IVA.getText())
        ,Short.parseShort(desconto.getText()),Short.parseShort(margen.getText()),
        material.getText(), Short.parseShort(toque.getText()) , compra.getValue(),Integer.parseInt(codFornecedor.getText()),bgprecoTotal);
        
         // Fechar janela
         Stage stage = (Stage) BotaoGuardar.getScene().getWindow();
         stage.close();
    }
    
    public void transfereCampos(int idartigo,String ini ,short qtd,String descri,BigDecimal pe,
            BigDecimal pre,short i,short desc,short mar,String mate,short to,Compra co,int codFor){
        id = idartigo;
        
        iniciais.setText(ini);
        quantidade.setText(Short.toString(qtd));
        descricao.setText(descri);
        peso.setText(pe.toString());
        preco.setText(pre.toString());
        IVA.setText(Short.toString(i));
        desconto.setText(Short.toString(desc));
        margen.setText(Short.toString(mar));
        material.setText(mate);
        toque.setText(Short.toString(to));
        compra.setValue(co);
        codFornecedor.setText(Integer.toString(codFor));
        
        
        
    }
    
    
    public void preencheComboboxCompra(){
        
        BLLCompra bLLCompra = new BLLCompra();
        
        obsCompra = FXCollections.observableArrayList(bLLCompra.DevolveCompra());
        
        compra.setItems(obsCompra);
        
    }
    
    
    
    
    
}
