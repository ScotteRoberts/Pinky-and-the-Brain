/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecs343.labcode;

import static edu.cecs343.labcode.CalendarHub.masterPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author ScottRoberts
 */
public class Help 
{
    public JPanel help()
    {
        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(new GridLayout(20,1));
        JLabel h = new JLabel("Welcome to the Help Menu!", JLabel.CENTER);
        h.setFont(new Font("ariel", Font.PLAIN, 24));
        helpPanel.add(h);
        helpPanel.add(new JPanel());
        
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel h2 = new JLabel("Home Menu: ");
            h2.setFont(new Font("ariel", Font.PLAIN, 16));
            p1.add(h2);
            JLabel h3 = new JLabel("the home menu will take you to the day view"
                    + " of the current day.");
            p1.add(h3);
        helpPanel.add(p1);
        
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel h4 = new JLabel("Create User: ");
            h4.setFont(new Font("ariel", Font.PLAIN, 16));
            p2.add(h4);
            JLabel h5 = new JLabel("create User will allow the Admin to make"
                    + " any type of employee that exists: Admin, Doctor, Nurse"
                    + " NonMedical.");
            p2.add(h5);
        helpPanel.add(p2);
        
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel h6 = new JLabel("Edit Patient: ");
            h6.setFont(new Font("ariel", Font.PLAIN, 16));
            p3.add(h6);
            JLabel h7 = new JLabel("edit Patient will allow the employee to change"
                    + " any of the information for a valid patient in the system.");
            p3.add(h7);
        helpPanel.add(p3);
        
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel h8 = new JLabel("Change Username: ");
            h8.setFont(new Font("ariel", Font.PLAIN, 16));
            p4.add(h8);
            JLabel h9 = new JLabel("admins have the ability to change the login"
                    + " username of any employee in the system.");
            p4.add(h9);
        helpPanel.add(p4);
        
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel h10 = new JLabel("Change Password: ");
            h10.setFont(new Font("ariel", Font.PLAIN, 16));
            p5.add(h10);
            JLabel h11 = new JLabel("the change password feature is for the current"
                    + " employee logged in. You must provide the current password"
                    + " and a new password for the change to occur.");
            p5.add(h11);
        helpPanel.add(p5);
        
        JPanel p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel h12 = new JLabel("Create Appointment: ");
            h12.setFont(new Font("ariel", Font.PLAIN, 16));
            p6.add(h12);
            JLabel h13 = new JLabel("to make an appointment, you must have a time"
                    + ", date, and doctor for the appointment. Choose any doctor"
                    + " in the drop down menu and pick an available time for an"
                    + " appointment.");
            p6.add(h13);
        helpPanel.add(p6);
        
        JPanel p7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel h14 = new JLabel("Edit Appointment: ");
            h14.setFont(new Font("ariel", Font.PLAIN, 16));
            p7.add(h14);
            JLabel h15 = new JLabel("please provide valid patient information,"
                    + " the doctor for the given appointment, and choose the edit"
                    + " or delete option.");
            p7.add(h15);
        helpPanel.add(p7);
        
        JPanel p8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel h16 = new JLabel("Day Display: ");
            h16.setFont(new Font("ariel", Font.PLAIN, 16));
            p8.add(h16);
            JLabel h17 = new JLabel("shows the day view, which contains all"
                    + " appointments for each doctor for the date shown.");
            p8.add(h17);
        helpPanel.add(p8);
        
        JPanel p9 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel h18 = new JLabel("Week Display: ");
            h18.setFont(new Font("ariel", Font.PLAIN, 16));
            p9.add(h18);
            JLabel h19 = new JLabel("shows the week view, which contains all"
                    + " filled appointment times for each day of the given week.");
            p9.add(h19);
        helpPanel.add(p9);
        
        JPanel p10 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel h20 = new JLabel("Month Display: ");
            h20.setFont(new Font("ariel", Font.PLAIN, 16));
            p10.add(h20);
            JLabel h21 = new JLabel("shows the month view, which contains all"
                    + " days of the month with clickable links to the day view"
                    + " of the clicked day.");
            p10.add(h21);
        helpPanel.add(p10);
        
        JPanel p11 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel h22 = new JLabel("Logout: ");
            h22.setFont(new Font("ariel", Font.PLAIN, 16));
            p11.add(h22);
            JLabel h23 = new JLabel("logs the user out and waits for a new login.");
            p11.add(h23);
        helpPanel.add(p11);
        
        JScrollPane helpScroll = new JScrollPane(helpPanel);
        helpScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        helpScroll.setBounds(0, 0, 1200, 600);

        JPanel contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1200,600));
        contentPanel.add(helpScroll);


        return contentPanel;
    }
}
    

