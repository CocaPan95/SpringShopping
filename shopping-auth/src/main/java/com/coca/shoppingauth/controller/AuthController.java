package com.coca.shoppingauth.controller;


import com.coca.shoppingauth.domain.Oauth2TokenDto;
import com.coca.shoppingcommon.constant.AuthConstant;
import com.coca.shoppingmodel.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 * Created by macro on 2020/7/17.
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;


    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public CommonResult<Oauth2TokenDto> postAccessToken(HttpServletRequest request,
                                                       @RequestParam String grant_type,
                                                        @RequestParam String client_id,
                                                         @RequestParam String client_secret,
                                                         @RequestParam(required = false) String refresh_token,
                                                         @RequestParam(required = false) String username,
                                                         @RequestParam(required = false) String password) throws HttpRequestMethodNotSupportedException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type",grant_type);
        parameters.put("client_id",client_id);
        parameters.put("client_secret",client_secret);
        parameters.putIfAbsent("refresh_token",refresh_token);
        parameters.putIfAbsent("username",username);
        parameters.putIfAbsent("password",password);
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(request.getUserPrincipal(), parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();

        return CommonResult.success(oauth2TokenDto);
    }
}
