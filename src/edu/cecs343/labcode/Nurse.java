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
public class Nurse extends MedicalEmployee{
    String licenseNumber;
    
    Nurse(String userNameN, String firstNameN, String lastNameN, 
            String phoneN, boolean fullTimeN, String degreeN, String salaryN,
            String collegeN, String licenseNumberN){
        super(userNameN, firstNameN, lastNameN, phoneN, fullTimeN, degreeN, salaryN, 
                collegeN);
        licenseNumber =licenseNumberN;
    }
    
}