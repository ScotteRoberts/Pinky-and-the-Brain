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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
    CalendarTest calHome;
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
        JMenuItem createPatient;
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
    JPanel dayInfoPanel;
    JPanel weekInfoPanel;
    JPanel monthInfoPanel;
    JPanel contentPanel;
    //---------------------------------------- All Labels
    JLabel loginInfo;
    JLabel calendarDisplayInfo;
    JLabel daysOfWeek;
    //---------------------------------------- Scroll Panes
    JScrollPane dayCalendarPane;
    JScrollPane weekCalendarPane;
    JScrollPane monthCalendarPane;
    //---------------------------------------- Increment/Decrement Buttons
    DayButton leftDay;
    
    WeeklyView wv = new WeeklyView();
    
    public CalendarHub()
    {
        //---------------------------------------- Main parts
        cal = CalendarTest.getInstance();
        calHome = CalendarTest.getInstance();
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
            //CreatePatient
            createPatient = new JMenuItem("Create Patient");
            createPatient.addActionListener(new HandleMenuItem());
            userInfo.add(createPatient);
            //EditUser
            editPatient = new JMenuItem("Edit Patient");
            editPatient.addActionListener(new HandleMenuItem());
            userInfo.add(editPatient);
        menuBar.add(userInfo);
        
        //Account Menu
        account = new JMenu("Account");
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
        dayInfoPanel = new JPanel();
        dayInfoPanel.setLayout(new GridLayout(100,2));
        dayInfoPanel.setBackground(Color.white);
        
        
        //WeekCalendar
        weekInfoPanel = new JPanel();
        weekInfoPanel.setBackground(Color.white);
        weekInfoPanel.setLayout(new GridLayout(15,7));
        
        //MonthCalendar
        monthInfoPanel = new JPanel();
        //monthInfoPanel.setBackground(Color.white);
        monthInfoPanel.setLayout(new GridLayout(5,7));
        
        dayChoice.setVisible(false);
    }
    
    public void setDayInfoPanel (String dayDate)
    {
        clearPanel(dayInfoPanel);
        System.out.println("The Date is: " + dayDate);
        calendarDisplayInfo.setText("Day View: " + dayDate);
        ArrayList<Appointment> appts = sysSQL.allAppointmentByDate(dayDate);
        if (appts.size() == 0)
            dayInfoPanel.add(new JLabel("No appointments today", JLabel.CENTER));
        
        String hour = null;
        for (int i = 0; i < appts.size(); i++)
        {
            if (!appts.get(i).aTime.equals(hour))
            {
                hour = appts.get(i).aTime;
                dayInfoPanel.add(new JLabel());
                JLabel tempLabel = new JLabel(hour, JLabel.LEFT);
                dayInfoPanel.add(tempLabel);
            }
            Patient tempPat = sysSQL.lookUpPatient(appts.get(i).pID);
            Employee tempDoc = sysSQL.lookUpEmployee(appts.get(i).doctorID);
            dayInfoPanel.add(new JLabel ("Patient: " + tempPat.patientLN + ", " + 
                    tempPat.patientFN + "  Doctor: " + tempDoc.lastName + ", "
                    + tempDoc.firstName, JLabel.LEFT));
        }
        calendarInfoPanel.add(calendarDisplayInfo);
        calendarInfoPanel.add(daysOfWeek);
        masterPanel.add(calendarInfoPanel);
        daysOfWeek.setVisible(false);

        dayCalendarPane = new JScrollPane(dayInfoPanel);
        dayCalendarPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dayCalendarPane.setBounds(0, 0, 1100, 600);
        contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1100,600));
        contentPanel.add(dayCalendarPane);

        JPanel dayAndButton = new JPanel();
        
        DayButton leftDay = new DayButton("<<", this.cal.decrementDay());
        leftDay.addActionListener(new HandleDayButton());
        DayButton rightDay = new DayButton(">>", this.cal.incrementDay());
        rightDay.addActionListener(new HandleDayButton());
        dayAndButton.add(leftDay);
        dayAndButton.add(contentPanel);
        dayAndButton.add(rightDay);
        masterPanel.add(dayAndButton);
    }
    
    
    public void setWeekInfoPanel(String dayDate)
    {
        clearPanel(weekInfoPanel);
        JPanel fullWeekPanel = new JPanel(new GridLayout(1,2));
        calendarInfoPanel.add(calendarDisplayInfo);
        calendarInfoPanel.add(daysOfWeek);
        masterPanel.add(calendarInfoPanel);
        daysOfWeek.setVisible(true);
        
        calendarDisplayInfo.setText("Week View: " + cal.getWeekRange());
        JPanel times = new JPanel(new GridLayout(40,1));
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if(i == 12)
                    times.add(new JLabel("" + 12 + ":" + j));
                else
                    times.add(new JLabel("" + (i+8) % 12 + ":" + (j*15)));
            }  
        }
        fullWeekPanel.add(times);
        
        JPanel appointments = new JPanel();
        appointments.setLayout(new GridLayout(20,1));
        for (int i = 0; i < 20; i++)
        {
            JPanel tempPanel = new JPanel(new GridLayout(1,7));
            for (int j = 0; j < 7; j++)
            {
               tempPanel.add(new JButton(""+j)); 
            }
           
            appointments.add(tempPanel);
        }
        
        weekCalendarPane = new JScrollPane(weekInfoPanel);
        weekCalendarPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        weekCalendarPane.setBounds(0, 0, 1100, 600);
        contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1100,600));
        contentPanel.add(weekCalendarPane);

        JPanel weekAndButton = new JPanel();
        JButton leftWeek = new JButton("<<");
        JButton rightWeek = new JButton(">>");
        weekAndButton.add(leftWeek);
        weekAndButton.add(contentPanel);
        weekAndButton.add(rightWeek);
        masterPanel.add(weekAndButton);
    }
    
    
    
    
    public void setMonthInfoPanel(String monthDate)
    {
        clearPanel(monthInfoPanel);
        calendarInfoPanel.add(calendarDisplayInfo);
        calendarInfoPanel.add(daysOfWeek);
        masterPanel.add(calendarInfoPanel);
        daysOfWeek.setVisible(true);

        calendarDisplayInfo.setText("Month View: " + cal.getMonth());
        CalendarTest tempCal = cal;
        //Calculate first day of Month
        tempCal.setMonthToStart();
        //This line needs fixing: cal.dayOfWeek is stuck on a different variable than the new calendar day we want.
        int tempDayOfWeek = tempCal.cal.get(Calendar.DAY_OF_WEEK);  //Get day of Week for new calendar
        //Create space for the first day of the month on the proper day of week
        for (int i = 1; i < tempDayOfWeek; i++)
        {
            monthInfoPanel.add(new JLabel("", JLabel.CENTER));
        }
        for (int i = 1; i <= cal.daysInMonth[cal.month]; i++)
        {
            
            DayButton tempButton = new DayButton(i, cal.month+1+"/"+i+"/"+cal.year);
            tempButton.addActionListener(new HandleDayButton());
            monthInfoPanel.add(tempButton);
            
        }
        
        monthCalendarPane = new JScrollPane(monthInfoPanel);
        monthCalendarPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        monthCalendarPane.setBounds(0, 0, 1100, 600);
        
        contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1100,600));
        contentPanel.add(monthCalendarPane);

        JPanel monthAndButton = new JPanel();
        JButton leftMonth = new JButton("<<");
        JButton rightMonth = new JButton(">>");
        monthAndButton.add(leftMonth);
        monthAndButton.add(contentPanel);
        monthAndButton.add(rightMonth);
        masterPanel.add(monthAndButton);
    }
    
    class DayButton extends JButton
    {
        String dayDate = null;
        //DayButton
        DayButton(int day, String d)
        {
            super("" + day);
            dayDate = d;
        }
        DayButton(String direction, String d)
        {
            super("" + direction);
            dayDate = d;
        }
        void setDay(String d)
        {
            dayDate = d;
        }
    }
    
    class HandleDayButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            DayButton button = (DayButton) e.getSource();
            clearMasterPanel();
            System.out.println("Date Pressed: " + button.dayDate);
            currentDisplay = 1;
            setVisibleHomePanel(currentEmployee, currentDisplay, button.dayDate);
            if(button.getText().equals("<<"))
                button.setDay(cal.decrementDay());
            else
                button.setDay(cal.incrementDay());
        }
    }
    
    class WeekButton extends JButton
    {
        String weekDate = null;
        //DayButton
        WeekButton(String direction, String d)
        {
            super("" + direction);
            weekDate = d;
        } 
    }
    
    class HandleWeekButton implements ActionListener
    {
            @Override
            public void actionPerformed(ActionEvent e) {
                WeekButton button = (WeekButton) e.getSource();
                clearMasterPanel();
                System.out.println("Date Pressed: " + button.weekDate);
                currentDisplay = 2;
                //Conflicting Problem!!!!
                //setWeekInfoPanel(button.weekDate);
                setVisibleHomePanel(currentEmployee, currentDisplay, button.weekDate);
            }
    }
    
    class MonthButton extends JButton
    {
        String monthDate = null;
        //DayButton
        MonthButton(String direction, String d)
        {
            super("" + direction);
            monthDate = d;
        } 
    }
    
    class HandleMonthButton implements ActionListener
    {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthButton button = (MonthButton) e.getSource();
                clearMasterPanel();
                System.out.println("Date Pressed: " + button.monthDate);
                currentDisplay = 3;
                //Conflicting Problem!!!!
                //setWeekInfoPanel(button.weekDate);
                setVisibleHomePanel(currentEmployee, currentDisplay, button.monthDate);
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
            if(command.equals("Create Patient"))
                setCreatePatientPanel();
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
        setVisibleHomePanel(currentEmployee, currentDisplay, calHome.getDay());
    }
    void setCreateUserPanel()
    {
        clearMasterPanel();
        masterPanel.add(sysGUI.createEmployee());
    }
    void setCreatePatientPanel()
    {
        System.out.println("Create Patient");
        clearMasterPanel();
        masterPanel.add(sysGUI.createPatient());
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
        setVisibleHomePanel(currentEmployee, currentDisplay, calHome.getDay());
    }
    /*
    void setDayDisplay()
    {
        clearMasterPanel();
        currentDisplay = 1;
        //pullDayQueries();
        setVisibleHomePanel(currentEmployee, currentDisplay);
    }
    */
    void changeToWeekDisplay()
    {
        clearMasterPanel();
//        currentDisplay = 2;
//        setVisibleHomePanel(currentEmployee, currentDisplay, calHome.getDay());
        masterPanel.add(wv.weekView());
    }
    void changeToMonthDisplay()
    {
        clearMasterPanel();
        currentDisplay = 3;
        setVisibleHomePanel(currentEmployee, currentDisplay, calHome.getMonth());
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
    public void setVisibleHomePanel(int menuAccess, int calendarType, String date)
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
                createUser.setVisible(true);
                createPatient.setVisible(false);
                break;
            //Medical Employee
            case 2:
                createUser.setVisible(false);
                createPatient.setVisible(true);
                break;
            //Non-Medical Employee
            case 3:
                createUser.setVisible(false);
                createPatient.setVisible(false);
                break;
        }
        switch(calendarType)
        {
            case 0:
                break;
            //day
            case 1:
                setDayInfoPanel(date);
                break;
            //week
            case 2:
                setWeekInfoPanel(date);
                break;
            //month
            case 3:
                setMonthInfoPanel(date);
                break;
        }
    }
    
    
    public static void main(String[] args) 
    {
        
        CalendarHub calHub = new CalendarHub();
        calHub.setMasterToFrame();
//        String week[] = calHub.cal.getDaysOfWeek();
//        for(int i= 0;i < week.length; i++){
//            System.out.println(week[i]);
//        }
//        calHub.cal.incrementWeekRange();
//        week = calHub.cal.getDaysOfWeek();
//        for(int i= 0;i < week.length; i++){
//            System.out.println(week[i]);
//        }
        
        calHub.setLogin();
        //calHub.setVisibleHomePanel(calHub.currentEmployee, calHub.currentDisplay);
        //calHub.setCreateUserPanel();
        calHub.showFrame();
        calHub.showMaster();
        AppointmentTester ap = new AppointmentTester();
        
    }
}
