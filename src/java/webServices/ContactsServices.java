/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import models.Contacts;
import models.ContactsDAO;

/**
 *
 * @author Dovile
 */
@Path("contactsSevices")
public class ContactsServices{
    
    @GET
    @Produces("application/json")
    public List<Contacts> getList(@Context HttpServletRequest request){
        return ContactsDAO.getbyPerson(request);        
    }
    
    @GET
    @Produces("application/json") 
    @Path("/{id}") 
       public Contacts select(@Context HttpServletRequest request,  @PathParam("id") Integer id){  
         return ContactsDAO.getOne(request, id);
    } 
        
    
    @DELETE
    @Produces("application/json")
    @Path("/{id}") 
    public Contacts delete(@Context HttpServletRequest request, @PathParam("id") Integer id){
        
        return ContactsDAO.delete(request, id); // galima negrazininti nieko
    }
    
//    
//    @POST
//    @Produces("application/json")
//    @Consumes("application/json")
//       public Contacts update(@Context HttpServletRequest request, Contacts c){
//       
//        return ContactsDAO.saveOrUpdate(request, c); // galima negrazininti nieko
//    }
        
        
}
