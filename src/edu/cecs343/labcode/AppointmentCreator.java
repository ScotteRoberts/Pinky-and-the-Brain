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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentCreator {
   
    private int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String [] months = {"1", "2", "3", "4", "5", "6", "7", "8",
        "9", "10", "11", "12"};
    private final String [] years = {"2017", "2018", "2019"};
    private JComboBox month = new JComboBox(months);
    private JComboBox day = new JComboBox();
    //private JComboBox day;
    private JComboBox year = new JComboBox(years);
    
    
    private JPanel d;
    
    
    AppointmentCreator(){
        month.addActionListener(new MonthSelection());
        
    }
    
    class MonthSelection implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object o = month.getItemAt(month.getSelectedIndex());
            int currentMonth = Integer.parseInt(o.toString());
            String d[];
            day.removeAllItems();
            switch(currentMonth){
                case 1:
                    d= new String[daysInMonth[0]];
                    for(int i = 0; i < daysInMonth[0]; i++){
                        day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                case 2:
                    d = new String[daysInMonth[1]];
                    for(int i = 0; i < daysInMonth[1]; i++){
                       day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                case 3:
                    d = new String[daysInMonth[2]];
                    for(int i = 0; i < daysInMonth[2]; i++){
                        day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                case 4:
                    d = new String[daysInMonth[3]];
                    for(int i = 0; i < daysInMonth[3]; i++){
                        day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                case 5:
                    d = new String[daysInMonth[4]];
                    for(int i = 0; i < daysInMonth[4]; i++){
                        day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                case 6:
                    d = new String[daysInMonth[5]];
                    for(int i = 0; i < daysInMonth[5]; i++){
                        day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                case 7:
                    d = new String[daysInMonth[6]];
                    for(int i = 0; i < daysInMonth[6]; i++){
                        day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                case 8:
                    d = new String[daysInMonth[7]];
                    for(int i = 0; i < daysInMonth[7]; i++){
                        day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                case 9:
                    d = new String[daysInMonth[8]];
                    for(int i = 0; i < daysInMonth[8]; i++){
                        day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                case 10:
                    d = new String[daysInMonth[10]];
                    for(int i = 0; i < daysInMonth[10]; i++){
                        day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                case 11:
                    d = new String[daysInMonth[10]];
                    for(int i = 0; i < daysInMonth[10]; i++){
                        day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                case 12:
                    d = new String[daysInMonth[11]];
                    for(int i = 0; i < daysInMonth[11]; i++){
                        day.addItem(Integer.toString(i + 1));
                    }
                    day.setVisible(true);
                    year.setVisible(true);
                    break;
                    
                default:
                    break;
     
            }
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

    
    
    public JPanel createAppt(){
        // Sets up GUI to create an appointment
        JPanel mainPanel;
        SystemGUI.clearPanel();

        String doc[];
        ArrayList<Employee> e = new ArrayList<Employee>();
        e = SystemGUI.sysSQL.getAllDoctors();
        
        if(!e.isEmpty()){
            doc = new String[e.size()];
            int i = 0;
            while(i < e.size()){
                doc[i] = e.get(i).lastName + ", " + e.get(i).firstName;
                i++;
            }
                
        }
        else{
            doc = new String[1];
            doc[0] = "None";
        }
        
        String oppTime[] = {"8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00",
            "11:30", "12:00", "12:30", "1:00", "1:30", "2:00", "2:30", "3:00", "3:30",
            "4:00", "4:30", "5:00", "5:30", "6:00"}; 
        
        // Set up mainPanel
        mainPanel = new JPanel(new GridLayout(7,1));
        
        // setup title
        JLabel message = new JLabel("Create new Appointment", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // setup patient info
        // setup first information panel
        JPanel panelInfo1 = new JPanel(new GridLayout(1,6));
        JLabel firstName = new JLabel("Patirnt First Name", JLabel.CENTER);
        JLabel lastName = new JLabel(" Patient Last Name", JLabel.CENTER);
        JLabel phone = new JLabel("Patient Phone", JLabel.CENTER);
        panelInfo1.add(firstName); panelInfo1.add(SystemGUI.eFirstName);
        panelInfo1.add(lastName); panelInfo1.add(SystemGUI.eLastName);
        panelInfo1.add(phone); panelInfo1.add(SystemGUI.ePhone);
        mainPanel.add(panelInfo1);
        
        
        // Doctor and date Pannel
        // setup doctor information
        JPanel dateTimeDoc = new JPanel(new GridLayout(1,4));
        JLabel time = new JLabel("Time", JLabel.CENTER);
        //JLabel date = new JLabel("Date", JLabel.CENTER);
        JLabel dName = new JLabel("Doctors", JLabel.CENTER);
        SystemGUI.timeBox = new JComboBox(oppTime);
        SystemGUI.docBox = new  JComboBox(doc);
        
        
        //dateTimeDoc.add(date); dateTimeDoc.add(SystemGUI.eDate);
        dateTimeDoc.add(time);dateTimeDoc.add(SystemGUI.timeBox);
        dateTimeDoc.add(dName); dateTimeDoc.add(SystemGUI.docBox);
        mainPanel.add(dateTimeDoc);
        
        // Set date
        d = new JPanel(new GridLayout(2, 3));
        JLabel mo = new JLabel("Month");
        JLabel da = new JLabel("Day");
        JLabel ye = new JLabel("Year");
        d.add(mo); d.add(da); d.add(ye);
        d.add(month); d.add(day); d.add(year);
        day.setVisible(false);
        year.setVisible(false);
        
        mainPanel.add(d);
        
        // Setup button
        JButton apptOk = new JButton("Ok");
        apptOk.addActionListener(new AppointmentCreationHandler());
        mainPanel.add(apptOk);
        
        /*
        // Setup back button
        //JButton back = new JButton("Back");
        //back.addActionListener(new BackButton());
        //mainPanel.add(back);
        */
        
        // Setup messange
        SystemGUI.mainLabel = new JLabel("", JLabel.CENTER);
        mainPanel.add(SystemGUI.mainLabel);
        
        // Add to master panel
        SystemGUI.masterPanel.add(mainPanel);
        SystemGUI.updatePanel();
        return mainPanel;
    }
    
    class AppointmentCreationHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object o5 = month.getItemAt(month.getSelectedIndex());
            Object o6 = day.getItemAt(day.getSelectedIndex());
            Object o7 = year.getItemAt(year.getSelectedIndex());
            String appointmentDate = o5.toString() + "/" + o6.toString() + "/" + o7.toString();
            SystemGUI.eDate.setText(appointmentDate);
            if(!SystemGUI.eFirstName.getText().equals("") && !SystemGUI.eLastName.getText().equals("") &&
                    !SystemGUI.ePhone.getText().equals("") && !SystemGUI.eDate.getText().equals("")){
                
                if(SystemGUI.currentEmployee != null){
                    // Test to see if all the parameters are entered
                    // Get doctors name
                    Object o = SystemGUI.docBox.getItemAt(SystemGUI.docBox.getSelectedIndex());
                    String preDoc = o.toString();
                    String lastName = preDoc.substring(0, preDoc.indexOf(','));
                    String firstName  = preDoc.substring(preDoc.indexOf(',')  + 2, preDoc.length());
                    // Gets time for the comboBox
                    Object o2 = SystemGUI.timeBox.getItemAt(SystemGUI.timeBox.getSelectedIndex());
                    String t = o2.toString() + ":00";
                    
                    String dEID = SystemGUI.sysSQL.testDoctorName(firstName, lastName);
                    EmployeeSchdule tempSch = SystemGUI.sysSQL.getEmployeeSchdule(dEID);
                    
                    //String pID =  
                    if(dEID != null){
                        if(SystemGUI.sysSQL.testAppt(dEID, SystemGUI.eDate.getText(), t)){
                            if(tempSch != null){
                                // If appointment is avalible the code moves forward
                                ArrayList<Patient> tempP = SystemGUI.sysSQL.lookUpPatient(SystemGUI.eLastName.getText(), 
                                        SystemGUI.eFirstName.getText(), SystemGUI.ePhone.getText());
                                int dow = -1;
                                try {
                                    dow = dayOfTheWeek(SystemGUI.eDate.getText());
                                } catch (ParseException ex) {
                                    Logger.getLogger(AppointmentCreator.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                if(tempSch.testDay(dow) != 0){
                                    if(!tempP.isEmpty()){
                                        // If Patient exists the appointment is made
                                        Appointment tempAppt = new Appointment("NA", tempP.get(0).pID,
                                                SystemGUI.currentEmployee.eID, dEID, SystemGUI.eDate.getText(),
                                                t);
                                        SystemGUI.sysSQL.makeNewAppointment(tempAppt);
                                        SystemGUI.mainLabel.setText("Created");
                                    }
                                    else{
                                        SystemGUI.mainLabel.setText("Patient not found");
                                    }
                                }
                                else{
                                    SystemGUI.mainLabel.setText("Doctor not avalible");
                                }
                            }
                            else{
                                SystemGUI.mainLabel.setText("The employee does not have a schdule");
                            }
                        }
                        else{
                            SystemGUI.mainLabel.setText("Doctor already has appintment at the"
                                    + " time");
                        }
                    }
                    else{
                        SystemGUI.mainLabel.setText("Doctor does not exist");
                    }
                }
                else{
                    SystemGUI.mainLabel.setText("No one is logged in the system");
                }
            }
            else{
                SystemGUI.mainLabel.setText("Missing paramaters");
            }
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    private int dayOfTheWeek(String date) throws ParseException{
        Calendar c = Calendar.getInstance();
        //new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        c.setTime(new SimpleDateFormat("dd/M/yyyy").parse(date));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch(dayOfWeek){
            case 2:
                // monday
                return 1;
            case 3:
                // tuesday
                return 2;
            case 4:
                // wednesday
                return 3;
            case 5:
                // thrusday
                return 4;
            case 6:
                // friday
                return 5;
            default:
                return -1;       
        }
    }
    
    
}
    

