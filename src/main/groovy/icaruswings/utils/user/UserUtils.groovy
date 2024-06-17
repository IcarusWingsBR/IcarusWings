package icaruswings.utils.user

import icaruswings.CustomerController

class UserUtils {

    public static Long getCurrentUserId() {
        CustomerController customer = new CustomerController()
    
        return customer.getCurrentCustomerId()
    }
}
