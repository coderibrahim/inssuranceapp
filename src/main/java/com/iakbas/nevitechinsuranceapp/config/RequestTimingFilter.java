package com.iakbas.nevitechinsuranceapp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class RequestTimingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        long start = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long duration = System.currentTimeMillis() - start;

        if (duration > 5) {
            String method = request.getMethod();
            String uri = request.getRequestURI();
            log.warn("Slow request: {} {} took {} ms", method, uri, duration);
        }
    }
}
