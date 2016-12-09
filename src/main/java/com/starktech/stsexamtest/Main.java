/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.stsexamtest;

import java.io.File;
import java.io.IOException;
import org.glassfish.embeddable.CommandResult;
import org.glassfish.embeddable.CommandRunner;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.glassfish.embeddable.archive.ScatteredArchive;

/**
 *
 * @author jolaadeadewale
 */
public class Main {

    public static void main(String[] args) {
        try {

            GlassFishProperties gfProps = new GlassFishProperties();
            gfProps.setPort("http-listener",
                    Integer.parseInt(System.getenv("PORT")));
            final GlassFish glassfish = GlassFishRuntime.bootstrap()
                    .newGlassFish(gfProps);
            glassfish.start();
            CommandRunner runner = glassfish.getCommandRunner();
 
            connectionPool(runner);
            ScatteredArchive war = new ScatteredArchive("StsExam",
                    ScatteredArchive.Type.WAR, new File("src/main/webapp"));
            war.addClassPath(new File("target/classes"));
            glassfish.getDeployer().deploy(war.toURI());

        } catch (GlassFishException | NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void connectionPool(CommandRunner runner) {        
        CommandResult result = runner.run("create-jdbc-connection-pool",
                "--datasourceclassname", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
                "--restype", "javax.sql.DataSource", 
                "--property", "user=jolaade:password=M2m7bYDusSmTBx4s:databasename=test:server=localhost:port=8889",
                "--steadypoolsize", "8",
                "--maxpoolsize", "32",  
                "jolaadeade"); 
    
        System.out.println("------output of create conn pool: " + result.getOutput());

        result = runner.run("create-jdbc-resource", "--connectionpoolid", "jolaadeade",
                "jdbc/myDatasource");

        System.out.println("------output of create jdbc: " + result.getOutput());
    }
}
