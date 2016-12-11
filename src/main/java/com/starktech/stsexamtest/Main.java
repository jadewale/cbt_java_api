/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.stsexamtest;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
            String dbUrl = System.getenv("CLEARDB_DATABASE_URL");
            if (dbUrl != null) {
                /*System.out.println("-------db url: " + dbUrl);
                Matcher matcher = Pattern.compile("mysql://(.*):(.*)@(.*)/(.*)").matcher(dbUrl);
                matcher.find();

                String host = matcher.group(3);
                String database = matcher.group(4);
                String user = matcher.group(1);
                String password = matcher.group(2);
                String properties = "user=" + user + ":password=" + password + ":databasename=" + database + ":loglevel=4:servername=" + host;
                System.out.println(properties); 
                connectionPool(runner, properties); */
                connectionPool(runner);
            } else {
                connectionPool(runner);
            }
            ScatteredArchive war = new ScatteredArchive("StsExam",
                    ScatteredArchive.Type.WAR, new File("src/main/webapp"));
            war.addClassPath(new File("target/classes"));
            glassfish.getDeployer().deploy(war.toURI());

        } catch (GlassFishException | NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
    //mysql://b579248f3101c2:e9ca812d@us-cdbr-iron-east-04.cleardb.net/heroku_5fff44d305e31ec?reconnect=true

    public static void connectionPool(CommandRunner runner) {
        CommandResult result = runner.run("create-jdbc-connection-pool",
                "--datasourceclassname", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
                "--restype", "javax.sql.DataSource",
                "--driverClassName", "com.mysql.jdbc.Driver",
                "--property", "user=b579248f3101c2:password=e9ca812d:databasename=heroku_5fff44d305e31ec:servername=us-cdbr-iron-east-04.cleardb.net:port=3306:ReconnectEnabled=true:ReconnectInterval=30000:autoReconnect=true:validationQuery=\"SELECT 1\"",
                "--wrapjdbcobjects","false",
                "--steadypoolsize", "8",
                "--maxpoolsize", "32",
                "--poolresize", "2",
                "--maxwait","60000",
                "--idletimeout", "30", 
                "--isisolationguaranteed", "true",
                "--isconnectvalidatereq", "false",
                "--failconnection", "false",
                "--allownoncomponentcallers", "false",
                "--validateatmostonceperiod", "0",
                "--leaktimeout", "0",
                "--leakreclaim", "false",
                "--creationretryattempts", "0",
                "--creationretryinterval","10",
                "--pooling", "true", 
                "--statementtimeout", "-1",
                "--statementleaktimeout", "0",
                "--statementleakreclaim", "false",
                "--maxconnectionusagecount", "0",
               /* "--associatewiththread", "false",
                "--wrapjdbcobjects", "true", 
               /* "--lazyconnectionenlistment", "false",
                "--associatewiththread", "false",
                "--matchconnections", "false",
                "--maxconnectionusagecount", "0",
                "--ping", "false",
<<<<<<< HEAD
                "--pooling", "true",
                "--statementcachesize", "0",
                "--wrapjdbcobjects", "false", 
=======
                "--statementcachesize", "0",
                "--wrapjdbcobjects", "false",  */
                "jolaadeade");

        System.out.println("------output of create conn pool: " + result.getOutput());

        result = runner.run("create-jdbc-resource", "--connectionpoolid", "jolaadeade",
                "jdbc/myDatasource");

        System.out.println("------output of create jdbc: " + result.getOutput());
    } 

    /*
     create-jdbc-connection-pool [--datasourceclassname=datasourceclassname] [--restype=restype]
    [--steadypoolsize=8] [--maxpoolsize=32] [--maxwait=60000] 
    [--poolresize=2] [--idletimeout=300] [--initsql=initsql] 
    [--isolationlevel=isolationlevel] [--isisolationguaranteed=true]
    [--isconnectvalidatereq=false] [--validationmethod=table] [--validationtable=validationtable] 
    [--failconnection=false] [--allownoncomponentcallers=false] [--nontransactionalconnections=false]
    [--validateatmostonceperiod=0] [--leaktimeout=0] [--leakreclaim=false] [--creationretryattempts=0] [--creationretryinterval=10] 
    [--sqltracelisteners=sqltracelisteners] [--statementtimeout=-1] [--statementleaktimeout=0] 
    [--statementleakreclaim=false] [--lazyconnectionenlistment=false]
    [--lazyconnectionassociation=false] [--associatewiththread=false]
    [--driverclassname=driverclassname] [--matchconnections=false] [--maxconnectionusagecount=0]
    [--ping=false] [--pooling=true] [--statementcachesize=0] [--validationclassname=validationclassname]
    [--wrapjdbcobjects=true] [--description=description] [--property=property] jdbc_connection_pool_id
     */
    public static void connectionPool(CommandRunner runner, String prop) {
        CommandResult result = runner.run("create-jdbc-connection-pool",
                "--datasourceclassname", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
                "--restype", "javax.sql.DataSource",
                "--driverClassName", "com.mysql.jdbc.Driver",
                "--property", prop,
                "--steadypoolsize", "8",
                "--maxpoolsize", "32",
                "--poolresize", "2",
                "jolaadeade");

        System.out.println("------output of create conn pool: " + result.getOutput());

        result = runner.run("create-jdbc-resource", "--connectionpoolid", "jolaadeade",
                "jdbc/myDatasource");

        System.out.println("------output of create jdbc: " + result.getOutput());
    }
}
