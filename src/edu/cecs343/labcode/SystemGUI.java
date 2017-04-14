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

public class SystemGUI {
    static CalendarHub calHub;
    
    public  static SystemSQL sysSQL; 
    
    public static JFrame mainFrame;
    
    //public JPanel mainPanel;
    public static JPanel masterPanel;
    
    public static JLabel mainLabel;
    
    JTextField test;
    
    // used by all employee fields
    public static JTextField employeeID = new JTextField(10);
    public static JTextField eFirstName = new JTextField(10);
    public static JTextField eLastName = new JTextField(10);
    public static JTextField ePhone  = new JTextField(10);
    public static JTextField eUserName = new JTextField(10);
    public static JTextField eReUserName = new JTextField(10);
    public static JPasswordField ePassword = new JPasswordField(10);
    public static JPasswordField eRePassword = new JPasswordField(10);
    public static JPasswordField oldPass = new JPasswordField(10);
    
    // Used to create doctor
    public static JTextField eDegree = new JTextField(10);
    public static JTextField eCollege = new JTextField(10);
    public static JTextField eLicenseNumber = new JTextField(10);
    
    // Non admin employee
    public static JTextField eTitle = new JTextField(10);
    
    // currrent employee information
    public static Employee        currentEmployee = null;
    public static AdminEmployee   currentAdmin = null;
    public static Doctor          currentDoctor = null;
    public static Nurse           currentNurse = null;
    public static NonMedicalEmployee currentNonMed =  null;
    public static Patient currentPatient = null;
    
    // Used to create account 
    public static final String eType[] = {"Admin", "Doctor", "Nurse", "Non-medical"}; 
    public static JTextField eDFirstName = new JTextField(10);
    public static JTextField eDLastName = new JTextField(10);
    public static JTextField eDate = new JTextField(10);
    public static JTextField eTime = new JTextField(10);
    public static JComboBox empTypes = new JComboBox(eType);
    
    // Used to edit patient
    public static JTextField newPatientFN = new JTextField(10);
    public static JTextField newPatientLN = new JTextField(10);
    public static JTextField newPatientPhone = new JTextField(10); 
    
    // Used to create appointment
    public static JComboBox docBox;
    public static JComboBox timeBox;
    
    // Edit appointment
    public static ArrayList<Appointment> editAppt = null;
    public static int changeEditAppointment = 0;
    public static JComboBox apptBox = null;
    public static final String SEARCH = "Search";
    public static String apptDateTimeDoc = null;
    
    private LoginPage login =  new LoginPage();
    private EmployeeCreation employeeCreator = new EmployeeCreation();
    
    
    SystemGUI(){
        prepareGUI();
        
    }
    SystemGUI(CalendarHub that)
    {
        calHub = that;
        sysSQL = calHub.sysSQL;
        prepareGUI();
    }
    
