package com.blueberry.security

import groovy.json.JsonBuilder
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * TODO: Document Me
 *
 * @author Ganeshji Marwaha
 * @since 8/24/14
 */
class RestAccessDeniedHandler implements AccessDeniedHandler {

    protected final Log log = LogFactory.getLog(this.getClass());

    @Override
    void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        log.debug("DDDDDDDDDDDDDDD--" + "RestAccessDeniedHandler.handle");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.contentType = 'application/json';

        def builder = new JsonBuilder();
        builder.error {
            code (HttpServletResponse.SC_FORBIDDEN)
            message (accessDeniedException.getMessage())
        };

        PrintWriter writer = response.getWriter();
        writer.println(builder.toPrettyString());
    }
}
