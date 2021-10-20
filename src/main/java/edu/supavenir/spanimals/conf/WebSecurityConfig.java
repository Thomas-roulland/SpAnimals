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

	http.authorizeRequests().antMatchers(HttpMethod.POST, "/login").hasRole("USER") // Specific api
											// method
		// request
		// based on role.
<<<<<<< HEAD
		.antMatchers("home", "/", "hello", "/console/**").permitAll() // permited urls to guest users(without
									      // login).
=======
<<<<<<< HEAD
		.antMatchers("home", "/", "hello", "console/**", "/animal/**").permitAll() // permited urls to guest
											   // users(without
		// login).
=======
		.antMatchers("home", "/", "hello").permitAll() // permited urls to guest users(without
							       // login).
>>>>>>> d88e7b5db162903e386bd636b888066faafb533e
>>>>>>> 5bc4a4400c52ca241dc7ebb2bc52ad004d03bcf0
		.anyRequest().authenticated().and().formLogin() // not specified form page to use default login page of
								// spring security
		.permitAll().and().logout().deleteCookies("JSESSIONID") // delete memory of browser after logout

		.and().rememberMe().key("uniqueAndSecret");
	; // remember me check box enabled.

	http.csrf().disable(); // ADD THIS CODE TO DISABLE CSRF IN PROJECT.**
	http.headers().frameOptions().disable();
<<<<<<< HEAD

=======
<<<<<<< HEAD
=======

>>>>>>> d88e7b5db162903e386bd636b888066faafb533e
>>>>>>> 5bc4a4400c52ca241dc7ebb2bc52ad004d03bcf0
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
	UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build();

	return new InMemoryUserDetailsManager(user);
    }
}