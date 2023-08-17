package org.jsp.api.service;

import org.jsp.api.dao.MerchantDao;
import org.jsp.api.dto.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class GetResetPasswordLink {
	@Autowired
	private MerchantDao dao;

	public String resetPasswordLink(HttpServletRequest request, Merchant merchant) {
		String token = RandomString.make(45);
		merchant.setToken(token);
		dao.updateMerchant(merchant);
		String siteurl=request.getRequestURL().toString();
		String url=siteurl.replace(request.getServletPath(), "");
		String reset_link=url+"/merchant/reset?token="+token;
		System.out.println(siteurl);
		System.out.println(url);
		System.out.println(reset_link);
		return reset_link;
	}
}
