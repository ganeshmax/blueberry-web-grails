package com.blueberry.security

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * TODO: Document Me
 *
 * @author Ganeshji Marwaha
 * @since 8/24/14
 */
class RestLogoutSuccessHandler implements LogoutSuccessHandler {

    protected final Log log = LogFactory.getLog(this.getClass());

    @Override
    void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("DDDDDDDDDDDDDDD--" + "RestLogoutSuccessHandler.onLogoutSuccess");
        // Do not do anything. This should technically return a 200
    }
}
