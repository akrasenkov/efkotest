package com.senik11.efkotest;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.senik11.efkotest.auth.feature.TokenAuthDynamicFeature;
import com.senik11.efkotest.config.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;

@ApplicationPath(App.APP_PATH)
public class App extends ResourceConfig {

    private final static Logger log = LoggerFactory.getLogger(App.class);

    public static final String APP_PATH = "/";

    public App() {
        SessionFactory hibernateSessionFactory;
        try {
            hibernateSessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception hex) {
            log.error(hex.toString());
            throw new RuntimeException(hex);
        }
        register(new SessionFactoryBinder(hibernateSessionFactory));
        register(new StorageBinder());
        register(new UseCaseBinder());
        register(new FeatureBinder());
        register(new AuthDataValueFactoryBinder());

        register(TokenAuthDynamicFeature.class);
        register(JacksonJaxbJsonProvider.class);

        register(NotesResource.class);
    }
}
