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

public class AppointmentCreator {
   
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
    
    AppointmentCreator(){
        //month.addActionListener(new MonthSelection());
        
    }
    
    
    JPanel createAppt(){
        // Sets up GUI to create an appointment
        JPanel mainPanel = new JPanel();
        JPanel midPanel = new JPanel(new GridLayout(1,9));
        JPanel selectPanel[] = new JPanel[7];
        SystemGUI.clearPanel();

        String doc[];
        ArrayList<Employee> e = new ArrayList<Employee>();
        e = SystemGUI.sysSQL.getAllDoctors();
        
        String dates[] = ct.getDaysOfWeek();
        
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
        
        String temp[] = new String[doc.length + 1];
        temp[0] = "Select";
        for(int i = 1; i < temp.length; i++){
            temp[i] = doc[i - 1];
        }
        doc = temp;
        
        SystemGUI.docBox = new JComboBox(doc);
        SystemGUI.docBox.addActionListener(new SelectedDoctor());
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
                if(i == 6){
                        
                    tempPanel.add(SystemGUI.docBox);
                        
                }
                else if(i == 5){
                    if(setDoc){
                        tempPanel.add(new JLabel(apptDoc.lastName + ", "
                        + apptDoc.firstName));
                    }
                    else{
                        tempPanel.add(new JLabel(""));
                    }
                    
                }
                else{
                        tempPanel.add(new JLabel(""));
                }
                if(setDoc){
                    
                    tempPanel.add(new JLabel(daysOfWeek[i - 1]));
                    tempPanel.add(new JLabel(dates[i - 1]));
                    for(int j = 0; j < oppTime.length; j++){
                        //SystemGUI.sysSQL.testAppt(apptDoc.eID, dates[i], oppTime[j]);
                        //apptDoc.testSchudle(dates[i], oppTime[j]);
                        if(apptDoc.testSchudle(dates[i - 1], oppTime[j])
                                && SystemGUI.sysSQL.testAppt(apptDoc.eID, dates[i - 1], oppTime[j]))
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

                    }
                }
                else{
                    tempPanel.add(new JLabel(daysOfWeek[i - 1]));
                    tempPanel.add(new JLabel(dates[i - 1]));
                    for(int j = 0; j < oppTime.length; j++){
//                        JButton t = new JButton(oppTime[j]);
//                        //int temp = j * i;
//                        JLabel tl =  new JLabel(dates[i - 1]);
//                        t.addActionListener(new ACtemp());
//                        tl.setVisible(false);
//                        t.add(tl);
                        tempPanel.add(new JLabel(oppTime[j]));

                    }

                }
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
            Object o = e.getSource();
            JButton b;
            b = (JButton) o;
            Object test = b.getComponent(0);
            JLabel test1 = (JLabel)test;
            
            //Object test1 = b.getComponent(1);
            SystemGUI.mainLabel.setText(test1.getText());
            apptTime = b.getText();
            apptDate = test1.getText();
            CalendarHub.clearMasterPanel();
            CalendarHub.masterPanel.add(enterPatient());
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    class SelectedDoctor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object o = SystemGUI.docBox.getItemAt(SystemGUI.docBox.getSelectedIndex());
            String preDoc = o.toString();
            String lastName = preDoc.substring(0, preDoc.indexOf(','));
            String firstName  = preDoc.substring(preDoc.indexOf(',')  + 2, preDoc.length());
            
            String dEID = SystemGUI.sysSQL.testDoctorName(firstName, lastName);
            EmployeeSchdule tempSch = SystemGUI.sysSQL.getEmployeeSchdule(dEID);
            apptDoc = SystemGUI.sysSQL.lookUpEmployee(dEID);
            apptDoc.setEID(dEID);
            apptDoc.setEmployeeSchdule(tempSch);
            
            setDoc = true;
            
            CalendarHub.clearMasterPanel();
            CalendarHub.masterPanel.add(createAppt());
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public JPanel enterPatient(){
        JPanel mainPanel;
        
        SystemGUI.clearPanel();
        SystemGUI.clearAllText();
        
        // Set up mainPanel
        mainPanel = new JPanel(new GridLayout(7,1));
        
        // setup title
        JLabel message = new JLabel("Edit Appointment", 
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
        
        // Add Search button
        JButton search =  new JButton("Search");
        search.addActionListener(new SearchPatient());
        search.setActionCommand(SEARCH);
        mainPanel.add(search);
        
        // Setup waring message
        SystemGUI.mainLabel =  new JLabel("");
        mainPanel.add(SystemGUI.mainLabel);
        
        return mainPanel;

    }
    
    class SearchPatient implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(!SystemGUI.eFirstName.getText().equals("") && !SystemGUI.eLastName.getText().equals("") &&
                    !SystemGUI.ePhone.getText().equals("")){
                
                if(SystemGUI.currentEmployee != null){
                                // If appointment is avalible the code moves forward
                    ArrayList<Patient> tempP = SystemGUI.sysSQL.lookUpPatient(SystemGUI.eLastName.getText(), 
                            SystemGUI.eFirstName.getText(), SystemGUI.ePhone.getText());

                    if(!tempP.isEmpty()){
                        // If Patient exists the appointment is made
                        Appointment tempAppt = new Appointment("NA", tempP.get(0).pID,
                                SystemGUI.currentEmployee.eID, apptDoc.eID, apptDate, apptTime);
                        SystemGUI.sysSQL.makeNewAppointment(tempAppt);
                        SystemGUI.mainLabel.setText("Created");
                    }
                    else{
                        SystemGUI.mainLabel.setText("Patient not found");
                    }
                }
            }
            else{
                SystemGUI.mainLabel.setText("Missing paramaters");
            }
        }        
    }
      
    
    // Edit appointment code
     public JPanel editAppointment(){
        // Sets up edit appointment pannel
        JPanel mainPanel;
        //SystemGUI.clearAllText();
        clearPanel();
        
        // Set up mainPanel
        mainPanel = new JPanel(new GridLayout(7,1));
        
        // setup title
        JLabel message = new JLabel("Edit Appointment", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // setup patient info
        // setup first information panel
        JPanel panelInfo1 = new JPanel(new GridLayout(1,6));
        JLabel firstName = new JLabel("Patirnt First Name", JLabel.CENTER);
        JLabel lastName = new JLabel(" Patient Last Name", JLabel.CENTER);
        JLabel phone = new JLabel("Patient Phone", JLabel.CENTER);
        panelInfo1.add(firstName); panelInfo1.add(eFirstName);
        panelInfo1.add(lastName); panelInfo1.add(eLastName);
        panelInfo1.add(phone); panelInfo1.add(ePhone);
        mainPanel.add(panelInfo1);
        
        // Add Search button
        JButton search =  new JButton("Search");
        search.addActionListener(new EASearch());
        search.setActionCommand(SEARCH);
        mainPanel.add(search);
        
        if(changeEditAppointment != 0){
            String apptTime[];
            apptTime = new String[editAppt.size()];
            JPanel buttons = new JPanel(new GridLayout(1,2));
            JButton edit = new JButton("Edit");
            JButton delete = new JButton("Delete");
            edit.addActionListener(new EASearch());
            delete.addActionListener(new EASearch());
            edit.setActionCommand("EDIT");
            delete.setActionCommand("DELETE");
            buttons.add(edit); buttons.add(delete);
            for(int i = 0; i < editAppt.size(); i++){
                Employee temp = new Employee("", "", "", "");
                temp = sysSQL.getDoctorName(editAppt.get(i).doctorID);
                apptTime[i] = "Date: " + editAppt.get(i).aDate + 
                        " Time: " + editAppt.get(i).aTime + " Doctor: " +
                        temp.firstName + " " + temp.lastName;
                
                
            }
            apptBox = new JComboBox(apptTime);
            mainPanel.add(apptBox);
            mainPanel.add(buttons);
            SystemGUI.changeEditAppointment = 0;
            
            
        }
        else{
            JLabel a  = new JLabel("");
            mainPanel.add(a);
            mainPanel.add(a);
        }
        
        /*
        // setup Back button
        JButton back = new JButton("Back");
        back.addActionListener(new BackButton());
        mainPanel.add(back);
*/
        
        // Setup messange
        mainLabel = new JLabel("", JLabel.CENTER);
        mainPanel.add(mainLabel);
        
        // Add mainPanel to master panel 
        SystemGUI.masterPanel.add(mainPanel);
        updatePanel();
        return mainPanel;
    }
    
    class EASearch implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals(SEARCH)){
                if(!eFirstName.getText().equals("") && !eLastName.getText().equals("") &&
                        !ePhone.getText().equals("")){
                    //Makes sure all the paramaters are entered into the GUI
                    ArrayList<Patient> tempP = sysSQL.lookUpPatient(eLastName.getText(), eFirstName.getText()
                            , ePhone.getText());
                    if(!tempP.isEmpty()){
                        // Searches the patient to make sure it exits
                        editAppt = null;
                        editAppt = sysSQL.getAllPatientAppt(eFirstName.getText(), eLastName.getText(), ePhone.getText());
                        if(!editAppt.isEmpty()){
                            // Gets all the patients appointments
                            editPatient = tempP.get(0);
                            changeEditAppointment = 1;
                            CalendarHub.clearMasterPanel();
                            CalendarHub.masterPanel.add(editAppointment());
                            
                            mainLabel.setText("Worked");
                        }
                        else{
                            mainLabel.setText("Patient does not have an appointment");
                        }
                    }
                    else{
                        mainLabel.setText("Patient does not exist");
                    }
                }
                else{
                    mainLabel.setText("Information not entered");
                }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            if(e.getActionCommand().equals("EDIT")){
                // This is edit action 
                apptDateTimeDoc = null;
                Object o = apptBox.getItemAt(apptBox.getSelectedIndex());
                apptDateTimeDoc = o.toString();
                CalendarHub.clearMasterPanel();
                CalendarHub.masterPanel.add(editAppointment2());
                String test;
                test = apptDateTimeDoc;
                int n1 = test.indexOf("Time");
                int n2 = test.indexOf(" ");
                String date = test.substring(n2 + 1, n1 - 1);
                test = test.replaceFirst(" ", "");
                test = test.replaceFirst(" ", "");       
                n1 = test.indexOf("Doctor");
                n2 = test.indexOf(" ");
                String time = test.substring(n2 + 1, n1 - 1);
                test = test.replaceFirst(" ", "");
                test = test.replaceFirst(" ", "");
                n2 = test.indexOf(" ");
                String d = test.substring(n2 + 1, test.length());
                
                n1 = d.indexOf(" ");
                String fn = d.substring(0, n1);
                String ln = d.substring(n1 + 1, d.length());
                
                String doctorID = sysSQL.testDoctorName(fn, ln);
                
                editApptDoc = SystemGUI.sysSQL.lookUpEmployee(doctorID);
                editApptDoc.setEID(doctorID);
                editApptDate = date;
                editApptTime = time;
                
                CalendarHub.clearMasterPanel();
                CalendarHub.masterPanel.add(editAppointment2());
                //editApptDoc.setEmployeeSchdule(tempSch);
                //mainLabel.setText(apptDateTimeDoc);
            }
            
