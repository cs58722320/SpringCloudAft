package com.springaft.common.exception;

/**
 * 名称：403 授权拒绝<br>
 * 描述：403 授权拒绝<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class DeniedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DeniedException(String message) {
        super(message);
    }

    public DeniedException(Throwable cause) {
        super(cause);
    }

    public DeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
