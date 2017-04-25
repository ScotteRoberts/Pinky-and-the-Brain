/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecs343.labcode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmedarbi
 */
public class Employee {
    public String eID;
    public String userName;
    private int password = 0;
    public String firstName;
    public  String lastName;
    public String phone;
    
    public EmployeeSchdule schdule;
    
    
    Employee(String userNameN, String firstNameN, String lastNameN, String phoneN){
        userName = userNameN;
        firstName = firstNameN;
        lastName = lastNameN;
        phone = phoneN;
    }
    
    public void setEID(String newEID){
        eID = newEID;
    }
    
    public void setPassword(int passwordN){
        password = passwordN;
    }
    
    
    public int getPassword(){
        return password;
    }
    
    public void setEmployeeSchdule(EmployeeSchdule s){
        schdule = s; 
    }
    
    public boolean testSchudle(String date, String t){
        Calendar c = Calendar.getInstance();
        try {
            //new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
            String tempD = date;
            int index = tempD.indexOf("/");
            String m = tempD.substring(0, index);
            tempD = tempD.substring(index + 1, tempD.length());
            
            index = tempD.indexOf("/");
            String d = tempD.substring(0, index);
            
            tempD = tempD.substring(index + 1, tempD.length());
            
            tempD = d + "/" + m + "/" + tempD;
            
            
            c.setTime(new SimpleDateFormat("dd/M/yyyy").parse(tempD));
        } catch (ParseException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int oppTime = 0;
        int dayStartTime = 1;
        int dayEndTime = -1;
        String time = t + ":00";
        switch(dayOfWeek){
            case 2:
                oppTime = timeToMin(time);
                dayStartTime = timeToMin(schdule.mondayStart);
                dayEndTime = timeToMin(schdule.mondayEnd);
                break;
                // monday
                //return 1;
            case 3:
                oppTime = timeToMin(time);
                dayStartTime = timeToMin(schdule.tuesdayStart);
                dayEndTime = timeToMin(schdule.tuesdayEnd);
                break;
                // tuesday
                //return 2;
            case 4:
                oppTime = timeToMin(time);
                oppTime = timeToMin(time);
                dayStartTime = timeToMin(schdule.wednesdayStart);
                dayEndTime = timeToMin(schdule.wednesdayEnd);
                break;
                // wednesday
                //return 3;
            case 5:
                oppTime = timeToMin(time);
                oppTime = timeToMin(time);
                dayStartTime = timeToMin(schdule.thrusdayStart);
                dayEndTime = timeToMin(schdule.thrusdayEnd);
                break;
                // thrusday
                //return 4;
            case 6:
                oppTime = timeToMin(time);
                oppTime = timeToMin(time);
                dayStartTime = timeToMin(schdule.fridayStart);
                dayEndTime = timeToMin(schdule.fridayEnd);
                break;
                // friday
                //return 5;
            default:
                //return false;
                //return -1;       
        }
        if((oppTime >= dayStartTime) && (oppTime <= dayEndTime)){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    private int timeToMin(String time){
        String temp = time;
        int index = temp.indexOf(":");
        int hoursToMins = Integer.parseInt(temp.substring(0, index));
        temp = temp.substring(index + 1, temp.length());
        index = temp.indexOf(":");
        int mins = Integer.parseInt(temp.substring(0, index));
        if(hoursToMins < 8){
            hoursToMins += 12;
        }
        return (hoursToMins * 60) + mins;
    }
    /*
    private int dayOfTheWeek(String date) throws ParseException{
        Calendar c = Calendar.getInstance();
        //new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        c.setTime(new SimpleDateFormat("dd/M/yyyy").parse(date));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch(dayOfWeek){
            case 2:
                // monday
                return 1;
            case 3:
                // tuesday
                return 2;
            case 4:
                // wednesday
                return 3;
            case 5:
                // thrusday
                return 4;
            case 6:
                // friday
                return 5;
            default:
                return -1;       
        }
        
    }
    */
    
    
}