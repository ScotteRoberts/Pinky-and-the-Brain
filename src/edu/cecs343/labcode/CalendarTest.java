/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecs343.labcode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.lang.*;
/**
 * Calendar class designed for the text-based prototype in Software Engineering
 * 341 group project
 * @author ScottRoberts
 */
public class CalendarTest{
    
    CalendarData calData;
    
    class CalendarData
    {
        int year;
        int month;
        int day;
        int weekYear;
        int dayOfWeek;
        int weekOfMonth;
        public CalendarData(int d, int m, int y, int wY, int dOW, int wOM)
        {
           day = d;
           month = m;
           year = y;
           weekYear = wY;
           dayOfWeek = dOW;
           weekOfMonth = wOM;
        }
        
        public String incrementDay()
        {
            if (dayOfWeek+1 <= 7 && day+1 <= daysInMonth[month])
                day++;
            else if (day+1 <= daysInMonth[month])
            {
                day++;
                weekYear++;
            }
            else
            {
                day = 1;
                month++;
                weekOfMonth = 1;
            }

            if(dayOfWeek == 7)
            {
                dayOfWeek = 1;
                weekOfMonth++;
            }  
            else
                dayOfWeek++;

            return getDay();
        }

        public String decrementDay()
        {
            if (day-1 != 0 && dayOfWeek - 1 != 0)
                day--;
            else if (day - 1 != 0)
            {
                day--;
                weekYear--;
            }
            else
            {
                month--;
                day = daysInMonth[month];
                //reset weekMonth to some value
                Calendar tempCal = Calendar.getInstance();
                tempCal.set(year, month, day);
                weekOfMonth = tempCal.get(Calendar.WEEK_OF_MONTH);
            }


            if (dayOfWeek == 1)
            {
                dayOfWeek = 7;
                weekOfMonth--;
            }
            else
                dayOfWeek--;

            return getDay();

        }
        public String getDay()
        {
            return "" + (month + 1) + "/" + day + "/" + year;
        }
        public String getDay(int firstDay)
        {
            return "" + (month + 1) + "/" + firstDay + "/" + year;
        }
        
    }
    
    
    //We have to worry about updating the time after
    //The program stays open. We don't want to rerun the
    //program to constantly keep time consistant.
    Calendar cal;
    
    private static CalendarTest instance = null;
    int[] hoursInDay = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    int[] minuteSegments = {00, 30, 60};
    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
    
    int year;
    int month;
    int day;
    int hour;
    int minute;
    int second;
    int weekYear;
    int dayOfWeek;
    int weekOfMonth;
    
    private CalendarTest()
    {
      cal = Calendar.getInstance();
      year = cal.get(Calendar.YEAR);
      month = cal.get(Calendar.MONTH);      // 0 to 11
      day = cal.get(Calendar.DAY_OF_MONTH);
      hour = cal.get(Calendar.HOUR_OF_DAY);
      minute = cal.get(Calendar.MINUTE);
      second = cal.get(Calendar.SECOND);
      weekYear = cal.get(Calendar.WEEK_OF_YEAR);
      dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
      weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
      
    }
    
    public static CalendarTest getInstance()
    {
        if (null == instance)
        {
            instance =  new CalendarTest();
        }
        return instance;
    }
    
    public String getDay()
    {
        return "" + (month + 1) + "/" + day + "/" + year;
    }
    public String getDay(int firstDay)
    {
        return "" + (month + 1) + "/" + firstDay + "/" + year;
    }
    
    public String getTodaysDate(){
        return (month + 1)  + "/" + day + "/" + year;
    }
    
    public String[] getDaysOfWeek()
    {
        String[] daysOfWeek = new String[7];
        CalendarData dataBus = new CalendarData(day, month, year, weekYear, dayOfWeek, weekOfMonth);
        for (int i = dayOfWeek; i >= 1; i--)
        {
            dataBus.decrementDay();
        }
        for (int i = 0; i < 7; i++)
        {
            daysOfWeek[i] = dataBus.incrementDay();
        }
        return daysOfWeek;
    }
    
