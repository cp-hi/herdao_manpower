package net.herdao.hdp.manpower.mpclient.handler;

import java.util.List;

import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.api.R;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
/**
 * 全局异常处理handler
 *
 * @author shuling
 * @date 2020-11-6 11:37:06
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	/**
     * 校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
	public R<String> validationBodyException(MethodArgumentNotValidException exception) {
		BindingResult result = exception.getBindingResult();
		StringBuffer errMsg = new StringBuffer();
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			if (ObjectUtil.isNotEmpty(errors)) {
				errors.forEach(p -> {
					FieldError fieldError = (FieldError) p;
					log.error("校验异常信息：{" + fieldError.getObjectName() + "},"
							  + "属性：field{" + fieldError.getField() + "},异常信息：errorMessage{" + fieldError.getDefaultMessage() + "}");
					errMsg.append(errMsg.length() > 0 ? "，" + fieldError.getDefaultMessage() : fieldError.getDefaultMessage());
				});
			}
		}
		return R.failed(errMsg.toString());
	}

    /**
     * 参数类型转换错误
     *
     * @param exception 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public R<String> parameterTypeException(HttpMessageConversionException exception){
        log.error(exception.getCause().getLocalizedMessage());
        return R.failed("类型转换错误");

    }

}
