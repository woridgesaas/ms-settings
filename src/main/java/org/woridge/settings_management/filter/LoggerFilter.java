package org.woridge.settings_management.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.woridge.settings_management.dto.tenant.TenantContext;
import org.woridge.settings_management.enumeration.LogType;

import java.io.IOException;

@Component
@Slf4j
public class LoggerFilter extends OncePerRequestFilter {
    @Value("${spring.application.name}")
    private String serviceName;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        String uri = request.getRequestURI();

        try {
            // MDC communs (for DEV)
            MDC.put("service", serviceName);
            MDC.put("method", request.getMethod());
            MDC.put("uri", request.getRequestURI());
            MDC.put("clientIp", request.getRemoteAddr());

            String tenantId = TenantContext.getCurrentTenant();
            MDC.put("tenant", tenantId != null ? tenantId : "dev-local");

            String cin = request.getHeader("X-User-CIN");
            String role = request.getHeader("X-User-Role");
            MDC.put("userMatricule", cin != null ? cin : "anonymous");
            MDC.put("userRole", role != null ? role : "N/A");

            MDC.put(LogType.class.getName(), LogType.DEV.getValue());

            filterChain.doFilter(request, response);

        } finally {
            long duration = System.currentTimeMillis() - startTime;
            MDC.put("duration", String.valueOf(duration));
            MDC.put("status", String.valueOf(response.getStatus()));
            log.info("Request completed");
        }
    }
}
