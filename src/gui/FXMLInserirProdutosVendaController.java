package gui;

import BLL.BLLArtigo;
import BLL.BLLLinhaArtigo;
import BLL.BLLVenda;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import jpa.entities.Artigo;
import jpa.entities.Venda;

public class FXMLInserirProdutosVendaController implements Initializable {

    private int idvenda = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    @FXML
    private JFXButton BotaoGuardar;

    @FXML
    private JFXButton botaoCancelar;
    
    @FXML
    private JFXTextField idArtigo;
    
    @FXML
    private JFXTextField quantidade;


    
    
    @FXML
    void cancelar(ActionEvent event) {
        // Fechar janela 
        Stage stage = (Stage) botaoCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void guardar(ActionEvent event) {
        boolean aux = false;
        try{
            BLLLinhaArtigo bLLLinhaArtigo = new BLLLinhaArtigo();
            BLLArtigo bLLArtigo = new BLLArtigo();
            BLLVenda bLLVenda = new BLLVenda();

            List<Venda> listaVenda = bLLVenda.DevolveVendaTopID();
            Venda v = listaVenda.get(0);

            List<Artigo> listaArtigos = bLLArtigo.DevolveArtigoID(Integer.parseInt(idArtigo.getText()));
            Artigo a = listaArtigos.get(0);

            // calcular o valor tendo a qtd para colocar na linha artigo 
            BigDecimal p = listaArtigos.get(0).getPrecofinal();
            BigDecimal q = new BigDecimal(quantidade.getText());
            p = p.multiply(q);

           
            if(listaArtigos.get(0).getQtd() >= Integer.parseInt(quantidade.getText())){
                bLLLinhaArtigo.insereLinhaArtigoParametros(v, a, Short.parseShort(quantidade.getText()),p);
                bLLArtigo.UpdateQTD( Short.parseShort(idArtigo.getText()), Short.parseShort(quantidade.getText()) );
                aux = true;
            }else{
                quantidade.setStyle("-fx-border-color: red ;");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Erro");
                errorAlert.setContentText("Quantidade introduzida superior à quantidade em stock do produto");
                errorAlert.showAndWait();
            }

            if(aux){
                // Fechar janela
                Stage stage = (Stage) BotaoGuardar.getScene().getWindow();
                stage.close();
            }
        }catch(RuntimeException ex){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Erro");
            errorAlert.setContentText("Nenhum produto em stock com o ID introduzido");
            errorAlert.showAndWait(); 
        }
        
    }
    
    
    public void transfereCampos(int i){
        idvenda = i;
    }
    
    
}
