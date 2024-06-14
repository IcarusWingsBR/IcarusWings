package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.adapters.UserAdapter
import icaruswings.repositories.UserRepository

@Transactional
class UserService {

    public User save(Customer customer, UserAdapter userAdapter) {
        User user = new User()

        user.customer = customer
        user.username = userAdapter.username
        user.password = userAdapter.password

        user.save(failOnError: true)

        createUserRole(user)

        return user
    }

    public User addUser(Customer customer, UserAdapter userAdapter) {
        User user = save(customer, userAdapter)

        return user
    }

    public User update (UserAdapter userAdapter) {
        Long id = userAdapter.id
        User user = UserRepository.get(id)

        user.username = userAdapter.username
        user.password = userAdapter.password

        user.save(failOnError: true)
    }

    private static User createUserRole(User user) {
        Role role = Role.findByAuthority('ROLE_USER')
        UserRole.create(user, role, true)

        return user
    }
}
