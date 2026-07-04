package ru.denisovmaksim.taskmanager.backend.dto;


public final class ApiPath {

    private ApiPath() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String API_V1 = "/api/v1";

    public static final String AUTH = API_V1 + "/auth/login";
    public static final String USERS = API_V1 + "/users";
    public static final String TASKS = API_V1 + "/tasks";
}
