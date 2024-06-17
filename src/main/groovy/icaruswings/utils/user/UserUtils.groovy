package icaruswings.utils.user

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import icaruswings.User

public class UserUtils {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().username
        User user = User.findByUsername(username)
        
        return user.customer.id;
    }
}