import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Payer {
    String name
    String email
    String cpfCnpj
    Address address

    static constraints = {
        name nullable: false, blank: false
        email nullable: true, blank: false
        cpfCnpj nullable: false, blank: false
        address nullable: true
    }
}