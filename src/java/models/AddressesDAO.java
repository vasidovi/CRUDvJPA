/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import EMF.EMF;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dovile
 */
public class AddressesDAO {
    
    public static List<Addresses> getbyPerson (HttpServletRequest request){
     
        List<Addresses> list = null;
        Integer id = Integer.parseInt(request.getParameter("personId"));
        Persons p = PersonsDAO.getOne(request, id);

        if (p != null) {
            list = p.getAddressesList();
            if (list != null) {
                List<Addresses> addressesList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    Integer addressId = ((Addresses) list.get(i)).getId();
                if (addressId!= null){
                EntityManager em = (EntityManager) request.getAttribute("em");     
                Addresses a = em.find(Addresses.class, addressId);  
                addressesList.add(a);
                }
                }
                System.out.println("Address list from DAO " + addressesList.toString());
                return addressesList;              
            }
        }
        return list;

    }
    
    
    
    public static Addresses getOne (HttpServletRequest request, Integer addressId){

            Addresses a =null; 
  
                if (addressId!= null){
               EntityManager em = (EntityManager) request.getAttribute("em");
     
           a = em.find(Addresses.class, addressId);     
                }
       return a;

    }
    
    public static Addresses saveOrUpdate (HttpServletRequest request, Integer personId,  Addresses aIn){
       
       Integer addressId = aIn.getId();       
       String address = aIn.getAddress();
       String city = aIn.getCity();
       String postalCode = aIn.getPostalCode();

       EntityManager em = (EntityManager) request.getAttribute("em");
       EntityTransaction tx;
       try {
           tx = EMF.getTransaction(em);
       } catch (Exception ex) {
           return null;
       }

       Addresses a = null;
       if (addressId != null) {
          a = em.find(Addresses.class, addressId);

       }
      if(a != null){
          a.setAddress(address);
          a.setCity(city);
          a.setPostalCode(postalCode);
      }
      else{

          a = new Addresses();
          Persons p = em.find(Persons.class,personId);
          a.setPersonId(p);
          a.setAddress(address);
          a.setCity(city);
          a.setPostalCode(postalCode);
          em.persist(a);
      }
       try {
           EMF.commitTransaction(tx);
       } catch (Exception ex) {
           EMF.rollbackTransaction(tx);
           return null;
       }
       return a;
   }
    
 
 public static Addresses delete (HttpServletRequest request){   
     String addressIdStr = request.getParameter("addressId");
        Integer addressId = null;
        try {
            addressId = Integer.parseInt(addressIdStr);
        } catch (NumberFormatException e) {
        }
        Addresses a = null;
        if (addressId != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            EntityTransaction tx;
            try {
                tx = EMF.getTransaction(em);
            } catch (Exception ex) {
                return null;
            }
            a = em.find(Addresses.class, addressId);
            em.remove(a);
            try {
                EMF.commitTransaction(tx);
            } catch (Exception ex) {
                EMF.rollbackTransaction(tx);
                return null;
            }
        }
        return a;
 }
}
