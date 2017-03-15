/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Benoit
 */
public class DateConvertion {

    public static Date convert(String aDate) throws ParseException {
        Date sqlDate = null;
        java.util.Date date = new java.util.Date(aDate);
        sqlDate = new Date(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String formatMdp = formatter.format(sqlDate).toUpperCase();
        date = new java.util.Date(formatMdp);
        sqlDate = new Date(date.getTime());
        return sqlDate;
    }
}
