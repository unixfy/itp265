public abstract class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private boolean banned;

    public User(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.banned = false;
    }
}
