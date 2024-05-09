import grails.compiler.GrailsCompileStatic


@GrailsCompileStatic
class Customer {
    String username
    String passwordHash
    String email
    String cpfCnpj
    Address address
}