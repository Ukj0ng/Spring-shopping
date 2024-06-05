package com.uk.shopping.common.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.uk.shopping.common.auth.PrincipalDetails;
import com.uk.shopping.user.model.User;
import com.uk.shopping.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserRepository userRepository;

    public JwtAuthorizationFilter(
        AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("JWTAuthorizationFilter doFilterInternal");

        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
        System.out.println("jwtHeader: " + jwtHeader);

        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

        String username = JWT
            .require(Algorithm.HMAC256(JwtProperties.SECRET))
            .build()
            .verify(jwtToken).getClaim("username")
            .asString();

        if (username != null) {
            User userEntity = userRepository.findByUsername(username);
            System.out.println("userEntity: " + userEntity.getUsername());

            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
            System.out.println("principalDetails: " + principalDetails.getUsername());

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, principalDetails, principalDetails.getAuthorities());
            System.out.println("Authentication: " + authentication);
            System.out.println("Authentication.principal: " + authentication.getPrincipal().toString());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
    }
}
