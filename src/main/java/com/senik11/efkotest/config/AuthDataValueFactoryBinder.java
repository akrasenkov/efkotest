package com.senik11.efkotest.config;

import com.senik11.efkotest.auth.annotation.AuthData;
import com.senik11.efkotest.auth.AuthDataValueFactoryProvider;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;

import javax.inject.Singleton;

public class AuthDataValueFactoryBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(AuthDataValueFactoryProvider.class)
                .to(ValueFactoryProvider.class)
                .in(Singleton.class);
        bind(AuthDataValueFactoryProvider.InjectionResolver.class)
                .to(new TypeLiteral<InjectionResolver<AuthData>>() {})
                .in(Singleton.class);
    }
}
