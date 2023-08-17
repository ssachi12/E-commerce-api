package org.jsp.api.service;

import org.jsp.api.dao.UserDao;
import org.jsp.api.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
@Service
public class GenerateUserLinkService {
	@Autowired
	private UserDao dao;

	public String getVerificationLinkUser(HttpServletRequest request, User user) {
		String token = RandomString.make(45);
		user.setToken(token);
		user.setStatus("InActive");
		dao.updateUser(user);
		String siteurl=request.getRequestURL().toString();
		String url=siteurl.replace(request.getServletPath(), "");
		String verify_link=url+"/active?token="+token;
		System.out.println(siteurl);
		System.out.println(url);
		System.out.println(verify_link);
		return verify_link;
	}
}
