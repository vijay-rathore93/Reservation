package com.busPortal.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.busPortal.entity.Customer;
import com.busPortal.entity.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final RestTemplate restTemplate;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		ResponseEntity<Customer> rec = restTemplate.getForEntity("http://localhost:9002/customer/" + name,
				Customer.class);

		return new CustomUserDetails(rec.getBody(), getSimpleGrantedAuthority(rec.getBody().getRoleList()));
	}

	private Set<SimpleGrantedAuthority> getSimpleGrantedAuthority(Set<Role> set) {
		HashSet<SimpleGrantedAuthority> hs = new HashSet<SimpleGrantedAuthority>();
		for (Role role : set) {
			SimpleGrantedAuthority roles = new SimpleGrantedAuthority("ROLE_" + role.getRoleName());
			hs.add(roles);
		}
		return hs;
	}

}
