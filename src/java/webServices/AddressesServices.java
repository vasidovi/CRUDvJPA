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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import models.Addresses;
import models.AddressesDAO;

/**
 *
 * @author Dovile
 */
@Path("addressesSevices")
public class AddressesServices {
    

         
    @GET
    @Produces("application/json")
    public List<Addresses> getList(@Context HttpServletRequest request){    
    return  AddressesDAO.getbyPerson(request);
    }
    
    
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Addresses select(@Context HttpServletRequest request, @PathParam("id") Integer id){           
    return AddressesDAO.getOne(request, id);
    }
//        
//    @PUT
//    @Produces("application/json")
//    @Consumes("application/json")
//    public Addresses add(@Context HttpServletRequest request){
//        
//      
//        return AddressesDAO.saveOrUpdate(request); // normaliai reiketu issaugoti i DB ir grazinti su id, kitom defaultinem reiksmems.
//    }
    
    
    @DELETE
    @Produces("application/json")
    @Consumes("application/json")
    public Addresses delete(@Context HttpServletRequest request){
        
        return AddressesDAO.delete(request); // galima negrazininti nieko
    }
    
    
//    @POST
//    @Produces("application/json")
//    @Consumes("application/json")
//       public Addresses update(@Context HttpServletRequest request){
//       
//        return AddressesDAO.saveOrUpdate(request); // galima negrazininti nieko
//    }
        
        
}
    

