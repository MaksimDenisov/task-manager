package ru.denisovmaksim.taskmanager.backend.config;

public final class InternalPath {
    private InternalPath() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String INTERNAL = "/internal";

    public static final String REPORT = INTERNAL + "/report";
    public static final String USERS_TASKS = INTERNAL + "/users-tasks";
}
