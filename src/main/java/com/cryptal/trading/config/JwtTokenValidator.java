package com.cryptal.trading.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.SecretKey;

public class JwtTokenValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if (jwt!=null) {
            jwt=jwt.substring(7);


            try {
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRETE_KEY.getBytes(StandardCharsets.UTF_8));
                Jws<Claims> jwsClaims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt);

                Claims claims = jwsClaims.getBody();
                String email=String.valueOf(claims.get("email"));
                String authorities=String.valueOf(claims.get("authorities"));
                List<GrantedAuthority> authoritiesList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                Authentication auth=new UsernamePasswordAuthenticationToken(
                        email,
                        authoritiesList,
                        authoritiesList
                );
                SecurityContextHolder.getContext().setAuthentication (auth);

            } catch (Exception e) {
                throw new RuntimeException("invalid token.....");
            }
        }
        filterChain.doFilter(request,response);
    }
}
