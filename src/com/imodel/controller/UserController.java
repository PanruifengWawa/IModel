package com.imodel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imodel.service.UserService;
import com.imodel.utils.DataWrapper;

@Controller
@RequestMapping(value="v1/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<String> login(
    		@RequestParam(value="userName",required=true) String userName,
    		@RequestParam(value="password",required=true) String password
    		) {
        return userService.login(userName, password);
    }

}
