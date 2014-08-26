package com.blueberry

class User {

	transient springSecurityService

	String username
	String password

    String fullName
    String email

    RoleType roleType = RoleType.ROLE_USER;

	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService', 'roleType']

	static constraints = {
		username        size: 5..15, blank: false, unique: true
		password        minSize: 5, blank: false
        email           email: true, nullable: true
        fullName        nullable: true
        roleType        bindable: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
