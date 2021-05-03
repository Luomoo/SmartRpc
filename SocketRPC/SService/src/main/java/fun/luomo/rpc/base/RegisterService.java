package fun.luomo.rpc.base;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luomo
 * @date 2021/5/2 23:33
 */
@Service
public class RegisterService {
    private static final Map<String, Class<?>> registerMap = new ConcurrentHashMap<>();

    public void registerService(String serviceName, Class<?> impl) {
        registerMap.put(serviceName, impl);
    }

    public Class<?> getService(String serviceName) {
        return registerMap.get(serviceName);
    }
}
