/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;

/**
 *
 * @author Dovile
// */

// Not used!!!!  Using date Serializer/Deserializer for this project did not work, because it consumed input stream (?)  , 
// The 

public class JSONDateDeserializer implements JsonbDeserializer<Date>{

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext dc, Type type) {
        
//        if (jp.getString().isEmpty()){
//            return null;
//        }
        
        String dateString = jp.getString();  
        
        
      
        try {
           
              TimeZone.setDefault(TimeZone.getTimeZone("CET"));
     Calendar calendar = Calendar.getInstance();
    
    Integer year=Integer.parseInt(dateString.substring(0,4));
    Integer month = Integer.parseInt(dateString.substring(5,7))-1;
    Integer day = Integer.parseInt(dateString.substring(8,10));
    calendar.set(year, month, day);
    Date date = calendar.getTime();
    
            
            return date;
        } catch (Exception ex) {
            Logger.getLogger(JSONDateDeserializer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
          
    } 
}  
    

