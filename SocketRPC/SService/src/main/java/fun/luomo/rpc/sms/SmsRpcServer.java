package fun.luomo.rpc.sms;

import fun.luomo.rpc.base.RpcServerFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @author luomo
 * @date 2021/5/3 13:00
 */
@Service
public class SmsRpcServer {
    @Autowired
    private RpcServerFrame rpcServerFrame;

    @PostConstruct
    public void server() throws Throwable {
        Random r = new Random();
        int port = 8778 + r.nextInt(100);
        rpcServerFrame.startService(SendSms.class.getName(), "127.0.0.1", port, SendSmsImpl.class);

    }
}
