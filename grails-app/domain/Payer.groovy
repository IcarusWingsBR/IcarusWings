import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Payer {
    String name
    String email
    String cpfCnpj
    Customer customer

}