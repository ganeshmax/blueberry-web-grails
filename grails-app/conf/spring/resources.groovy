import com.blueberry.security.RestAccessDeniedHandler
import com.blueberry.security.RestAuthenticationEntryPoint
import com.blueberry.security.RestAuthenticationFailureHandler
import com.blueberry.security.RestAuthenticationSuccessHandler
import com.blueberry.security.RestLogoutSuccessHandler

// Place your Spring DSL code here
beans = {

    // GTG - Returns 401 and does NOT redirect to a login page
    authenticationEntryPoint(RestAuthenticationEntryPoint) {
        // No configuration required because we are directly implementing AuthenticationEntryPoint
    }

    // GTG - Returns a 200 and does NOT redirect to a login success URL
    authenticationSuccessHandler(RestAuthenticationSuccessHandler) {
        // TODO: Consider implementing AuthenticationSuccessHandler so that you can avoid these configurations
        requestCache = ref('requestCache')
        redirectStrategy = ref('redirectStrategy')
    }

    // GTG - Returns a 403 and does not redirect to any form of error page
    authenticationFailureHandler(RestAuthenticationFailureHandler) {
        // No configuration required because we are directly implementing AuthenticationFailureHandler
    }

    // GTG - Returns a 403 and does not redirect to any form of error page
    accessDeniedHandler(RestAccessDeniedHandler) {
        // No configuration required because we are directly implementing AccessDeniedHandler
    }

    // GTG - Returns a 200 and does not redirect to a logout success URL
    logoutSuccessHandler(RestLogoutSuccessHandler) {
        // No configuration required because we are directly implementing LogoutSuccessHandler
    }

}
