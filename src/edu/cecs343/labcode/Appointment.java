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
public class Appointment {
    
    public String  aID, pID, eID, doctorID, aDate, aTime;
    
    Appointment(String aIDN, String patientIDN, String employeeIDN, 
            String doctorIDN, String aDateN, String aTimeN){
        aID = aIDN;
        pID = patientIDN;
        eID = employeeIDN;
        doctorID = doctorIDN;
        aDate = aDateN;
        aTime = aTimeN;
    }
    
}