package ru.denisovmaksim.taskmanager.backend.config;


public final class ApiPath {
    private ApiPath() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String API_V1 = "/api/v1";

    public static final String AUTH = API_V1 + "/auth/login";
    public static final String USER = API_V1 + "/user";
    public static final String TASKS = API_V1 + "/tasks";
}
