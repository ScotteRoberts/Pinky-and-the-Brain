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
public class EmployeeSchdule {
    public int monday, tuesday, wednesday, thrusday, friday;
    
    EmployeeSchdule(int eMonday, int eTuesday, int eWednesday, int eThrusday, int eFriday){
        monday = eMonday;
        tuesday = eTuesday;
        wednesday = eWednesday;
        thrusday = eThrusday;
        friday = eFriday;
    }
    
    int testDay(int day){
        // 1 monday, 2 tuesday, 3 thrusday, 4 friday, 5 saturday
        switch(day){
            case 1:
                return monday;
            case 2:
                return tuesday;
            case 3:
                return wednesday;
            case 4:
                return thrusday;
            case 5:
                return friday;
        }
        return 0;
    }
    
}
