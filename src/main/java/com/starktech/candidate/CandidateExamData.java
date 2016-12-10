/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.candidate;


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
import java.util.HashMap;

/**
 *
 * @author jolaadeadewale
 */
public class CandidateExamData {

    public ReportSummary getResultSummary(String subject) {
        try {
            ReportSummary report = new ReportSummary();
            try (final Connection connection = DatabaseConnection.getConnection(); final PreparedStatement getDistinctColums = 
                    DatabaseConnection.queryDB(connection, QueryReport.GET_DISTINCT_COLUMN,
                    "Get distinct column"); final PreparedStatement getAverage = DatabaseConnection.queryDB(connection, 
                         QueryReport.GET_AVERAGE, "get Average data")) {
                            DatabaseConnection.setData(getDistinctColums, subject,
                                "String", 1);
                
                ArrayList<String> dateYear = new ArrayList<>();
                try (ResultSet result = Utility.execQuery(getDistinctColums)) {
                    while (result.next()) {
                        dateYear.add(Utility.parseString(DatabaseConnection.getData(result,
                                "String", "dateYear")));
                    }
                }
                
                
                final HashMap<String, String> averageResult = new HashMap(); 
                
                dateYear.forEach(key -> {
                    DatabaseConnection.setData(getAverage, subject,
                                "String", 1);
                    DatabaseConnection.setData(getAverage, key,
                                "String", 2);
                    
                    try(final ResultSet resultSet = Utility.execQuery(getAverage)) {
                        
                        while(resultSet.next()) {
                            averageResult.put(key, Utility.parseString(resultSet.getInt(1)));
                        }
                    }catch(SQLException e) {
                        LogData.Log(e.getMessage(), "Get Average Data");
                    }
                });
               report.setTotalAverage(averageResult);
               report.setMaxGrades(getMaxGrades(connection, subject));
               report.setCompleteAverage(getCompleteAverage(connection, subject));
               report.setPassRate(getPassRate(connection, subject));
               report.setTotalCandidates(getTotalCandidates(connection, subject));
               
               return report;

            }
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "Result");
        }
        return null;
    }
    
    private ArrayList<ReportResponse> getMaxGrades(Connection connection, String subject) throws SQLException{
        try(PreparedStatement getMaxGrades = DatabaseConnection.queryDB(connection, 
                QueryReport.GET_MAX_GRADES, "Get Highest grade for course")) {
            
            DatabaseConnection.setData(getMaxGrades, subject,
                                "String", 1);
            
            try(ResultSet result = Utility.execQuery(getMaxGrades)) {
                ArrayList<ReportResponse> reportResponseStore = new ArrayList();
                ReportResponse reports;
                while(result.next()) {
                    reports = new ReportResponse();
                    reports.setSubject(subject);
                    reports.setDate(Utility.parseString(DatabaseConnection.getData(result, 
                        "String", "dateYear")));
                    reports.setCandidates(Utility.parseString(DatabaseConnection.getData(result, 
                        "String", "matric")));
                    reports.setResult(Utility.parseString(DatabaseConnection.getData(result, 
                        "String", "score")));
                    reportResponseStore.add(reports);
                }
                
                return reportResponseStore;
            }
            
        }
    }
    
    private int getCompleteAverage(Connection connection, String subject) throws SQLException{
       try(PreparedStatement getCompleteAverage = DatabaseConnection.queryDB(connection, 
               QueryReport.GET_COMPLETE_AVG_EXAM, "Average candidates that completed exam")) {
           
           DatabaseConnection.setData(getCompleteAverage, subject, "String", 1);
           
           try(ResultSet result = Utility.execQuery(getCompleteAverage)) {
               while(result.next()) {
                   return Utility.parseNumber(String.valueOf(result.getInt(1)));
               }
           }
       }
       return 0;
    }
    
    private int getPassRate(Connection connection, String subject) throws SQLException {
        try(PreparedStatement getPassRate = DatabaseConnection.queryDB(connection, 
            QueryReport.GET_PASS_RATE, "Get Pass rate for Candidates")) {
            
            DatabaseConnection.setData(getPassRate, subject, "String", 1);
            try(ResultSet results = Utility.execQuery(getPassRate)) {
                while(results.next()) {
                    return Utility.parseNumber(String.valueOf(results.getInt(1)));
                }
            }
        }
        
        return 0;
    }
    
    private int getTotalCandidates(Connection connection, String subject) throws SQLException{
        try(PreparedStatement getTotal = DatabaseConnection.queryDB(connection, 
                QueryReport.GET_TOTAL_CANDIDATE, "Get Total Candidat")) {
            
             DatabaseConnection.setData(getTotal, subject, "String", 1);
             try(ResultSet result = Utility.execQuery(getTotal)) {
                 while(result.next()) {
                    return Utility.parseNumber(String.valueOf(result.getInt(1))); 
                 }
             }
        }
        
        return 0;
    }
    
    class ReportSummary {
        
        private String exam;
        private int passRate;
        private int completeAverage;
        private ArrayList<ReportResponse> maxGrades;
        private HashMap<String, String> totalAverage;
        private int totalCandidates;

        public String getExam() {
            return exam;
        }

        public void setExam(String exam) {
            this.exam = exam;
        }

        public int getPassRate() {
            return passRate;
        }

        public void setPassRate(int passRate) {
            this.passRate = passRate;
        }

        public int getTotalCandidates() {
            return totalCandidates;
        }

        public void setTotalCandidates(int totalCandidates) {
            this.totalCandidates = totalCandidates;
        }

        public int getCompleteAverage() {
            return completeAverage;
        }

        public void setCompleteAverage(int completeAverage) {
            this.completeAverage = completeAverage;
        }

        public ArrayList<ReportResponse> getMaxGrades() {
            return maxGrades;
        }

        public void setMaxGrades(ArrayList<ReportResponse> maxGrades) {
            this.maxGrades = maxGrades;
        }

        public HashMap<String, String> getTotalAverage() {
            return totalAverage;
        }

        public void setTotalAverage(HashMap<String, String> totalAverage) {
            this.totalAverage = totalAverage;
        }
        
    }
}
 