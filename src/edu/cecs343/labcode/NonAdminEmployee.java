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
public class NonAdminEmployee extends Employee{
    
    public boolean fullTime = true;
    
    NonAdminEmployee(String userNameN, String firstNameN, String lastNameN, 
            String phoneN, boolean fullTimeN){
        super(userNameN, firstNameN, lastNameN, phoneN);
    }
    
}
