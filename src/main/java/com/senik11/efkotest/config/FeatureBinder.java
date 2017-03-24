package com.senik11.efkotest.config;

import com.senik11.efkotest.auth.feature.TokenAuthFilter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class FeatureBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(TokenAuthFilter.class).to(TokenAuthFilter.class);
    }
}
