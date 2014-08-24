package com.blueberry

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

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
        }
        else {
            user.save flush:true
            render status: CREATED
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
                render status:OK
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
