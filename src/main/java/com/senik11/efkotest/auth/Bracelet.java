package com.senik11.efkotest.auth;

import java.security.Principal;

public class Bracelet implements Principal {

    private final String token;

    public Bracelet(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String getName() {
        return getToken();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bracelet bracelet = (Bracelet) o;

        return token != null ? token.equals(bracelet.token) : bracelet.token == null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bracelet{");
        sb.append("token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
