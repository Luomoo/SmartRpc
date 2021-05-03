package fun.luomo.rpc.service;

/**
 * @author luomo
 * @date 2021/5/3 23:33
 */
public abstract class RegisterService {
    public Class<?> getService(String serviceName) {
        return null;
    }


    public void registerService(String serviceName, Class<?> impl) {
    }

    public void regRemote(String serviceName, String host, int port, Class impl) throws Throwable {
    }
}
