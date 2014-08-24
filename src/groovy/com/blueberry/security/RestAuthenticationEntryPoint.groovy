package com.blueberry.security

import groovy.json.JsonBuilder
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Control will be transferred here if the client requests a resource that is supposed to be authenticated, but is not.
 * In form-login scenario, we will be redirected to a login URL so that the user can enter login credentials.
 * But, in rest scenario, that does not make sense. So, we just return 401 and a message so that the rest client can
 * login before accessing the protected resource.
 *
 * @author Ganeshji Marwaha
 * @since 8/24/14
 */
class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    protected final Log log = LogFactory.getLog(this.getClass());

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        log.debug("DDDDDDDDDDDDDDD--" + "RestAuthenticationEntryPoint.commence");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.contentType = 'application/json';

        def builder = new JsonBuilder();
        builder.error {
            code (HttpServletResponse.SC_UNAUTHORIZED)
            message (authException.getMessage())
        };

        PrintWriter writer = response.getWriter();
        writer.println(builder.toPrettyString());
    }
}
