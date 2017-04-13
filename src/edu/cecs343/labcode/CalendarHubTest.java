/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecs343.labcode;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author ScottRoberts
 */
public class CalendarHubTest {
    JFrame frame;
    
    JMenuBar menuBar;
    
    JMenu home;
    
    JMenu appointments;
    JMenuItem createAppointment;
    JMenuItem editAppointment;
    JMenuItem deleteAppointment;
    
    JMenu patients;
    JMenuItem createPatient;
    JMenuItem editPatient;
    
    JMenu nonMedicalEmployees;
    JMenuItem createNonMedicalEmployee;
    JMenuItem editNonMedicalEmployee;
    
    JMenu medicalEmployees;
    JMenuItem createMedicalEmployee;
    JMenuItem editMedicalEmployee;
    
    JMenu admin;
    JMenuItem createAdmin;
    JMenuItem editAdmin;
    
    JMenu account;
    JMenuItem changePassword;
    
    JMenu calendarDisplay;
    JMenuItem dayDisplay;
    JMenuItem weekDisplay;
    JMenuItem monthDisplay;
    
    JMenu logout;
    
    //Panel control variables
    int currentEmployee = 3;
    int currentDisplay = 0;
    int currentDBItem = 0;
    
    int startTime = 8;
    int endTime = 8;
    
    public static CalendarHub instance = null;
    CalendarTest cal;
    
    //All Panels
    JPanel masterPanel;
    JPanel calendarInfoPanel;
    JPanel tempPanel1;
    JPanel tempPanel2;
    JPanel tempPanel3;
    JPanel contentPanel;
    
    //All Labels
    JLabel loginInfo;
    JLabel calendarDisplayInfo;
    JLabel daysOfWeek;
    
    //Scroll Pane
    JScrollPane dayCalendarPane;
    JScrollPane weekCalendarPane;
    JScrollPane monthCalendarPane;
    
