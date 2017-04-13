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
public class MedicalEmployee extends NonAdminEmployee {
    public String degree;
    public String salary;
    String college;
    
    MedicalEmployee(String userNameN, String firstNameN, String lastNameN, 
            String phoneN, boolean fullTimeN, String degreeN, String salaryN,
            String collegeN){
        super(userNameN, firstNameN, lastNameN, phoneN, fullTimeN);
        degree = degreeN;
        salary = salaryN;
        college = collegeN;
    }
}
