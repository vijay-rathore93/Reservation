package com.busPortal.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.model.CustomerDTO;
import com.busPortal.model.LoginDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final RestTemplate restTemplate;

	@Value("${CUSTOMER_SERVICE_BY_NAME}")
	private String customerByNameURL;

//	@Override
//	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//
//		ResponseEntity<CustomerDTO> rec = restTemplate.getForEntity(customerByNameURL + name, CustomerDTO.class);
//
//		return new CustomUserDetails(rec.getBody(), getSimpleGrantedAuthority(rec.getBody().getRoleName()));
//	}
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		ResponseEntity<LoginDTO> rec = restTemplate.getForEntity("http://localhost:9001/login/" + name, LoginDTO.class);

		return new CustomUserDetails(rec.getBody(), getSimpleGrantedAuthority(rec.getBody().getRoleName()));
	}
	
	

	private Set<SimpleGrantedAuthority> getSimpleGrantedAuthority(String roleName) {
		HashSet<SimpleGrantedAuthority> hs = new HashSet<SimpleGrantedAuthority>();
		hs.add(new SimpleGrantedAuthority("ROLE_" + roleName));
		return hs;
	}

}
