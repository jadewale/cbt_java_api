/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.services;

/**
 *
 * @author jolaadeadewale
 */
public class UserDetails {
    
    public static boolean validPassword(String password) {
        return password != null;
    }
    
    public static boolean validUsername(String username) {
        return username != null;
    }
    
    public static boolean validEmail(String email) {
        return email != null;
    }
    
    public static String unHashPassword(String password) {
        return password;
    }
}
