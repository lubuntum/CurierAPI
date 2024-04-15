package com.example.curierplus.local;

import com.example.curierplus.enities.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateHelper {
    public static SimpleDateFormat baseDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static SimpleDateFormat serverDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    public static String getCurrentDate(){
        Date date = new Date();
        return baseDateFormat.format(date);
    }
    /**
     * Получаем время от сервера, в формате dd.MM.yyyy HH:mm, а затем извлекаем только время
     * */
    public static String getTimeFromDate(String dateStr)  {
        try{
            Date date = serverDateFormat.parse(dateStr);
            return timeFormat.format(date);
        } catch (ParseException e){
            return "time parse error";
        }
    }
    public static int getCurrentOrdersCount(List<Order> orderList) {
        try {
            int currentOrders = 0;
            Calendar currentCalendar = Calendar.getInstance();
            for (Order order : orderList) {
                Calendar orderCalendar = Calendar.getInstance();
                orderCalendar.setTime(serverDateFormat.parse(order.getFullDate()));
                if(pureCompareCalendars(currentCalendar, orderCalendar) == 0) currentOrders++;
            }
            return currentOrders;
        } catch (Exception e){
            return 0;
        }
    }
    private static int pureCompareCalendars(Calendar c1, Calendar c2){
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        return c1.compareTo(c2);
    }
    public static boolean isDateToday(String dateStr) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseDateFormat.parse(dateStr));
        Calendar currentCalendar = Calendar.getInstance();
        if(pureCompareCalendars(calendar, currentCalendar) == 0) return true;
        else return false;
    }
}
