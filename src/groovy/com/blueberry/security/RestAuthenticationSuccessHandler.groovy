package com.blueberry.security

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import org.springframework.security.web.savedrequest.SavedRequest
import org.springframework.util.StringUtils

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Control is transferred here after successful authentication so that the user is redirected to a success URL. The
 * success URL maybe a default URL or a URL in the query parameter or in the saved Request. But, this is applicable
 * only for form-login scenario.
 * For rest scenario, we just return a 200 so that the client can continue to access their protected resource with a
 * new REST call.
 *
 * @author Ganeshji Marwaha
 * @since 8/24/14
 */
class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    protected final Log log = LogFactory.getLog(this.getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        log.debug("DDDDDDDDDDDDDDD--" + "RestAuthenticationSuccessHandler.onAuthenticationSuccess");
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            requestCache.removeRequest(request, response);
        }

        clearAuthenticationAttributes(request);
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
