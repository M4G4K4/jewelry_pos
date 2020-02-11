package BLL;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jpa.entities.Utilizador;

public class BLLFuncionario {
    
    private static final String PERSISTENCE_UNIT_NAME = "OurivesariaProjetoPU";
    private static EntityManagerFactory factory;
    
    // Insere Funcionario 
     public void insereFuncionario(Utilizador utilizador){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
      
       em.getTransaction().begin();
       em.persist(utilizador);
       em.getTransaction().commit();
    }
    
    
    // Retorna lista com todos os utilizadores (Funcionarios)
    public List<Utilizador> DevolveFuncionario(){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Utilizador.findAll");
       List<Utilizador> lista = q.getResultList();
       
       
       return lista;
    }
    
     public List<Utilizador> DevolveFuncionarioLessAdmin(){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Utilizador.findByNotAdmin");
       List<Utilizador> lista = q.getResultList();
       
       
       return lista;
    }
    
    
    
    public void removefuncionario(Utilizador utilizador){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
      
       em.getTransaction().begin();
       em.remove(utilizador);
       em.getTransaction().commit();
        
    }
    
     public void removefuncionarioID(int id){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       Query q = em.createNamedQuery("Utilizador.findByIdutilizador").setParameter("idutilizador",id);
       List<Utilizador> lista = q.getResultList();
        
       Utilizador u = lista.get(0); 
    

      
       em.getTransaction().begin();
       em.remove(u);
       em.getTransaction().commit();
        
    }
     
     public void editFuncionario(int id, String login, String pass){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       Query q = em.createNamedQuery("Utilizador.findByIdutilizador").setParameter("idutilizador",id);
       List<Utilizador> lista = q.getResultList();
        
        Utilizador u = lista.get(0); 
        u.setLogin(login);
        u.setPass(pass);
        
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        
    }
     
     public List<Utilizador> DevolveFuncionarioNome(String nome){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Utilizador.findByLogin").setParameter("login", nome);
       List<Utilizador> lista = q.getResultList();
       
       
       return lista;
    }
     

    
    
    
    
}
