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
// Required libraries 
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;


public class SystemSQL {
    
    private Scanner scan =  new Scanner(System.in);
    private static final String PASS = "test";
    private static final String USER = "test";
    private static final String DBNAME = "test";
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";
//            + "testdb;user=";
//    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private Connection conn = null; //initialize the connection
    private Statement stmt = null;  //initialize the statement that we're using
    private Statement ps = null;
    PreparedStatement prepState = null;
    
    SystemSQL(){
        connectToDB();
    }
    
    // Connects to the DB
    private void connectToDB(){
        DB_URL = DB_URL + DBNAME + ";user="+ USER + ";password=" + PASS;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
        } 
        catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    
    // People are added into the DB. Some of these will become private.
    public String createNewEmployee( Employee newEmp){
        // Insert new employee into the system. 
        // Returns true when employee is made. 
        // Reutrns false when username is not avalible
        
        newEmp.setEID(Integer.toString(generateEmployeeID()));
    
        
        String SQLRun = "INSERT INTO employee(employeeID, userName, HPassword, firstName, lastName, Phone) " +
            "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            prepState = conn.prepareStatement(SQLRun);
           
            prepState.setString(1, newEmp.eID);
            prepState.setString(2, newEmp.userName.toUpperCase());
            prepState.setInt(3, newEmp.getPassword());
            prepState.setString(4, newEmp.firstName.toUpperCase());
            prepState.setString(5, newEmp.lastName.toUpperCase());
            prepState.setString(6, newEmp.phone);
            prepState.executeUpdate();
            
         } 
         catch (SQLException ex) {
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, ex);
          }
            return newEmp.eID;
        
    }
    
    public void createAdmin(AdminEmployee newAdmin){
        // Creates an admin account
        Employee newEmployee = new Employee(newAdmin.userName, newAdmin.firstName, newAdmin.lastName,
                newAdmin.phone);
        newEmployee.setPassword(newAdmin.getPassword());
        newAdmin.setEID(createNewEmployee(newEmployee));
        
        String adminCreateSQL = "INSERT INTO adminEmployee(employeeID, adminDate, "
                + "reasonForAdmin) VALUES(?, ?, ?)";
        try{
            prepState = conn.prepareStatement(adminCreateSQL);
            prepState.setString(1, newAdmin.eID);
            prepState.setString(2, newAdmin.adminDate);
            prepState.setString(3, newAdmin.reason);
            prepState.executeUpdate();
        }
        catch(SQLException se){
           Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public String createNonAdmin(NonAdminEmployee newNonAdminEmployee){
        // Creates nonAdmin Accounts
        Employee newEmployee = new Employee(newNonAdminEmployee.userName, newNonAdminEmployee.firstName,
            newNonAdminEmployee.lastName, newNonAdminEmployee.phone);
        newEmployee.setPassword(newNonAdminEmployee.getPassword());
        newNonAdminEmployee.setEID(createNewEmployee(newEmployee));
        String nonAdminSQL = "INSERT INTO nonAdminEmployee(employeeID, fullTime_Part)"
                + " VALUES(?, ?)";
        try{
            prepState = conn.prepareStatement(nonAdminSQL);
            prepState.setString(1, newNonAdminEmployee.eID);
            prepState.setBoolean(2, newNonAdminEmployee.fullTime);
            prepState.executeUpdate();
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return newNonAdminEmployee.eID;
    }
    
    public String createMedicalEmployee(MedicalEmployee newMedicalEmployee){
        // Creates a medical employee
        NonAdminEmployee newNonAdminEmployee = new NonAdminEmployee(newMedicalEmployee.userName, newMedicalEmployee.firstName,
            newMedicalEmployee.lastName, newMedicalEmployee.phone, newMedicalEmployee.fullTime);
        newNonAdminEmployee.setPassword(newMedicalEmployee.getPassword());
        newMedicalEmployee.setEID(createNonAdmin(newNonAdminEmployee));
        
        String newMedSQL = "INSERT INTO medicalEmployee(employeeID, degree, salary, "
                + "college) VALUES(?, ? , ?, ?)";
        try{
            prepState = conn.prepareStatement(newMedSQL);
            prepState.setString(1, newMedicalEmployee.eID);
            prepState.setString(2, newMedicalEmployee.degree);
            prepState.setString(3, newMedicalEmployee.salary);
            prepState.setString(4, newMedicalEmployee.college);
            prepState.executeUpdate();
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return newMedicalEmployee.eID;
    }
    
    public void createDoctor(Doctor newDoctor){
        // Creates a doctor
        MedicalEmployee newMedicalEmployee = new MedicalEmployee(newDoctor.userName,
            newDoctor.firstName, newDoctor.lastName, newDoctor.phone, newDoctor.fullTime, 
            newDoctor.degree, newDoctor.salary, newDoctor.college);
        newMedicalEmployee.setPassword(newDoctor.getPassword());
        newDoctor.setEID(createMedicalEmployee(newMedicalEmployee));
        
        String createDocSQL = "INSERT INTO doctor(employeeID, licenseNumber) "
                + "VALUES(?, ?)";
        try{
            prepState = conn.prepareStatement(createDocSQL);
            prepState.setString(1, newDoctor.eID);
            prepState.setString(2, newDoctor.licenseNumber);
            prepState.executeUpdate();
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public void createNurse(Nurse newNurse){
        // Creates a new nurse
        MedicalEmployee newMedicalEmployee = new MedicalEmployee(newNurse.userName,
            newNurse.firstName, newNurse.lastName, newNurse.phone, newNurse.fullTime, 
            newNurse.degree, newNurse.salary, newNurse.college);
        newMedicalEmployee.setPassword(newNurse.getPassword());
        newNurse.setEID(createMedicalEmployee(newMedicalEmployee));
        
        String createNurseSQL = "INSERT INTO nurse(employeeID, NLicenseNumber) "
                + "VALUES(?, ?)";
        try{
            prepState = conn.prepareStatement(createNurseSQL);
            prepState.setString(1, newNurse.eID);
            prepState.setString(2, newNurse.licenseNumber);
            prepState.executeUpdate();
        }
        catch(SQLException se){
           Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se); 
        }
    }
    
    public void createNonMedical(NonMedicalEmployee newNonMedicalEmployee){
        NonAdminEmployee newNonAdminEmployee = new NonAdminEmployee(newNonMedicalEmployee.userName, 
                newNonMedicalEmployee.firstName, newNonMedicalEmployee.lastName, 
                newNonMedicalEmployee.phone, newNonMedicalEmployee.fullTime);
        newNonAdminEmployee.setPassword(newNonMedicalEmployee.getPassword());
        newNonMedicalEmployee.setEID(createNonAdmin(newNonAdminEmployee));
        
        String CNMSQL = "INSERT INTO NonMedicalEmployee(employeeID, hourlyWage,"
                + " title) VALUES(?, ?, ?)";
        try{
            prepState = conn.prepareStatement(CNMSQL);
            prepState.setString(1,  newNonMedicalEmployee.eID);
            prepState.setString(2, newNonMedicalEmployee.hourlyWage);
            prepState.setString(3, newNonMedicalEmployee.eTitle);
            prepState.executeUpdate();
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public void createNewPatient(Patient newPatient){
        // Adds new patient into the system.
        newPatient.setPID(Integer.toString(generatePatientID()));
        
        String createPatientSQL = "INSERT INTO patient(patientID, employeeID, "
                + "patientFN, patientLN, patientPhone, patientIns, patientEmail) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            prepState = conn.prepareStatement(createPatientSQL);
            prepState.setString(1, newPatient.pID);
            prepState.setString(2, newPatient.eID);
            prepState.setString(3, newPatient.patientFN);
            prepState.setString(4, newPatient.patientLN);
            prepState.setString(5, newPatient.patientPhone);
            prepState.setString(6, newPatient.ins);
            prepState.setString(7, newPatient.email);
            prepState.executeUpdate();
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public boolean makeNewAppointment(Appointment newAppointment){
        // Creates new appointment
        String aID = Integer.toString(generateApptID());
        String MNASQL = "INSERT INTO appointment(aID, patientID, employeeID, "
                + "doctorID, aDate, aTime) VALUES(?, ?, ?, ?, ?, ?)";
        try{
            prepState = conn.prepareStatement(MNASQL);
            prepState.setString(1, aID);
            prepState.setString(2, newAppointment.pID);
            prepState.setString(3, newAppointment.eID);
            prepState.setString(4, newAppointment.doctorID);
            prepState.setString(5, newAppointment.aDate);
            prepState.setString(6, newAppointment.aTime);
            prepState.executeUpdate();
            return true;
                
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return false;
        
    }
    
    public void addEmployeeSchdule(int employeeId, EmployeeSchdule es){
        String sql = "INSERT INTO schedule(employeeID, mondayStart, mondayEnd,"
                + " tuesdayStart, tuesdayEnd, wednesdayStart, wednesdayEnd,"
                + "thrusdayStart, thrusdayEnd, fridayStart, fridayEnd) "
                + "VALUES(?, ?, ?, ?, ? ,? , ?, ?, ?, ?, ? )";
        try{
            prepState = conn.prepareStatement(sql);
            prepState.setInt(1, employeeId);
            prepState.setString(2, es.mondayStart);
            prepState.setString(3, es.mondayEnd);
            prepState.setString(4, es.tuesdayStart);
            prepState.setString(5, es.tuesdayEnd);
            prepState.setString(6, es.wednesdayStart);
            prepState.setString(7, es.wednesdayEnd);
            prepState.setString(8, es.thrusdayStart);
            prepState.setString(9, es.tuesdayEnd);
            prepState.setString(10, es.fridayStart);
            prepState.setString(11, es.fridayEnd);
            prepState.executeUpdate();
            
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public EmployeeSchdule getEmployeeSchdule(String employeeId){
        ResultSet rs;
        EmployeeSchdule tempS;
        String sql = "SELECT * FROM schedule WHERE employeeID = ?";
        try{
            prepState = conn.prepareStatement(sql);
            prepState.setString(1, employeeId);
            rs = prepState.executeQuery();
            if(rs.next() == true){
                String mondayStart = rs.getString("mondayStart");
                String mondayEnd   = rs.getString("mondayEnd");
                String tuesdayStart = rs.getString("tuesdayStart");
                String tuesdayEnd = rs.getString("tuesdayEnd");
                String wednesdayStart = rs.getString("wednesdayStart");
                String wednesdayEnd   = rs.getString("wednesdayEnd");
                String thrusdayStart = rs.getString("thrusdayStart");
                String thrusdayEnd = rs.getString("thrusdayEnd");
                String fridayStart = rs.getString("fridayStart");
                String fridayEnd    = rs.getString("fridayEnd");
                tempS = new EmployeeSchdule(mondayStart, mondayEnd, tuesdayStart, tuesdayEnd,
                        wednesdayStart, wednesdayEnd, thrusdayStart, thrusdayEnd,  
                        fridayStart, fridayEnd);
                return tempS;
            }
            else{
                return null;
            }
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        
        return null;
    }
    
    
    // All Keys and unique variables are tested here !!!!
    public boolean testUserName(String userName){
        // Returns true if the UserName is avalible
        ResultSet userNameRs = null;
        //String testUserName = "SELECT userName From employee WHERE userName = ?";
        try{
            String testUserName = "SELECT userName From employee "
                    + "WHERE userName = ?";
            prepState = conn.prepareStatement(testUserName);
            prepState.setString(1, userName);
            userNameRs = prepState.executeQuery();
            if(userNameRs.next() == false){
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return false;
    }
    
    public boolean testLicenseNumber(String licenseNumber, boolean docOrNurse){
        // This is capable to test both nurse and doctors Licenses if they are
        // already in the system.
        String licenseSQL = null;
        ResultSet rs = null;
        if(docOrNurse){
            licenseSQL = "SELECT licenseNumber FROM doctor WHERE licenseNumber = ?";
        }
        else{
            licenseSQL = "SELECT NLicenseNumber FROM nurse WHERE NLicenseNumber = ?";
        }
        try{
            prepState = conn.prepareStatement(licenseSQL);
            prepState.setString(1, licenseNumber);
            rs = prepState.executeQuery();
            if(rs.next() != true){
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return false;
    }
    
    public boolean testAppt(String dID, String aDate, String aTime){
        // This functions tests to make sure which time and day a doctor is 
        // avalible
        ResultSet rs = null;
        String testInfo = "SELECT * FROM appointment WHERE doctorID = ? AND aTime = ? AND aDate = ?";
         
        try{
            prepState = conn.prepareStatement(testInfo);
            prepState.setString(1, dID);
            prepState.setString(2, aTime);
            prepState.setString(3, aDate);
            rs = prepState.executeQuery();
            
            if(rs.next() != true){
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        
        return false;
    }
    
    public int testEmployeeType(String employeeID){
        // This fucntion tests to see what kind of employee has been selected based
        // on eID
        // 1 is for Admin
        // 2 is for Doctor
        // 3 is for Nurse
        // 4 is for NonMedical Employee
        // 5 Other
        ResultSet rs = null;
        String isAdmin = "SELECT * FROM adminEmployee WHERE employeeID = ?";
        String isDoctor = "SELECT * FROM doctor WHERE employeeID = ?";
        String isNurse = "SELECT * FROM nurse WHERE employeeID = ?";
        String isNonMedicalEmployee = "SELECT * FROM nonMedicalEmployee WHERE employeeID = ?";
        try{
            // Test Admin
            prepState = conn.prepareStatement(isAdmin);
            prepState.setString(1, employeeID);
            rs = prepState.executeQuery();
            if(rs.next()){
                return 1;
            }
            else{// Test Doctor
                prepState = conn.prepareStatement(isDoctor);
                prepState.setString(1, employeeID);
                rs = prepState.executeQuery();
                if(rs.next()){
                    return 2;
                }
                else{// Test Nurse
                    prepState = conn.prepareStatement(isNurse);
                    prepState.setString(1, employeeID);
                    rs = prepState.executeQuery();
                    if(rs.next()){
                        return 2;
                    }
                    else{// Test nonMedicalEmplyee
                        prepState = conn.prepareStatement(isNonMedicalEmployee);
                        prepState.setString(1, employeeID);
                        rs = prepState.executeQuery();
                        if(rs.next()){
                            return 3;
                        }
                        else{
                            return 4;
                        }
                    }
                }
            }
            
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return 0;
    }
    
    public boolean verifyEmployee(String eID){
        // This fucntion takes an eID and verifies if the emplyee exists
        ResultSet rs = null;
        String sql = "SELECT * FROM EMPLOYEE WHERE employeeID = ?";
        try{
            prepState = conn.prepareStatement(sql);
            prepState.setString(1, eID);
            rs = prepState.executeQuery();
            if(rs.next() == true){
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return false;
    }
    
    
    // Generates IDs
    private int generateEmployeeID(){
        // This function generates new employeeID. All ID's for the time being 
        // are integers
        ResultSet rs = null;
        String idGenSQL = "SELECT MAX(employeeID) as \"employeeID\" FROM employee";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(idGenSQL);
            if(rs.next() == false){
                return 1;
            }
            else{
                return (rs.getInt("employeeID") + 1);
                    }
        }
        catch(SQLException e){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return 0;
    }
    
    private int generatePatientID(){
        // This fucntion generates a new patientID and returns it
        ResultSet rs = null;
        String GPSQL = "SELECT MAX(patientID) as \"patientID\" FROM patient";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GPSQL);
            if(rs.next() != true){
                return 1;
            }
            else{
                return (rs.getInt("patientID") + 1);
            }
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return 0;
    }
    
    public int generateApptID(){
        // This function is used to generate new AppointmentID
        ResultSet rs = null;
        String generateApptSQL = "SELECT MAX(aID) as \"aID\" FROM appointment";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(generateApptSQL);
            if(rs.next() == false){
                return 1;
            }
            else{
                return (rs.getInt("aID") + 1);
            }
            
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return 0;
    }
    
    // All Queries start here:
    public void printAllEmployee(){
        try{
            ResultSet rs = null;
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM employee";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String employeeID = rs.getString("employeeID");
                String userName = rs.getString("userName");
                String HPassword = rs.getString("HPassword");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("phone");

                //Display values
                System.out.println(employeeID + " " +
                        userName + " " + HPassword + " " + firstName
                + " " + lastName + " " + phone);
            }
        }
        catch(Exception se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public ArrayList<Patient> lookUpPatient(String PLastName, String PFirstName, String PPhone){
        // This looks up a patient by first and last name and phone number.
        // This does not account for muntiple patients with the same properties.
        ArrayList<Patient> pat = new ArrayList<Patient>();
        Patient tempPat = null;
        ResultSet rs = null;
        String pLookUpQuery = "Select * FROM patient WHERE patientLN = ? AND patientFN = ?"
                + " AND patientPhone = ?";
        try{
            prepState = conn.prepareStatement(pLookUpQuery);
            prepState.setString(1, PLastName);
            prepState.setString(2, PFirstName);
            prepState.setString(3, PPhone);
            rs = prepState.executeQuery();
            while(rs.next()){
                String pID = rs.getString("patientID");
                String eID = rs.getString("employeeID");
                String patientFN = rs.getString("patientFN");
                String patientLN = rs.getString("patientLN");
                String patientPhone = rs.getString("patientPhone");
                String patientIns = rs.getString("patientIns");
                String email = rs.getString("patientEmail");
                
                tempPat = new Patient(pID, eID, patientFN, patientLN, patientPhone,
                 patientIns);
                tempPat.setEmail(email);
                pat.add(tempPat);
                tempPat = null;
            }
            return pat;
            
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        
        return null;
    }
    
    public Patient lookUpPatient(String rpID){
        // This looks up a patient by first and last name and phone number.
        // This does not account for muntiple patients with the same properties.
        Patient pat = null;
        ResultSet rs = null;
        String pLookUpQuery = "Select * FROM patient WHERE patientID = ?";
        try{
            prepState = conn.prepareStatement(pLookUpQuery);
            prepState.setString(1, rpID);
            rs = prepState.executeQuery();
            if(rs.next()){
                String pID = rs.getString("patientID");
                String eID = rs.getString("employeeID");
                String patientFN = rs.getString("patientFN");
                String patientLN = rs.getString("patientLN");
                String patientPhone = rs.getString("patientPhone");
                String patientIns = rs.getString("patientIns");
                String patinerEmail = rs.getString("patientEmail");
                
                pat = new Patient(pID, eID, patientFN, patientLN, patientPhone,
                 patientIns);
                pat.setEmail(patinerEmail);
            }
            return pat;
            
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        
        return null;
    }
     
    public Employee lookUpEmployee(String reID){
        // This looks up a patient by first and last name and phone number.
        // This does not account for muntiple patients with the same properties.
        Employee emp = null;
        ResultSet rs = null;
        String pLookUpQuery = "Select * FROM employee WHERE employeeID = ?";
        try{
            prepState = conn.prepareStatement(pLookUpQuery);
            prepState.setString(1, reID);
            rs = prepState.executeQuery();
            if(rs.next()){
                
                String userName = rs.getString("userName");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("phone");
                
                emp = new Employee(userName, firstName, lastName, phone);
            }
            return emp;
            
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        
        return null;
    }
    
    public ArrayList<Appointment> lookUpAppointment(String aDateC, String aTime){
        /*
        This returns an ArrayList which contains all appointments at the given
        date and time
        */
        ArrayList<Appointment> a = new ArrayList<Appointment>();
        Appointment aTemp = null;
        ResultSet rs = null;
        String LUASQL = "SELECT * FROM appointment WHERE aDate = ? AND aTime = ?";
        try{
            prepState = conn.prepareStatement(LUASQL);
            prepState.setString(1, aDateC);
            prepState.setString(2, aTime);
            rs = prepState.executeQuery();
            while(rs.next()){
                String aID = rs.getString("aID");
                String patientID = rs.getString("patientID");
                String employeeID = rs.getString("employeeID");
                String doctorID = rs.getString("doctorID");
                aDateC = rs.getString("aDate");
                aTime = rs.getString("aTime");
                
                aTemp = new Appointment(aID, patientID, employeeID, doctorID,
                    aDateC, aTime);
                a.add(aTemp);
                aTemp =  null;
            }
            return a;
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        
        return null;
    }
    
    public ArrayList<Appointment> doctorsAppointment(String doctorIDi, 
            String aDateC, String aTime){
        /*
        This returns an ArrayList which contains all appointments at the given
        date and time
        */
        ArrayList<Appointment> a = new ArrayList<Appointment>();
        Appointment aTemp = null;
        ResultSet rs = null;
        String LUASQL = "SELECT * FROM appointment WHERE aDate = ? AND aTime = ?"
                + " AND doctorID = ?";
        try{
            prepState = conn.prepareStatement(LUASQL);
            prepState.setString(1, aDateC);
            prepState.setString(2, aTime);
            prepState.setString(3, doctorIDi);
            rs = prepState.executeQuery();
            while(rs.next()){
                String aID = rs.getString("aID");
                String patientID = rs.getString("patientID");
                String employeeID = rs.getString("employeeID");
                String doctorID = rs.getString("doctorID");
                aDateC = rs.getString("aDate");
                aTime = rs.getString("aTime");
                
                aTemp = new Appointment(aID, patientID, employeeID, doctorID,
                    aDateC, aTime);
                a.add(aTemp);
                aTemp =  null;
            }
            return a;
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        
        return null;
    }
    
    public ArrayList<Appointment> allAppointmentByDate(String date){
        /*
        Returns an ArrayList<Appointment> which contains all the appointment
        on a given date
        */
        ArrayList<Appointment> a = new ArrayList<Appointment>();
        Appointment aTemp = null;
        ResultSet rs = null;
        System.out.println("Appointment by date 1");
        String LUASQL = "SELECT * FROM appointment WHERE aDate = ? ORDER BY aTime";
        try{
            
            prepState = conn.prepareStatement(LUASQL);
            System.out.println("Appointment by date 2");
            prepState.setString(1, date);
            rs = prepState.executeQuery();
            while(rs.next()){
                String aID = rs.getString("aID");
                String patientID = rs.getString("patientID");
                String employeeID = rs.getString("employeeID");
                String doctorID = rs.getString("doctorID");
                String aDate = rs.getString("aDate");
                String aTime = rs.getString("aTime");
                
                aTemp = new Appointment(aID, patientID, employeeID, doctorID,
                    aDate, aTime);
                a.add(aTemp);
                aTemp =  null;
            }
            return a;
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        
        return null;
    }
    
    public Employee getEmployee(String employeeFirstName, String employeeLastName,
            String employeePhone){
        // Returns an Employee object by name and phone
        ResultSet rs = null;
        String SQLQuery = "SELECT * FROM EMPLOYEE WHERE firstName = ? AND lastName = ?"
                + "AND phone = ?";
        try{
            prepState = conn.prepareStatement(SQLQuery);
            prepState.setString(1, employeeFirstName.toUpperCase());
            prepState.setString(2, employeeLastName.toUpperCase());
            prepState.setString(3, employeePhone);
            rs = prepState.executeQuery();
            if(rs.next()){
                String eID = rs.getString("employeeID");
                String userName = rs.getString("userName");
                int hPassword = rs.getInt("hPassword");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("phone");
                
                Employee tempEmployee;
                tempEmployee = new Employee(userName, firstName, lastName, phone);
                tempEmployee.setEID(eID);
                return tempEmployee;
                        
            }
            else{
                return null;
            }
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return null;
    }
    
    public Employee getEmployeeUserName(String userNameN, String password){
        // Returns all employee information with username and password
        ResultSet rs = null;
        String SQLQuery = "SELECT * FROM EMPLOYEE WHERE userName = ? AND HPassword = ?";
        try{
            prepState = conn.prepareStatement(SQLQuery);
            prepState.setString(1,userNameN);
            prepState.setInt(2, password.hashCode());
            rs = prepState.executeQuery();
            if(rs.next()){
                String eID = rs.getString("employeeID");
                String userName = rs.getString("userName");
                int hPassword = rs.getInt("hPassword");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("phone");
                
                Employee tempEmployee;
                tempEmployee = new Employee(userName, firstName, lastName, phone);
                tempEmployee.setEID(eID);
                return tempEmployee;
                        
            }
            else{
                return null;
            }
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return null;
    }
    
    public ArrayList<Employee> getAllEmployee(){
        ArrayList<Employee> allEmps = new ArrayList<Employee>();
        String sql = "SELECT * FROM Employee";
        ResultSet rs;
        try{
            prepState = conn.prepareStatement(sql);
            rs = prepState.executeQuery();
            while(rs.next()){
                String employeeID = rs.getString("employeeID");
                String userName = rs.getString("userName");
                //int HPassword = rs.getInt("HPassword");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("phone");
                Employee tempEmployee = new Employee(userName, firstName, lastName, phone);
                tempEmployee.setEID(employeeID);
                allEmps.add(tempEmployee);
            }
            return allEmps;
        }
        catch(SQLException se){
            
        }
        
        
        return null;
    }
    
    public String testDoctorName(String firstName, String lastName){
        // Gets doctor employeeID by first and last name 
        ResultSet rs;
        String sqlStatement = "SELECT * FROM employee" +
            " NATURAL JOIN nonAdminEmployee" +
            " NATURAL JOIN medicalEmployee" +
            " NATURAL JOIN doctor" +
            " WHERE firstName = ? AND lastName = ?";
        try{
        prepState = conn.prepareStatement(sqlStatement);
        prepState.setString(1, firstName.toUpperCase());
        prepState.setString(2, lastName.toUpperCase());
        rs = prepState.executeQuery();
        if(rs.next() == true){
            return rs.getString("employeeID");
        }
        else{
            return null;
        }
        }
        catch(SQLException se){
           Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se); 
        }
        return null;
    }
    
    public ArrayList<Employee> getAllDoctors(){
        /*
        This fucntion returns an ArrayList<Employee> which contains doctors 
        information. All the doctors in the system are in the ArrayList
        */
        ResultSet rs;
        String sql = "SELECT * FROM EMPLOYEE "
                + "NATURAL JOIN MedicalEmployee "
                + "NATURAL JOIN Doctor";
        ArrayList<Employee> p = new ArrayList<Employee>();
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Employee emp = new Employee("", "", "", "");
                emp.eID = rs.getString("employeeID");
                emp.firstName = rs.getString("firstName");
                emp.lastName = rs.getString("lastName");
                p.add(emp);
            }
            return p;
        }
        catch(SQLException se){
           Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);  
        }
        
        return null;
    }
    
    public ArrayList<Appointment> getAllPatientAppt(String firstName, String lastName, String phone){
        /*
        This returns an ArrayList<Appointment> which contains all the appointments 
        for a given patient. The inputs are the first name, last name and 
        phone number of the patient
        */
       ResultSet rs = null;
       String query = "SELECT * FROM Patient "
               + "INNER JOIN Appointment "
               + "ON appointment.PATIENTID = PATIENT.PATIENTID "
               + "WHERE patientFN = ? AND patientLN = ? "
               + "AND patientPhone = ?";
       try{ 
           ArrayList<Appointment> temp = new ArrayList<Appointment>();
           prepState = conn.prepareStatement(query);
           prepState.setString(1, firstName);
           prepState.setString(2, lastName);
           prepState.setString(3, phone);
           rs = prepState.executeQuery();
           while(rs.next()){
               Appointment tempA = new Appointment("", "", "", "", "", "");
               tempA.aDate = rs.getString("aDate");
               tempA.aTime = rs.getString("aTime");
               tempA.doctorID = rs.getString("doctorID");
               temp.add(tempA);
           }
           return temp;
       }
       catch(SQLException se){
           Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
       }
               
       return null;
    }
    
    public Employee getDoctorName(String docID){
        /*
        This fucntion returns an Employee object of doctor if it is in the db
        */
        ResultSet rs;
        String sql = "SELECT * FROM Employee "
                + "NATURAL JOIN nonAdminEmployee "
                + "NATURAL JOIN medicalEmployee "
                + "NATURAL JOIN Doctor "
                + "WHERE employeeID = ?";
        try{
            prepState = conn.prepareStatement(sql);
            prepState.setString(1, docID);
            rs = prepState.executeQuery();
            if(rs.next()){
                Employee temp = new Employee("", "", "", "");
                temp.firstName = rs.getString("firstName");
                temp.lastName = rs.getString("lastName");
                return temp;
            }
            else{
                return null;
            }
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        return null;
    }
    
    public void upDatePassword(String userName, String password){
        // Updates password with userName and Password
        String setSQL = "UPDATE EMPLOYEE "
                + "SET HPassword = ? "
                + "WHERE userName = ?";
        
        try{
            prepState = conn.prepareStatement(setSQL);
            prepState.setInt(1, password.hashCode());
            prepState.setString(2, userName);
            prepState.executeUpdate();
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
        
    }
    
    
    public void updateUserName(String eID, String userName){
        /// This fucntion is used to update username of the employee
        // It uses the eID to update information
        ResultSet rs = null;
        String sql = "UPDATE EMPlOYEE "
                + "SET userName = ? "
                + "WHERE employeeID = ?";
        try{
           prepState = conn.prepareStatement(sql);
           prepState.setString(1, userName);
           prepState.setString(2, eID);
           prepState.executeUpdate();
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public void updatePatientSQL(Patient p){
        // Updates patient
        // Uses Patient ID to update the patient 
        String sql = "UPDATE Patient "
                + "SET patientFN = ?, "
                + "patientLN = ?, "
                + "patientPhone = ? "
                + "WHERE patientID = ?";
        try{
            prepState = conn.prepareStatement(sql);
            prepState.setString(1, p.patientFN);
            prepState.setString(2, p.patientLN);
            prepState.setString(3, p.patientPhone);
            prepState.setString(4, p.pID);
            prepState.executeUpdate();
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public void updateAppointment(String dID, String oDate, String oTime, 
            String nDate, String nTime){
        /*
        This code is is used to update and appointment with the doctors id,
        time and date. This does not change the doctor, but only the date and time.
        */
        String sql = "UPDATE Appointment "
                + "SET aDate = ?, "
                + "aTime = ? "
                + "WHERE doctorID = ? AND aDate = ? AND aTime = ?";
        try{
            prepState = conn.prepareStatement(sql);
            prepState.setString(1, nDate);
            prepState.setString(2, nTime);
            prepState.setString(3, dID);
            prepState.setString(4, oDate);
            prepState.setString(5, oTime);
            prepState.executeUpdate();
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public void deleteAppointment(String dID, String oDate, String oTime){
        /*
        This code is is used to delete an appointment with time, date and
        doctorID.
        */
        String sql = "DELETE FROM Appointment "
                + "WHERE doctorID = ? AND aDate = ? AND aTime = ?";
        try{
            prepState = conn.prepareStatement(sql);
            prepState.setString(1, dID);
            prepState.setString(2, oDate);
            prepState.setString(3, oTime);
            prepState.executeUpdate();
        }
        catch(SQLException se){
            Logger.getLogger(SystemSQL.class.getName()).log(Level.SEVERE, null, se);
        }
    }
}

