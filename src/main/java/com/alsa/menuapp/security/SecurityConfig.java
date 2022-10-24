package com.alsa.menuapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alsa.menuapp.filter.CustomAuthenticationFilter;
import com.alsa.menuapp.filter.CustomAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //LOGIN enpoint
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/login").permitAll();

        //USERS endpoints
        //http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/**").hasAnyAuthority("ROLE_ADMIN");
        //http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyAuthority("ROLE_ADMIN");
        //http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/**").hasAnyAuthority("ROLE_ADMIN");

        //ROLES enpoints
        //http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/roles/**").hasAnyAuthority("ROLE_ADMIN");
        //http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/roles/**").hasAnyAuthority("ROLE_ADMIN");
        //http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/roles/**").hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
