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

	private String[] requestURI = { "/**", "/registerUser", "/createUserUI", "/confirm", "/forgotPassword", "/sendMail",
			"/resetPassword"

	};

	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}

	public void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests().antMatchers(requestURI).permitAll();
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.successHandler(customSuccesHandler).failureHandler(customErrorHandler);

		http.authorizeRequests().antMatchers("/travels/**", "travel")
				.hasAnyRole(ApplicationUserRole.TRAVELS.name(), ApplicationUserRole.ADMIN.name())
				.antMatchers("/seat/**", "/seats")
				.hasAnyRole(ApplicationUserRole.TRAVELS.name(), ApplicationUserRole.ADMIN.name())
				.antMatchers("/buses", "/bus", "/busStatus/**", "/busCategory/**")
				.hasAnyRole(ApplicationUserRole.TRAVELS.name(), ApplicationUserRole.ADMIN.name())
				.antMatchers("/bookings", "/booking/**", "/ticket", "/cancelTicket")
				.hasAnyRole(ApplicationUserRole.PASSENGER.name(), ApplicationUserRole.ADMIN.name())
				.antMatchers("/customers/**", "/customer/**", "/confirmCusomer/**")
				.hasAnyRole(ApplicationUserRole.PASSENGER.name(), ApplicationUserRole.ADMIN.name());

	}

	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/", "/css/**", "/js/**", "/webjars/**");
	}

}
