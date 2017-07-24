package com.wugao.center.infrastruture.spring.web.authentication;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(username)) {
			throw new UsernameNotFoundException("");
		}
		
		// 用户类型
		return new User(username, "111111", true, true, true, false, AuthorityUtils.createAuthorityList());
	}

}