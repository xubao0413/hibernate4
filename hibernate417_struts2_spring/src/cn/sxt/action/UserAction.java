package cn.sxt.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.sxt.pojo.User;
import cn.sxt.service.UserService;

@Controller("userAction")
public class UserAction {
	private List<User> list;
	@Autowired
	private UserService userService;
	
	public String list(){
		list = userService.getList();
		return "success";
	}
	
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
