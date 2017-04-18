/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecs343.labcode;

import static edu.cecs343.labcode.CalendarHub.masterPanel;
import java.awt.Dimension;
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
        helpPanel.setLayout(new GridLayout(4,1));
        JLabel h1 = new JLabel("Do you feel lucky punk? Well, do yah?");
        helpPanel.add(h1);
        JLabel h2 = new JLabel("1. This is not a drill.");
        helpPanel.add(h2);
        JLabel h3 = new JLabel("2. This is a drill.");
        helpPanel.add(h3);

        JScrollPane helpScroll = new JScrollPane(helpPanel);
        helpScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        helpScroll.setBounds(0, 0, 1200, 600);

        JPanel contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1200,600));
        contentPanel.add(helpScroll);


        return contentPanel;
    }
}
    

