package com.senik11.efkotest.auth.feature;

import com.senik11.efkotest.auth.annotation.IgnoreAuth;

import javax.inject.Inject;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import java.lang.reflect.Method;

public class TokenAuthDynamicFeature implements DynamicFeature {

    private final TokenAuthFilter tokenAuthFilter;

    @Inject
    public TokenAuthDynamicFeature(TokenAuthFilter tokenAuthFilter) {
        this.tokenAuthFilter = tokenAuthFilter;
    }

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        Method method = resourceInfo.getResourceMethod();
        if (!method.isAnnotationPresent(IgnoreAuth.class)) {
            context.register(tokenAuthFilter);
        }
    }

}
