package org.jsp.api.service;

import org.jsp.api.dao.UserDao;
import org.jsp.api.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
@Service
public class GetUserResetPasswordLink {
	@Autowired
	private UserDao dao;

	public String resetPasswordLinkUser(HttpServletRequest request, User user) {
		String token = RandomString.make(45);
		user.setToken(token);
		dao.updateUser(user);
		String siteurl=request.getRequestURL().toString();
		String url=siteurl.replace(request.getServletPath(), "");
		String reset_link=url+"/user/reset?token="+token;
		System.out.println(siteurl);
		System.out.println(url);
		System.out.println(reset_link);
		return reset_link;
	}
}