    public String getWeekRange()
    {
        String weekStart = getFirstDayOfWeek();
        //Change this immediately
        int weekFinish = 1;
        return "" + (month + 1) + "/" + weekStart + " - " + (month + 1) + "/" + weekFinish;
    }
    
    public String getFirstDayOfWeek()
    {
        int firstDay;
        if (day > dayOfWeek)
            firstDay = day - dayOfWeek + 1;
        //This needs fixing
        else
        {
            month--;
            firstDay = daysInMonth[month] - (dayOfWeek - day - 1);
        }
        
        return getDay(firstDay);
    }
    
    public String incrementDay()
    {
        if (dayOfWeek+1 <= 7 && day+1 <= daysInMonth[month])
            day++;
        else if (day+1 <= daysInMonth[month])
        {
            day++;
            weekYear++;
        }
        else
        {
            day = 1;
            month++;
            weekOfMonth = 1;
        }

        if(dayOfWeek == 7)
        {
            dayOfWeek = 1;
            weekOfMonth++;
        }  
        else
            dayOfWeek++;
        
        return getDay();
    }
    
    public String decrementDay()
    {
        if (day-1 != 0 && dayOfWeek - 1 != 0)
            day--;
        else if (day - 1 != 0)
        {
            day--;
            weekYear--;
        }
        else
        {
            month--;
            day = daysInMonth[month];
            //reset weekMonth to some value
            Calendar tempCal = Calendar.getInstance();
            tempCal.set(year, month, day);
            weekOfMonth = tempCal.get(Calendar.WEEK_OF_MONTH);
        }
        
        
        if (dayOfWeek == 1)
        {
            dayOfWeek = 7;
            weekOfMonth--;
        }
        else
            dayOfWeek--;
        
        return getDay();
        
    }
    
    
    public String incrementWeekRange()
    {
        if (day+7 <= daysInMonth[month])
        {
            day+=7;
            weekYear++;
        }
        else
        {
            day = (day+7) - daysInMonth[month];
            month++;
            weekYear++;
            //weekOfMonth = 1;
            
        }
        
        return getWeekRange(); 
    }
    
    public String decrementWeekRange()
    {
        if (day-7 > 0)
        {
            day-=7;
            weekYear--;
            //weekOfMonth--;
            
        }
        else
        {
            month--;
            day = (day-7) + daysInMonth[month];
            weekYear--;
            //weekOfMonth = ;
            
        }
        return getWeekRange();
    }
    
    public String getMonth()
    {
        return (month + 1) + "/" + year;
    }
    
    public String incrementMonth()
    {
        if(month+1 < 11)
        {
            month++;
        }
        else
        {
            month = 0;
            year++;
        }
        return getMonth();
    }
    
    public String decrementMonth()
    {
        if (month - 1 >= 0)
        {
            month--;
        }
        else
        {
            month = 11;
            year--;
        }
        return getMonth();
    }
    
    
    
    public void displayDay()
    {
        
        System.out.println("\nDay View:\n");
        System.out.printf("Today is %4d/%02d/%02d %02d:%02d:%02d\n\n",  // Pad with zero
          year, month+1, day, hour, minute, second);
        for (int i = 0; i < hoursInDay.length; i++)
        {
            for (int j = 0; j < minuteSegments.length - 1; j++)
            {
                //Replace with a query for an appointment (patient, Doctor, Hour, minute) 
                System.out.printf("%d:%02d\tSearch with Query\n", hoursInDay[i], minuteSegments[j]);
                if (hour == hoursInDay[i] && 
                        minute >= minuteSegments[j] &&
                        minute < minuteSegments [j+1])
                    System.out.printf("-----------------------------"
                            + "------------------------- CurrentTime: %02d:%02d\n", hour, minute);
            }
            
        }
        
    }
    
