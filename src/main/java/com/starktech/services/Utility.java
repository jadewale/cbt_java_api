/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author jolaadeadewale
 */
public class Utility {
    public static int execUpdate(PreparedStatement statement) {
        try{
            return statement.executeUpdate();
        }
        catch(SQLException e) {
            LogData.Log(e.getMessage(), "Utility");
        }
        
        LogData.Log("An expection occured in update", "Utility");
        throw new IllegalStateException("An expection occured in update");
        
    }
    
    public static ResultSet execQuery(PreparedStatement statement) {
        try{
            return statement.executeQuery();
        }
        catch(SQLException e) {
            LogData.Log(e.getMessage(), "Utility");
        }
        
        LogData.Log("An expection occured in update", "Utility");
        throw new IllegalStateException("An expection occured in query");
    }
    
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }
    
    public static void endConnection(Object obj) {
        try{
            useReflection(obj, "close");
        }
        catch(Exception e) {
            LogData.Log(e.getMessage(), "Utility");
        }
    }
    
    public static void useReflection(Object obj, String methodToCall) {
        try{
            Method method = obj.getClass().getMethod(methodToCall, (Class<?>) null);
            method.invoke(obj, (Object) null);
        }
        catch(NoSuchMethodException | IllegalAccessException | 
                InvocationTargetException e) {
            LogData.Log(e.getMessage(), "Utility");
        }
    }
    
    public static Time parseTime(String time) {
        return Time.valueOf(time);
    }
    
    public static String parseString(Object data) {
        if(data == null) {
            return "null";
        }
        return data.toString();
    }
     
    public static int parseNumber(Object number) {
        return Integer.parseInt((String) number);
    }
    
    public static Date parseDate(String date) {
        try{
        java.util.Date dateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        
        return new java.sql.Date(dateUtil.getTime());
        }
        catch(ParseException e) {
            LogData.Log(e.getMessage(), "Utility");
        }
        
        throw new IllegalStateException("Error in parse Date");
    }
    
    public static Short parseShort(String value) {
        return Short.parseShort(value);
    }
    
    public static void appender(StringBuilder struct, String data) {
        struct.append(data);
    }
    
    public static void endPreparedStatement(PreparedStatement prep) {
        if(isNotNull(prep)) {
            try{
              prep.close();  
            }
            catch(SQLException e) {
                LogData.Log("Prepared statement error ".concat(e.getMessage()), "Utility");
            }
        }
    }
    
    public static void endResultSet(ResultSet res) {
        try{
            if(isNotNull(res)) {
                res.close();
            }
        }
        catch(SQLException e) {
            LogData.Log("Result set error ".concat(e.getMessage()), "Utility");
        }
    }
    
    public static String response(Object obj) {
        return new Gson().toJson(obj);
    }
    
    public static String [] JsonParse(String data, String [] list, int size) {
        JsonObject  jobject = new JsonParser().parse(data).getAsJsonObject();
        String [] jsonData = new String[size];
        int count = 0;
        for (String value : list) {
            if(jobject.get(value).isJsonPrimitive()) {
                jsonData[count++] = jobject.get(value).getAsString();
            }
            if(jobject.get(value).isJsonArray()) {
                jsonData[count++] = jobject.get(value).getAsJsonArray().toString();
            }
            
            if(jobject.get(value).isJsonObject()) {
                jsonData[count++] = jobject.get(value).getAsJsonObject().toString();
            }         
        }
        
        return jsonData;
    }
    
    public static ArrayList<String> stringToArray(String data, String delimiter){
        int first = data.indexOf(delimiter);
        int second = 0;
        ArrayList<String> candidateId = new ArrayList<>();
        
        while(first != -1) { 
            second = data.indexOf(delimiter, (first + 1));
            candidateId.add(data.substring((first + 1), second));
            first = data.indexOf(delimiter, (second + 1));
        }
        
        return candidateId;
    }
}
