package icaruswings.adapters

class UserAdapter {

    String username

    String password

    public UserAdapter(Map params) {
        this.username = params.email
        this.password = params.password
    }
}
