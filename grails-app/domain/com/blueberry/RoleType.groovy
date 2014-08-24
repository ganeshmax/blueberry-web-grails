package com.blueberry

/**
 * TODO: Document Me
 *
 * @author Ganeshji Marwaha
 * @since 8/24/14
 */
public enum RoleType {
    ROLE_ADMIN(1),
    ROLE_OPERATOR(2),
    ROLE_PROVIDER(3),
    ROLE_RECEIVER(4),
    ROLE_USER(5)

    private RoleType(Integer roleId) {

    }
}