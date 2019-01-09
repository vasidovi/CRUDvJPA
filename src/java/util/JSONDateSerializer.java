/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Calendar;
import java.util.Date;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author Dovile
 */

// Not used!!!!

public class JSONDateSerializer implements JsonbSerializer<Date>{

    @Override
    public void serialize(Date d, JsonGenerator jg, SerializationContext sc) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        String  day = "" + c.get(Calendar.DAY_OF_MONTH);
        String month ="" + (c.get(Calendar.MONTH) + 1);
        if ( day.length()  == 1){
            day = "0" + day;
        }
        if ( month.length() ==1 ){
            month = "0" + month;
        }

        String dateString = c.get(Calendar.YEAR) + "-" + month + "-" + day;
        jg.write(dateString);
    }
    
}
