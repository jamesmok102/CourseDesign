package xyz.jamesmok.minishop.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import xyz.jamesmok.minishop.util.JwtUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginFilter3", urlPatterns = {"/userislogin/*"})
public class JWTFilter3 implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    public String resolveToken(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader("Authorization3");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if(!"OPTIONS".equals(httpServletRequest.getMethod())){
            //得到一个纯token码
            String jwt = resolveToken(httpServletRequest);
            //logger.info(jwt);
//            try {
//                //解析jwt
//                JwtUtil.checkToken(jwt);
//                filterChain.doFilter(servletRequest,servletResponse);
//            } catch (UserException e) {
//                //若有异常统一抛出401，token认证失败
//                logger.info("-------------------------------------------");
//                httpServletResponse.sendError(401,e.getMessage());
//            }
            if (JwtUtil.checkToken(jwt)) {
                Jws<Claims> claimsJws = Jwts.parser().setSigningKey("admin").parseClaimsJws(jwt);
                //logger.info(claimsJws.toString());
                String username = claimsJws.getBody().get("username", String.class);
                String role = claimsJws.getBody().get("role", String.class);
                //logger.info(username);
                servletRequest.setAttribute("username", username);
                servletRequest.setAttribute("role", role);
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                httpServletResponse.sendError(401, "jwt error");
            }
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }
}
