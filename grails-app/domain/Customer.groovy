import grails.compiler.GrailsCompileStatic
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@GrailsCompileStatic
class Customer implements UserDetails{
    String username
    String passwordHash
    String email
    String cpfCnpj
    Address address
    boolean accountNonExpired = true
    boolean accountNonLocked = true
    boolean credentialsNonExpired = true
    boolean enabled = true

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return null
    }

    @Override
    String getPassword() {
        return null
    }

    @Override
    String getUsername() {
        return null
    }

    @Override
    boolean isAccountNonExpired() {
        return false
    }

    @Override
    boolean isAccountNonLocked() {
        return false
    }

    @Override
    boolean isCredentialsNonExpired() {
        return false
    }

    @Override
    boolean isEnabled() {
        return false
    }
}