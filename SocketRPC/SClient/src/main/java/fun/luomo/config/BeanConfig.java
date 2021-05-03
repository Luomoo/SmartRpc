package fun.luomo.config;

import fun.luomo.rpc.sms.SendSms;
import fun.luomo.rpc.client.RpcClientFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Autowired
    private RpcClientFrame rpcClientFrame;

    @Bean
    public SendSms getSmsService() throws Exception{
        return rpcClientFrame.getRemoteProxyObject(SendSms.class);
    }
}