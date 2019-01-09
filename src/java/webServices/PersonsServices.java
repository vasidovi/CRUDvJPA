/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import models.Addresses;
import models.AddressesDAO;
import models.Contacts;
import models.ContactsDAO;
import models.Persons;
import models.PersonsDAO;

/**
 *
 * @author Dovile
 */
@Path("personsSevices")
public class PersonsServices {
         
    @GET
    @Produces("application/json")
    public List<Persons> getList(@Context HttpServletRequest request){
        
        return PersonsDAO.getAllPersons(request);
    }
    
    
    @GET
    @Produces("application/json")
    @Path("/{id}") 
       public Persons select(@Context HttpServletRequest request,  @PathParam("id") Integer id){
       
        return PersonsDAO.getOne(request, id); // galima negrazininti nieko
    } 
       
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{id}/addContact") 
    public Contacts addContact(@Context HttpServletRequest request, @PathParam("id") Integer id, Contacts c){
              
     return ContactsDAO.saveOrUpdate(request, id, c);
    }
    
     @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{id}/addAddress") 
    public Addresses addAddress(@Context HttpServletRequest request, @PathParam("id") Integer id, Addresses a){
              
     return AddressesDAO.saveOrUpdate(request, id, a); 
    }
                        
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Persons add(Persons p, @Context HttpServletRequest request){
        return PersonsDAO.saveOrUpdate(request, p); // normaliai reiketu issaugoti i DB ir grazinti su id, kitom defaultinem reiksmems.
    }
    
    
    @DELETE
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/{id}") 
    public Persons delete(@Context HttpServletRequest request, @PathParam("id") Integer id){
        return PersonsDAO.delete(request, id); // galima negrazininti nieko
    }
    
                    
}
    
    
    
    
    
    
    
    
    
    
    

