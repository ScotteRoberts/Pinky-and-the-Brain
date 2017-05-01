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
import static edu.cecs343.labcode.SystemGUI.SEARCH;
import static edu.cecs343.labcode.SystemGUI.apptBox;
import static edu.cecs343.labcode.SystemGUI.apptDateTimeDoc;
import static edu.cecs343.labcode.SystemGUI.changeEditAppointment;
import static edu.cecs343.labcode.SystemGUI.clearPanel;
import static edu.cecs343.labcode.SystemGUI.eFirstName;
import static edu.cecs343.labcode.SystemGUI.eLastName;
import static edu.cecs343.labcode.SystemGUI.ePhone;
import static edu.cecs343.labcode.SystemGUI.editAppt;
import static edu.cecs343.labcode.SystemGUI.mainLabel;
import static edu.cecs343.labcode.SystemGUI.sysSQL;
import static edu.cecs343.labcode.SystemGUI.updatePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeeklyView {
    
    private int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String [] months = {"1", "2", "3", "4", "5", "6", "7", "8",
        "9", "10", "11", "12"};
    private String daysOfWeek[] = {"Sunday", "Monday", "Tuesday", "Wednesday", 
        "Thrusday", "Firday", "Saturday"};
    private final String [] years = {"2017", "2018", "2019"};
    private JComboBox month = new JComboBox(months);
    private JComboBox day = new JComboBox();
    //private JComboBox day;
    private JComboBox year = new JComboBox(years);
    
    private CalendarTest ct = CalendarTest.getInstance();
    
    String oppTime[] = {"8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00",
            "11:30", "12:00", "12:30", "1:00", "1:30", "2:00", "2:30", "3:00", "3:30",
            "4:00", "4:30", "5:00", "5:30", "6:00"}; 
    
    private JPanel d;
    
    private boolean setDoc = false;
    private boolean editSetDoc = false;
    
    private Employee apptDoc;
    
    private String apptDate = null;
    private String apptTime = null;
    
    private ArrayList<Appointment> editAppt = null;
    
    private Employee editApptDoc; 
    private Patient editPatient;
    
    private String editApptDate, editApptTime;
    
    WeeklyView(){
        
    }
    
    public JPanel weekView(){
        // Sets up GUI to create an appointment
        JPanel mainPanel = new JPanel();
        JPanel midPanel = new JPanel(new GridLayout(1,9));
        JPanel selectPanel[] = new JPanel[7];
        SystemGUI.clearPanel();

//        String doc[];
//        ArrayList<Employee> e = new ArrayList<Employee>();
//        e = SystemGUI.sysSQL.getAllDoctors();
//        
        String dates[] = ct.getDaysOfWeek();
//        
//        if(!e.isEmpty()){
//            doc = new String[e.size()];
//            int i = 0;
//            while(i < e.size()){
//                doc[i] = e.get(i).lastName + ", " + e.get(i).firstName;
//                i++;
//            }
//                
//        }
//        else{
//            doc = new String[1];
//            doc[0] = "None";
//        }
        
//        String temp[] = new String[doc.length + 1];
//        temp[0] = "Select";
//        for(int i = 1; i < temp.length; i++){
//            temp[i] = doc[i - 1];
//        }
//        doc = temp;
        
//        SystemGUI.docBox = new JComboBox(doc);
//        SystemGUI.docBox.addActionListener(new SelectedDoctor());
        SystemGUI.mainLabel =  new JLabel("");
        for(int i = 0;i < 9; i++){
            JPanel tempPanel = new JPanel(new GridLayout(oppTime.length + 5, 1));
            if(i == 0){
                for(int j = 0; j < oppTime.length; j++){
                    if(j == oppTime.length/2){
                        JButton t = new JButton("<<");
                        tempPanel.add(t);
                    }
                    else{
                        tempPanel.add(new JLabel(""));
                    }
                }
                
            }
            else if(i == 8){
                for(int j = 0; j < oppTime.length; j++){
                    if(j == oppTime.length/2){
                        JButton t = new JButton(">>");
                        tempPanel.add(t);
                    }
                    else{
                        tempPanel.add(new JLabel(""));
                    }
                }
                
            }
            else{
//                if(i == 6){
//                        
//                    tempPanel.add(SystemGUI.docBox);
//                        
//                }
//                else if(i == 5){
//                    if(setDoc){
//                        tempPanel.add(new JLabel(apptDoc.lastName + ", "
//                        + apptDoc.firstName));
//                    }
//                    else{
//                        tempPanel.add(new JLabel(""));
//                    }
//                    
//                }
//                else{
//                        tempPanel.add(new JLabel(""));
//                }
                tempPanel.add(new JLabel(""));
                //if(setDoc){
                    
                    tempPanel.add(new JLabel(daysOfWeek[i - 1]));
                    tempPanel.add(new JLabel(dates[i - 1]));
                    for(int j = 0; j < oppTime.length; j++){
                        //SystemGUI.sysSQL.testAppt(apptDoc.eID, dates[i], oppTime[j]);
                        //apptDoc.testSchudle(dates[i], oppTime[j]);
                        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
                        appointments = SystemGUI.sysSQL.lookUpAppointment(dates[i - 1], oppTime[j]);
                        if(!appointments.isEmpty())
                        {
                            JButton t = new JButton(oppTime[j]);
                            //int temp = j * i;
                            JLabel tl =  new JLabel(dates[i - 1]);
                            t.addActionListener(new ACtemp());
                            tl.setVisible(false);
                            t.add(tl);
                            tempPanel.add(t);
                        }
                        else{
                           tempPanel.add(new JLabel(oppTime[j])); 
                            
                        }

                    //}
                }
//                else{
//                    tempPanel.add(new JLabel(daysOfWeek[i - 1]));
//                    tempPanel.add(new JLabel(dates[i - 1]));
//                    for(int j = 0; j < oppTime.length; j++){
////                        JButton t = new JButton(oppTime[j]);
////                        //int temp = j * i;
////                        JLabel tl =  new JLabel(dates[i - 1]);
////                        t.addActionListener(new ACtemp());
////                        tl.setVisible(false);
////                        t.add(tl);
//                        tempPanel.add(new JLabel(oppTime[j]));
//
//                    }
//
//                }
                if(i == 3){
                tempPanel.add(SystemGUI.mainLabel);
                }
                else{
                tempPanel.add(new JLabel(""));
                }
            }
        midPanel.add(tempPanel);
        
        
        }
        
        mainPanel.add(midPanel);
        
        
        setDoc = false;
        return mainPanel;
    }
    
    class ACtemp implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            Object o = e.getSource();
//            JButton b;
//            b = (JButton) o;
//            Object test = b.getComponent(0);
//            JLabel test1 = (JLabel)test;
//            
//            //Object test1 = b.getComponent(1);
//            SystemGUI.mainLabel.setText(test1.getText());
//            apptTime = b.getText();
//            apptDate = test1.getText();
//            CalendarHub.clearMasterPanel();
//            CalendarHub.masterPanel.add(enterPatient());
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    class SelectedDoctor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            Object o = SystemGUI.docBox.getItemAt(SystemGUI.docBox.getSelectedIndex());
//            String preDoc = o.toString();
//            String lastName = preDoc.substring(0, preDoc.indexOf(','));
//            String firstName  = preDoc.substring(preDoc.indexOf(',')  + 2, preDoc.length());
//            
//            String dEID = SystemGUI.sysSQL.testDoctorName(firstName, lastName);
//            EmployeeSchdule tempSch = SystemGUI.sysSQL.getEmployeeSchdule(dEID);
//            apptDoc = SystemGUI.sysSQL.lookUpEmployee(dEID);
//            apptDoc.setEID(dEID);
//            apptDoc.setEmployeeSchdule(tempSch);
//            
//            setDoc = true;
//            
//            CalendarHub.clearMasterPanel();
//            //CalendarHub.masterPanel.add(createAppt());
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    
    
    
    
}
