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
import java.util.ArrayList;
public class CECS343 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // TODO code application logic here
        //SystemSQL p = new SystemSQL();
        /*
        This tests createNurse SQL method. This also tests createEmployee,
        createNonAdminEmployee, createMedicalEmployee
        Nurse n = new Nurse("SexyNurse", "Sarah", "Lauren", "1234", true, "RN", 
                "50000" ,"CSULB", "12345");
        p.createNurse(n);
        */
        
        /*
        This tests createDoctor SQL method. This also tests createEmployee,
        createNonAdminEmployee, createMedicalEmployee
        Doctor d = new Doctor("DumbDoctor2", "Jack", "Ron", "1234", true, "MD", 
                "500000" ,"UCI", "acb3e2");
        p.createDoctor(d);
        System.out.println(d.eID);
        */
        
        
        /*
        AdminEmployee a = new AdminEmployee("RobotMan", "Rob", "Stark", "12345",
            "1234", "King of the north");
        p.createAdmin(a);
        */
        
        /*
        NonMedicalEmployee nme = new NonMedicalEmployee("CoolDude", "Sammy", "Winn", "2345", "12", "Clerk");
        p.createNonMedical(nme);
        System.out.println(nme.eID);
        */
        
        /*
        Employee c = p.getEmployeeUserName("SAMMY", "12345");
        */
        
        /*
        for(int i = 100; i < 111; i++){
            //p.testEmployeeType(Integer.toString(i));
            System.out.println(p.testEmployeeType(Integer.toString(i)));
        }
        */
        
        SystemGUI g = new SystemGUI();
        //g.makeAdminAccount();
        //g.createNurse();
        //g.createDoctor();
        //g.createNonMedicalEmployee();
        //g.login();
        //while(true);
        //System.out.println(g.sysSQL.testDoctorName("Josh", "Carton"));
        //g.createDoctor();
        //int temp = g.sysSQL.generateApptID();
        /*
        String preDoc = "Ben, Carson";
        String lastName = preDoc.substring(0, preDoc.indexOf(','));
        String firstName  = preDoc.substring(preDoc.indexOf(',')  + 2, preDoc.length());
        
        System.out.println(lastName + " " + firstName);
        */
        //g.editAppointment();
        /*
        String test = "Date: 01/10/1990 Time: 6:00 Doctor: Ben Carson";
        int n1 = test.indexOf("Time");
        int n2 = test.indexOf(" ");
        String date = test.substring(n2 + 1, n1 - 1);
        System.out.println(date);
        test = test.replaceFirst(" ", "");
        //System.out.println(test);
        test = test.replaceFirst(" ", "");
        //System.out.println(test);
        
        System.out.println(" ");
        n1 = test.indexOf("Doctor");
        n2 = test.indexOf(" ");
        String time = test.substring(n2 + 1, n1 - 1);
        System.out.println(time);
        test = test.replaceFirst(" ", "");
        //System.out.println(test);
        test = test.replaceFirst(" ", "");
        //System.out.println(test);
        
        System.out.println(" ");
        //n1 = test.indexOf("Doctor");
        n2 = test.indexOf(" ");
        String d = test.substring(n2 + 1, test.length());
        System.out.println(d);
        n1 = d.indexOf(" ");
        String fn = d.substring(0, n1);
        String ln = d.substring(n1 + 1, d.length());
        System.out.println(ln);
        */
        
        //g.updatePatient();
        //g.editUserName();
        
        
        
        
        
    }
    
}
