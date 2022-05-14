package com.example.chocolateshop.enums;

public enum Permission {
    PRODUCTS_READ("products:read"),
    PRODUCTS_WRITE("products:write"),
    USERS_READ("users_read"),
    USERS_WRITE("users_write");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
