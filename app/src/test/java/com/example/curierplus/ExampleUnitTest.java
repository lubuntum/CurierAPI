package com.example.curierplus;

import org.junit.Test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void currentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = dateFormat.format(new Date());
        assertEquals("15.04.2024", currentDate);
    }
    @Test
    public void serverDate() throws ParseException {
        String serverDateTime = "15.04.2024 19:34";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = dateFormat.parse(serverDateTime);
        Calendar serverDateCalendar = Calendar.getInstance();
        serverDateCalendar.setTime(date);
        serverDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        serverDateCalendar.set(Calendar.MINUTE, 0);
        serverDateCalendar.set(Calendar.SECOND, 0);
        serverDateCalendar.set(Calendar.MILLISECOND, 0);

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);

        assertEquals(0, serverDateCalendar.compareTo(currentCalendar));

    }
    //
}