/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jpa.entities.Artigo;
import jpa.entities.Cliente;
import jpa.entities.LinhaArtigo;
import jpa.entities.Venda;

/**
 *
 * @author Pedro
 */
public class BLLVenda {
    
    private static final String PERSISTENCE_UNIT_NAME = "OurivesariaProjetoPU";
    private static EntityManagerFactory factory;
    
    
    
    public void InsereNovaVenda(Cliente c,Date data,BigDecimal total){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
        Venda v = new Venda();
        v.setIdcliente(c);
        v.setTotal(total);
        v.setData(data);
        
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
    }
    
    public void InsereNovaVenda2(Venda venda){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       em.getTransaction().begin();
       em.persist(venda);
       em.getTransaction().commit();
    }
    
    
    public void novaVenda(){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       
       Venda v = new Venda();
       v.setTotal(BigDecimal.ONE);
               
       
       em.getTransaction().begin();
       em.persist(v);
       em.getTransaction().commit();
        
    }
    
    
    // Retorna lista com Todas as vendas
    public List<Venda> DevolveVendas(){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       
       Query q = em.createNamedQuery("Venda.findAll"); 
       List<Venda> lista = q.getResultList();
       
       return lista;
    }
    
       public List<Venda> DevolveVendaTopID(){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       
       Query q = em.createNamedQuery("Venda.findtopID"); 
       List<Venda> lista = q.getResultList();
       
       return lista;
    }
    
    
       
       
    public void InsereNovosProdutos(Artigo a, short quantidade , BigDecimal valor){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       
       Query q = em.createNamedQuery("Venda.findAll");
       List<Venda> lista = q.getResultList();
       
       // enche a lista com a query o ultimo na lista é a ultima venda criada 
       int s = lista.size();
       Venda v = lista.get(s); 
       
       LinhaArtigo la = new LinhaArtigo();
       
       la.setArtigo(a);
       la.setVenda(v);
       la.setQtd(quantidade);
       la.setValor(valor);
       
       em.getTransaction().begin();
       em.persist(la);
       em.getTransaction().commit();
        
       
    }
    
    public void editVenda(int id,Cliente cliente,BigDecimal total ,Date date){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       
       Query q = em.createNamedQuery("Venda.findByIdvenda").setParameter("idvenda", id);
       List<Venda> lista = q.getResultList();
       
       Venda v = lista.get(0);
       
       v.setTotal(total);
       v.setIdcliente(cliente);
       v.setData(date);
       
       
       
       em.getTransaction().begin();
       em.persist(v);
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
       
                
        em.getTransaction().begin();
        em.persist(cl);
        em.getTransaction().commit();
        
    }
     
     public List<Venda> DevolveVendasClientes(Cliente cliente){
       factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
       EntityManager em = factory.createEntityManager();
       
       
       Query q = em.createNamedQuery("Venda.findByCliente").setParameter("idcliente", cliente); 
       List<Venda> lista = q.getResultList();
       
       return lista;
    }
     
     
    
}
