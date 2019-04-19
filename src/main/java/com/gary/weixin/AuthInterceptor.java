package com.gary.weixin;

import com.alibaba.fastjson.JSONObject;
import com.gary.weixin.utils.SimpleCacheHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Gary on 2019-04-19.
 *
 * @author Gary
 * @version 0.0.1
 * @date 2019-04-19
 * @since 0.0.1
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            response.sendRedirect("/error");
        }
        JSONObject user = getUserInfo(code);
        if (user != null) {
            System.out.println(user.getString("UserId"));
            System.out.println(user.getString("DeviceId"));
        }
        assert user != null;
        request.setAttribute("userId", user.getString("UserId"));
        return super.preHandle(request, response, handler);
    }

    private JSONObject getUserInfo(String code) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=" + getAccessToken() + "&code=" + code;
        return get(url);
    }

    private JSONObject get(String url) {
        String response = restTemplate.getForObject(url, String.class);
        if (StringUtils.isEmpty(response))
            return null;
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (jsonObject.getIntValue("errcode") == 0) {
            return jsonObject;
        }
        return null;
    }


    /**
     * 企业id：ww66f2d5b59e8e9240
     * 应用密钥：d2tcpwd8nx-p_Ea6Yx0gaxh7BrhEnm4ByPalCRzQUg4
     *
     * @return
     */
    private String getAccessToken() {
        String cacheKey = "ACCESS_TOKEN";
        String token = (String) SimpleCacheHolder.getCache(cacheKey);

        if (StringUtils.isEmpty(token)) {
            JSONObject jsonObject = requestToken();
            if (jsonObject != null) {
                token = jsonObject.getString("access_token");
                int expires = jsonObject.getIntValue("expires_in");
                SimpleCacheHolder.setCache(cacheKey, token, (expires - 200) * 100L);
            }
        }
        return token;
    }

    private JSONObject requestToken() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww66f2d5b59e8e9240&corpsecret=d2tcpwd8nx-p_Ea6Yx0gaxh7BrhEnm4ByPalCRzQUg4";
        return get(url);
    }
}
