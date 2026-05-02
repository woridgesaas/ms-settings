package org.woridge.settings_management.dto.tenant;

public class TenantContext {
    private static  ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setCurrentTenant(String sub) {
        TenantContext.currentTenant.set(sub);
    }

    public static String getCurrentTenant(){
        return TenantContext.currentTenant.get();
    }

    public static void clear(){
        TenantContext.currentTenant.remove();
    }
}
