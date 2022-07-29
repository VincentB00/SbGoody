package com.ecommerce.used_good.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http.csrf().disable();
        
        http.authorizeRequests((requests) -> requests
                                            .anyRequest()
                                            .permitAll());
		
        http.formLogin();
		http.httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new Sha256PasswordEncoder();
    }

    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception 
    {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new Sha256PasswordEncoder());
	}
}
