/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cecs343.labcode;
import java.awt.*;
import javax.swing.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
/**
 *
 * @author ScottRoberts
 */
public class MenuBarTest {
    JFrame frame;
    
    JMenuBar menuBar;
    
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
    
    public MenuBarTest()
    {
        frame = new JFrame();
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setMaximumSize(DimMax);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        menuBar = new JMenuBar();
        
        //Appointments Menu
        appointments = new JMenu("Appointments");
        appointments.setMnemonic(KeyEvent.VK_M);
        appointments.addMenuListener(new HandleMenu());
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
        patients.addMenuListener(new HandleMenu());
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
        nonMedicalEmployees.addMenuListener(new HandleMenu());
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
        medicalEmployees.addMenuListener(new HandleMenu());
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
        admin.addMenuListener(new HandleMenu());
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
        account.addMenuListener(new HandleMenu());
        //Account's Items
        changePassword = new JMenuItem("Change Password");
        changePassword.addActionListener(new HandleMenuItem());
        account.add(changePassword);
        menuBar.add(account);
        
        //Calendar Display Menu
        calendarDisplay = new JMenu("Calendar Display");
        calendarDisplay.setMnemonic(KeyEvent.VK_M);
        calendarDisplay.addMenuListener(new HandleMenu());
        //Calendar Display Items
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
        
        frame.setJMenuBar(menuBar);
        
    }
    public static void main(String[] args)
    {
        MenuBarTest mbt = new MenuBarTest();
        //mbt.menuBar.remove(mbt.calendarDisplay);
        mbt.calendarDisplay.setVisible(false);
    }
    public class HandleMenu implements MenuListener
    {
        @Override
        public void menuSelected(MenuEvent e) {
            System.out.println("Menu Selected");
        }

        @Override
        public void menuDeselected(MenuEvent e) {
            System.out.println("Menu Deselected");
        }

        @Override
        public void menuCanceled(MenuEvent e) {
            System.out.println("Menu Canceled");
                    
        }
        
    }
    public class HandleMenuItem implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            System.out.println("Selected: " + command);
        }
        
    }
}
