package com.example.cloneinstagramback.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class JwtTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());

            String jwt = Jwts.builder()
                    .setIssuer("instagram")
                    .claim("authorities", this.populateAuthorities(authentication.getAuthorities()))
                    .claim("username", authentication.getName())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+30000000))
                    .signWith(key).compact();

            response.setHeader(SecurityContext.HEADER, jwt);

        }else if(!shouldNotFiltered(request)){
            filterChain.doFilter(request,response);
        }
    }

        public String populateAuthorities(Collection<? extends GrantedAuthority> collection){
            Set<String> authorities = new HashSet<>();
            for(GrantedAuthority authority: collection){
            authorities.add(authority.getAuthority());
            }

            return String.join(",",authorities);
        }

        protected boolean shouldNotFiltered(HttpServletRequest req) throws ServletException{
                String servletPath =  req.getServletPath();
                return servletPath.equals("/signIn") || servletPath.equals("/swagger-ui/**") || servletPath.equals("/api-docs/**");
        }
    }
