package kit.pano.febs.common.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * token过期抛出这个
 *
 * @author Pano
 */
public class TokenTimeoutException extends AuthenticationException {

    private static final long serialVersionUID = -8313101744886192005L;

    public TokenTimeoutException(String message) {
        super(message);
    }
}
