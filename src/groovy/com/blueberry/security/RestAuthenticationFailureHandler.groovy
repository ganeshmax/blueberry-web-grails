package com.blueberry.security

import groovy.json.JsonBuilder
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * TODO: Document Me
 *
 * @author Ganeshji Marwaha
 * @since 8/24/14
 */
class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    void onAuthenticationFailure(HttpServletRequest request,
                                 HttpServletResponse response,
                                 AuthenticationException exception) throws IOException, ServletException {

        log.debug("DDDDDDDDDDDDDDD--" + "RestAuthenticationFailureHandler.onAuthenticationFailure");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.contentType = 'application/json';

        def builder = new JsonBuilder();
        builder.error {
            code (HttpServletResponse.SC_FORBIDDEN)
            message (exception.getMessage())
        };

        PrintWriter writer = response.getWriter();
        writer.println(builder.toPrettyString());
    }
}
