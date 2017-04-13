/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecs343.labcode;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author ScottRoberts
 */
public class CalendarHub {
    //---------------------------------------- Single Instance of CalendarHub
    public static CalendarHub instance = null;
    //------------------------------- Links to the working back-end classes
    SystemGUI sysGUI;
    SystemSQL sysSQL;
    CalendarTest cal;
    //---------------------------------------- Single Jframe
    JFrame frame;
    //----------------------------------- Single MenuBar for home page view
    JMenuBar menuBar;
    //--------------- Home Menu button to take you to the calendar day view.
    JMenu home;
    JMenuItem homeItem;
    //----------------------------------------- Appointments menu and items
    JMenu appointments;
    JMenuItem createAppointment;
    JMenuItem editAppointment;
    //----------------------------------------- Appointments menu and items
    JMenu patients;
    JMenuItem createPatient;
    JMenuItem editPatient;
    //----------------------------------------- NonMedicalEmployee menu and items
    JMenu nonMedicalEmployees;
    JMenuItem createNonMedicalEmployee;
    JMenuItem editNonMedicalEmployee;
    //----------------------------------------- MedicalEmployee menu and items
    JMenu medicalEmployees;
    JMenuItem createDoctor;
    JMenuItem editDoctor;
    JMenuItem createNurse;
    JMenuItem editNurse;
    //----------------------------------------- Admin menu and items
    JMenu admin;
    JMenuItem createAdmin;
    JMenuItem editAdmin;
    //----------------------------------------- Account menu and items
    JMenu account;
    JMenuItem changePassword;
    //----------------------------------------- Calendar Display menu and items
    JMenu calendarDisplay;
    JMenuItem dayDisplay;
        JMenu dayChoice;
        JTextField calendarDate;
        JMenuItem calendarDateGo;
    JMenuItem weekDisplay;
    JMenuItem monthDisplay;
    //----------------------------------------- Logout menu (takes to login page)
    JMenu logout;
    JMenuItem logoutItem;
    //----------------------------------------- Panel control variables
    int currentEmployee = 1;
    int currentDisplay = 0;
    int currentDBItem = 0;
    //----------------------------------------- Hours of Operation
    int startTime = 8;
    int endTime = 8;
    //---------------------------------------- All Panels
    public static JPanel masterPanel;
    JPanel calendarInfoPanel;
    JPanel tempPanel1;
    JPanel tempPanel2;
    JPanel tempPanel3;
    JPanel contentPanel;
    //---------------------------------------- All Labels
    JLabel loginInfo;
    JLabel calendarDisplayInfo;
    JLabel daysOfWeek;
    //---------------------------------------- Scroll Panes
    JScrollPane dayCalendarPane;
    JScrollPane weekCalendarPane;
    JScrollPane monthCalendarPane;
    
