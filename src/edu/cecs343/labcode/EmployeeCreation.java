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

public class EmployeeCreation {
    private Employee            localEmployee;
    private AdminEmployee       localAdmin;
    private Doctor              localDoctor;
    private Nurse               localNurse;
    private NonMedicalEmployee  localNonMedicalEmployee;
    
    //private final String schduleHours[] = {"NA", "8am-6pm", "8am-12pm", "1pm-6pm"};  
    private final String daysInWeek[] = {"Monday", "Tuesday", "Wednesday", 
        "Thrusday", "Friday"};
    private final String amPm[] = {"am", "pm"};
    String oppTime[] = {"8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00",
            "11:30", "12:00", "12:30", "1:00", "1:30", "2:00", "2:30", "3:00", "3:30",
            "4:00", "4:30", "5:00", "5:30", "6:00"}; 
    private JComboBox startTime[] = new JComboBox[7];
    private JComboBox startAmPm[] = new JComboBox[7];
    private JComboBox endTime[] = new JComboBox[7];
    private JComboBox endAmPm[] = new JComboBox[7];
    private int empType;
    
    
    EmployeeCreation(){
        for(int i = 0; i < 7; i++){
            startTime[i] = new JComboBox(oppTime);
            endTime[i] = new JComboBox(oppTime);
            startAmPm[i] = new JComboBox(amPm);
            endAmPm[i] = new JComboBox(amPm);
            
        }
    }
    
    
    
    
    public JPanel createEmployee(){
        /*
        Sets up the GUI which is used to create a doctor
        */
        JPanel mainPanel;
        SystemGUI.clearPanel();
        SystemGUI.clearAllText();
        
        // setup main mainPanel
        mainPanel = new JPanel(new GridLayout(4, 1));
        
        // setup title
        JLabel message = new JLabel("Create New Employee", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // setup first information panel
        JPanel panelInfo1 = new JPanel(new GridLayout(1,6));
        JLabel firstName = new JLabel("First Name", JLabel.CENTER);
        JLabel lastName = new JLabel("Last Name", JLabel.CENTER);
        JLabel phone = new JLabel("Phone", JLabel.CENTER);
        panelInfo1.add(firstName); panelInfo1.add(SystemGUI.eFirstName);
        panelInfo1.add(lastName); panelInfo1.add(SystemGUI.eLastName);
        panelInfo1.add(phone); panelInfo1.add(SystemGUI.ePhone);
        mainPanel.add(panelInfo1);
        
        
        // setup employee buttons
        JPanel actionP = new JPanel(new GridLayout(1,2));
        JButton enter = new JButton("Ok");
        enter.addActionListener(new AddEmpAction());
        actionP.add(enter);
        actionP.add(SystemGUI.empTypes);
        mainPanel.add(actionP);
        
        // Setup waring message
        SystemGUI.mainLabel =  new JLabel("");
        mainPanel.add(SystemGUI.mainLabel);
        
        SystemGUI.masterPanel.add(mainPanel);
        SystemGUI.updatePanel();
        return mainPanel;
   
    }
    
    
    class AddEmpAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!SystemGUI.eFirstName.getText().equals("") && !SystemGUI.eLastName.getText().equals("") && 
                    !SystemGUI.ePhone.getText().equals("")){
                Object o = SystemGUI.empTypes.getItemAt(SystemGUI.empTypes.getSelectedIndex());
                String employeeSelected = o.toString();
  
                if(employeeSelected.equals(SystemGUI.eType[0])){
                    CalendarHub.clearMasterPanel();
                    CalendarHub.masterPanel.add(makeAdminAccount());
                    System.out.println(SystemGUI.eType[0]);
                    SystemGUI.mainLabel.setText(SystemGUI.eType[0]);                    
                }
                else if(employeeSelected.equals(SystemGUI.eType[1])){
                    CalendarHub.clearMasterPanel();
                    CalendarHub.masterPanel.add(createDoctor());
                    System.out.println(SystemGUI.eType[1]);
                    SystemGUI.mainLabel.setText(SystemGUI.eType[1]);
                }
                else if(employeeSelected.equals(SystemGUI.eType[2])){
                    CalendarHub.clearMasterPanel();
                    CalendarHub.masterPanel.add(createNurse());
                    System.out.println(SystemGUI.eType[2]);
                    SystemGUI.mainLabel.equals(SystemGUI.eType[2]);
                }
                else if(employeeSelected.equals(SystemGUI.eType[3])){
                    CalendarHub.clearMasterPanel();
                    CalendarHub.masterPanel.add(createNonMedicalEmployee());
                    System.out.println(SystemGUI.eType[3]);
                    SystemGUI.mainLabel.setText(SystemGUI.eType[3]);
                }
                
            }
            else{
                SystemGUI.mainLabel.setText("Missing paramets");
            }
            
            
            