    public CalendarHubTest()
    {
        //Main parts
        cal = CalendarTest.getInstance();
        
        frame = new JFrame("New Nightmare");
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
        home.addMenuListener(new HandleHomeMenu());
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
        deleteAppointment = new JMenuItem("Delete Appointment");
        deleteAppointment.addActionListener(new HandleMenuItem());
        appointments.add(deleteAppointment);
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
        editNonMedicalEmployee = new JMenuItem("Edit Non-Medical Employee");
        editNonMedicalEmployee.addActionListener(new HandleMenuItem());
        nonMedicalEmployees.add(editNonMedicalEmployee);
        menuBar.add(nonMedicalEmployees);
        
        //MedicalEmployees Menu
        medicalEmployees = new JMenu("Medical Employees");
        medicalEmployees.setMnemonic(KeyEvent.VK_M);
        //MedicalEmployees' Items
        createMedicalEmployee = new JMenuItem("Create Medical Employee");
        createMedicalEmployee.addActionListener(new HandleMenuItem());
        medicalEmployees.add(createMedicalEmployee);
        editMedicalEmployee = new JMenuItem("Edit Medical Employee");
        editMedicalEmployee.addActionListener(new HandleMenuItem());
        medicalEmployees.add(editMedicalEmployee);
        menuBar.add(medicalEmployees);
        
        //Admin
        admin = new JMenu("Admin");
        admin.setMnemonic(KeyEvent.VK_M);
        //Admin Items
        createAdmin = new JMenuItem("Create Admin");
        createAdmin.addActionListener(new HandleMenuItem());
        admin.add(createAdmin);
        editAdmin = new JMenuItem("Edit Admin");
        editAdmin.addActionListener(new HandleMenuItem());
        admin.add(editAdmin);
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
        //Appointments Items
        dayDisplay = new JMenuItem("Day Display");
        dayDisplay.addActionListener(new HandleMenuItem());
        calendarDisplay.add(dayDisplay);
        weekDisplay = new JMenuItem("Week Display");
        weekDisplay.addActionListener(new HandleMenuItem());
        calendarDisplay.add(weekDisplay);
        monthDisplay = new JMenuItem("Month Display");
        monthDisplay.addActionListener(new HandleMenuItem());
        calendarDisplay.add(monthDisplay);
        menuBar.add(calendarDisplay);
        
        //Right Justified
        menuBar.add(Box.createGlue());
       
        //Logout Menu
        logout = new JMenu("Logout");
        logout.addMenuListener(new HandleLogoutMenu());
        logout.setMnemonic(KeyEvent.VK_M);
        menuBar.add(logout);
        
        loginInfo = new JLabel("Doctor Roberts");
        menuBar.add(loginInfo);
        calendarDisplayInfo = new JLabel("", JLabel.CENTER);
        
        
        
        //Make All Panels
        masterPanel = new JPanel();
        masterPanel.setBackground(Color.LIGHT_GRAY);
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
        tempPanel1.setBackground(Color.white);
        //This layout is a pain in the ass for this setup.
        tempPanel1.setLayout(new GridLayout(100,2));
        //Pull a query for how many appointments there are for each hour.
        int[] numAppointmentsPerHour = {12,5,8,1,2,0,0,1,2,1,0,0,0,0,0,0};
        for (int i = startTime, k = 0; i <= endTime + 12; i++, k++)
        {
            if (i%12 == 0)
            {
                JLabel tempLabel = new JLabel("12:00", JLabel.LEFT);
                //tempLabel.setFont(tempLabel.getFont().deriveFont(20.0f));
                tempPanel1.add(tempLabel);
                
            }
            else
                tempPanel1.add(new JLabel("" + i%12 + ":00", JLabel.LEFT));
            //tempPanel1.add(new JLabel("", JLabel.LEFT));
            for (int j = 0; j< numAppointmentsPerHour[k]; j++)
            {
                //Maybe start using something to hold these day queries
                //so we dont have to contruct this whole Panel each time.
                tempPanel1.add(new JLabel ("Day Query", JLabel.LEFT));
                tempPanel1.add(new JLabel("", JLabel.LEFT));
            }
            tempPanel1.add(new JLabel("", JLabel.LEFT));
            tempPanel1.add(new JLabel("", JLabel.LEFT));
            tempPanel1.add(new JLabel("", JLabel.LEFT));
        }
        //pullDayQueries();
        
        //WeekCalendar
        tempPanel2 = new JPanel();
        tempPanel1.setBackground(Color.white);
        tempPanel2.setLayout(new GridLayout(15,7));
        pullWeekQueries();
        
        
        //MonthCalendar
        tempPanel3 = new JPanel();
        tempPanel1.setBackground(Color.white);
        tempPanel3.setLayout(new GridLayout(5,7));
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
    
    public void pullDayQueries()
    {
        //Pull a query for how many appointments there are for each hour.
        int[] numAppointmentsPerHour = {12,5,8,1,2,0,0,1,2,1,0,0,0,0,0,0};
        for (int i = startTime, k = 0; i <= endTime + 12; i++, k++)
        {
            if (i%12 == 0)
            {
                tempPanel1.add(new JLabel("12:00", JLabel.LEFT));
            }
            else
                tempPanel1.add(new JLabel("" + i%12 + ":00", JLabel.LEFT));
            //tempPanel1.add(new JLabel("", JLabel.LEFT));
            for (int j = 0; j< numAppointmentsPerHour[k]; j++)
            {
                //Maybe start using something to hold these day queries
                //so we dont have to contruct this whole Panel each time.
                tempPanel1.add(new JLabel ("Day Query", JLabel.LEFT));
                tempPanel1.add(new JLabel("", JLabel.LEFT));
            }
            
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
    public void clearMasterPanel()
    {
        masterPanel.removeAll();
        masterPanel.updateUI();
    }
    
    public class HandleHomeMenu implements MenuListener
    {
        @Override
        public void menuSelected(MenuEvent e) {
            clearMasterPanel();
            currentDisplay = 1;
            setVisibleHomePanel(currentEmployee, currentDisplay);
        }
        @Override
        public void menuDeselected(MenuEvent e) {}
        @Override
        public void menuCanceled(MenuEvent e) {}
    }
    public class HandleLogoutMenu implements MenuListener
    {
        @Override
        public void menuSelected(MenuEvent e) {
            clearMasterPanel();
            //Send to Login page and change currentEmployee to whoever logs in.
            setVisibleHomePanel(currentEmployee, currentDisplay);
        }
        @Override
        public void menuDeselected(MenuEvent e) {}
        @Override
        public void menuCanceled(MenuEvent e) {} 
    }
    public class HandleMenuItem implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            System.out.println("Selected: " + command);
            if(command.equals("Create Appointment"))
                setCreateAppointmentPanel();
            if(command.equals("Edit Appointment"))
                setEditAppointmentPanel();
            if(command.equals("Delete Appointment"))
                setCreateAppointmentPanel();
            if(command.equals("Create Patient"))
                setCreatePatientPanel();
            if(command.equals("Edit Patient"))
                setEditPatientPanel();
            if(command.equals("Create Non-Medical Employee"))
                setCreateNonMedicalEmployeePanel();
            if(command.equals("Edit Non-Medical Employee"))
                setEditNonMedicalEmployeePanel();
            if(command.equals("Create Medical Employee"))
                setCreateMedicalEmployeePanel();
            if(command.equals("Edit Medical Employee"))
                setEditMedicalEmployeePanel();
            if(command.equals("Create Admin"))
                setCreateAdminPanel();
            if(command.equals("Edit Admin"))
                setEditAdminPanel();
            if(command.equals("Change Password"))
                setChangePasswordPanel();
            if(command.equals("Day Display"))
                changeToDayDisplay();
            if(command.equals("Week Display"))
                changeToWeekDisplay();
            if(command.equals("Month Display"))
                changeToMonthDisplay();
        }
        void setCreateAppointmentPanel()
        {
            clearMasterPanel();
            setVisibleDatabaseItemPanel(1);
        }
        void setEditAppointmentPanel()
        {
            clearMasterPanel();
            setVisibleDatabaseItemPanel(2);
        }
        void setCreatePatientPanel()
        {
            clearMasterPanel();
            setVisibleDatabaseItemPanel(3);
        }
        void setEditPatientPanel()
        {
            clearMasterPanel();
            setVisibleDatabaseItemPanel(4);
        }
        void setCreateNonMedicalEmployeePanel()
        {
            clearMasterPanel();
            setVisibleDatabaseItemPanel(5);
        }
        void setEditNonMedicalEmployeePanel()
        {
            clearMasterPanel();
            setVisibleDatabaseItemPanel(6);
        }
        void setCreateMedicalEmployeePanel()
        {
            clearMasterPanel();
            setVisibleDatabaseItemPanel(7);
        }
        void setEditMedicalEmployeePanel()
        {
            clearMasterPanel();
            setVisibleDatabaseItemPanel(8);
        }
        void setCreateAdminPanel()
        {
            clearMasterPanel();
            setVisibleDatabaseItemPanel(9);
        }
        void setEditAdminPanel()
        {
            clearMasterPanel();
            setVisibleDatabaseItemPanel(10);
        }
        void setChangePasswordPanel()
        {
            clearMasterPanel();
            setVisibleDatabaseItemPanel(11);
        }
        void changeToDayDisplay()
        {
            clearMasterPanel();
            currentDisplay = 1;
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
    }
    public void setVisibleDatabaseItemPanel(int menuItemPanel)
    {
        switch(menuItemPanel)
        {
            //Create Appointment
            case 1:
                
                break;
            //Edit Appointment
            case 2:
                break;
            //Delete Appointment
            case 3:
                break;
            //Create Patient
            case 4:
                break;
            //Edit Patient
            case 5:
                break;
            //Create Non-Medical Employee
            case 6:
                break;
            //Edit Non-Medical Employee
            case 7:
                break;
            //Create Medical Employee
            case 8:
                break;
            //Edit Medical Employee
            case 9:
                break;
            //Create Admin
            case 10:
                break;
            //Edit Admin
            case 11:
                break;
            //Change Password
            case 12:
                break;
        }
    }
    
    public void setVisibleHomePanel(int menuAccess, int calendarType)
    {
        JPanel tempPanel;
        switch (menuAccess)
        {
            //Admin
            case 1:
                home.setVisible(true);
                appointments.setVisible(true);
                patients.setVisible(true);
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
        CalendarHubTest calHubTest = new CalendarHubTest();
        calHubTest.setMasterToFrame();
        calHubTest.setVisibleHomePanel(calHubTest.currentEmployee, calHubTest.currentDisplay);
        calHubTest.showFrame();
        calHubTest.showMaster();
    }
}
