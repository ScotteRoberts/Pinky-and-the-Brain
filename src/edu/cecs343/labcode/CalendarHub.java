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
    Help help = new Help();
    AppointmentCreator dateStuff;
    //---------------------------------------- Single Jframe
    JFrame frame;
    //----------------------------------- Single MenuBar for home page view
    JMenuBar menuBar;
    //--------------- Home Menu button to take you to the calendar day view.
    JMenu home;
    JMenuItem homeItem;
    //----------------------------------------- User Info
    JMenu userInfo;
    JMenuItem createUser;
    JMenuItem editPatient;
    //----------------------------------------- Account menu and items
    JMenu account;
    JMenuItem changeUsername;
    JMenuItem changePassword;
    //----------------------------------------- Appointments menu and items
    JMenu appointments;
    JMenuItem createAppointment;
    JMenuItem editAppointment;
    //----------------------------------------- Calendar Display menu and items
    JMenu calendarDisplay;
    JMenuItem dayDisplay;
        JMenu dayChoice;
        JComboBox day;
    JMenuItem weekDisplay;
    JMenuItem monthDisplay;
    //----------------------------------------- Help
    JMenu helpMenu;
    JMenuItem helpMenuItem;
    
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
        dateStuff = new AppointmentCreator();
        
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
            //Home Menu Item
            homeItem = new JMenuItem("Home");
            homeItem.addActionListener(new HandleMenuItem());
            home.add(homeItem);
        menuBar.add(home);
        
        //UserInfo
        userInfo = new JMenu("User Info");
            //CreateUser
            createUser = new JMenuItem("Create User");
            createUser.addActionListener(new HandleMenuItem());
            userInfo.add(createUser);
            //EditUser
            editPatient = new JMenuItem("Edit Patient");
            editPatient.addActionListener(new HandleMenuItem());
            userInfo.add(editPatient);
        menuBar.add(userInfo);
        
        //Account Menu
        account = new JMenu("Account");
        account.setMnemonic(KeyEvent.VK_M);
            //Account's Items
            changeUsername = new JMenuItem("Change Username");
            changeUsername.addActionListener(new HandleMenuItem());
            account.add(changeUsername);
            changePassword = new JMenuItem("Change Password");
            changePassword.addActionListener(new HandleMenuItem());
            account.add(changePassword);
        menuBar.add(account);
        
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
        
        //Calendar Display Menu
        calendarDisplay = new JMenu("Calendar Display");
        calendarDisplay.setMnemonic(KeyEvent.VK_M);
            //Day Display
            dayDisplay = new JMenuItem("Day Display");
            dayDisplay.addActionListener(new HandleMenuItem());
            calendarDisplay.add(dayDisplay);
            //Day Choice Menu
            dayChoice = new JMenu("Day Choice");
            dayChoice.setMnemonic(KeyEvent.VK_S);
            calendarDisplay.add(dayChoice);
                //Select Date Field
                day = new JComboBox();
                day.addActionListener(dateStuff.new MonthSelection());
                dayChoice.add(day);
                
            //Week Display
            weekDisplay = new JMenuItem("Week Display");
            weekDisplay.addActionListener(new HandleMenuItem());
            calendarDisplay.add(weekDisplay);
            //Month Display
            monthDisplay = new JMenuItem("Month Display");
            monthDisplay.addActionListener(new HandleMenuItem());
            calendarDisplay.add(monthDisplay);
        menuBar.add(calendarDisplay);
        
        //Help Menu
        helpMenu = new JMenu("Help");
            //HelpMenuItem
            helpMenuItem = new JMenuItem("Help");
            helpMenuItem.addActionListener(new HandleMenuItem());
            helpMenu.add(helpMenuItem);
        menuBar.add(helpMenu);
            
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
        //this is a comment
        
          
    }
    
    public void pullDayQueries(String date)
    {
        calendarDisplayInfo.setText("Day View: " + date);
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
    
    class HandleDayChoiceComboBox implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            
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
            
            CalendarButton tempButton = new CalendarButton(i, cal.month+1+"/"+i+"/"+cal.year);
            tempButton.addActionListener(new HandleCalendarButton());
            tempPanel3.add(tempButton);
            
        }
    }
    class CalendarButton extends JButton
    {
        String date;
        CalendarButton(int day, String d)
        {
            super("" + day);
            date = d;
        }
        
    }
    public class HandleCalendarButton implements ActionListener
    {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalendarButton button = (CalendarButton) e.getSource();
                clearMasterPanel();
                currentDisplay = 1;
                pullDayQueries(button.date);
                setVisibleHomePanel(currentEmployee, currentDisplay);
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
            if(command.equals("Create User"))
                setCreateUserPanel();
            if(command.equals("Edit Patient"))
                setEditPatientPanel();
            if(command.equals("Change Username"))
                setChangeUsernamePanel();
            if(command.equals("Change Password"))
                setChangePasswordPanel();
            if(command.equals("Create Appointment"))
                setCreateAppointmentPanel();
            if(command.equals("Edit Appointment"))
                setEditAppointmentPanel();
            if(command.equals("Day Display"))
                changeToDayDisplay();
            if(command.equals("Week Display"))
                changeToWeekDisplay();
            if(command.equals("Month Display"))
                changeToMonthDisplay();
            if(command.equals("Help"))
                setHelpDisplay();
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
    void setCreateUserPanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.createEmployee());
    }
    void setEditPatientPanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.updatePatient());
    }
    void setChangeUsernamePanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.editUserName());
    }
    void setChangePasswordPanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.changePassword());
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
        //pullDayQueries();
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
    void setHelpDisplay()
    {
        clearMasterPanel();
        //_____________________________________________Change this to sysGUI
        masterPanel.add(help.help());
    }
    //-------------------------------------------- Default Panel
    void setLogin()
    {
        clearMasterPanel();
        //Send to Login page and change currentEmployee to whoever logs in.
        menuBar.setVisible(false);
        masterPanel.add(sysGUI.login());
        //masterPanel.add(sysGUI.createEmployee());
    }
    
    //----------------------------------------------------------------- Home Panel Visibility
    public void setVisibleHomePanel(int menuAccess, int calendarType)
    {
        clearMasterPanel();
        menuBar.setVisible(true);
            home.setVisible(true);
            userInfo.setVisible(true);
            account.setVisible(true);
            appointments.setVisible(true);
            calendarDisplay.setVisible(true);
        switch (menuAccess)
        {
            //Admin
            case 1:
                
                break;
            //Medical Employee
            case 2:
                
                break;
            //Non-Medical Employee
            case 3:
                
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
