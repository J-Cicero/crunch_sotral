package com.smart.sotral.Shared.constants;

public class JavaConstant {

    public final static String FRONTEND_URL = "*";
    public final static String API_BASE_URL = "/api";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";

    public final static String[] PUBLIC_URLS = {
            "/api/users/**",
            API_BASE_URL + "/public/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/api-docs/**",
            API_BASE_URL + "/swagger-ui/**",
            API_BASE_URL + "/swagger-ui.html",
            API_BASE_URL + "/v3/api-docs/**",
            API_BASE_URL + "/api-docs/**",
            "/actuator/health",  // Health check pour Render
            "/actuator/info"
    };

    // URLs pour ADMIN
    public final static String[] ADMIN_URLS = {
            "/admin/**",
            "/users/all/**",
            "/config/**"
    };

    public final static String[] ADMIN_ONLY_URLS = {
            "/api/users/register/admin"
    };
    
    // URLs pour GESTIONNAIRE
    public final static String[] GESTIONNAIRE_URLS = {
            "/gestion/**",
            "/reports/**"
    };

    // URLs pour USAGER authentifié
    public final static String[] USAGER_URLS = {
            "/profile/**",
            "/dashboard/**"
    };
    
    // URLs pour CONSULTANT
    public final static String[] CONSULTANT_URLS = {
            "/consultations/**",
            "/analytics/**"
    };
    
    // URLs pour FREELANCE
    public final static String[] FREELANCE_URLS = {
            "/projects/**",
            "/tasks/**"
    };

}
