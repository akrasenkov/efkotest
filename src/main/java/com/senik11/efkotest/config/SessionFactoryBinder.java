package com.senik11.efkotest.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.SessionFactory;

public class SessionFactoryBinder extends AbstractBinder {

    private final SessionFactory sessionFactory;

    public SessionFactoryBinder(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected void configure() {
        bind(sessionFactory).to(SessionFactory.class).proxy(false);
    }
}
