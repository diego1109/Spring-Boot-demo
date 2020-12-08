package com.example.security.filters;

import com.example.security.api.UserRepository;
import com.example.security.domain.User;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthorizationUserFilter extends OncePerRequestFilter {

  @Autowired private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String username = request.getParameter("username");

    Optional<User> optional = userRepository.ofId(username);
    if (!optional.isPresent()) {
      throw new UsernameNotFoundException(username + " not found!");
    }
    User fetched = optional.get();

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            fetched,
            null,
            fetched.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    filterChain.doFilter(request,response);
  }
}
