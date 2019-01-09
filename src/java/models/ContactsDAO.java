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
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dovile
 */
public class ContactsDAO {

    public static List<Contacts> getbyPerson(HttpServletRequest request) {

        List<Contacts> list = null;        
        Integer id = Integer.parseInt(request.getParameter("personId"));
        Persons p = PersonsDAO.getOne(request,id);

        if (p != null) {
            list = p.getContactsList();
            if (list != null) {
                List<Contacts> contactList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    Integer contactId = ((Contacts) list.get(i)).getId();
                if (contactId!= null){
                EntityManager em = (EntityManager) request.getAttribute("em");     
                Contacts c = em.find(Contacts.class, contactId);  
                contactList.add(c);
                }
                }
                return contactList;              
            }
        }
        return list;

    }

    public static Contacts getOne(HttpServletRequest request, Integer contactId) {

        Contacts c = null;       
        if (contactId != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            c = em.find(Contacts.class, contactId);
        }
        return c;

    }

    public static Contacts saveOrUpdate(HttpServletRequest request, Integer personId,  Contacts cIn) {
//        String personIdString = request.getParameter("personId");
//        Integer personId = null;
//        try {
//            personId = Integer.parseInt(personIdString);
//        } catch (NumberFormatException e) {
//        }
        
        Integer contactId = cIn.getId();       
        String contact = cIn.getContact();
        String contactType = cIn.getContactType();

        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx;
        try {
            tx = EMF.getTransaction(em);
        } catch (Exception ex) {
            return null;
        }
        
        Contacts c = null;
        if (contactId != null) {
            c = em.find(Contacts.class, contactId);

        }
        if (c != null) {

            c.setContact(contact);
            c.setContactType(contactType);
        } else {
            c = new Contacts();
            Persons p = em.find(Persons.class, personId);
            c.setPersonId(p);
            c.setContact(contact);
            c.setContactType(contactType);
            em.persist(c);
        }
        try {
            EMF.commitTransaction(tx);
        } catch (Exception ex) {
            EMF.rollbackTransaction(tx);
            return null;
        }
        return c;
    }

    public static Contacts delete(HttpServletRequest request, Integer contactId) {

        Contacts c = null;

        if (contactId != null) {
            EntityManager em = (EntityManager) request.getAttribute("em");
            EntityTransaction tx;
            try {
                tx = EMF.getTransaction(em);
            } catch (Exception ex) {
                return null;
            }
            c = em.find(Contacts.class, contactId);
            em.remove(c);
            try {
                EMF.commitTransaction(tx);
            } catch (Exception ex) {
                EMF.rollbackTransaction(tx);
                return null;
            }
        }
        return c;
    }
}
