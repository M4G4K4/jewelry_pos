package BLL;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jpa.entities.Artigo;
import jpa.entities.LinhaArtigo;
import jpa.entities.LinhaArtigoPK;
import jpa.entities.Venda;

public class BLLLinhaArtigo {

    private static final String PERSISTENCE_UNIT_NAME = "OurivesariaProjetoPU";
    private static EntityManagerFactory factory;
    
    
    public void insereLinhaArtigoParametros(Venda venda,Artigo artigo,short qtd,BigDecimal valor){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
        LinhaArtigo a = new LinhaArtigo();
        
        LinhaArtigoPK lpk = new LinhaArtigoPK();
        lpk.setIdartigo(artigo.getIdartigo());
        lpk.setIdvenda(venda.getIdvenda());
        
        a.setLinhaArtigoPK(lpk);
        
        
        a.setVenda(venda);
        a.setArtigo(artigo);
        a.setQtd(qtd);
        a.setValor(valor);
        

        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }
    
    
    
    public void insereLinhaArtigo(LinhaArtigo linhaArtigo){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
      
       em.getTransaction().begin();
       em.persist(linhaArtigo);
       em.getTransaction().commit();
    }
    
    public List<LinhaArtigo> DevolveLinhaArtigoID(int id){
        
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("LinhaArtigo.findByIdvenda").setParameter("idvenda",id);
       List<LinhaArtigo> lista = q.getResultList();
       
       return lista;
    }
    
    
}
