package com.mjwise.demo.controller.data.user;

import com.alibaba.fastjson.JSONObject;
import com.mjwise.demo.entity.systerm.SystemUser;
import com.mjwise.demo.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
//@RestController=@Controller+@ResponseBody
@RequestMapping("/api/user/")
public class LoginController {

	@Autowired
	SystemUserService userService = new SystemUserService();

	@PostMapping("/getUser")
	public JSONObject getUser(@RequestBody SystemUser user, HttpSession session){
		JSONObject ob = new JSONObject();
		ob.put("id",user.getId());
		ob.put("name",user.getUserName());
		ob.put("password",user.getUserName());

		return ob;
//		SystemUser user = userService.find();
//		if(user!=null && user.getPassword().equals(password)){
//			session.setAttribute("loginUser",username);
//			return "home";
//		} else {
//			map.put("msg","用户名密码登陆错误");
//			return "login";
//		}
	}

	@PostMapping("/update")
	public String register(@RequestParam(value = "username", required = false) String username,
	                       @RequestParam(value = "password", required = false) String password){
		if(username!=null && password!=null){
			SystemUser user = userService.find(username);
			if(user!=null){
				userService.update(username,password);
			}
			else{
				userService.save(username,password);
			}
		}
		return "home";
	}


}
