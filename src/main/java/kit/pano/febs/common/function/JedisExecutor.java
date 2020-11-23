package kit.pano.febs.common.function;

import kit.pano.febs.common.exception.RedisConnectException;

/**
 * @author Pano
 */
@FunctionalInterface
public interface JedisExecutor<T, R> {

    /**
     * @param t 执行方法参数
     * @return
     * @throws RedisConnectException
     */
    R excute(T t) throws RedisConnectException;
}
