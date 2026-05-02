package org.woridge.settings_management.enumeration;

public enum LogType {
    DEV("Dev"),
    AUDIT("Audit");


    private String value;
    LogType(String x){
        this.value = x;
    }

    public String getValue(){
        return this.value;
    }
}