    public CalendarHub()
    {
        //---------------------------------------- Main parts
        cal = CalendarTest.getInstance();
        sysSQL = new SystemSQL();
        sysGUI = new SystemGUI(this);
        //sysSQL = new SystemSQL(this);
        
        //---------------------------------------- Frame
        frame = new JFrame("Pro");
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setMaximumSize(DimMax);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.getContentPane().setBackground(Color.green);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        //Home Menu
        home = new JMenu("Home");
        home.setMnemonic(KeyEvent.VK_M);
        //home.addMenuListener(new HandleHomeMenu());
        homeItem = new JMenuItem("Home");
        homeItem.addActionListener(new HandleMenuItem());
        home.add(homeItem);
        menuBar.add(home);
        
        //Appointments Menu
        appointments = new JMenu("Appointments");
        appointments.setMnemonic(KeyEvent.VK_M);
        //Appointments Items
        createAppointment = new JMenuItem("Create Appointment");
        createAppointment.addActionListener(new HandleMenuItem());
        appointments.add(createAppointment);
        editAppointment = new JMenuItem("Edit Appointment");
        editAppointment.addActionListener(new HandleMenuItem());
        appointments.add(editAppointment);
        menuBar.add(appointments);
        
        //Patients Menu
        patients = new JMenu("Patients");
        patients.setMnemonic(KeyEvent.VK_M);
        //Patient's Items
        createPatient = new JMenuItem("Create Patient");
        createPatient.addActionListener(new HandleMenuItem());
        patients.add(createPatient);
        editPatient = new JMenuItem("Edit Patient");
        editPatient.addActionListener(new HandleMenuItem());
        patients.add(editPatient);
        menuBar.add(patients);
        
        //Non-MedicalEmployees Menu
        nonMedicalEmployees = new JMenu("Non-Medical Employees");
        nonMedicalEmployees.setMnemonic(KeyEvent.VK_M);
        //Non-MedicalEmployees' Items
        createNonMedicalEmployee = new JMenuItem("Create Non-Medical Employee");
        createNonMedicalEmployee.addActionListener(new HandleMenuItem());
        nonMedicalEmployees.add(createNonMedicalEmployee);
        //editNonMedicalEmployee = new JMenuItem("Edit Non-Medical Employee");
        //editNonMedicalEmployee.addActionListener(new HandleMenuItem());
        //nonMedicalEmployees.add(editNonMedicalEmployee);
        menuBar.add(nonMedicalEmployees);
        
        //MedicalEmployees Menu
        medicalEmployees = new JMenu("Medical Employees");
        medicalEmployees.setMnemonic(KeyEvent.VK_M);
        //MedicalEmployees' Items
        createDoctor = new JMenuItem("Create Doctor");
        createDoctor.addActionListener(new HandleMenuItem());
        medicalEmployees.add(createDoctor);
        //editDoctor = new JMenuItem("Edit Doctor");
        //editDoctor.addActionListener(new HandleMenuItem());
        //medicalEmployees.add(editDoctor);
        createNurse = new JMenuItem("Create Nurse");
        createNurse.addActionListener(new HandleMenuItem());
        medicalEmployees.add(createNurse);
        //editNurse = new JMenuItem("Edit Nurse");
        //editNurse.addActionListener(new HandleMenuItem());
        //medicalEmployees.add(editNurse);
        menuBar.add(medicalEmployees);
        
        //Admin
        admin = new JMenu("Admin");
        admin.setMnemonic(KeyEvent.VK_M);
        //Admin Items
        createAdmin = new JMenuItem("Create Admin");
        createAdmin.addActionListener(new HandleMenuItem());
        admin.add(createAdmin);
        //editAdmin = new JMenuItem("Edit Admin");
        //editAdmin.addActionListener(new HandleMenuItem());
        //admin.add(editAdmin);
        menuBar.add(admin);
        
        //Account Menu
        account = new JMenu("Account");
        account.setMnemonic(KeyEvent.VK_M);
        //Account's Items
        changePassword = new JMenuItem("Change Password");
        changePassword.addActionListener(new HandleMenuItem());
        account.add(changePassword);
        menuBar.add(account);
        
        //Appointments Menu
        calendarDisplay = new JMenu("Calendar Display");
        calendarDisplay.setMnemonic(KeyEvent.VK_M);
        weekDisplay = new JMenuItem("Week Display");
        weekDisplay.addActionListener(new HandleMenuItem());
        calendarDisplay.add(weekDisplay);
        monthDisplay = new JMenuItem("Month Display");
        monthDisplay.addActionListener(new HandleMenuItem());
        calendarDisplay.add(monthDisplay);
        menuBar.add(calendarDisplay);
        //Appointments Items
        dayDisplay = new JMenuItem("Day Display");
        dayDisplay.addActionListener(new HandleMenuItem());
        calendarDisplay.add(dayDisplay);
        
        //Day Menu
        calendarDisplay.addSeparator();
        dayChoice = new JMenu("Day Choice");
        dayChoice.setMnemonic(KeyEvent.VK_S);
        calendarDisplay.add(dayChoice);
        calendarDate = new JTextField(10);
        dayChoice.add(calendarDate);
        calendarDateGo = new JMenuItem("Go");
        calendarDateGo.addActionListener(new HandleMenuItem());
        dayChoice.add(calendarDateGo);

        //Right Justified
        menuBar.add(Box.createGlue());
       
        //Logout Menu
        logout = new JMenu("Logout");
        //logout.addMenuListener(new HandleLogoutMenu());
        logout.setMnemonic(KeyEvent.VK_M);
        logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(new HandleMenuItem());
        logout.add(logoutItem);
        menuBar.add(logout);
        
        //Current User Info
        loginInfo = new JLabel("");
        menuBar.add(loginInfo);
        calendarDisplayInfo = new JLabel("", JLabel.CENTER);
        
        //Start with menuBar not visible because of Default Login Page
        menuBar.setVisible(false);
        
        //Make All Panels
        masterPanel = new JPanel();
        //masterPanel.setBackground(Color.LIGHT_GRAY);
        calendarInfoPanel = new JPanel();
        calendarInfoPanel.setBackground(Color.white);
        calendarInfoPanel.setLayout(new GridLayout(2,1));
        
        //This label exists on each Employee Panel, but is only visible when
        //The week view is chosen.
        daysOfWeek = new JLabel(
                  "Sun                                    "
                + "Mon                                    "
                + "Tues                                     "
                + "Wed                                 "
                + "Thurs                                       "
                + "Fri                                      "
                + "Sat   ", JLabel.CENTER);
        
        //DayCalendar
        tempPanel1 = new JPanel();
        tempPanel1.setLayout(new GridLayout(100,2));
        tempPanel1.setBackground(Color.white);
        pullDayQueries(cal.getDay());
        
        //WeekCalendar
        tempPanel2 = new JPanel();
        tempPanel1.setBackground(Color.white);
        tempPanel2.setLayout(new GridLayout(15,7));
        pullWeekQueries();
        
        //MonthCalendar
        tempPanel3 = new JPanel();
        tempPanel1.setBackground(Color.white);
        tempPanel3.setLayout(new GridLayout(5,7));
        displayMonthCalendar();
          
    }
    