    public void displayWeek()
    {
        System.out.println("\nWeek View:\n");
        
        
        //Maybe try and roll back from a specific day of the week.
        //We would need to know the day and then provide 7 different cases
        //for the amount of days to roll back and roll forward.
        System.out.println("\tSun\tMon\tTues\tWed\tThur\tFri\tSat\n");
        for (int i = 0; i < hoursInDay.length; i++)
        {
            for (int j = 0; j < minuteSegments.length - 1; j++)
            {
                //Replace with a query for an appointment (patient, Doctor, day, Hour, minute)
                System.out.printf("%d:%02d\tQuery\tQuery\tQuery\tQuery\tQuery\tQuery\tQuery\n", 
                        hoursInDay[i], minuteSegments[j]);
                if (hour == hoursInDay[i] && minute > minuteSegments[j] && minute <= minuteSegments [j+1])
                    System.out.printf("-----------------------------"
                            + "------------------------------- CurrentTime: %02d:%02d\n", hour, minute);
            }
            
        }
    }
    
    //Not sure how this will affect us
    public void setMonthToStart()
    {
        cal.clear();    //Clear the calendar
        cal.set(year, month, 1); //Set calendar to first day of the month
    }
    
    public void setCalendarToNormal()
    {
        cal.clear();
        cal.set(year, month, day);
    }
    
    public void displayMonth()
    {
        setMonthToStart();
        int tempDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);  //Get day of Week for new calendar
        
        System.out.println("\nMonth View:\n");
        //Print the month heading
        System.out.println("S\tM\tTu\tW\tTh\tF\tS");
        //Create space for the first day of the month on the proper day of week
        for (int i = 1; i < tempDayOfWeek; i++)
        {
            System.out.print("\t");
        }
        //Print all days of the month
        for (int i = 1; i <= daysInMonth[month]; i++) 
        {
            System.out.printf("%02d\t", i);
           
            if (((i + tempDayOfWeek - 1) % 7 == 0)) 
                System.out.println(); 
        }
        System.out.println();
        
        /*
        System.out.println("Month View");
        System.out.println("   " + month + " " + year);
        System.out.println("S\tM\tTu\tW\tTh\tF\tS");
        Math.abs(day - dayOfWeek);
        
        // print the calendar
        for (int i = 1; i < dayOfWeek; i++)
            System.out.print("\t");
        for (int i = 1; i <= daysInMonth[month]; i++) 
        {
            System.out.printf("%02d\t", i);
            if (((i + day) % 7 == 0)) 
                System.out.println();
        }
        */
    }
    
    
    
    public static void main (String[] args)
    {
        
        CalendarTest calTest = new CalendarTest();
        /*
        for (int i = 0; i < 50; i++)
        {
            System.out.println("Date: " + calTest.getDay());
            System.out.println("DayOfWeek: " + calTest.dayOfWeek);
            System.out.println("WeekYear: " + calTest.weekYear);
            System.out.println("WeekMonth: " + calTest.weekOfMonth);
            calTest.incrementDay();
        }   
        System.out.println("Break ================================\n\n\n\n");
        for (int i = 0; i < 50; i++)
        {
            System.out.println("Date: " + calTest.getDay());
            System.out.println("DayOfWeek: " + calTest.dayOfWeek);
            System.out.println("WeekYear: " + calTest.weekYear);
            System.out.println("WeekMonth: " + calTest.weekOfMonth);
            calTest.decrementDay();
        }
*/
        System.out.println("Break ================================\n\n\n\n");
        for (int i = 0; i < 50; i++)
        {
            System.out.println("Date: " + calTest.getDay());
            String []tempArr = calTest.getDaysOfWeek();
            for (int j = 0; j < 7; j++)
            {
                System.out.println("\t" + tempArr[j]);
            }
            System.out.println("DayOfWeek: " + calTest.dayOfWeek);
            System.out.println("WeekYear: " + calTest.weekYear);
            System.out.println("WeekMonth: " + calTest.weekOfMonth);
            calTest.incrementWeekRange();
        }
        System.out.println("Break ================================\n\n\n\n");
        for (int i = 0; i < 50; i++)
        {
            System.out.println("Date: " + calTest.getDay());
            System.out.println("DayOfWeek: " + calTest.dayOfWeek);
            System.out.println("WeekYear: " + calTest.weekYear);
            System.out.println("WeekMonth: " + calTest.weekOfMonth);
            calTest.decrementWeekRange();
        }
        System.out.println("Break ================================\n\n\n\n");
        
        
        calTest.displayDay();
        calTest.displayWeek();
        calTest.displayMonth();
        
    }
}