import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Address {
    private String cep
    private String street
    private String neighborhood
    private String city
    private String state
    private int number
    private String details

    static constraints = {
        cep nullable: true, blank: false
        street nullable: true, blank: false
        neighborhood nullable: true, blank: false
        city nullable: true, blank: false
        state nullable: true, blank: false
        number nullable: true
        details nullable: true
    }
}