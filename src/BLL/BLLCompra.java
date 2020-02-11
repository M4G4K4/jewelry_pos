package BLL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jpa.entities.Compra;
import jpa.entities.Fornecedor;
import jpa.entities.Utilizador;

public class BLLCompra {
    
    private static final String PERSISTENCE_UNIT_NAME = "OurivesariaProjetoPU";
    private static EntityManagerFactory factory;
    
    
   public void insereCompra(Compra compra){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
      
       em.getTransaction().begin();
       em.persist(compra);
       em.getTransaction().commit();
    }
   
   
   
   public List<Compra> DevolveCompra(){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Compra.findAll");
       List<Compra> lista = q.getResultList();
       return lista;
       
   }
   
   
     public List<Compra> DevolveCompraDESC(){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Compra.findByAllDESC");
       List<Compra> lista = q.getResultList();
       return lista;
       
   }
   
   public void editCompra(int id, Date date , float valorTotal , Utilizador us , Fornecedor forne, int numFatura){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       Query q = em.createNamedQuery("Compra.findByIdcompra").setParameter("idcompra",id);
       List<Compra> lista = q.getResultList();
        
        Compra u = lista.get(0); 
        u.setData(date);
        
        BigDecimal bg = new BigDecimal(valorTotal);
        u.setValortotal(bg);
        
        u.setIdfuncionario(us);
        u.setIdfornecedor(forne);
        u.setNfatura(numFatura);
        
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        
    }
   
   public List<Compra> DevolveCompraData(Date inicio , Date fim){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Compra.findBybetweenData").setParameter("datastart", inicio).setParameter("dataend", fim);
       List<Compra> lista = q.getResultList();
       
       
       return lista;
       
   }
   
    public List<Compra> DevolveCompraData2(Date inicio , Date fim){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Compra.findAll");
       List<Compra> lista = q.getResultList();
       
       
       
   
       
       
       return lista;
   }
   
   public List<Compra> DevolveCompraFuncionario(int id){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Compra.findByIdFuncionario").setParameter("idfuncionario", id);
       List<Compra> lista = q.getResultList();
       
       return lista;
   }
   
   public List<Compra> DevolveCompraFuncionario2(Utilizador utilizador){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Compra.findByIdFuncionario").setParameter("idfuncionario", utilizador);
       List<Compra> lista = q.getResultList();
       
       return lista;
   }
   
   public List<Compra> DevolveCompraFornecedor(int id){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Compra.findByIdFornecedor").setParameter("idfornecedor", id);
       List<Compra> lista = q.getResultList();

       return lista;
   }
   
   
   public List<Compra> DevolveCompraFornecedor2(Fornecedor fornecedor){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Compra.findByIdFornecedor").setParameter("idfornecedor", fornecedor);
       List<Compra> lista = q.getResultList();

       return lista;
   }
 
   
   
    
   
    
    
}
