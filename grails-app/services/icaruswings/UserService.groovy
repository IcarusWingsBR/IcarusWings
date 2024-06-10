package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.adapters.UserAdapter

@Transactional
class UserService {

    public User save(Customer customer, UserAdapter userAdapter) {

        User user = new User()

        user.customer = customer
        user.username = userAdapter.username
        user.password = userAdapter.password

        user.save(failOnError: true)

        return user
    }

    public User addUser(UserAdapter userAdapter) {
        Customer customer = userAdapter.customer
        User user = save(customer, userAdapter)

        Role role = Role.findByAuthority('ROLE_USER')
        UserRole.create(user, role, true)

        return user
    }
}
