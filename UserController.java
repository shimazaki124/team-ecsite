package jp.co.internous.wings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.wings.model.domain.MstUser;
import jp.co.internous.wings.model.form.UserForm;
import jp.co.internous.wings.model.mapper.MstUserMapper;
import jp.co.internous.wings.model.session.LoginSession;

@Controller
@RequestMapping("/wings/user")
public class UserController {
	
	@Autowired
	private MstUserMapper mstUserMapper;
	
	@Autowired
	private LoginSession loginSession;
		
	@RequestMapping("/")
	public String index(Model m) {
		m.addAttribute("loginSession", loginSession);
		return "register_user";
	}
	
	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestBody UserForm f) {
		int count = mstUserMapper.findCountByUserName(f.getUserName());
		return count > 0;
	}

	@RequestMapping("/register")
	@ResponseBody
	public boolean register(@RequestBody UserForm f) {
		MstUser user = new MstUser(f);
		int count = mstUserMapper.insert(user);
		return count > 0;
	}

}
