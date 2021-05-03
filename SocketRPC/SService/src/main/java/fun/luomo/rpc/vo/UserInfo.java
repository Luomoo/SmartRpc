package fun.luomo.rpc.vo;

import java.io.Serializable;

/**
 * @author luomo
 * @date 2021/5/2 23:31
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private String phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
