/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.reports;


import com.starktech.services.DatabaseConnection;
import com.starktech.services.LogData;
import com.starktech.services.QueryReport;
import com.starktech.services.ReportResponse;
import com.starktech.services.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jolaade Adewale
 */
public class Report {

   
    public ArrayList<ReportResponse> sendReport() {
        try(Connection connection = DatabaseConnection.getConnection(); PreparedStatement reportDate = 
                DatabaseConnection.queryDB(connection, QueryReport.ALL_REPORT, "get all reports");
                ResultSet resultset = Utility.execQuery(reportDate);) {
            ArrayList<ReportResponse> sendResults = new ArrayList<>();
            ReportResponse reports;
             while(resultset.next()) {
                 reports = new ReportResponse();
                 reports.setSubject(Utility.parseString(DatabaseConnection.getData(resultset,
                        "String", "Subject")));
                 reports.setResult(Utility.parseString(DatabaseConnection.getData(resultset,
                        "String", "Result")));
                 reports.setCandidates(Utility.parseString(DatabaseConnection.getData(resultset, 
                        "String", "Amount")));
                 reports.setDate(Utility.parseString(DatabaseConnection.getData(resultset, 
                        "String", "Date")));
                 sendResults.add(reports);
             }
             
             return sendResults;
        }
        catch(SQLException e) {
            LogData.Log(e.getMessage(), "Report class");
        }
        
        return null;
    }

   
    public void getSinglereport() {
        throw new IllegalStateException("Please review, use try & catch");
        /*
        reportData = DatabaseConnection.initialiseDatabase(connection, reportData, 
            "get exam report with date", "Report", QueryReport.SUBJECT_REPORT); */
    }
  
    public String getSheet(String s, String date) {
        throw new IllegalStateException("Please use try and catch");
        /*
        StringBuilder resultBuild = new StringBuilder();
        try {
            reportData.setString(1, s);
            reportData.setString(2, date);
            resultset = reportData.executeQuery();

            while (resultset.next()) {
                StringBuilder builder = new StringBuilder();
                builder.append("#");

                builder.append(resultset.getString("Subject").concat(","));
                builder.append(resultset.getString("Result").concat(","));

                builder.append(resultset.getString("Amount").concat(","));
                builder.append(resultset.getString("Date"));
                resultBuild.append(builder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                resultset.close();
                if (connection != null) {
                    connection.close();
                }
                DatabaseConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultBuild.toString(); */
    }
}
