/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonValue;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author Dovile
 */
public class JSONDateAdapter implements JsonbAdapter<Date, String> {

    @Override
    public String adaptToJson(Date d)
            throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        String dateString = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH);
        return dateString;
    }

    @Override
    public Date adaptFromJson(String json)
            throws Exception {
        String dateString = json;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(dateString);
        return d;
    }

}