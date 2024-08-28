package io.microservices.demo.Configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class UserContextFilter extends OncePerRequestFilter {

  private final Logger LOGGER = LoggerFactory.getLogger(UserContextFilter.class);


  @Override
  protected void doFilterInternal(
          HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {

      String userId = request.getHeader("userId");
      if(userId != null)
        UserContext.setUserId(Long.valueOf(userId));
      filterChain.doFilter(request, response);

  }
}