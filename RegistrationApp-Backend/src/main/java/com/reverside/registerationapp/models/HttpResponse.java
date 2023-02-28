package com.reverside.registerationapp.models;

public class HttpResponse {
    private String message;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "message='" + message + '\'' +
                ", user=" + user +
                '}';
    }
}
