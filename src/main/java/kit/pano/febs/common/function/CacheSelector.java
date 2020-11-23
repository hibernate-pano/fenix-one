package kit.pano.febs.common.function;

/**
 * @author Pano
 */
@FunctionalInterface
public interface CacheSelector<T> {

    /**
     * @return
     * @throws Exception
     */
    T select() throws Exception;
}
