package com.wugao.center.domain.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.wugao.center.infrastruture.exception.AppException;

@Validated
@Service
public class UserService {

	public static final String USERNAME_ADMIN = "admin";
	private static final String DEFAULT_PASSSORD = "111111";

	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepo;

	public User saveUser(@Valid User user) {
		// 用户名
		if (!isLegalUsername(user.getUsername())) {
			throw new AppException("账号必须英文或数字开头，并且不能包含中文及特殊字符");
		}
		if (userRepo.getByUsername(user.getUsername()) != null) {
			throw new AppException("账号已存在");
		}
		// 密码
		if (StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(passwordEncoder.encode(DEFAULT_PASSSORD));
		} else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		return userRepo.save(user);
	}

	public void updateUser(String id, @Valid User user) {
		User savedUser = userRepo.getById(id);
		if (savedUser == null) {
			throw new AppException("账号不存在");
		}
		// 用户名（不能更改）
		// 密码
		savedUser.setPassword(StringUtils.isEmpty(user.getPassword()) ? savedUser.getPassword() : user.getPassword());
		// 昵称
		savedUser.setNickname(StringUtils.isEmpty(user.getNickname()) ? savedUser.getNickname() : user.getNickname());
		// 手机号
		savedUser.setMobile(user.getMobile());
		savedUser.setEmail(user.getEmail());
		// 状态
		savedUser.setEnabled(user.getEnabled() == null ? savedUser.getEnabled() : user.getEnabled());
		// 保存更新
		userRepo.update(savedUser);
	}

	public void resetUserPassword(String userId) {
		User user = userRepo.getById(userId);
		if (user == null) {
			throw new AppException("用户不存在");
		}
		user.setPassword(passwordEncoder.encode(DEFAULT_PASSSORD));
		/* user.setPasswordChangeTime(new Date()); */
		userRepo.update(user);
	}

	private static boolean isLegalUsername(String username) {
		String regex = "^[A-Za-z0-9_-]{1,20}$";
		Pattern p = Pattern.compile(regex);
		return p.matcher(username).matches();
	}

}
