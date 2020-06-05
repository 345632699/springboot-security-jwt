package com.hotel.admin.exception;

import com.hotel.common.BaseException;
import com.hotel.common.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 全局异常
 * </p>
 *
 * @package: com.xkcoding.rbac.security.exception
 * @description: 全局异常
 * @author: yangkai.shen
 * @date: Created in 2018-12-10 17:24
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends BaseException {
    public CustomException(Status status) {
        super(status);
    }

    public CustomException(Status status, Object data) {
        super(status, data);
    }

    public CustomException(Integer code, String message) {
        super(code, message);
    }

    public CustomException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
