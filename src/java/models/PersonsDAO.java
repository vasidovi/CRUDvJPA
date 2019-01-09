/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import EMF.EMF;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dovile
 */
public class PersonsDAO {
    
    public static List<Persons> getAllPersons (HttpServletRequest request){
     
     
        
        EntityManager em = (EntityManager) request.getAttribute("em");
        Query query = em.createNamedQuery("Persons.findAll");          
        List list = query.getResultList();
                        
            return list;
    }
    
        public static Persons getOne(HttpServletRequest request, Integer personId){
                          
            Persons p =null;   
                if (personId!= null){
               EntityManager em = (EntityManager) request.getAttribute("em");
     
           p = em.find(Persons.class, personId);     
                }
            return p;
      
        }  
        
        
public static Persons delete(HttpServletRequest request, Integer id ) {


        Persons p = null;
        if (id != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            EntityTransaction tx;
            try {
                tx = EMF.getTransaction(em);
            } catch (Exception ex) {
                return null;
            }
            p = em.find(Persons.class, id);
            em.remove(p);
            try {
                EMF.commitTransaction(tx);
            } catch (Exception ex) {
                EMF.rollbackTransaction(tx);
                return null;
            }
        }
        return p;
    }        
        
        
        
  public static Persons saveOrUpdate(HttpServletRequest request, Persons pIn) {
      
      

        Integer id = pIn.getId();
        

         String firstName = pIn.getFirstName();
         String lastName = pIn.getLastName();
         Date birthDate = pIn.getBirthDate();   
        BigDecimal salary = pIn.getSalary();
             
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx;
        try {
            tx = EMF.getTransaction(em);
        } catch (Exception ex) {
            return null;
        }
        
        Persons p = null;
        if (id != null) {
            p = em.find(Persons.class, id);

        }
        if (p != null) {
            p.setFirstName(firstName);
            p.setLastName(lastName);
            p.setBirthDate(birthDate);
            p.setSalary(salary);
        } else {
            p = new Persons();
            p.setFirstName(firstName);
            p.setLastName(lastName);
            p.setBirthDate(birthDate);
            p.setSalary(salary);
            em.persist(p);
        }
        try {
            EMF.commitTransaction(tx);
        } catch (Exception ex) {
            EMF.rollbackTransaction(tx);
            return null;
        }
        return p;

    }


    
    
       
        
    }
    

