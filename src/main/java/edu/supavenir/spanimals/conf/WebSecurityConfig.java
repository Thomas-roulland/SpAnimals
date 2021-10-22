package edu.supavenir.spanimals.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

	String[] staticResources = { "/css/**", "/images/**", "/fonts/**", "/scripts/**", };

	http.authorizeRequests().antMatchers(HttpMethod.POST, "/login").hasRole("USER") // Specific api

		// method
		// request
		// based on role.
		.antMatchers("home", "/", "hello", "console/**", "/animal/**", "/images/**").permitAll() // permited
													 // urls to
													 // guest //
		// users(without
		// login).
		.anyRequest().authenticated().and().formLogin() // not specified form page to use default login page of
								// spring security
		.permitAll().and().logout().deleteCookies("JSESSIONID") // delete memory of browser after logout

		.and().rememberMe().key("uniqueAndSecret");

	http.csrf().disable(); // ADD THIS CODE TO DISABLE CSRF IN PROJECT.**
	http.headers().frameOptions().disable();

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
	UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build();

	return new InMemoryUserDetailsManager(user);
    }
}
