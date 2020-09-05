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
import com.busPortal.model.RoleDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final RestTemplate restTemplate;
	
	
	@Value("${CUSTOMER_SERVICE}")
	private String customerURL;
	

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		ResponseEntity<CustomerDTO> rec = restTemplate.getForEntity(customerURL + name,
				CustomerDTO.class);

		return new CustomUserDetails(rec.getBody(), getSimpleGrantedAuthority(rec.getBody().getRoleList()));
	}

	private Set<SimpleGrantedAuthority> getSimpleGrantedAuthority(Set<RoleDTO> set) {
		HashSet<SimpleGrantedAuthority> hs = new HashSet<SimpleGrantedAuthority>();
		for (RoleDTO role : set) {
			SimpleGrantedAuthority roles = new SimpleGrantedAuthority("ROLE_" + role.getRoleName());
			hs.add(roles);
		}
		return hs;
	}

}
