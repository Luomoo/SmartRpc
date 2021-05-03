package fun.luomo.rpc.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luomo
 * @date 2021/5/2 23:41
 */
@Service
public class RegisterServiceWithRegCenter extends RegisterService {
    private final Map<String, Class<?>> serviceMap = new ConcurrentHashMap<>();

    public void regRemote(String serviceName, String host, int port, Class impl) throws Throwable {
        //登记到注册中心
        Socket socket = null;
        ObjectOutputStream output = null;
        ObjectInputStream input = null;

        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 9999));

            output = new ObjectOutputStream(socket.getOutputStream());
            /*注册服务*/
            output.writeBoolean(false);
            /*提供的服务名*/
            output.writeUTF(serviceName);
            /*服务提供方的IP*/
            output.writeUTF(host);
            /*服务提供方的端口*/
            output.writeInt(port);
            output.flush();

            input = new ObjectInputStream(socket.getInputStream());
            if (input.readBoolean()) {
                System.out.println("服务[" + serviceName + "]注册成功!");
            }

            /*可提供服务放入本地缓存*/
            serviceMap.put(serviceName, impl);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) socket.close();
            if (output != null) output.close();
            if (input != null) input.close();
        }
    }

    @Override
    public Class<?> getService(String serviceName) {
        return serviceMap.get(serviceName);
    }
}
