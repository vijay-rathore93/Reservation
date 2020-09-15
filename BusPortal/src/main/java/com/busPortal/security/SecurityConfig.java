package com.busPortal.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.busPortal.utility.ApplicationUserRole;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final CustomUserDetailsService customUserDetailsService;
	private final CustomErrorHandler customErrorHandler;
	private final CustomSuccesHandler customSuccesHandler;

	private String[] allowedRequestURI = { "/createCustomer", "/confirmCustomer" };

	private String[] travelsRoleRequestURIs = { "/travels/**", "travel", "/seat/**", "/seats", "/buses", "/bus",
			"/busStatus/**", "/busCategory/**","/customers/**" };
	private String[] passengerRoleRequestURIs = { "/bookings", "/booking/**", "/ticket", "/cancelTicket",
			"/customer/**"};

	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}

	public void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests().antMatchers(allowedRequestURI).permitAll();
		http.authorizeRequests().antMatchers(travelsRoleRequestURIs)
				.hasAnyRole(ApplicationUserRole.TRAVELS.name(), ApplicationUserRole.ADMIN.name())
				.antMatchers(passengerRoleRequestURIs)
				.hasAnyRole(ApplicationUserRole.PASSENGER.name(), ApplicationUserRole.ADMIN.name());

		http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().formLogin().loginPage("/login")
				.successHandler(customSuccesHandler).failureHandler(customErrorHandler).permitAll();

	}

	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/", "/css/**", "/js/**", "/webjars/**");
	}

}
