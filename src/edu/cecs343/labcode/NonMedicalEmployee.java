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
public class NonMedicalEmployee extends NonAdminEmployee{
   public String hourlyWage;
   public String eTitle;
   
   NonMedicalEmployee(String userNameN, String firstNameN, String lastNameN, String phoneN,
           String hourlyWageN, String eTitleN){
       super(userNameN, firstNameN, lastNameN, phoneN, true);
       hourlyWage = hourlyWageN;
       eTitle = eTitleN;
   }
}