package icaruswings.adapters

import icaruswings.Customer
import icaruswings.repositories.CustomerRepository

class UserAdapter {

    Customer customer

    String username

    String password

    public UserAdapter(Map params) {
        if (params.customerId) this.customer = CustomerRepository.get(params.customerId)
        this.username = params.email
        this.password = params.password
    }
}
