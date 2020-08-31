package com.system.bibliotec.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.config.ApiPropertyConfig;


@RestController
@RequestMapping("/token/revoke")
public class TokenResource {

	@Autowired
	private ApiPropertyConfig apiProperty;


	@Resource(name = "tokenServices")
	private ConsumerTokenServices tokenServices;



	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public void revokeToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.contains("Bearer")) {
			String tokenId = authorization.substring("Bearer".length() + 1);
			System.out.println("tokenId : " + tokenId);
			tokenServices.revokeToken(tokenId);
			//tokenStore.removeRefreshToken(token);
		}
	}



	@DeleteMapping("/revokeee")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(apiProperty.getSeguranca().isEnableHttps());
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);

		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}




}
