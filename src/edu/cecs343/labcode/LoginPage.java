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

public class LoginPage {
    
    LoginPage(){}
    
    public JPanel login(){
    
        //Fucntion sets up the login page
        
        
        JPanel mainPanel;
        SystemGUI.clearPanel();
 
        //SystemGUI.wait = true;
        mainPanel = new JPanel(new GridLayout(5,1));
        // Show title
        JLabel message = new JLabel("Welcome to Prometheus");
        message.setFont(new Font("ariel", Font.PLAIN, 24));
        mainPanel.add(message);
        
        // Set first login panel
        JPanel userNameP1 = new JPanel(new GridLayout(1,2));
        JLabel userName = new JLabel("Username");
        SystemGUI.eUserName = new JTextField(10);
        userNameP1.add(userName);
        userNameP1.add(SystemGUI.eUserName);
        mainPanel.add(userNameP1);
        
        // Set second login panel
        JPanel userNameP2 =   new JPanel(new GridLayout(1,2));
        JLabel password = new JLabel("Password");
        SystemGUI.ePassword = new JPasswordField(10);
        userNameP2.add(password);
        userNameP2.add(SystemGUI.ePassword);
        mainPanel.add(userNameP2);
        
        // Set thrid panel
        JPanel userNameP3 = new JPanel(new GridLayout(1,1));
        JButton loginOk = new JButton("Login");
        loginOk.addActionListener(new loginResponse());
        userNameP3.add(loginOk);
        mainPanel.add(userNameP3);
        
        
        // Set fourth panel
        JPanel userNameP4 = new JPanel(new GridLayout(1,1));
        SystemGUI.mainLabel = new JLabel("");
        userNameP4.add(SystemGUI.mainLabel);
        mainPanel.add(userNameP4);
        
        //SystemGUI.mainFrame.add(mainPanel);
        SystemGUI.masterPanel.add(mainPanel);
        SystemGUI.updatePanel();
        return mainPanel;
    }
    
    class AccountInfo
    {
        String userName;
        int employeeType;
        public AccountInfo()
        {
            
        }
    }
    
    class loginResponse implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(!SystemGUI.eUserName.getText().equals("") && 
                    !(new String(SystemGUI.ePassword.getPassword())).equals("")){
                // Test username and then change status
                System.out.println("Login probe 1");
                // How will I return employee information??
                if(SystemGUI.sysSQL.testUserName(SystemGUI.eUserName.getText())){
                    String tempPassword = new String(SystemGUI.ePassword.getPassword());
                    SystemGUI.currentEmployee = SystemGUI.sysSQL.getEmployeeUserName(SystemGUI.eUserName.getText().toUpperCase(), 
                            tempPassword);
                    if(SystemGUI.currentEmployee != null){
                        SystemGUI.mainLabel.setText("Found");
                        // Change screen to main screen
                        int employeeType = SystemGUI.sysSQL.testEmployeeType(SystemGUI.currentEmployee.eID);
                        String currentUsername = SystemGUI.eUserName.getText().toUpperCase();
                        SystemGUI.calHub.loginInfo.setText(currentUsername);
                        SystemGUI.calHub.currentDisplay = 1;
                        int displayType = 1;
                        String dayDate = SystemGUI.calHub.cal.getDay();
                        switch(employeeType){
                            case 1:
                                //admin
                                SystemGUI.calHub.setVisibleHomePanel(employeeType, displayType, dayDate);
                                SystemGUI.calHub.currentEmployee = employeeType;
                                break;
                            case 2:
                                //Medical
                                SystemGUI.calHub.setVisibleHomePanel(employeeType, displayType, dayDate);
                                SystemGUI.calHub.currentEmployee = employeeType;
                                break;
                            case 3:
                                //NonMedical
                                SystemGUI.calHub.setVisibleHomePanel(employeeType, displayType, dayDate);
                                SystemGUI.calHub.currentEmployee = employeeType;
                                break;
                            default:
                                break;
                        }
                             
                    }
                    else{
                        SystemGUI.mainLabel.setText("Invalid password");
                        }
                }
                else{
                    SystemGUI.mainLabel.setText("Invalid username");
                }
                
                
                return;
                
            }
            else{
                SystemGUI.mainLabel.setText("NULL");
            }
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
