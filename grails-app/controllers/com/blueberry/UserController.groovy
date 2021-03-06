package com.blueberry

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional
class UserController {

    @Secured(['ROLE_USER'])
    def index() {
        respond User.list();
    }

    @Secured(['ROLE_USER'])
    def show(User user) {
        if(user == null) {
            render status:NOT_FOUND
        } else {
            respond user
        }
    }

    @Secured(['ROLE_USER'])
    def save(User user) {
        if(user.hasErrors()) {
            respond user.errors
        } else {
            Role role = Role.where {
                authority == user.roleType.toString()
            }.get()
            user.save()

            UserRole.create user, role
            respond user, status: CREATED
        }
    }

    @Secured(['ROLE_USER'])
    def update(User user) {
        if(user == null) {
            respond status: NOT_FOUND;
        } else {
            if(user.hasErrors()) {
                respond user.errors
            } else {
                user.save flush:true
                respond user, status:OK
            }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(User user) {
        if(user == null) {
            respond status: NOT_FOUND;
        } else {
            if(user.hasErrors()) {
                respond user.errors
            } else {
                user.delete flush:true
                render status:NO_CONTENT
            }
        }
    }
}
