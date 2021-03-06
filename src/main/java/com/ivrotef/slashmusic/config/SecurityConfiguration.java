package com.ivrotef.slashmusic.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

  @Autowired
  private UserDetailsService userConfiguration;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
       .authorizeRequests()
          .antMatchers("/resources/**", "/signup").permitAll()
          .antMatchers("/admin/**").access("hasRole('ADMIN')")
          .anyRequest().authenticated()
          .and()
       .formLogin()
          .loginPage("/login")
          .usernameParameter("correo")
          .permitAll()
          .defaultSuccessUrl("/")
          .and()
       .logout()
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .permitAll()
          .logoutSuccessUrl("/login?logout");
  }

  @Bean
  public AuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(encoder());
    provider.setUserDetailsService(userConfiguration);
    return provider;
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager customAuthenticationManager() throws Exception {
    return authenticationManager();
  }


  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userConfiguration).passwordEncoder(encoder());
  }

}
