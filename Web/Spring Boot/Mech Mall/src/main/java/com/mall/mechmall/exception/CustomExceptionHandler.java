package com.mall.mechmall.exception;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Author:
 * @Date: 2024-06-29-21:38
 * @Description:
 */
@ControllerAdvice
public class CustomExceptionHandler {

    // 处理访问被拒绝的异常（403）
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.FORBIDDEN.value());
        jsonObject.put("error", "没有权限访问");
        jsonObject.put("message", "您没有权限执行此操作");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(jsonObject);
    }

    // 处理用户名不存在的异常（401）
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.UNAUTHORIZED.value());
        jsonObject.put("error", "用户名不存在");
        jsonObject.put("message", "用户名不存在，请检查您的输入");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jsonObject);
    }

    // 处理凭证无效的异常（401）
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.UNAUTHORIZED.value());
        jsonObject.put("error", "凭证无效");
        jsonObject.put("message", "用户名或密码错误，请重新输入");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jsonObject);
    }

    // 处理账号被禁用的异常（403）
    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<Object> handleDisabledException(DisabledException ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.FORBIDDEN.value());
        jsonObject.put("error", "账号被禁用");
        jsonObject.put("message", "您的账号已被禁用，请联系管理员");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(jsonObject);
    }

    // 处理认证异常（401）
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.UNAUTHORIZED.value());
        jsonObject.put("error", "认证异常");
        jsonObject.put("message", "认证失败，请重新登录");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jsonObject);
    }

    // 处理通用异常（500）
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        jsonObject.put("error", "服务器内部错误");
        jsonObject.put("message", "服务器遇到了一个问题，请稍后再试");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonObject);
    }
}