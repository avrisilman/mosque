package mosque.list.com.mosque.entity;

/**
 * Created by domikado on 3/27/17.
 */

public class UserRegister {
    public String username;
    public String password;
    public String email;
    public String full_name;
    public String last_name;

    public UserRegister(String username, String password, String email, String full_name, String last_name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.full_name = full_name;
        this.last_name = last_name;
    }
}
