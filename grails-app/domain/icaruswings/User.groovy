package icaruswings

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic
import icaruswings.utils.BaseEntity

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1

    Customer customer

    String username

    String password

    boolean enabled = true

    boolean accountExpired = false

    boolean accountLocked = false

    boolean passwordExpired = false


    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true
        customer blank: false
    }

    static mapping = {
        password column: '`password`'
    }
}