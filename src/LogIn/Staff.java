package LogIn;

import java.io.Serializable;

public class Staff implements Serializable {
    public String username;
    public String password;
    public Staff(String username,String password){
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object obj) {
        Staff staff = (Staff) obj;
        if (this.getUsername().equals(staff.getUsername()) && this.getPassword().equals(staff.getPassword())){
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return String.format("%s-%s",this.getUsername(),this.getPassword());
    }
}
