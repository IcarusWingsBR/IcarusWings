package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.adapters.UserAdapter
import icaruswings.repositories.UserRepository
import icaruswings.utils.validator.EmailValidator

@Transactional
class UserService {

    public User save(Customer customer, UserAdapter userAdapter) {
        User validatedUser = validateSave(userAdapter)

        if (validatedUser.hasErrors()) throw new ValidationException("Não foi possível salvar o pagador", validatedUser.errors)

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

    public User update(UserAdapter userAdapter) {
        User validatedUser = validateSave(userAdapter)

        if (validatedUser.hasErrors()) throw new ValidationException("Não foi possível salvar o pagador", validatedUser.errors)

        Long id = userAdapter.id
        User user = UserRepository.get(id)
        user.username = userAdapter.username
        user.password = userAdapter.password
        user.save(failOnError: true)
    }

    public List<User> list(Long customerId, String filter) {
        if (filter == "deleted") return UserRepository.query([
                customerId : customerId,
                deletedOnly: true
        ]
        ).readOnly().list()

        return UserRepository.query([
                customerId: customerId,
        ]
        ).readOnly().list()
    }

    public User find(Long customerId, Long id) {
        User user = UserRepository.query([customerId: customerId, id: id]).get()

        return user
    }

    public void delete(User currentUser, Long id) {
        User user = find(currentUser.customer.id, id)

        if (!user) throw new RuntimeException("Esse usuário não existe")

        user.deleted = true

        user.save(failOnError: true)
    }

    public void restore(Long customerId, Long id) {
        User user = UserRepository.query([customerId: customerId, id: id, deletedOnly: true]).get()

        if (!user) throw new RuntimeException("Esse usuário não está deletado ou não existe")

        user.deleted = false

        user.save(failOnError: true)
    }

    private static User createUserRole(User user) {
        Role role = Role.findByAuthority('ROLE_USER')
        UserRole.create(user, role, true)

        return user
    }

    private User validateSave(UserAdapter userAdapter) {
        User user = new User()

        if (!userAdapter.username) {
            user.errors.rejectValue("name", null, "O campo email é obrigatório")
        } else if (!EmailValidator.isValidEmail(userAdapter.username)) {
            user.errors.rejectValue("email", null, "O email informado é inválido")
        }

        if (!userAdapter.password) {
            user.errors.rejectValue("cpfCnpj", null, "O campo senha é obrigatório")
        }

        return user
    }
}