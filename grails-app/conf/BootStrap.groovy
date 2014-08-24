import com.blueberry.Role
import com.blueberry.User
import com.blueberry.UserRole

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def adminUser = new User(username: 'admin', password: 'password').save(flush: true);
        def userUser = new User(username: 'user', password: 'password').save(flush: true);

        UserRole.create adminUser, adminRole, true
        UserRole.create userUser, userRole, true

        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2

    }

    def destroy = {
    }
}
