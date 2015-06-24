package com.asiainfo.baas.marathon.main;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import com.asiainfo.baas.marathon.ressouce.StudentResource;


/**
 * @author ""
 */
public class App {

    private static final URI BASE_URI = URI.create("http://localhost:8080/marathon/");

    public static void main(String[] args) {
        try {
            Map<String, String> initParams = new HashMap<String, String>();
            initParams.put(ServerProperties.PROVIDER_PACKAGES,  StudentResource.class.getPackage().getName());
            
            final HttpServer server = GrizzlyWebContainerFactory.create(BASE_URI, ServletContainer.class, initParams);

            System.out.println("Application started. Try out Hit enter to stop it...");
            System.in.read();
            server.shutdownNow();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
