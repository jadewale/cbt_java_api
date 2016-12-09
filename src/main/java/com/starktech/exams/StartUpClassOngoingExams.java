/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.exams;


import com.starktech.services.DatabaseConnection;
import com.starktech.services.ExamBuilder;
import com.starktech.services.LogData;
import com.starktech.services.Query;
import com.starktech.services.QueryStudents;
import com.starktech.services.Utility;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.starktech.endpoint.ExamResource;

/**
 *
 * @author bola odesile
 */
public class StartUpClassOngoingExams {

    //public static HashMap<String, String> studentsRegistered = new HashMap();
    //public static boolean isLogin = false;
    public Response manageOngoingExam() {
        File files = new File(System.getProperty("user.home")
                + System.getProperty("file.separator") + "Images");
        if (!files.isDirectory()) {
            files.mkdir();
        }
        ExamBuilder exams = null;
        try {
            try (Connection connection = DatabaseConnection.getConnection();
                    PreparedStatement getOngoingExams = DatabaseConnection.queryDB(connection, Query.ONGOING_EXAMS,
                            "Start Ongoing Exams"); PreparedStatement getAllRegisteredStudents
                    = DatabaseConnection.queryDB(connection, QueryStudents.GET_EXAM_STUDENTS,
                            "Get All Exam candidates"); ResultSet resultSet1 = Utility.execQuery(getAllRegisteredStudents);
                    ResultSet resultSet = Utility.execQuery(getOngoingExams); PreparedStatement examRegister
                    = DatabaseConnection.queryDB(connection, QueryStudents.GET_ALL_STUDENTS, "All Students")) {

                Runnable multhiThread = new Runnable() {
                    @Override
                    public void run() { 
                        try {
                            System.out.println("Started another thread");
                            ExamResource.registeredCourses = new HashMap();
                            ExamResource.getNewRegisteredStudents = "";
                            while (resultSet1.next()) {
                                String matricNumber = Utility.parseString(DatabaseConnection.getData(resultSet1,
                                        "String", "matricExam")).split("\\*\\*\\*")[0];
                                ArrayList<String> exams = new ArrayList<>(1);
                                if (ExamResource.registeredCourses.containsKey(matricNumber)) {
                                    exams = ExamResource.registeredCourses.get(matricNumber);
                                    exams.add(Utility.parseString(DatabaseConnection.getData(resultSet1,
                                            "String", "ExamName")));
                                    ExamResource.registeredCourses.put(matricNumber, exams);
                                    continue;
                                }
                                exams.add(Utility.parseString(DatabaseConnection.getData(resultSet1,
                                        "String", "ExamName")));
                                ExamResource.registeredCourses.put(matricNumber, exams);
                            }

                            try (ResultSet getResults2 = Utility.execQuery(examRegister)) {
                                String[] studentData;
                                while (getResults2.next()) {
                                    studentData = new String[5];
                                    studentData[0] = Utility.parseString(DatabaseConnection.getData(getResults2,
                                            "String", "LastName"));
                                    studentData[1] = Utility.parseString(DatabaseConnection.getData(getResults2,
                                            "String", "FirstName"));
                                    studentData[2] = Utility.parseString(DatabaseConnection.getData(getResults2,
                                            "String", "MiddleName"));
                                    studentData[3] = Utility.parseString(DatabaseConnection.getData(getResults2,
                                            "String", "Gender"));
                                    studentData[4] = Utility.parseString(DatabaseConnection.getData(getResults2,
                                            "String", "StudentNumber"));

                                    //studentsRegistered.put(getResults2.getString("StudentNumber"), getResults2.getString("LastName") + " " + getResults2.getString("FirstName"));
                                    ExamResource.registeredStudents.put(getResults2.getString("StudentNumber"), studentData);
                                }
                            }

                        } catch (SQLException e) {
                            LogData.Log(e.getMessage(), "Start up class");
                        }
                    }
                };

                Thread thread = new Thread(multhiThread);
                thread.start();

                ArrayList<ExamBuilder> examBuilder = new ArrayList<>(1);
                
                while (resultSet.next()) {
                    exams = new ExamBuilder();
                    exams.setExamName(Utility.parseString(DatabaseConnection.getData(resultSet,
                            "String", "ExamName")));
                    exams.setScheduleDateTime(Utility.parseString(DatabaseConnection.getData(resultSet,
                            "String", "ScheduleDateTime")));
                    exams.setTime(Utility.parseString(DatabaseConnection.getData(resultSet,
                            "String", "time")));
                    exams.setRegisteredCandidates(Utility.parseString(DatabaseConnection.getData(resultSet,
                            "String", "RegisteredCandidates")));
                    exams.setDuration(Utility.parseString(DatabaseConnection.getData(resultSet, "String",
                            "Duration")));
                    exams.setAmountOfQuestions(Utility.parseString(DatabaseConnection.getData(resultSet, "String",
                            "Amount")));
                    exams.setFeedback(Utility.parseString(DatabaseConnection.getData(resultSet, "String",
                            "FeedBack")));
                    examBuilder.add(exams);
                }
                
                Response sendResponse = new Response();
                sendResponse.setData(examBuilder);
                sendResponse.setExams(setAllQuestions(exams, examBuilder));
                
                return sendResponse; 
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public ExamBuilder setAllQuestions(ExamBuilder exams, ArrayList<ExamBuilder> examBuilder) {
        GetAllQuestionsGetAllPeople getQuestions = new GetAllQuestionsGetAllPeople(); 
        exams = new ExamBuilder();
        exams.setExamName("AllExams");
        exams.setDuration("AllExams");
        exams.setExamQuestions(getQuestions.getExamQuestions(examBuilder));
         
        return exams;
    }
    
    class Response {
        private ExamBuilder exams;
        private ArrayList<ExamBuilder> data;

        public ExamBuilder getExams() {
            return exams; 
        }

        public void setExams(ExamBuilder exams) {
            this.exams = exams;
        }

        public ArrayList<ExamBuilder> getData() {
            return data;
        }

        public void setData(ArrayList<ExamBuilder> data) {
            this.data = data;
        }
        
    }

    public void allowCandidateStart() {
        throw new IllegalStateException("Please convert to try with resources");
        /*PreparedStatement allowSign = null;
        ResultSet getResults2 = null;
        try {

            System.out.println(" in allow canidadates Log In");

            allowSign = DatabaseConnection.initialiseDatabase(null, allowSign,
                    "allow candidate Start", "StartUpClassOngoingExams", QueryStudents.GET_ALL_STUDENTS);
            getResults2 = allowSign.executeQuery();
            while (getResults2.next()) {
                StringBuilder builder = new StringBuilder();
                builder.append("#");
                builder.append(getResults2.getString("LastName").concat(","));
                builder.append(getResults2.getString("FirstName").concat(","));
                builder.append(getResults2.getString("MiddleName").concat(","));
                builder.append(getResults2.getString("Gender").concat(","));
                builder.append(getResults2.getString("StudentNumber"));
                studentsRegistered.put(getResults2.getString("StudentNumber"), getResults2.getString("LastName") + " " + getResults2.getString("FirstName"));
                Exam.registeredStudents.put(getResults2.getString("StudentNumber"), builder.toString());

            }
            isLogin = true;

        } catch (SQLException e) {

            LogData.Log(e.getMessage(), "Candidate start");
        } */
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
 /*public void startOngoingExam() {
        File files = new File(System.getProperty("user.home")
                + System.getProperty("file.separator") + "Images");
        if (!files.isDirectory()) {
            files.mkdir();
        }

        System.out.println("control start:1 getting all students registered for exams ");
        Exam.registeredCourses = new HashMap();
        HashMap<String, String> matrics = new HashMap<String, String>();
        Exam.getNewRegisteredStudents = "";
        ResultSet resultSet1 = null;
        try {
            StringBuilder builder;
            resultSet1 = Exam.getAllRegisteredStudents.executeQuery();
            while (resultSet1.next()) {
                String matricNumber = resultSet1.getString("matricExam").split("\\*\\*\\*")[0];
                matrics.put(matricNumber, "");
                if (Exam.registeredCourses.containsKey(matricNumber)) {
                    StringBuilder builder2 = new StringBuilder();
                    builder2.append((String) Exam.registeredCourses.get(matricNumber))
                            .append(",").append(resultSet1.getString("ExamName"));
                    Exam.registeredCourses.put(matricNumber, builder2.toString());
                    continue;
                }

                Exam.registeredCourses.put(matricNumber, resultSet1.getString("ExamName"));
            }
            Set<String> keys = matrics.keySet();
            for (String matricNumbers : keys) {
                Exam.getNewRegisteredStudents = Exam.getNewRegisteredStudents + matricNumbers + ",";
                Exam.registerStudentsExam.setString(1, matricNumbers);

                ResultSet getResults2 = Exam.registerStudentsExam.executeQuery();
                while (getResults2.next()) {
                    builder = new StringBuilder();
                    builder.append("#");
                    builder.append(getResults2.getString("LastName").concat(","));
                    builder.append(getResults2.getString("FirstName").concat(","));
                    builder.append(getResults2.getString("MiddleName").concat(","));
                    builder.append(getResults2.getString("Gender").concat(","));
                    builder.append(getResults2.getString("StudentNumber"));
                    studentsRegistered.put(getResults2.getString("StudentNumber"), getResults2.getString("LastName") + " " + getResults2.getString("FirstName"));
                    Exam.registeredStudents.put(getResults2.getString("StudentNumber"), builder.toString());
                }
            }
            StringBuilder result = new StringBuilder();
            ResultSet resultSet = Exam.getOngoingExams.executeQuery();
            while (resultSet.next()) {
                builder = new StringBuilder();
                builder.append("#");
                builder.append(resultSet.getString("ExamName").concat(","));
                builder.append(resultSet.getString("ScheduleDateTime").concat(","));
                builder.append(resultSet.getString("time").concat(","));
                builder.append(resultSet.getString("RegisteredCandidates").concat(","));
                builder.append(resultSet.getString("Duration"));
                GetAllQuestionsGetAllPeople.getAllQuestions((String) resultSet.getString("ExamName"));
                GetAllQuestionsGetAllPeople.getAllPeople((String) resultSet.getString("Duration"), (String) resultSet.getString("ExamName"), (String) resultSet.getString("FeedBack"), (String) resultSet.getString("Amount"));
                result.append(builder);
            }
            Exam.ongoingExam = result.toString();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }

                DatabaseConnection.closeConnection();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }*/
}
