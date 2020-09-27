package com.busPortal.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.busPortal.model.CustomerDTO;
import com.busPortal.model.LoginDTO;

public class CustomUserDetails implements UserDetails{
	
	private String userName;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	

	public CustomUserDetails(LoginDTO loginDTO, Set<SimpleGrantedAuthority> simpleGrantedAuthority) {
		
		this.userName = loginDTO.getUserName();
		this.password = loginDTO.getPassword();
		this.authorities = simpleGrantedAuthority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	

}
