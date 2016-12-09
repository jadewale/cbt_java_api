/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.stsexamtest;

import java.io.File;
import java.io.IOException;
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
    
    public static void main(String [] args) {
        try{
           GlassFishProperties gfProps = new GlassFishProperties();
      gfProps.setPort("http-listener",
          Integer.parseInt(System.getenv("PORT")));
      final GlassFish glassfish = GlassFishRuntime.bootstrap()
          .newGlassFish(gfProps);
      glassfish.start();
      
            ScatteredArchive war = new ScatteredArchive("myApp",
          ScatteredArchive.Type.WAR, new File("src/main/webapp"));
            war.addClassPath(new File("target/classes"));
            glassfish.getDeployer().deploy(war.toURI());  
 
        } catch(GlassFishException | NumberFormatException | IOException e) { 
            e.printStackTrace();
        }
    }
}