            if(e.getActionCommand().equals("DELETE")){
                // This is delete action
                apptDateTimeDoc = null;
                Object o = apptBox.getItemAt(apptBox.getSelectedIndex());
                apptDateTimeDoc = o.toString();
                //mainLabel.setText(apptDateTimeDoc);
                String test;
                test = apptDateTimeDoc;
                int n1 = test.indexOf("Time");
                int n2 = test.indexOf(" ");
                String date = test.substring(n2 + 1, n1 - 1);
                test = test.replaceFirst(" ", "");
                test = test.replaceFirst(" ", "");       
                n1 = test.indexOf("Doctor");
                n2 = test.indexOf(" ");
                String time = test.substring(n2 + 1, n1 - 1);
                test = test.replaceFirst(" ", "");
                test = test.replaceFirst(" ", "");
                n2 = test.indexOf(" ");
                String d = test.substring(n2 + 1, test.length());
                
                n1 = d.indexOf(" ");
                String fn = d.substring(0, n1);
                String ln = d.substring(n1 + 1, d.length());
                
                String doctorID = sysSQL.testDoctorName(fn, ln);
                changeEditAppointment = 0;
                SystemGUI.clearAllText();
                //Delete appointment
                sysSQL.deleteAppointment(doctorID, date, time);
                mainLabel.setText("Deleted");
                CalendarHub.clearMasterPanel();
                CalendarHub.masterPanel.add(editAppointment());
            }
            
        }
    }
    
    private JPanel editAppointment2(){
        JPanel mainPanel = new JPanel();
        JPanel midPanel = new JPanel(new GridLayout(1,9));
        JPanel selectPanel[] = new JPanel[7];
        SystemGUI.clearPanel();

        String doc[];
        ArrayList<Employee> e = new ArrayList<Employee>();
        e = SystemGUI.sysSQL.getAllDoctors();
        
        String dates[] = ct.getDaysOfWeek();
        
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
        
        String temp[] = new String[doc.length + 1];
        temp[0] = "Select";
        for(int i = 1; i < temp.length; i++){
            temp[i] = doc[i - 1];
        }
        doc = temp;
        
        SystemGUI.docBox = new JComboBox(doc);
        SystemGUI.docBox.addActionListener(new EditSelectedDoctor());
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
                if(i == 6){
                        
                    tempPanel.add(SystemGUI.docBox);
                        
                }
                else if(i == 5){
                    if(editSetDoc){
                        tempPanel.add(new JLabel(apptDoc.lastName + ", "
                        + apptDoc.firstName));
                    }
                    else{
                        tempPanel.add(new JLabel(""));
                    }
                    
                }
                else{
                        tempPanel.add(new JLabel(""));
                }
                if(editSetDoc){
                    
                    tempPanel.add(new JLabel(daysOfWeek[i - 1]));
                    tempPanel.add(new JLabel(dates[i - 1]));
                    for(int j = 0; j < oppTime.length; j++){
                        //SystemGUI.sysSQL.testAppt(apptDoc.eID, dates[i], oppTime[j]);
                        //apptDoc.testSchudle(dates[i], oppTime[j]);
                        if(apptDoc.testSchudle(dates[i - 1], oppTime[j])
                                && SystemGUI.sysSQL.testAppt(apptDoc.eID, dates[i - 1], oppTime[j]))
                        {
                            JButton t = new JButton(oppTime[j]);
                            //int temp = j * i;
                            JLabel tl =  new JLabel(dates[i - 1]);
                            t.addActionListener(new EditButton());
                            tl.setVisible(false);
                            t.add(tl);
                            tempPanel.add(t);
                        }
                        else{
                           tempPanel.add(new JLabel(oppTime[j])); 
                            
                        }

                    }
                }
                else{
                    tempPanel.add(new JLabel(daysOfWeek[i - 1]));
                    tempPanel.add(new JLabel(dates[i - 1]));
                    for(int j = 0; j < oppTime.length; j++){
//                        JButton t = new JButton(oppTime[j]);
//                        //int temp = j * i;
//                        JLabel tl =  new JLabel(dates[i - 1]);
//                        t.addActionListener(new ACtemp());
//                        tl.setVisible(false);
//                        t.add(tl);
                        tempPanel.add(new JLabel(oppTime[j]));

                    }

                }
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
        
        
        editSetDoc = false;
        return mainPanel;
    
    }
    
    class EditSelectedDoctor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object o = SystemGUI.docBox.getItemAt(SystemGUI.docBox.getSelectedIndex());
            String preDoc = o.toString();
            String lastName = preDoc.substring(0, preDoc.indexOf(','));
            String firstName  = preDoc.substring(preDoc.indexOf(',')  + 2, preDoc.length());
            
            String dEID = SystemGUI.sysSQL.testDoctorName(firstName, lastName);
            EmployeeSchdule tempSch = SystemGUI.sysSQL.getEmployeeSchdule(dEID);
            apptDoc = SystemGUI.sysSQL.lookUpEmployee(dEID);
            apptDoc.setEID(dEID);
            apptDoc.setEmployeeSchdule(tempSch);
            
            editSetDoc = true;
            
            CalendarHub.clearMasterPanel();
            CalendarHub.masterPanel.add(editAppointment2());
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    class EditButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Object o = e.getSource();
            JButton b;
            b = (JButton) o;
            Object test = b.getComponent(0);
            JLabel test1 = (JLabel)test;
            
            //Object test1 = b.getComponent(1);
            SystemGUI.mainLabel.setText(test1.getText());
            apptTime = b.getText();
            apptDate = test1.getText();
            
            sysSQL.deleteAppointment(editApptDoc.eID, editApptDate, editApptTime);
            
            Appointment tempAppt = new Appointment("NA", editPatient.pID,
                                SystemGUI.currentEmployee.eID, apptDoc.eID, apptDate, apptTime);
            SystemGUI.sysSQL.makeNewAppointment(tempAppt);
            SystemGUI.mainLabel.setText("Changed");
            changeEditAppointment = 0;
            SystemGUI.clearAllText();
            CalendarHub.clearMasterPanel();
            CalendarHub.masterPanel.add(editAppointment());
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
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
    
    
    /*
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
        
        
        // Setup back button
        //JButton back = new JButton("Back");
        //back.addActionListener(new BackButton());
        //mainPanel.add(back);
        
        
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
*/
    
    
}
    

