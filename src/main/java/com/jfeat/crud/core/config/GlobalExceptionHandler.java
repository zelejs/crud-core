package com.jfeat.crud.core.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.ErrorTip;
import com.jfeat.crud.base.util.StrKit;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author Admin
 * @date 2016年11月12日 下午3:19:56
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = Logger.getLogger(this.getClass());

    /**
     * 拦截业务异常
     *
     * @author Admin
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTip businessException(BusinessException e) {
        return new ErrorTip(e.getCode(), e.getMessage());
    }

    /**
     * 无权访问该资源
     *
     * @author Admin
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorTip credentials(UndeclaredThrowableException e) {
        return new ErrorTip(BusinessCode.NoPermission);
    }

    /**
     * 参数校验出错
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTip validation(ConstraintViolationException e) {
        return new ErrorTip(BusinessCode.BadRequest);
    }

    /**
     * 参数校验出错
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTip beanValidation(MethodArgumentNotValidException e) {
        ErrorTip errorTip = new ErrorTip(BusinessCode.BadRequest);
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorTip.add(error.getField(), error.getDefaultMessage());
        }
        return errorTip;
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTip methodArgumentMismatch(MethodArgumentTypeMismatchException e) {
        ErrorTip errorTip = new ErrorTip(BusinessCode.BadRequest);
        errorTip.add(e.getName(), e.getMessage());
        return errorTip;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTip sqlException(DataIntegrityViolationException e) {
        String message = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
        ErrorTip errorTip = new ErrorTip(BusinessCode.BadRequest);
        errorTip.setMessage(message);
        if (StrKit.notBlank(message) && message.contains("foreign key")) {
            errorTip = new ErrorTip(BusinessCode.DeleteAssociatedOne);
        }
        if (StrKit.notBlank(message) && message.contains("Duplicate entry")) {
            errorTip = new ErrorTip(BusinessCode.DuplicateKey);
        }
        return errorTip;
    }

    /**
     * 拦截未知的运行时异常
     *
     * @author Admin
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip unknownError(RuntimeException e) {
        log.error("运行时异常:", e);
        if (e.getClass().getSimpleName().equals("CRUDException")) {
            JSONObject json = (JSONObject) JSON.toJSON(e);
            Integer code = json.getInteger("code");
            return new ErrorTip(code, e.getMessage());
        }
        return new ErrorTip(BusinessCode.CodeBase.getCode(), e.toString());
    }

}
