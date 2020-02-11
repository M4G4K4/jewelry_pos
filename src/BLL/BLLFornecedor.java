package BLL;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jpa.entities.Cliente;
import jpa.entities.Cpostal;
import jpa.entities.Fornecedor;


public class BLLFornecedor {
    private static final String PERSISTENCE_UNIT_NAME = "OurivesariaProjetoPU";
    private static EntityManagerFactory factory;
    
    
    // Insere Fornecedor passando Fornecedor 
    public void insereFornecedor(Fornecedor fornecedor){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
      
       em.getTransaction().begin();
       em.persist(fornecedor);
       em.getTransaction().commit();
    }
    
    
    
    // Retorna lista com todos os Fornecedores 
    public List<Fornecedor> DevolveFornecedor(){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Fornecedor.findAll");
       List<Fornecedor> lista = q.getResultList();
       return lista;
    }
    
    
    
    public void editFornecedor(int id,String name,String street,String tele,String mail,String obser ,String cpostal){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       Query q = em.createNamedQuery("Fornecedor.findByIdfornecedor").setParameter("idfornecedor",id);
       List<Fornecedor> lista = q.getResultList();
        
       Cpostal cp = new Cpostal();
       cp.setCpostal(cpostal);
       
        Fornecedor u = lista.get(0); 
        u.setNome(name);
        u.setMorada(street);
        u.setTelemovel(tele);
        u.setEmail(mail);
        u.setObs(obser);
        u.setCpostal(cp);
               
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        
    }
    
    
      public void removeFornecedor(int IDfornecedor){
       
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        
        Query q = em.createNamedQuery("Fornecedor.findByIdfornecedor").setParameter("idfornecedor",IDfornecedor);
        List<Fornecedor> lista = q.getResultList();
        
        Fornecedor f = lista.get(0); 
       
        
        em.getTransaction().begin();
        em.remove(f);
        em.getTransaction().commit();
    }
      
      
      // Retorna lista com todos os Fornecedores 
    public List<Fornecedor> DevolveFornecedorNome(String nome){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Fornecedor.findByNomeLike").setParameter("nome", nome);
       List<Fornecedor> lista = q.getResultList();
       return lista;
    }
    
    
    // Retorna lista com todos os Fornecedores 
    public List<Fornecedor> DevolveFornecedorMorada(String morada){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
            
       Query q = em.createNamedQuery("Fornecedor.findByMoradaLike").setParameter("morada", morada);
       List<Fornecedor> lista = q.getResultList();
       return lista;
    }
    
    
    // Retorna lista com todos os Fornecedores 
    public List<Fornecedor> DevolveFornecedorTelemovel(String telemovel){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Fornecedor.findByTelemovel").setParameter("telemovel", telemovel);
       List<Fornecedor> lista = q.getResultList();
       return lista;
    }
    
    
    
    
    
}
