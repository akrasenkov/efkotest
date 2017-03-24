package com.senik11.efkotest.auth;

import com.senik11.efkotest.auth.annotation.AuthData;
import com.senik11.efkotest.auth.feature.TokenAuthFilter;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.internal.inject.ParamInjectionResolver;
import org.glassfish.jersey.server.model.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.security.Principal;

public class AuthDataValueFactoryProvider extends AbstractValueFactoryProvider {

    private final Logger log = LoggerFactory.getLogger(TokenAuthFilter.class);

    @Inject
    protected AuthDataValueFactoryProvider(MultivaluedParameterExtractorProvider mpep, ServiceLocator locator) {
        super(mpep, locator, Parameter.Source.UNKNOWN);
    }

    @Override
    protected Factory<?> createValueFactory(Parameter parameter) {
        if (noAuthDataAnnotation(parameter) || notAuthDataParameterClass(parameter)) {
            return null;
        }
        return new AbstractContainerRequestValueFactory<Principal>() {
            @Override
            public Principal provide() {
                Principal authData = getContainerRequest().getSecurityContext().getUserPrincipal();
                if (authData == null) {
                    log.error("Unauthenticated request?");
                    throw new IllegalStateException("Unauthenticated request?");
                }
                return authData;
            }
        };
    }

    private static boolean noAuthDataAnnotation(Parameter parameter) {
        return !parameter.isAnnotationPresent(AuthData.class);
    }

    private static boolean notAuthDataParameterClass(Parameter parameter) {
        return !parameter.getRawType().equals(Bracelet.class);
    }

    @Singleton
    public static class InjectionResolver extends ParamInjectionResolver<AuthData> {
        public InjectionResolver() {
            super(AuthDataValueFactoryProvider.class);
        }
    }
}