            //hrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    
    public JPanel makeAdminAccount(){
        /*
        Sets up the GUI to make an admin account
        */
        JPanel mainPanel;
        SystemGUI.clearPanel();
        //SystemGUI.clearAllText();
        
        // This panel creates an admin
        mainPanel = new JPanel(new GridLayout(5,1));
        
        // Enter heading
        JLabel message = new JLabel("Admin does not exist make account", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // Enter persons informantion
        JPanel enterInfo = new JPanel(new GridLayout(2,3));
        JLabel firstName = new JLabel("First Name", JLabel.CENTER);
        JLabel lastName = new JLabel("Last Name", JLabel.CENTER);
        JLabel phone = new JLabel("Phone Number", JLabel.CENTER);
        enterInfo.add(firstName);
        enterInfo.add(lastName);
        enterInfo.add(phone);
        enterInfo.add(SystemGUI.eFirstName);
        enterInfo.add(SystemGUI.eLastName);
        enterInfo.add(SystemGUI.ePhone);
        mainPanel.add(enterInfo);
        
        // Enter persons userName and Passwords
        JPanel account = new JPanel(new GridLayout(3,2));
        JLabel userName = new JLabel("Username", JLabel.CENTER);
        JLabel password = new JLabel("password", JLabel.CENTER);
        JLabel rePassword = new JLabel("Reenter Password", JLabel.CENTER);
        account.add(userName);
        account.add(SystemGUI.eUserName);
        account.add(password);
        account.add(SystemGUI.ePassword);
        account.add(rePassword);
        account.add(SystemGUI.eRePassword);
        mainPanel.add(account);
        //clearAllText();
        
        // Add button
        JButton adminEnter = new JButton("OK");
        adminEnter.addActionListener(new adminEnterAction());
        //adminEnter.setMaximumSize(a);
        mainPanel.add(adminEnter);
        
        
        SystemGUI.mainLabel = new JLabel("", JLabel.CENTER);
        mainPanel.add(SystemGUI.mainLabel);
        
        //SystemGUI.mainFrame.add(mainPanel);
        //SystemGUI.masterPanel.add(mainPanel);
        //updatePanel();
        return mainPanel;
         
    }
       
    class adminEnterAction implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!SystemGUI.eFirstName.getText().equals("") && !SystemGUI.eLastName.getText().equals("") && 
                    !SystemGUI.ePhone.getText().equals("") && !SystemGUI.eUserName.getText().equals("") &&
                    !(new String(SystemGUI.ePassword.getPassword()).equals("")) && 
                    !(new String(SystemGUI.eRePassword.getPassword()).equals(""))){
                SystemGUI.mainLabel.setText("Everthing has been entered");
                if(new String(SystemGUI.ePassword.getPassword()).equals(new String(SystemGUI.eRePassword.getPassword()))){
                    // Enter SQL statement. 
                    // Username must be tested
                    //Must make adminEmployee object and 
                    // insert it into the account;
                    AdminEmployee newAdmin = new AdminEmployee(SystemGUI.eUserName.getText(),
                            SystemGUI.eFirstName.getText(), SystemGUI.eLastName.getText(), SystemGUI.ePhone.getText(),
                            "NA", "NA");
                    newAdmin.setPassword((new String(SystemGUI.ePassword.getPassword())).hashCode());
                    localAdmin = newAdmin;
                    empType = 1;
                    CalendarHub.clearMasterPanel();
                    CalendarHub.masterPanel.add(enterSchdule());
                    //SystemGUI.sysSQL.createAdmin(newAdmin);
                    
                    //login();
                    //LearnGUITest.status = 1;
                    //LearnGUITest.wait = false;
                    return;
                    
                }
                else{
                    SystemGUI.mainLabel.setText("Passwords don't match");
                }
            }
            else{
                SystemGUI.mainLabel.setText("Parameters are missing");
            }
            
