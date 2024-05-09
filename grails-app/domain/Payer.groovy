import utils.BaseEntity

class Payer extends BaseEntity {
    String name
    String email
    String cpfCnpj
    Customer customer
}