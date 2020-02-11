/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jpa.entities.Cliente;
import jpa.entities.Cpostal;

/**
 *
 * @author Pedro
 */
public class BLLCodigoPostal {
    
    
     private static final String PERSISTENCE_UNIT_NAME = "OurivesariaProjetoPU";
    private static EntityManagerFactory factory;
    
    
    
    public void insereCPostal(Cpostal cp){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
      
       em.getTransaction().begin();
       em.persist(cp);
       em.getTransaction().commit();
    }
    
     public List<Cpostal> DevolveCpostal(String cp){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
        
       Query q = em.createNamedQuery("Cpostal.findByCpostal").setParameter("cpostal", cp);
       List<Cpostal> lista = q.getResultList();
       return lista;
    }
    
}
