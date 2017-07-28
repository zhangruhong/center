package com.wugao.center.support.spring.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;

public class RoleVoter implements AccessDecisionVoter<FilterInvocation> {

	@Override
	public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> attributes) {
		// 如果不是系统用户（可能是匿名用户），则弃权
		if (!(authentication.getPrincipal() instanceof UserDetails)) {
			return ACCESS_ABSTAIN;
		}

		// 如果是admin则无条件允许访问
		String username = ((UserDetails) authentication.getPrincipal()).getUsername();
		if ("admin".equals(username)) {
			return ACCESS_GRANTED;
		}

		// 角色不匹配，则弃权
		return ACCESS_ABSTAIN;
	}


	@Override
	public boolean supports(ConfigAttribute attribute) {
		return attribute instanceof SecurityConfig;
	}


	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(FilterInvocation.class);
	}

}
