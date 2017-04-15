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
    
    
}