    public static void prepareGUI(){
        /*
        This fucntion just sets up the frame
        */
        SystemGUI.mainFrame = null;
        SystemGUI.mainFrame = new JFrame("Lets Go!!");
        
        
        //Setting frame to invisible
        SystemGUI.mainFrame.setVisible(false);
        
        Dimension minDimension;
        minDimension = Toolkit.getDefaultToolkit().getScreenSize();
        //SystemGUI.mainFrame.setSize(minDimension);
        //SystemGUI.mainFrame.setMaximumSize(minDimension);
        //SystemGUI.mainFrame.setLayout(new GridLayout(3, 1));
        //SystemGUI.mainFrame.setLayout(new FlowLayout());
        //SystemGUI.mainFrame.setLayout(new BorderLayout());
        
        //SystemGUI.mainFrame.setSize(800,600);
        //SystemGUI.mainFrame.setLayout(new GridLayout(3, 1));
        
        SystemGUI.mainFrame.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         } 
        }); 
        
        
        SystemGUI.masterPanel = new JPanel();
        SystemGUI.mainFrame.add(SystemGUI.masterPanel);
        //SystemGUI.mainFrame.setVisible(true);
    }
    
    public static void killFrame(){
        // This is used to kill the frame
        SystemGUI.mainFrame.setVisible(false);
    }
    
    public static void setFrame(){
        // Sets the frame
        SystemGUI.mainFrame.setVisible(true);
    }
    
    public static void clearPanel(){
        // Clears the master panel
        SystemGUI.masterPanel.removeAll();
        SystemGUI.masterPanel.updateUI();
    }
    
    public static void updatePanel(){
        // Updates the panel
        SystemGUI.masterPanel.updateUI();
    }
    
    public static void clearAllText(){
        // Clears all the texts
        employeeID.setText("");
        eUserName.setText("");
        eFirstName.setText("");
        eLastName.setText("");
        ePhone.setText("");
        ePassword.setText("");
        eRePassword.setText("");
        oldPass.setText("");
        eDegree.setText("");
        eCollege.setText("");
        eLicenseNumber.setText("");
        eTitle.setText("");
        eDFirstName.setText("");
        eDLastName.setText("");
        eTime.setText("");
        eDate.setText("");
        eReUserName.setText("");
        newPatientFN.setText("");
        newPatientLN.setText("");
        newPatientPhone.setText("");
        
    }
    
    public JPanel login(){
        return login.login();

    }
    
    public JPanel createEmployee(){
        return employeeCreator.createEmployee();
    }
    
    public JPanel makeAdminAccount(){
        return employeeCreator.makeAdminAccount();
    }
    
    public JPanel createDoctor(){
        return employeeCreator.createDoctor();
    }
    
    public JPanel createNurse(){
        return employeeCreator.createNurse();
    }
    
    public JPanel createNonMedicalEmployee(){
        return employeeCreator.createNonMedicalEmployee();
    }

    
    public JPanel createPatient(){
        // Patient creation GUI is set up here
        JPanel mainPanel;
        clearPanel();
        mainPanel = new JPanel(new GridLayout(5, 1));
        
        // setup title
        JLabel message = new JLabel("Create new Patient", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // setup first information panel
        JPanel panelInfo1 = new JPanel(new GridLayout(1,6));
        JLabel firstName = new JLabel("First Name", JLabel.CENTER);
        JLabel lastName = new JLabel("Last Name", JLabel.CENTER);
        JLabel phone = new JLabel("Phone", JLabel.CENTER);
        panelInfo1.add(firstName); panelInfo1.add(eFirstName);
        panelInfo1.add(lastName); panelInfo1.add(eLastName);
        panelInfo1.add(phone); panelInfo1.add(ePhone);
        mainPanel.add(panelInfo1);
        
        // Setup ok button
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionCreatePatient());
        mainPanel.add(okButton);
        
        /*
        // Setup back button
        JButton back = new JButton("Back");
        back.addActionListener(new BackButton());
        mainPanel.add(back);
*/
        
        // Setup waring message
        mainLabel =  new JLabel("");
        mainPanel.add(mainLabel);
        
        SystemGUI.masterPanel.add(mainPanel);
        updatePanel();
        return mainPanel;
        
        
    }
    
    class ActionCreatePatient implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!eFirstName.getText().equals("") && !eLastName.getText().equals("") && 
                    !ePhone.getText().equals("")){
                System.out.println("Create Patient 1");
                //If all paramaters are entered the code moves foward
                //mainLabel.setText("Everthing has been entered");
                if(currentEmployee != null){
                    //If employees is logged in the code moves forward
                    Patient tempPatient = new Patient("NA", currentEmployee.eID, 
                            eFirstName.getText(), eLastName.getText(), 
                            ePhone.getText(), "NA");
                    //There might be an issue of clicking the button mutlitple
                    //time and making the same patient over and over again.
                    sysSQL.createNewPatient(tempPatient);
                    
                    mainLabel.setText("Patient Created");
                            
                }
                else{
                    mainLabel.setText("Not logged in");
                }
            }
            else{
                mainLabel.setText("Parameters are missing");
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public JPanel createAppt(){
        // Sets up GUI to create an appointment
        JPanel mainPanel;
        clearPanel();
        
        String doc[];
        ArrayList<Employee> e = new ArrayList<Employee>();
        e = sysSQL.getAllDoctors();
        
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
        mainPanel = new JPanel(new GridLayout(6,1));
        
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
        panelInfo1.add(firstName); panelInfo1.add(eFirstName);
        panelInfo1.add(lastName); panelInfo1.add(eLastName);
        panelInfo1.add(phone); panelInfo1.add(ePhone);
        mainPanel.add(panelInfo1);
        
        
        // Doctor and date Pannel
        // setup doctor information
        JPanel dateTimeDoc = new JPanel(new GridLayout(1,6));
        JLabel time = new JLabel("Time", JLabel.CENTER);
        JLabel date = new JLabel("Date", JLabel.CENTER);
        JLabel dName = new JLabel("Doctors", JLabel.CENTER);
        timeBox = new JComboBox(oppTime);
        docBox = new  JComboBox(doc);
        dateTimeDoc.add(date); dateTimeDoc.add(eDate);
        dateTimeDoc.add(time);dateTimeDoc.add(timeBox);
        dateTimeDoc.add(dName); dateTimeDoc.add(docBox);
        mainPanel.add(dateTimeDoc);
        
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
        mainLabel = new JLabel("", JLabel.CENTER);
        mainPanel.add(mainLabel);
        
        // Add to master panel
        SystemGUI.masterPanel.add(mainPanel);
        updatePanel();
        return mainPanel;
    }
    
    class AppointmentCreationHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(!eFirstName.getText().equals("") && !eLastName.getText().equals("") &&
                    !ePhone.getText().equals("") && !eDate.getText().equals("")){
                if(currentEmployee != null){
                    // Test to see if all the parameters are entered
                    // Get doctors name
                    Object o = docBox.getItemAt(docBox.getSelectedIndex());
                    String preDoc = o.toString();
                    String lastName = preDoc.substring(0, preDoc.indexOf(','));
                    String firstName  = preDoc.substring(preDoc.indexOf(',')  + 2, preDoc.length());
                    // Gets time for the comboBox
                    Object o2 = timeBox.getItemAt(timeBox.getSelectedIndex());
                    String t = o2.toString() + ":00";
                    
                    String dEID = sysSQL.testDoctorName(firstName, lastName);
                    //String pID =  
                    if(dEID != null){
                        System.out.println("Test Appt 1");
                        if(sysSQL.testAppt(dEID, eDate.getText(), t)){
                            System.out.println("Test Appt 2");
                            // If appointment is avalible the code moves forward
                            ArrayList<Patient> tempP = sysSQL.lookUpPatient(eLastName.getText(), 
                                    eFirstName.getText(), ePhone.getText());
                            if(!tempP.isEmpty()){
                                // If Patient exists the appointment is made
                                Appointment tempAppt = new Appointment("NA", tempP.get(0).pID,
                                        currentEmployee.eID, dEID, eDate.getText(),
                                        t);
                                sysSQL.makeNewAppointment(tempAppt);
                                mainLabel.setText("Created");
                            }
                            else{
                                mainLabel.setText("Patient not found");
                            }
                        }
                        else{
                            mainLabel.setText("Doctor already has appintment at the"
                                    + " time");
                        }
                    }
                    else{
                        mainLabel.setText("Doctor does not exist");
                    }
                }
                else{
                    mainLabel.setText("No one is logged in the system");
                }
            }
            else{
                mainLabel.setText("Missing paramaters");
            }
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public JPanel changePassword(){
        // Changes Password GUI is made here
        JPanel mainPanel;
        clearPanel();
        clearAllText();
        
        // setup main mainPanel
        mainPanel = new JPanel(new GridLayout(4, 1));
        
        // setup title
        JLabel message = new JLabel("Change Password", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        
        // Create Password Pannel
        JPanel passPannel = new JPanel(new GridLayout(3,2));
        JLabel currPassword = new JLabel("Enter Old Passowrd", JLabel.CENTER);
        JLabel newPass = new JLabel("New Password", JLabel.CENTER);
        JLabel newRePass = new JLabel("Re-Enter New Password", JLabel.CENTER);
        passPannel.add(currPassword); passPannel.add(oldPass);
        passPannel.add(newPass); passPannel.add(ePassword);
        passPannel.add(newRePass);passPannel.add(eRePassword);
        mainPanel.add(passPannel);
        ePassword.setText("");
        
        // Add button
        JButton pressOk = new JButton("ok");
        pressOk.addActionListener(new ActionChangePass());
        mainPanel.add(pressOk);
        
        // Add main label
        mainLabel = new JLabel("", JLabel.CENTER);
        mainPanel.add(mainLabel);
        
        // Add to master panel
        SystemGUI.masterPanel.add(mainPanel);
        updatePanel();
        return mainPanel;
    }
    
    class ActionChangePass implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!(new String(oldPass.getPassword())).equals("") &&
                    !(new String(ePassword.getPassword()).equals("")) &&
                    !(new String(eRePassword.getPassword()).equals(""))){
                // If all the paramaters is entered in the code moves forward
                if((sysSQL.getEmployeeUserName(currentEmployee.userName, new String(oldPass.getPassword())))
                    != null){
                    // Verifies the old password is correct
                        mainLabel.setText("Correct Password");
                        if((new String(ePassword.getPassword())).equals(new String(eRePassword.getPassword()))){
                            //Compares the new passwords are equal
                            sysSQL.upDatePassword(currentEmployee.userName, new String(ePassword.getPassword()));
                            mainLabel.setText("Changed");
                        }
                        else{
                            mainLabel.setText("Passwords don't match");
                            
                        }
                    }
                else{
                    mainLabel.setText("Incorrect Password");
                }
                
                //mainLabel.setText("All information entered");
            }
            else{
                mainLabel.setText("Missing information");
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public JPanel editUserName(){
        // Updates user name
        JPanel mainPanel;
        clearPanel();
        clearAllText();
        
        // setup main mainPanel
        mainPanel = new JPanel(new GridLayout(5, 1));
        
        // setup title
        JLabel message = new JLabel("Change User Name", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // setup eid pannel
        JPanel eid = new JPanel(new GridLayout(1,2));
        JLabel eID = new JLabel("Employee ID", JLabel.CENTER);
        eid.add(eID); eid.add(employeeID);
        mainPanel.add(eid);
        
        // setup username
        JPanel userNameP = new JPanel(new GridLayout(2,3));
        JLabel userName = new JLabel("New Username", JLabel.CENTER);
        JLabel reUserName = new JLabel("reEnter New UserName", JLabel.CENTER);
        userNameP.add(userName); userNameP.add(eUserName);
        userNameP.add(reUserName); userNameP.add(eReUserName);
        mainPanel.add(userNameP);
        
        // setup button pannel
        JButton ok = new JButton("Ok");
        ok.addActionListener(new EditUserNameAction());
        mainPanel.add(ok);
        
        // Add main label
        mainLabel = new JLabel("", JLabel.CENTER);
        mainPanel.add(mainLabel);
        
        // Add to master panel
        SystemGUI.masterPanel.add(mainPanel);
        updatePanel();
        return mainPanel;
    }
        
    class EditUserNameAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!eUserName.getText().equals("") && !employeeID.getText().equals("")
                    && !eReUserName.getText().equals("")){
                // Test to see if all the paramaters are entered
                if(sysSQL.verifyEmployee(employeeID.getText())){
                    if(eUserName.getText().equals(eReUserName.getText())){
                        // Test to see if the usernames match
                        if(sysSQL.testUserName(eUserName.getText())){
                            // If all is etered the statement is executed
                            sysSQL.updateUserName(employeeID.getText(), eUserName.getText());
                            mainLabel.setText("Updated");
                        }
                        else{
                            mainLabel.setText("User Name exists");
                        }
                    }
                    else{
                        mainLabel.setText("Usernames don't match");
                    }
                }
                else{
                    mainLabel.setText("Employee does not exist");
                }
            }
            else{
                mainLabel.setText("Missing fields");
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public JPanel updatePatient(){
        // Updates user name
        JPanel mainPanel;
        clearPanel();
        clearAllText();
        
        // setup main mainPanel
        mainPanel = new JPanel(new GridLayout(4, 1));
        
        // setup title
        JLabel message = new JLabel("Look up Patient", 
                JLabel.CENTER);
        mainPanel.add(message);
        
        // Setup username panel
        JPanel userNameP = new JPanel(new GridLayout(1,6));
        JLabel pFName = new JLabel("First Name", JLabel.CENTER);
        JLabel pLName =  new JLabel("Last Name", JLabel.CENTER);
        JLabel pPhone = new JLabel("Phone number", JLabel.CENTER);
        userNameP.add(pFName); userNameP.add(eFirstName);
        userNameP.add(pLName); userNameP.add(eLastName);
        userNameP.add(pPhone); userNameP.add(ePhone);
        mainPanel.add(userNameP);
        
        // setup button
        JButton search = new JButton("Search");
        search.addActionListener(new UpdatePatient());
        mainPanel.add(search);
        
        // Add main label
        mainLabel = new JLabel("", JLabel.CENTER);
        mainPanel.add(mainLabel);
        
        // Add to master panel
        SystemGUI.masterPanel.add(mainPanel);
        updatePanel();
        return mainPanel;
    }
    
    class UpdatePatient implements ActionListener{
        // This function is used to update patient
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!eFirstName.getText().equals("") && !eLastName.getText().equals("")
                    && !ePhone.getText().equals("")){
                // Test to see if all the parameters has been entered
                ArrayList<Patient> p = new ArrayList<Patient>();
                p = sysSQL.lookUpPatient(eLastName.getText(), eFirstName.getText(),
                        ePhone.getText());
                if(!p.isEmpty()){
                    // Test to see if the patient exits
                    // Go to other window
                    currentPatient = p.get(0);
                    CalendarHub.clearMasterPanel();
                    CalendarHub.masterPanel.add(updatePatient2(p.get(0)));
                }
                else{
                    mainLabel.setText("Patient does not exist");
                }
            }
            else{
                mainLabel.setText("Fields missing");
            }
                
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    private JPanel updatePatient2(Patient p){
        // Updates user name
        JPanel mainPanel;
        clearPanel();
        //clearAllTest();
        
        // setup main mainPanel
        mainPanel = new JPanel(new GridLayout(5, 1));
        
        // setup title
        JLabel message = new JLabel("Found", 
                JLabel.CENTER);
        mainPanel.add(message);
        
        // Setup user information
        JPanel userInfo = new JPanel(new GridLayout(1,6));
        JLabel oldPNameFN = new JLabel("First Name: ", JLabel.CENTER);
        JLabel actualPFN = new JLabel(p.patientFN, JLabel.CENTER);
        JLabel oldPNameLN = new JLabel("Last Name: ", JLabel.CENTER);
        JLabel actualPLN = new JLabel(p.patientLN, JLabel.CENTER);
        JLabel oldPPhone =  new JLabel("Phone :", JLabel.CENTER);
        JLabel actualPhone = new JLabel(p.patientPhone, JLabel.CENTER);
        userInfo.add(oldPNameFN); userInfo.add(actualPFN);
        userInfo.add(oldPNameLN); userInfo.add(actualPLN);
        userInfo.add(oldPPhone); userInfo.add(actualPhone);
        mainPanel.add(userInfo);
        
        // Setup username panel
        JPanel userNameP = new JPanel(new GridLayout(1,6));
        JLabel pFName = new JLabel(" New First Name", JLabel.CENTER);
        JLabel pLName =  new JLabel("New Last Name", JLabel.CENTER);
        JLabel pPhone = new JLabel("New Phone number", JLabel.CENTER);
        userNameP.add(pFName); userNameP.add(eFirstName);
        userNameP.add(pLName); userNameP.add(eLastName);
        userNameP.add(pPhone); userNameP.add(ePhone);
        mainPanel.add(userNameP);
        
        // setup button
        JButton update1 = new JButton("Update");
        update1.addActionListener(new UpdatePatient2());
        mainPanel.add(update1);
        
        // Add main label
        mainLabel = new JLabel("", JLabel.CENTER);
        mainPanel.add(mainLabel);
        
        // Add to master panel
        SystemGUI.masterPanel.add(mainPanel);
        updatePanel();
        return mainPanel;
    }
    
    class UpdatePatient2 implements ActionListener{

          public void actionPerformed(ActionEvent e) {
            if(!eFirstName.getText().equals("") && !eLastName.getText().equals("")
                    && !ePhone.getText().equals("")){
                // Test to see if all the parameters have been entered
                currentPatient.patientFN = eFirstName.getText();
                currentPatient.patientLN = eLastName.getText();
                currentPatient.patientPhone = ePhone.getText();
                sysSQL.updatePatientSQL(currentPatient);
                
            }
            else{
                mainLabel.setText("Fields missing");
            }
                
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public class BackButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //Add main menu option here
            
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
   
    public JPanel editAppointment(){
        // Sets up edit appointment pannel
        JPanel mainPanel;
        
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
             
                //Delete appointment
                sysSQL.deleteAppointment(doctorID, date, time);
                mainLabel.setText("Deleted");
            }
            
        }
    }
    
    private JPanel editAppointment2(){
        // Sets up the other half need to exicute edit appointment
        JPanel mainPanel;
        clearPanel();
        
        String doc[];
        ArrayList<Employee> e = new ArrayList<Employee>();
        e = sysSQL.getAllDoctors();
        
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
        mainPanel = new JPanel(new GridLayout(6,1));
        
        // setup title
        JLabel message = new JLabel("Change Appointment Time Date", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // Doctor and date Pannel
        // setup doctor information
        JPanel dateTimeDoc = new JPanel(new GridLayout(1,4));
        JLabel time = new JLabel("Time", JLabel.CENTER);
        JLabel date = new JLabel("Date", JLabel.CENTER);
        JLabel dName = new JLabel("Doctors", JLabel.CENTER);
        timeBox = new JComboBox(oppTime);
        docBox = new  JComboBox(doc);
        dateTimeDoc.add(date); dateTimeDoc.add(eDate);
        dateTimeDoc.add(time);dateTimeDoc.add(timeBox);
        //dateTimeDoc.add(dName); dateTimeDoc.add(docBox);
        mainPanel.add(dateTimeDoc);
        
        // Setup button
        JButton apptOk = new JButton("Ok");
        apptOk.addActionListener(new EditAppointmentHandler());
        mainPanel.add(apptOk);
        
        /*
        // Setup back button
        JButton back = new JButton("Back");
        back.addActionListener(new BackButton());
        mainPanel.add(back);
*/
       
        
        // Setup messange
        mainLabel = new JLabel("", JLabel.CENTER);
        mainPanel.add(mainLabel);
        
        // Add to master panel
        SystemGUI.masterPanel.add(mainPanel);
        updatePanel();
        return mainPanel;
    }
    
    class EditAppointmentHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //This  action handler edits appointment
            if(!eDate.getText().equals("")){
                // Test parsing is used to get the DATE, TIME and DOCTOR name
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
                
                Object o2 = timeBox.getItemAt(timeBox.getSelectedIndex());
                String t = o2.toString() + ":00";
                
                if(sysSQL.testAppt(doctorID, eDate.getText(), t)){
                    //Edit 
                    sysSQL.updateAppointment(doctorID, date, time, eDate.getText(), t);
                    
                }
                else{
                    mainLabel.setText("Dotor already has appointment");
                }    
            }
            else{
                mainLabel.setText("missing date");
            }
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    
    
}

