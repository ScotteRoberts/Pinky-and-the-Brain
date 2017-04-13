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
public class Patient {
    
    public String pID, eID, patientFN, patientLN, patientPhone, ins;
    
    Patient(String pIDN, String eIDN, String patientFNN, String patientLNN, 
            String patientPhoneN, String insN){
        pID = pIDN;
        eID = eIDN;
        patientFN = patientFNN;
        patientLN = patientLNN;
        patientPhone = patientPhoneN;
        ins = insN;
    }
    
    public void setPID(String newPID){
        pID = newPID;
    }
    
}

