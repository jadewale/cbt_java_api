/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.services;

/**
 * handles Administrator database query 
 * @author jolaadeadewale
 */
public class QueryAdministrator {
    
    public static final String CREATE_ADMIN = "INSERT  INTO "
        + "administrator(UserName, Password, FirstName, LastName,Email) "
        + "VALUES (?, ?, ?, ?, ?)";
    
    public static final String ADMIN_PASSWORD = "SELECT password FROM administrator"
        + " WHERE email=?";
    
    public static final String EDIT_ADMIN = "SELECT * FROM administrator WHERE "
        + "UserName =? ";
    
    public static final String DELETE_ADMIN = "DELETE * FROM   administrator "
        + "WHERE UserName =? ";
    
    public static final String UPDATE_ADMIN_OLD = "UPDATE administrator SET FirstName =?, "
        + "LastName=?, Email=?, UserName=?, Password=? WHERE UserName = ?";
    
    public static final String UPDATE_ADMIN = "UPDATE administrator SET FirstName =?, "
        + "LastName=?, Email=?, MiddleName=?, Number=?, Number2=?, Address=?, "
        + "Address2=?, Initials=?,  Gender=? WHERE UserName = ?";
    
    public static final String LOG_IN_ADMIN = "SELECT * FROM administrator WHERE administrator.UserName =? "
        + "and administrator.Password=? ";
    
}
