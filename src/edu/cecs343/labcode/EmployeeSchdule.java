/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecs343.labcode;

/**
 *
 * @author ahmedarbi
 */
public class EmployeeSchdule {
    public String mondayStart, mondayEnd, tuesdayStart, tuesdayEnd,
            wednesdayStart, wednesdayEnd, thrusdayStart, thrusdayEnd, 
            fridayStart, fridayEnd;
    
    EmployeeSchdule(String eMondayStart, String eMondayEnd, String eTuesdayStart,
            String eTuesdayEnd, String eWednesdayStart, String eWednesdayEnd,
            String eThrusdayStart, String eThrusdayEnd, String eFridayStart, 
            String eFridayEnd){
        mondayStart = eMondayStart;
        mondayEnd = eMondayEnd;
        tuesdayStart = eTuesdayStart;
        tuesdayEnd = eTuesdayEnd;
        wednesdayStart = eWednesdayStart;
        wednesdayEnd = eWednesdayEnd;
        thrusdayStart = eThrusdayStart;
        thrusdayEnd = eThrusdayEnd;
        fridayStart = eFridayStart;
        fridayEnd = eFridayEnd;
    }
    
    
    
}
