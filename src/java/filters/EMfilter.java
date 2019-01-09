package filters;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import EMF.EMF;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dovile
 */
@WebFilter(filterName = "EMfilter", urlPatterns = {"/*"})
public class EMfilter implements Filter {
    
   
    public EMfilter() {
    }    
    
 
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        EntityManager em = EMF.getEntityManager();
        
        request.setAttribute("em", em);

        chain.doFilter(request, response);
        
        
        EMF.returnEntityManager(em);
     
    }

   
    public void destroy() {        
        
        // duoda galimybe filtrui atlaisvinti resursus
    }

    
    public void init(FilterConfig filterConfig) {        
       
        // iskvieciamas viena karta 
    }
}
    

