package com.reyes.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Daniel
 */
public class App {
    
    EntityManagerFactory emf = null;
    EntityManager em = null;
    
    public void guardarCategoria(String nombre, boolean estado){
        emf = Persistence.createEntityManagerFactory("primePU");
        em = emf.createEntityManager();        
        em.getTransaction().begin();
        Categoria cat = new Categoria();
        
        try {
            cat.setNombre(nombre);
            cat.setEstado(estado);
            em.persist(cat);
            em.getTransaction().commit();
            System.out.println("Se guardo");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            em.close();
            emf.close();
        }
    }
    
    public static void main(String[] args) {
        
        App app = new App();
        app.guardarCategoria("ruby", true);

        

    }
}
