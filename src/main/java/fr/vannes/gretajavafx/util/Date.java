package fr.vannes.gretajavafx.util;

import java.text.SimpleDateFormat;

public class Date {
    public static String conversionDateFr(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
