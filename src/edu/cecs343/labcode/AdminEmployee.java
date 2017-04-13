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
public class AdminEmployee extends Employee {
    
   // private String test = "It works!!";
    public String adminDate;
    public String reason;
    
    AdminEmployee(String userNameN, String firstNameN, String lastNameN, String phoneN,
            String adminDateN, String reasonN){
        super(userNameN, firstNameN, lastNameN, phoneN);
        adminDate = adminDateN;
        reason = reasonN;
    }
    
}
