/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.services;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jolaade Adewale
 */
public class UtilityDataResult {
    
    public static String result(ResultSet result, String string) {
        try{
            return result.getString(string);
        }
        catch(SQLException e) {
            LogData.Log(e.getMessage(), "UtilityDataResult");
        }
        
        throw new IllegalArgumentException(string + " column does not exist");      
    }
    
}
