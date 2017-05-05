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

public class PatientCreation {
    
    PatientCreation(){
        
    }
        public JPanel createPatient(){
        // Patient creation GUI is set up here
        JPanel mainPanel;
        SystemGUI.clearPanel();
        mainPanel = new JPanel(new GridLayout(5, 1));
        
        // setup title
        JLabel message = new JLabel("Create new Patient", 
                JLabel.CENTER);
        
        message.setFont((new Font("ariel", Font.PLAIN, 24)));
        mainPanel.add(message);
        
        // setup first information panel
        JPanel panelInfo1 = new JPanel(new GridLayout(1,4));
        JLabel firstName = new JLabel("First Name", JLabel.CENTER);
        JLabel lastName = new JLabel("Last Name", JLabel.CENTER);
        JLabel phone = new JLabel("Phone", JLabel.CENTER);
        JLabel email =  new JLabel("Email", JLabel.CENTER);
        panelInfo1.add(firstName);panelInfo1.add(lastName);
        panelInfo1.add(phone);panelInfo1.add(email);
        
        JPanel panelInfo2 = new JPanel(new GridLayout(1,4));
        panelInfo2.add(SystemGUI.eFirstName);
        panelInfo2.add(SystemGUI.eLastName);
        panelInfo2.add(SystemGUI.ePhone);
        panelInfo2.add(SystemGUI.email);
        mainPanel.add(panelInfo1);
        mainPanel.add(panelInfo2);
        
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
        SystemGUI.mainLabel =  new JLabel("");
        mainPanel.add(SystemGUI.mainLabel);
        
        SystemGUI.masterPanel.add(mainPanel);
        SystemGUI.updatePanel();
        return mainPanel;
        
        
    }
    
    class ActionCreatePatient implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!SystemGUI.eFirstName.getText().equals("") && !SystemGUI.eLastName.getText().equals("") && 
                    !SystemGUI.ePhone.getText().equals("") && !SystemGUI.email.getText().equals("")){
                System.out.println("Create Patient 1");
                //If all paramaters are entered the code moves foward
                //mainLabel.setText("Everthing has been entered");
                if(SystemGUI.currentEmployee != null){
                    //If employees is logged in the code moves forward
                    Patient tempPatient = new Patient("NA", SystemGUI.currentEmployee.eID, 
                            SystemGUI.eFirstName.getText(), SystemGUI.eLastName.getText(), 
                            SystemGUI.ePhone.getText(), "NA");
                    tempPatient.setEmail(SystemGUI.email.getText());
                    //There might be an issue of clicking the button mutlitple
                    //time and making the same patient over and over again.
                    SystemGUI.sysSQL.createNewPatient(tempPatient);
                    
                    SystemGUI.mainLabel.setText("Patient Created");
                            
                }
                else{
                    SystemGUI.mainLabel.setText("Not logged in");
                }
            }
            else{
                SystemGUI.mainLabel.setText("Parameters are missing");
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
