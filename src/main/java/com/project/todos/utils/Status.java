package com.project.todos.utils;

public enum Status {
    PENDING("pending"),
    APPROVED("approved"),
    ASSIGNED("assigned"),
    COMPLETED("completed"),
    ABORTED("aborted"),
    PROGRESS("progress");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
