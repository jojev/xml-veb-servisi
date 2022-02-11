package main.java.com.xml.userbackend.config;

import io.jsonwebtoken.Claims;
import main.java.com.xml.userbackend.config.dto.JwtParseResponseDTO;
import main.java.com.xml.userbackend.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    public static final String HEADER = "Authorization";

    public static final String HEADER_VALUE_PREFIX = "Bearer";

    @Autowired
    TokenUtils tokenUtils;

    private final RestTemplate restTemplate;

    public AuthenticationFilter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader(HEADER);
        if (token != null) {
            token = token.replace(HEADER_VALUE_PREFIX + " ", "");
            try {
                JwtParseResponseDTO responseDto = parseJwt(token);
                List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();
                for (String role : responseDto.getAuthorities()) {
                    authoritiesList.add(new SimpleGrantedAuthority(role));
                }
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        responseDto.getUsername(),
                        null,
                        authoritiesList
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception ignore) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }

    private JwtParseResponseDTO parseJwt(String token) {
        Claims claims = tokenUtils.getAllClaimsFromToken(token);
        List<String> roles = new ArrayList<>();
        roles.add((String) claims.get("role"));
        return new JwtParseResponseDTO((String) claims.get("username"), roles);
    }
}

