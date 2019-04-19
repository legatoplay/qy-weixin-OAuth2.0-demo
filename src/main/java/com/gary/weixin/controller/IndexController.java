package com.gary.weixin.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Gary on 2019-04-19.
 *
 * @author Gary
 * @version 0.0.1
 * @date 2019-04-19
 * @since 0.0.1
 */
@CrossOrigin()
@RestController
public class IndexController {
    @RequestMapping
    public String index(HttpServletRequest request) {
        return "welcome:" + request.getAttribute("userId");
    }
}
