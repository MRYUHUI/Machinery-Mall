package com.mall.mechmall.filter;

import com.mall.mechmall.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * JWT认证过滤器
 *
 * 这个过滤器用于处理每个请求，验证JWT Token并设置认证信息到Spring Security上下文中。
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    /**
     * 过滤请求并处理JWT认证逻辑。
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param filterChain 过滤器链
     * @throws ServletException 如果发生Servlet异常
     * @throws IOException 如果发生IO异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 检查是否为OPTIONS请求，如果是则放行
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            System.out.println("OPTIONS请求，放行");
            filterChain.doFilter(request, response);
            return;
        }

        // 从请求头中获取JWT Token
        String jwt = parseJwt(request);
        // 验证JWT Token的有效性并设置认证信息到Spring Security上下文中
        if (jwt != null) {
            try {
                if (jwtUtils.validateToken(jwt)) {
                    String username = jwtUtils.getUsernameFromToken(jwt); // 从Token中获取用户名
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username); // 加载用户详细信息
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()); // 创建认证对象
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // 设置认证详情
                    SecurityContextHolder.getContext().setAuthentication(authentication); // 将认证对象设置到Spring Security上下文中
                }
            } catch (ExpiredJwtException | UnsupportedJwtException e) {
                System.out.println("JWT Token 处理异常: " + e.getMessage());
                // Token过期或不支持的Token异常处理
                logger.error("JWT Token处理异常: {}", e);
                SecurityContextHolder.clearContext(); // 清除上下文，确保没有残留认证信息
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token过期或无效");
                return;
            }
        }

        // 继续执行过滤器链中的下一个过滤器
        filterChain.doFilter(request, response);
    }

    /**
     * 从请求中解析JWT Token。
     *
     * @param request 请求对象
     * @return JWT Token字符串，如果未找到则返回null
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7); // 去掉"Bearer "前缀，获取JWT Token部分
        }
        return null;
    }
}