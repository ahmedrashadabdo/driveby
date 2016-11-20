package thehub.com.drive_by;

/**
 * Created by Emalindah on 17/08/2016.
 */
public class User {

    String  email, username, password, mobile;



    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    public String getMobile()
    {
        return this.mobile;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return this.username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return this.password;
    }
}
