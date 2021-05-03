package fun.luomo.rpc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author luomo
 * @date 2021/5/2 23:37
 */
@Service
public class RpcServerFrame {
    @Autowired
    private RegisterServiceWithRegCenter registerServiceWithRegCenter;
    @Autowired
    private RegisterServiceWithLocal registerServiceWithLocal;
    private RegisterService registerService;

    private static class ServerTask implements Runnable {
        Socket socket;
        RegisterService registerService;

        public ServerTask(Socket socket, RegisterService registerService) {
            this.socket = socket;
            this.registerService = registerService;
        }

        @Override
        public void run() {
            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

                /*方法所在类名接口名*/
                String serviceName = inputStream.readUTF();
                /*方法的名字*/
                String methodName = inputStream.readUTF();
                /*方法的入参类型*/
                Class<?>[] paramTypes = (Class<?>[]) inputStream.readObject();
                /*方法的入参的值*/
                Object[] args = (Object[]) inputStream.readObject();

                Class<?> serviceClass = registerService.getService(serviceName);

                if (serviceClass == null) {
                    throw new ClassNotFoundException(serviceName + " not found");
                }
                /*通过反射，执行实际的服务*/
                Method method = serviceClass.getMethod(methodName, paramTypes);
//                Object result = method.invoke(serviceClass.newInstance(), args);
                Object result = method.invoke(serviceClass.getDeclaredConstructor().newInstance(), args);

                /*将服务的执行结果通知调用者*/
                outputStream.writeObject(result);
                outputStream.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void startService(String serviceName, String host, int port, Class<?> impl, boolean isReg) throws Throwable {
        ServerSocket socket = new ServerSocket();
        socket.bind(new InetSocketAddress(port));
        System.out.println("服务正在运行--------端口:" + port);
        if (isReg) {
            registerService = registerServiceWithRegCenter;
            registerServiceWithRegCenter.regRemote(serviceName, host, port, impl);
        } else {
            registerService = registerServiceWithLocal;
            registerServiceWithLocal.registerService(serviceName, impl);
        }
        try {
            while (true) {
                new Thread(new ServerTask(socket.accept(), registerService)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
