package com.dzl.blog2.config.security;

import com.dzl.blog2.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ApiRequestLogging
 * JwtAuthEntryPoint
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        logger.info(buildMessage(httpServletRequest));
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Sorry, You're not authorized to access this resource.");
    }

    private String buildMessage(HttpServletRequest request) {
        StringBuilder msg = new StringBuilder();
        String user = String.format("%-23s", "Unauthorized anonymous");
        msg.append(user)
                .append(" At ")
                .append(String.format("%16s", HttpUtil.getRemoteHost(request)))
                .append(" -> ")
                .append("[")
                .append(request.getMethod())
                .append("]")
                .append(request.getRequestURI());

        String queryString = request.getQueryString();
        if (queryString != null) {
            msg.append('?').append(queryString);
        }
        return msg.toString();
    }
}