    public void pullDayQueries(String date)
    {
        clearPanel(tempPanel1);
        ArrayList<Appointment> appts;
        appts = sysSQL.allAppointmentByDate(date);
        if (appts.size() == 0)
            tempPanel1.add(new JLabel("No appointments today", JLabel.CENTER));
        
        String hour = null;
        for (int i = 0; i < appts.size(); i++)
        {
            if (!appts.get(i).aTime.equals(hour))
            {
                hour = appts.get(i).aTime;
                tempPanel1.add(new JLabel());
                JLabel tempLabel = new JLabel(hour, JLabel.LEFT);
                tempPanel1.add(tempLabel);
            }
            Patient tempPat = sysSQL.lookUpPatient(appts.get(i).pID);
            Employee tempDoc = sysSQL.lookUpEmployee(appts.get(i).doctorID);
            tempPanel1.add(new JLabel ("Patient: " + tempPat.patientLN + ", " + 
                    tempPat.patientFN + "  Doctor: " + tempDoc.lastName + ", "
                    + tempDoc.firstName, JLabel.LEFT));
        }
        
    }
    
    public void pullWeekQueries()
    {
        for (int i = 0; i <100; i++)
        {
            //Populates Columns first
            tempPanel2.add(new JLabel ("# of Appointments", JLabel.CENTER));
        }
    }
    
    public void displayMonthCalendar()
    {
        //Calculate first day of Month
        cal.setMonthToStart();
        //This line needs fixing: cal.dayOfWeek is stuck on a different variable than the new calendar day we want.
        int tempDayOfWeek = cal.cal.get(Calendar.DAY_OF_WEEK);  //Get day of Week for new calendar
        //Create space for the first day of the month on the proper day of week
        for (int i = 1; i < tempDayOfWeek; i++)
        {
            tempPanel3.add(new JLabel("", JLabel.CENTER));
        }
        for (int i = 1; i <= cal.daysInMonth[cal.month]; i++)
        {
            tempPanel3.add(new JLabel("" + i, JLabel.CENTER));
        }
    }
    
    public static CalendarHub getInstance()
    {
        if (null == instance)
        {
            instance = new CalendarHub();
        }
        return instance;
    }
 
