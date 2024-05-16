package icaruswings

import icaruswings.utils.BasePerson

class Customer extends BasePerson{

    static constraints = {
        email unique: true
        cpfCnpj size: 11..18, unique: true
    }
}