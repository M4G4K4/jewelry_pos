package BLL;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jpa.entities.Artigo;
import jpa.entities.Compra;

public class BLLArtigo {
    
    private static final String PERSISTENCE_UNIT_NAME = "OurivesariaProjetoPU";
    private static EntityManagerFactory factory;
    
    
    // Devolve Lista com todos os artigos 
    public List<Artigo> DevolveArtigo(){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Artigo.findAll");
       List<Artigo> lista = q.getResultList();
       return lista;
    }
    
    public List<Artigo> DevolveArtigoID(int id){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Artigo.findByIdartigo").setParameter("idartigo", id);
       List<Artigo> lista = q.getResultList();
       return lista;
    }
    
    
     // Insere Artigo passando artigo 
    public void insereArtigo(Artigo artigo){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
      
       em.getTransaction().begin();
       em.persist(artigo);
       em.getTransaction().commit();
    }
    
    
    public void editArtigo(int idartigo,String ini ,short qtd,String descri,BigDecimal pe,
            BigDecimal pre,short i,short desc,short mar,String mate,short to,Compra co,int codFor,BigDecimal preTotal){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       Query q = em.createNamedQuery("Artigo.findByIdartigo").setParameter("idartigo",idartigo);
       List<Artigo> lista = q.getResultList();
        
        Artigo a = lista.get(0); 
        a.setIniciais(ini);
        a.setQtd(qtd);
        a.setDescricao(descri);
        a.setPeso(pe);
        a.setPrecoCompra(pre);
        a.setIva(i);
        a.setDesconto(desc);
        a.setMargem(mar);
        a.setMetal(mate);
        a.setToque(to);
        a.setIdcompra(co);
        a.setIdartigofornecedor(codFor);
        a.setPrecofinal(preTotal);
        
        
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        
    }
    
    
    // Remove artigo passando o ID
    public void removeArtigo(int idArtigo){
       
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
        Query q = em.createNamedQuery("Artigo.findByIdartigo").setParameter("idartigo",idArtigo);
        List<Artigo> lista = q.getResultList();
        
        Artigo a = lista.get(0); 
       
        
        em.getTransaction().begin();
        em.remove(a);
        em.getTransaction().commit();
    }
    
    
    public List<Artigo> DevolveArtigoIniciais(String ini){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Artigo.findByIniciais").setParameter("iniciais", ini);
       List<Artigo> lista = q.getResultList();
       
       return lista;
    }
    
    public List<Artigo> DevolveArtigoDescricao(String desc){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Artigo.findByDescricaoLike").setParameter("descricao", desc);
       List<Artigo> lista = q.getResultList();
       
       return lista;
    }
    


    public List<Artigo> DevolveArtigoIDartigoFornecedor(int id){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       
       Query q = em.createNamedQuery("Artigo.findByIdartigofornecedor").setParameter("idartigofornecedor", id);
       List<Artigo> lista = q.getResultList();
       
       return lista;
    }
    
    
     public List<Artigo> UpdateQTD(short id, short quantidade){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Artigo.findByIdartigo").setParameter("idartigo", id);
       List<Artigo> lista = q.getResultList();
       
       int i = lista.get(0).getQtd();
       short  a = (short) (i - quantidade);
       
       Artigo artigo = lista.get(0);
     
       artigo.setQtd(a);
               
       em.getTransaction().begin();
       em.persist(artigo);
       em.getTransaction().commit();
       
       return lista;
    }
    
    
    
    
    
}