    public void showFrame()
    {
        frame.setVisible(true);
    }
    public void showMaster()
    {
        masterPanel.setVisible(true);
    }
    public void setMasterToFrame()
    {
        frame.add(masterPanel);
    }
    public static void clearMasterPanel()
    {
        masterPanel.removeAll();
        masterPanel.updateUI();
    }
    void clearPanel(JPanel panel)
    {
        panel.removeAll();
    }
    //------------------------------------------------------ MenuItem Listener
    public class HandleMenuItem implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            System.out.println("Selected: " + command);
            if(command.equals("Home"))
                setHomePanel();
            if(command.equals("Create Appointment"))
                setCreateAppointmentPanel();
            if(command.equals("Edit Appointment"))
                setEditAppointmentPanel();
            if(command.equals("Create Patient"))
                setCreatePatientPanel();
            if(command.equals("Edit Patient"))
                setEditPatientPanel();
            if(command.equals("Create Non-Medical Employee"))
                setCreateNonMedicalEmployeePanel();
            if(command.equals("Edit Non-Medical Employee"))
                setEditNonMedicalEmployeePanel();
            if(command.equals("Create Doctor"))
                setCreateDoctorPanel();
            if(command.equals("Edit Doctor"))
                setEditDoctorPanel();
            if(command.equals("Create Nurse"))
                setCreateNursePanel();
            if(command.equals("Edit Nurse"))
                setEditNursePanel();
            if(command.equals("Create Admin"))
                setCreateAdminPanel();
            if(command.equals("Edit Admin"))
                setEditAdminPanel();
            if(command.equals("Change Password"))
                setChangePasswordPanel();
            if(command.equals("Day Display"))
                changeToDayDisplay();
            if(command.equals("Go"))
                setDayDisplay();
            if(command.equals("Week Display"))
                changeToWeekDisplay();
            if(command.equals("Month Display"))
                changeToMonthDisplay();
            if(command.equals("Logout"))
                setLogin();     
        } 
    }
    void setHomePanel()
    {
        clearMasterPanel();
        currentDisplay = 1;
        pullDayQueries(cal.getDay());
        setVisibleHomePanel(currentEmployee, currentDisplay);
    }
    void setCreateAppointmentPanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.createAppt());
    }
    void setEditAppointmentPanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.editAppointment());
    }
    void setCreatePatientPanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.createPatient());
    }
    void setEditPatientPanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.updatePatient());
    }
    void setCreateNonMedicalEmployeePanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.createNonMedicalEmployee());
    }
    void setEditNonMedicalEmployeePanel()
    {
        clearMasterPanel();
        //masterPanel.add(sysGUI.editNonMedicalEmployee());
    }
    void setCreateDoctorPanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.createDoctor());
    }
    void setEditDoctorPanel()
    {
        clearMasterPanel();
        //masterPanel.add(sysGUI.editDoctor());
    }
    void setCreateNursePanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.createNurse());
    }
    void setEditNursePanel()
    {
        clearMasterPanel();
        //masterPanel.add(sysGUI.editNurse());
    }
    void setCreateAdminPanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.makeAdminAccount());
    }
    void setEditAdminPanel()
    {
        clearMasterPanel();
        //masterPanel.add(sysGUI.editAdminAccount());
    }
    void setChangePasswordPanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.changePassword());
    }
    void changeToDayDisplay()
    {
        clearMasterPanel();
        currentDisplay = 1;
        setVisibleHomePanel(currentEmployee, currentDisplay);
    }
    void setDayDisplay()
    {
        clearMasterPanel();
        currentDisplay = 1;
        pullDayQueries(calendarDate.getText());
        setVisibleHomePanel(currentEmployee, currentDisplay);
    }
    void changeToWeekDisplay()
    {
        clearMasterPanel();
        currentDisplay = 2;
        setVisibleHomePanel(currentEmployee, currentDisplay);
    }
    void changeToMonthDisplay()
    {
        clearMasterPanel();
        currentDisplay = 3;
        setVisibleHomePanel(currentEmployee, currentDisplay);
    }
    //-------------------------------------------- Default Panel
    void setLogin()
    {
        clearMasterPanel();
        //Send to Login page and change currentEmployee to whoever logs in.
        menuBar.setVisible(false);
        //masterPanel.add(sysGUI.login());
        masterPanel.add(sysGUI.createEmployee());
    }
    
    //----------------------------------------------------------------- Home Panel Visibility
    public void setVisibleHomePanel(int menuAccess, int calendarType)
    {
        clearMasterPanel();
        menuBar.setVisible(true);
        switch (menuAccess)
        {
            //Admin
            case 1:
                home.setVisible(true);
                appointments.setVisible(true);
                patients.setVisible(false);
                nonMedicalEmployees.setVisible(true);
                medicalEmployees.setVisible(true);
                admin.setVisible(true);
                account.setVisible(true);
                calendarDisplay.setVisible(true);
                break;
            //Medical Employee
            case 2:
                home.setVisible(true);
                appointments.setVisible(true);
                patients.setVisible(true);
                nonMedicalEmployees.setVisible(false);
                medicalEmployees.setVisible(false);
                admin.setVisible(false);
                account.setVisible(true);
                calendarDisplay.setVisible(true);
                break;
            //Non-Medical Employee
            case 3:
                home.setVisible(true);
                appointments.setVisible(true);
                patients.setVisible(false);
                nonMedicalEmployees.setVisible(false);
                medicalEmployees.setVisible(false);
                admin.setVisible(false);
                account.setVisible(true);
                calendarDisplay.setVisible(true);
                break;
        }
        switch(calendarType)
        {
            case 0:
                break;
            //day
            case 1:
                calendarInfoPanel.add(calendarDisplayInfo);
                calendarInfoPanel.add(daysOfWeek);
                masterPanel.add(calendarInfoPanel);
                daysOfWeek.setVisible(false);
                //pullDayQueries();
                dayCalendarPane = new JScrollPane(tempPanel1);
                calendarDisplayInfo.setText("Day View: " + cal.getDay());
                dayCalendarPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                dayCalendarPane.setBounds(0, 0, 1200, 600);
                contentPanel = new JPanel(null);
                contentPanel.setPreferredSize(new Dimension(1200,600));
                contentPanel.add(dayCalendarPane);
                masterPanel.add(contentPanel);
                break;
            //week
            case 2:
                calendarInfoPanel.add(calendarDisplayInfo);
                calendarInfoPanel.add(daysOfWeek);
                masterPanel.add(calendarInfoPanel);
                daysOfWeek.setVisible(true);
                weekCalendarPane = new JScrollPane(tempPanel2);
                calendarDisplayInfo.setText("Week View: " + cal.getWeek());
                weekCalendarPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                weekCalendarPane.setBounds(0, 0, 1200, 600);
                contentPanel = new JPanel(null);
                contentPanel.setPreferredSize(new Dimension(1200,600));
                contentPanel.add(weekCalendarPane);
                masterPanel.add(contentPanel);
                break;
            //month
            case 3:
                calendarInfoPanel.add(calendarDisplayInfo);
                calendarInfoPanel.add(daysOfWeek);
                masterPanel.add(calendarInfoPanel);
                daysOfWeek.setVisible(true);
                monthCalendarPane = new JScrollPane(tempPanel3);
                calendarDisplayInfo.setText("Month View: " + cal.getMonth());
                monthCalendarPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                monthCalendarPane.setBounds(0, 0, 1200, 600);
                contentPanel = new JPanel(null);
                contentPanel.setPreferredSize(new Dimension(1200,600));
                contentPanel.add(monthCalendarPane);
                masterPanel.add(contentPanel);
                break;
        }
    }
    
    
    public static void main(String[] args) 
    {
        
        CalendarHub calHub = new CalendarHub();
        calHub.setMasterToFrame();
        calHub.setLogin();
        //calHub.setVisibleHomePanel(calHub.currentEmployee, calHub.currentDisplay);
        calHub.showFrame();
        calHub.showMaster();
    }
}
