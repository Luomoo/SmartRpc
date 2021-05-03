package fun.luomo.rpc.vo;
import java.io.Serializable;


public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String userName;
    private final String phone;

    public UserInfo(String userName, String phone) {
        this.userName = userName;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhone() {
        return phone;
    }
}
