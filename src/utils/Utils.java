/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Adson
 */
public abstract class Utils {
    public static Calendar toCalendar(String data) throws ParseException{
        Date date = (new SimpleDateFormat("dd/MM/yyyy")).parse(data);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        return calendar;
    }
}
