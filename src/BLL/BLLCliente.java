
package BLL;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jpa.entities.Cliente;
import jpa.entities.Cpostal;


public class BLLCliente {
    
    private static final String PERSISTENCE_UNIT_NAME = "OurivesariaProjetoPU";
    private static EntityManagerFactory factory;
    
    // Insere Cliente passando os parametros 
    public void insereClienteParametros(String nome,String telemovel,String email,int NIF,int CC,int nContribuinte,Date datanasc,String obs,String codpostal,String Morada){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
        
        Cliente c = new Cliente();
        c.setNome(nome);
        c.setTelemovel(telemovel);
        c.setEmail(email);
        c.setNif(NIF);
        c.setCc(CC);
        c.setNcontribuinte(nContribuinte);
        c.setDatanascimento(datanasc);
        c.setObs(obs);
        c.setMorada(Morada);
        
        Cpostal cp = new Cpostal();
        cp.setCpostal(codpostal);
        
        
        c.setCpostal(cp);

        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
    
    // Insere Cliente passando cliente 
    public void insereCliente(Cliente cliente){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
      
       em.getTransaction().begin();
       em.persist(cliente);
       em.getTransaction().commit();
    }
    
    
    
    
    public void editCliente(int IDcliente,String nome,String telemovel,String email,int NIF,int CC,int nContribuinte,String obs,String codpostal,String Morada){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       Query q = em.createNamedQuery("Cliente.findByIdcliente").setParameter("idcliente",IDcliente);
       List<Cliente> lista = q.getResultList();
        
        Cliente cl = lista.get(0); 
        
        cl.setNome(nome);
        cl.setTelemovel(telemovel);
        cl.setEmail(email);
        cl.setNif(NIF);
        cl.setCc(CC);
        cl.setNcontribuinte(nContribuinte);
       
        cl.setObs(obs);
        cl.setMorada(Morada);
        
            Cpostal cp = new Cpostal();
            cp.setCpostal(codpostal);
        
        cl.setCpostal(cp);
                
        em.getTransaction().begin();
        em.persist(cl);
        em.getTransaction().commit();
        
    }
    
    public void removeCliente(int IDcliente){
       
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
        Query q = em.createNamedQuery("Cliente.findByIdcliente").setParameter("idcliente",IDcliente);
       List<Cliente> lista = q.getResultList();
        
        Cliente cl = lista.get(0); 
       
        
        em.getTransaction().begin();
        em.remove(cl);
        em.getTransaction().commit();
    }
    
    
    public List<Cliente> DevolveCliente(){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Cliente.findAll");
       List<Cliente> lista = q.getResultList();
       return lista;
    }
    
    public List<Cliente> DevolveClienteID(int idcliente){
        
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Cliente.findByIdcliente").setParameter("idcliente",idcliente);
       List<Cliente> lista = q.getResultList();
       
      
       
       return lista;
    }
    
    
    public List<Cliente> DevolveCliente2(String nome){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Cliente.findByNomeLike").setParameter("nome", nome);
       List<Cliente> lista = q.getResultList();
       return lista;
    }
    
    
     public List<Cliente> DevolveClienteMorada(String morada){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Cliente.findByMoradaLike").setParameter("morada", morada);
       List<Cliente> lista = q.getResultList();
       return lista;
    }
     
     public List<Cliente> DevolveClienteTelemovel(String telemovel){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Cliente.findByTelemovel").setParameter("telemovel", telemovel);
       List<Cliente> lista = q.getResultList();
       return lista;
    }
     
    public List<Cliente> DevolveClienteNif(BigDecimal nif){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Cliente.findByNif").setParameter("nif", nif);
       List<Cliente> lista = q.getResultList();
       return lista;
    }
    
    
    
    
    
    
}
