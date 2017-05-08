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
public class AppointmentTester {
    private SystemSQL sq = new SystemSQL();
    private CalendarTest ct =  CalendarTest.getInstance();
    private CheckSum cs = new CheckSum();
    
    
    
    class CheckSum{
        ArrayList<Appointment> current;
        ArrayList<Appointment> tomorrow;
        ArrayList<Appointment> newList;
        CheckSum(){
            intCurrent();
        }
        
        private void intCurrent(){
            //String sTemp = ct.day + "/" + (ct.month + 1) + "/" + ct.year;
            //String sTemp = (ct.month + 1)  + "/" + ct.day + "/" + ct.year;
            current = sq.allAppointmentByDate(ct.emails.getTodaysDate());
            if(!current.isEmpty()){
                sendMessage(current);
            }
            tomorrow = sq.allAppointmentByDate(ct.emails.getTomorrowsDate());
            if(!tomorrow.isEmpty()){
                sendMessage(tomorrow);
            }
        }
        
        public void printAppts(){
            for(int i = 0; i < current.size(); i++){
                System.out.println(current.get(i).aID);
            }
        }
        
        public void updateAppt(){
            newList = sq.allAppointmentByDate(ct.emails.getTodaysDate());
            testUpdate();
        }
        
        private void testUpdate(){
            ArrayList<Appointment> newMail = new ArrayList<Appointment>();
            if((current.size() == newList.size()) || 
                    (current.size() < newList.size())){
                // Update situation
                for(int i = 0; i < newList.size(); i++){
                    boolean found = false;
                    for(int j = 0; j < current.size(); j++){
                        if(newList.get(i).aID.equals(current.get(j).aID)){
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        newMail.add(newList.get(i));
                    }
                }
            }
            if(!newMail.isEmpty()){
                sendMessage(newMail);
            }
            current = newList;
            
            newList =  sq.allAppointmentByDate(ct.emails.getTomorrowsDate());
            newMail = new ArrayList<Appointment>();
            
            if((tomorrow.size() == newList.size()) || 
                    (tomorrow.size() < newList.size())){
                // Update situation
                for(int i = 0; i < newList.size(); i++){
                    boolean found = false;
                    for(int j = 0; j < tomorrow.size(); j++){
                        if(newList.get(i).aID.equals(tomorrow.get(j).aID)){
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        newMail.add(newList.get(i));
                    }
                }
            }
            if(!newMail.isEmpty()){
                sendMessage(newMail);
            }
            
            tomorrow = newList;
            
            
        }
        
        
        private void sendMessage(ArrayList<Appointment> a){
            for(int i = 0; i < a.size(); i++){
                Patient p = SystemGUI.sysSQL.lookUpPatient(a.get(i).pID);
                Employee e = SystemGUI.sysSQL.lookUpEmployee(a.get(i).doctorID);
                String email = p.email;
                String message = p.patientFN + " " + p.patientLN + "\n" +
                        "With doctor: " + e.firstName + " " + e.lastName + "\n" +
                        "At: " + a.get(i).aTime + " Date: " + a.get(i).aDate;
                String subject = "Appointment Reminder";
                SendMail.send(email, subject, message);
            }
            
        }
        
        
    }
    
    Thread t;
    class ThreadTest implements Runnable{
    
    Thread t;
    public void run() {
        
        
       while(true) {
          try {
             // thread to sleep for 1000 milliseconds
             //cs.printAppts();
             //
             cs.updateAppt();
             Thread.sleep(1000);
          } catch (Exception e) {
             System.out.println(e);
          }
       }
    }
    }

    
    
    AppointmentTester(){
      Thread t = new Thread(new ThreadTest());
      // this will call run() function
      t.start();
    }  
    
    
}