            //mainLabel.setText("You entered something");
            System.out.println("First Name: " + SystemGUI.eFirstName.getText());
            System.out.println("Last Name: " + SystemGUI.eLastName.getText());
            System.out.println("Phone: " + SystemGUI.ePhone.getText());
            System.out.println("Username: " + SystemGUI.eUserName.getText());
            System.out.println("Username: " + SystemGUI.eUserName.getText());
                    
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        
    }
    
    public JPanel createDoctor(){
        /*
        Sets up the GUI which is used to create a doctor
        */
        JPanel mainPanel;
        SystemGUI.clearPanel();
        //SystemGUI.clearAllText();
        
        // setup main mainPanel
        mainPanel = new JPanel(new GridLayout(9, 1));
        
        // setup title
        JLabel message = new JLabel("Create new Doctor", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // setup first information panel
        JPanel panelInfo1 = new JPanel(new GridLayout(1,6));
        JLabel firstName = new JLabel("First Name", JLabel.CENTER);
        JLabel lastName = new JLabel("Last Name", JLabel.CENTER);
        JLabel phone = new JLabel("Phone", JLabel.CENTER);
        panelInfo1.add(firstName); panelInfo1.add(SystemGUI.eFirstName);
        panelInfo1.add(lastName); panelInfo1.add(SystemGUI.eLastName);
        panelInfo1.add(phone); panelInfo1.add(SystemGUI.ePhone);
        mainPanel.add(panelInfo1);
        
        
        // Setup second info pannel
        JPanel panelInfo2 = new JPanel(new GridLayout(1,6));
        JLabel degree = new JLabel("Degree", JLabel.CENTER);
        JLabel college = new JLabel("College", JLabel.CENTER);
        JLabel licenseNumber = new JLabel("License Number", JLabel.CENTER);
        panelInfo2.add(degree); panelInfo2.add(SystemGUI.eDegree);
        panelInfo2.add(college); panelInfo2.add(SystemGUI.eCollege);
        panelInfo2.add(licenseNumber); panelInfo2.add(SystemGUI.eLicenseNumber);
        mainPanel.add(panelInfo2);
        
        // Setup username panel
        JPanel userNameP = new JPanel(new GridLayout(1,2));
        JLabel userName = new JLabel("User Name", JLabel.CENTER);
        userNameP.add(userName); userNameP.add(SystemGUI.eUserName);
        mainPanel.add(userNameP);
        
        // Setup password Panel
        JPanel passwordP = new JPanel(new GridLayout(1,2));
        JLabel password = new JLabel("Password", JLabel.CENTER);
        passwordP.add(password); passwordP.add(SystemGUI.ePassword);
        mainPanel.add(passwordP);
        
        // Setup SystemGUI.eRePassword
        JPanel rePasswordP = new JPanel(new GridLayout(1,2));
        JLabel rePassword = new JLabel("Re-Password", JLabel.CENTER);
        rePasswordP.add(rePassword); rePasswordP.add(SystemGUI.eRePassword);
        mainPanel.add(rePasswordP);
        
        // Setup ok button
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new AddDoctorAction());
        mainPanel.add(okButton);
        
        /*
        // Setup back button
        JButton back = new JButton("Back");
        back.addActionListener(new BackButton());
        mainPanel.add(back);
*/
        
        // Setup waring message
        SystemGUI.mainLabel =  new JLabel("");
        mainPanel.add(SystemGUI.mainLabel);
        
        SystemGUI.masterPanel.add(mainPanel);
        SystemGUI.updatePanel();
        return mainPanel;
    
    }
    
    class AddDoctorAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(!SystemGUI.eFirstName.getText().equals("") && !SystemGUI.eLastName.getText().equals("") && 
                    !SystemGUI.ePhone.getText().equals("") && !SystemGUI.eUserName.getText().equals("") &&
                    !SystemGUI.eDegree.getText().equals("") && !SystemGUI.eCollege.getText().equals("") &&
                    !SystemGUI.eLicenseNumber.getText().equals("") &&
                    !(new String(SystemGUI.ePassword.getPassword()).equals("")) && 
                    !(new String(SystemGUI.eRePassword.getPassword()).equals(""))){
                // Test to see if all the paramaters are entered into the GUI
                //mainLabel.setText("Everthing has been entered");
                if(SystemGUI.sysSQL.testUserName(SystemGUI.eUserName.getText().toUpperCase())){
                    // If user name is valid the program proceds forward
                    if((new String(SystemGUI.ePassword.getPassword())).equals(new String(SystemGUI.eRePassword.getPassword()))){
                        //If the passwords match the the code proceds forward
                        //mainLabel.setText("All fields entered");
                        if(SystemGUI.sysSQL.testLicenseNumber(SystemGUI.eLicenseNumber.getText(), true)){
                            // If the license number is not in the db the program
                            // moves forward
                            Doctor tempDoctor = new Doctor(SystemGUI.eUserName.getText(), 
                                    SystemGUI.eFirstName.getText(), SystemGUI.eLastName.getText(), 
                                    SystemGUI.ePhone.getText(), true, SystemGUI.eDegree.getText(),
                                    "NA", SystemGUI.eCollege.getText(), SystemGUI.eLicenseNumber.getText());
                            tempDoctor.setPassword((new String(SystemGUI.eRePassword.getPassword())).hashCode());
                            localDoctor = tempDoctor;
                            empType = 2;   
                            CalendarHub.clearMasterPanel();
                            CalendarHub.masterPanel.add(enterSchdule());
                            //SystemGUI.mainLabel.setText("Created");
                            return;
                        }
                        else{
                            SystemGUI.mainLabel.setText("License number already exists");
                        }
                    }
                    else{
                        SystemGUI.mainLabel.setText("Passwords don't match");
                    }
                }
                else{
                    SystemGUI.mainLabel.setText("Username in already exists enter new "
                            + "username.");
                }
            }
            else{
                SystemGUI.mainLabel.setText("Parameters are missing");
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public JPanel createNurse(){
        // This generates gui to create a Nurse
        JPanel mainPanel;
        SystemGUI.clearPanel();
        //SystemGUI.clearAllText();
        
        // setup main mainPanel
        mainPanel = new JPanel(new GridLayout(9, 1));
        
        // setup title
        JLabel message = new JLabel("Create new Nurse", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // setup first information panel
        JPanel panelInfo1 = new JPanel(new GridLayout(1,6));
        JLabel firstName = new JLabel("First Name", JLabel.CENTER);
        JLabel lastName = new JLabel("Last Name", JLabel.CENTER);
        JLabel phone = new JLabel("Phone", JLabel.CENTER);
        panelInfo1.add(firstName); panelInfo1.add(SystemGUI.eFirstName);
        panelInfo1.add(lastName); panelInfo1.add(SystemGUI.eLastName);
        panelInfo1.add(phone); panelInfo1.add(SystemGUI.ePhone);
        mainPanel.add(panelInfo1);
        
        
        // Setup second info pannel
        JPanel panelInfo2 = new JPanel(new GridLayout(1,6));
        JLabel degree = new JLabel("Degree", JLabel.CENTER);
        JLabel college = new JLabel("College", JLabel.CENTER);
        JLabel licenseNumber = new JLabel("License Number", JLabel.CENTER);
        panelInfo2.add(degree); panelInfo2.add(SystemGUI.eDegree);
        panelInfo2.add(college); panelInfo2.add(SystemGUI.eCollege);
        panelInfo2.add(licenseNumber); panelInfo2.add(SystemGUI.eLicenseNumber);
        mainPanel.add(panelInfo2);
        
        // Setup username panel
        JPanel userNameP = new JPanel(new GridLayout(1,2));
        JLabel userName = new JLabel("User Name", JLabel.CENTER);
        userNameP.add(userName); userNameP.add(SystemGUI.eUserName);
        mainPanel.add(userNameP);
        
        // Setup password Panel
        JPanel passwordP = new JPanel(new GridLayout(1,2));
        JLabel password = new JLabel("Password", JLabel.CENTER);
        passwordP.add(password); passwordP.add(SystemGUI.ePassword);
        mainPanel.add(passwordP);
        
        // Setup SystemGUI.eRePassword
        JPanel rePasswordP = new JPanel(new GridLayout(1,2));
        JLabel rePassword = new JLabel("Re-Password", JLabel.CENTER);
        rePasswordP.add(rePassword); rePasswordP.add(SystemGUI.eRePassword);
        mainPanel.add(rePasswordP);
        
        // Setup ok button
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new AddNurseAction());
        mainPanel.add(okButton);
        
        /*
        // Setup back button
        JButton back = new JButton("Back");
        back.addActionListener(new BackButton());
        mainPanel.add(back);
*/
        
        // Setup waring message
        SystemGUI.mainLabel =  new JLabel("");
        mainPanel.add(SystemGUI.mainLabel);
        
        SystemGUI.masterPanel.add(mainPanel);
        SystemGUI.updatePanel();
        return mainPanel;
    }
    
    class AddNurseAction implements ActionListener{

        @Override
       public void actionPerformed(ActionEvent e) {
            if(!SystemGUI.eFirstName.getText().equals("") && !SystemGUI.eLastName.getText().equals("") && 
                    !SystemGUI.ePhone.getText().equals("") && !SystemGUI.eUserName.getText().equals("") &&
                    !SystemGUI.eDegree.getText().equals("") && !SystemGUI.eCollege.getText().equals("") &&
                    !SystemGUI.eLicenseNumber.getText().equals("") &&
                    !(new String(SystemGUI.ePassword.getPassword()).equals("")) && 
                    !(new String(SystemGUI.eRePassword.getPassword()).equals(""))){
                // If all the paramaters are entered into the GUI the code 
                // proceeds forward
                //mainLabel.setText("Everthing has been entered");
                if(SystemGUI.sysSQL.testUserName(SystemGUI.eUserName.getText().toUpperCase())){
                    // If userName is avalible the code moves forward
                    if((new String(SystemGUI.ePassword.getPassword())).equals(new String(SystemGUI.eRePassword.getPassword()))){
                        // If passwords match then the code moves forward
                        //mainLabel.setText("All fields entered");
                        if(SystemGUI.sysSQL.testLicenseNumber(SystemGUI.eLicenseNumber.getText(), false)){
                            // If the license number is in the system the code does
                            // not move forward
                            Nurse tempNurse = new Nurse(SystemGUI.eUserName.getText(), 
                                    SystemGUI.eFirstName.getText(), SystemGUI.eLastName.getText(), 
                                    SystemGUI.ePhone.getText(), true, SystemGUI.eDegree.getText(),
                                    "NA", SystemGUI.eCollege.getText(), SystemGUI.eLicenseNumber.getText());
                            tempNurse.setPassword((new String(SystemGUI.eRePassword.getPassword())).hashCode());
                            localNurse = tempNurse;
                            empType = 3;
                            CalendarHub.clearMasterPanel();
                            CalendarHub.masterPanel.add(enterSchdule());
                            
                            SystemGUI.mainLabel.setText("Created");
                            return;
                        }
                        else{
                            SystemGUI.mainLabel.setText("License number already exists");
                        }
                    }
                    else{
                        SystemGUI.mainLabel.setText("Passwords don't match");
                    }
                }
                else{
                    SystemGUI.mainLabel.setText("Username in already exists enter new "
                            + "username.");
                }
            }
            else{
                SystemGUI.mainLabel.setText("Parameters are missing");
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public JPanel createNonMedicalEmployee(){
        // Sets up GUI to create nonMedicalEmployee
        JPanel mainPanel;
        SystemGUI.clearPanel();
        //SystemGUI.clearAllText();
        
        // setup main mainPanel
        mainPanel = new JPanel(new GridLayout(8, 1));
        
        // setup title
        JLabel message = new JLabel("Create new Non-Medical", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // setup first information panel
        JPanel panelInfo1 = new JPanel(new GridLayout(1,8));
        JLabel firstName = new JLabel("First Name", JLabel.CENTER);
        JLabel lastName = new JLabel("Last Name", JLabel.CENTER);
        JLabel phone = new JLabel("Phone", JLabel.CENTER);
        JLabel title =  new JLabel("Title", JLabel.CENTER);
        panelInfo1.add(firstName); panelInfo1.add(SystemGUI.eFirstName);
        panelInfo1.add(lastName); panelInfo1.add(SystemGUI.eLastName);
        panelInfo1.add(phone); panelInfo1.add(SystemGUI.ePhone);
        panelInfo1.add(title); panelInfo1.add(SystemGUI.eTitle);
        mainPanel.add(panelInfo1);
        
        // Setup username panel
        JPanel userNameP = new JPanel(new GridLayout(1,2));
        JLabel userName = new JLabel("User Name", JLabel.CENTER);
        userNameP.add(userName); userNameP.add(SystemGUI.eUserName);
        mainPanel.add(userNameP);
        
        // Setup password Panel
        JPanel passwordP = new JPanel(new GridLayout(1,2));
        JLabel password = new JLabel("Password", JLabel.CENTER);
        passwordP.add(password); passwordP.add(SystemGUI.ePassword);
        mainPanel.add(passwordP);
        
        // Setup SystemGUI.eRePassword
        JPanel rePasswordP = new JPanel(new GridLayout(1,2));
        JLabel rePassword = new JLabel("Re-Password", JLabel.CENTER);
        rePasswordP.add(rePassword); rePasswordP.add(SystemGUI.eRePassword);
        mainPanel.add(rePasswordP);
        
        // Setup ok button
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new AddNonMedicalEmployee());
        mainPanel.add(okButton);
        
        /*
        // Setup back button
        JButton back = new JButton("Back");
        back.addActionListener(new BackButton());
        mainPanel.add(back);
*/
        
        // Setup waring message
        SystemGUI.mainLabel =  new JLabel("");
        mainPanel.add(SystemGUI.mainLabel);
        
        SystemGUI.masterPanel.add(mainPanel);
        SystemGUI.updatePanel();
        return mainPanel;
    }
    class AddNonMedicalEmployee implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!SystemGUI.eFirstName.getText().equals("") && !SystemGUI.eLastName.getText().equals("") && 
                    !SystemGUI.ePhone.getText().equals("") && !SystemGUI.eUserName.getText().equals("") &&
                    !SystemGUI.eTitle.getText().equals("") &&
                    !(new String(SystemGUI.ePassword.getPassword()).equals("")) && 
                    !(new String(SystemGUI.eRePassword.getPassword()).equals(""))){
                // If all the paramaters are entered into the GUI the code moves
                // forward
                //mainLabel.setText("Everthing has been entered");
                if(SystemGUI.sysSQL.testUserName(SystemGUI.eUserName.getText().toUpperCase())){
                    if((new String(SystemGUI.ePassword.getPassword())).equals(new String(SystemGUI.eRePassword.getPassword()))){
                        //mainLabel.setText("All fields entered");
                        //If the password matches the code moves forward
                        NonMedicalEmployee tempNME;
                        tempNME = new NonMedicalEmployee(SystemGUI.eUserName.getText(), SystemGUI.eFirstName.getText(),
                                SystemGUI.eLastName.getText(), SystemGUI.ePhone.getText(), "NA",
                                SystemGUI.eTitle.getText());
                        tempNME.setPassword((new String(SystemGUI.ePassword.getPassword())).hashCode());
                        
                        localNonMedicalEmployee = tempNME;
                        empType = 4;
                        CalendarHub.clearMasterPanel();
                        CalendarHub.masterPanel.add(enterSchdule());
                        
                    
                        SystemGUI.mainLabel.setText("Created");
                        return;
                    }
                    else{
                        SystemGUI.mainLabel.setText("Passwords don't match");
                    }
                }
                else{
                    SystemGUI.mainLabel.setText("Username in already exists enter new "
                            + "username.");
                }
            }
            else{
                SystemGUI.mainLabel.setText("Parameters are missing");
                
            }
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }   
    }
    
    
    public JPanel enterSchdule(){
        JPanel mainPanel;
        SystemGUI.clearPanel();
        SystemGUI.clearAllText();
        
        // setup main mainPanel
        mainPanel = new JPanel(new GridLayout(11, 1));
        
        // setup title
        JLabel message = new JLabel("Create Schedule", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // setup days
        for(int i = 0; i < 5; i++){
            JPanel dayPanel = new JPanel(new GridLayout(1, 8));
            JLabel dayLabel = new JLabel(daysInWeek[i], JLabel.CENTER);
            dayPanel.add(dayLabel);
            dayPanel.add(new JLabel("Start Time", JLabel.CENTER));
            dayPanel.add(startTime[i]);
            dayPanel.add(startAmPm[i]);
            dayPanel.add(new JLabel("End Time", JLabel.CENTER));
            dayPanel.add(endTime[i]);
            dayPanel.add(endAmPm[i]);
            //dayPanel.add(days[i]);
            mainPanel.add(dayPanel);
        }
        
        // setup button
//            JButton enter = new JButton("Enter");
//            enter.addActionListener(new ActionSchdule());
//            mainPanel.add(enter);
        
        JButton threeM =  new JButton("Set For 3 Months");
        threeM.addActionListener(new ActionSchdule());
        //threeM.setActionCommand(SEARCH);
        mainPanel.add(threeM);
        
        JButton sixM = new JButton("Set For 6 Months");
        sixM.addActionListener(new ActionSchdule());
        //sixM.setActionCommand(SEARCH);
        mainPanel.add(sixM);
        
        JButton yearS = new JButton("Set For Year");
        yearS.addActionListener(new ActionSchdule());
        //yearS.setActionCommand(SEARCH);
        mainPanel.add(yearS);
        
        // Setup waring message
        SystemGUI.mainLabel =  new JLabel("");
        mainPanel.add(SystemGUI.mainLabel);
        
        return mainPanel;
    } 
    
   class ActionSchdule implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String sTime[] = new String[5];
            String eTime[] = new String[5];
            String sAmPm[] = new String[5];
            String eAmPm[] = new String[5];
            Employee tempEmp;
            EmployeeSchdule es;
            boolean timeTrue = true;
            
            for(int i = 0; i < 5; i++){
                Object o = startTime[i].getItemAt(startTime[i].getSelectedIndex());
                sTime[i] = new String(o.toString());
                o = startAmPm[i].getItemAt(startAmPm[i].getSelectedIndex());
                sAmPm[i] = new String(o.toString());
                o = endTime[i].getItemAt(endTime[i].getSelectedIndex());
                eTime[i] = new String(o.toString());
                o = endAmPm[i].getItemAt(endAmPm[i].getSelectedIndex());
                eAmPm[i] = new String(o.toString());
            }
            
            timeTrue = true;
            for(int i = 0; i < 5; i++){
                int trueRange = testTimeRange(sTime[i], sAmPm[i], eTime[i], eAmPm[i]);
                if(trueRange == 0){
                    timeTrue = false;
                }
                else if(trueRange == -1){
                    timeTrue = false;
                }
            }
                
            if(timeTrue){
                es = new EmployeeSchdule(sTime[0], eTime[0], sTime[1], eTime[1], sTime[2],
                eTime[2], sTime[3], eTime[3], sTime[4], eTime[4]);
                switch(empType){
                    case 0:
                        tempEmp = null;
                        break;
                    case 1:
                        // Set Admin
                        SystemGUI.sysSQL.createAdmin(localAdmin);
                        tempEmp = SystemGUI.sysSQL.getEmployee(localAdmin.firstName, 
                                localAdmin.lastName, localAdmin.phone);
                        localAdmin = null;
                        break;

                    case 2:
                        // Set Doctor
                        SystemGUI.sysSQL.createDoctor(localDoctor);
                        tempEmp = SystemGUI.sysSQL.getEmployee(localDoctor.firstName, 
                                localDoctor.lastName, localDoctor.phone);
                        localDoctor = null;
                        break;

                    case 3:
                        // Set Nurse
                        SystemGUI.sysSQL.createNurse(localNurse);
                        tempEmp = SystemGUI.sysSQL.getEmployee(localNurse.firstName, localNurse.lastName,
                                localNurse.phone);
                        localNurse = null;
                        break;
                    case 4:
                        SystemGUI.sysSQL.createNonMedical(localNonMedicalEmployee);
                        tempEmp = SystemGUI.sysSQL.getEmployee(localNonMedicalEmployee.firstName, localNonMedicalEmployee.lastName,
                                localNonMedicalEmployee.phone);
                        localNonMedicalEmployee = null;
                        break;
                    default:
                        tempEmp = null;
                        break;
                }
                SystemGUI.sysSQL.addEmployeeSchdule(Integer.parseInt(tempEmp.eID), es);
                SystemGUI.mainLabel.setText("Created");
            }
            else{
                SystemGUI.mainLabel.setText("Not Created");
            }
            
            
            //empType = 0;
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
	
	
	private int testTimeRange(String sTime, String sAmPm, String eTime,
	                String eAmPm){
            int firstIndex;
            String sHour;
            String sMinutes;
            firstIndex = sTime.indexOf(":");
            sHour = sTime.substring(0, firstIndex);
            sMinutes = sTime.substring(firstIndex + 1, sTime.length() - 1);
            int sHourInt = Integer.parseInt(sHour);
            int sMinutesInt = Integer.parseInt(sMinutes);

            if(sAmPm.equals("pm") && sHourInt < 12){
                sHourInt += 12;
            }

            String eHour;
            String eMinutes;
            firstIndex = eTime.indexOf(":");
            eHour = eTime.substring(0, firstIndex);
            eMinutes = eTime.substring(firstIndex + 1, eTime.length() - 1);
            int eHourInt = Integer.parseInt(eHour);
            int eMinutesInt = Integer.parseInt(eMinutes);

            if(eAmPm.equals("pm") && eHourInt < 12){
                eHourInt += 12;
            }

            if((sHourInt > 18) || (eHourInt > 18)){
                return -1;
            }

            if((sHourInt * 60 + sMinutesInt) < (eHourInt * 60 + eMinutesInt)){
                SystemGUI.mainLabel.setText("sTime hours: " + sHourInt + " minutes: " + 
                        sMinutesInt);
                return 1;
            }
            else{
                return 0;
            }

        }
    
}
