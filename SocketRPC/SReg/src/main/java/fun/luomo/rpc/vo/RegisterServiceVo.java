package fun.luomo.rpc.vo;

import java.io.Serializable;

public class RegisterServiceVo implements Serializable {
    private final String host;/*服务提供者的ip地址*/
    private final int port;/*服务提供者的端口*/

    public RegisterServiceVo(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}