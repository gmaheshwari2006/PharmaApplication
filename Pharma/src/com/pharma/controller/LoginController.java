package com.pharma.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pharma.dao.UserDao;
import com.pharma.entity.City;
import com.pharma.entity.UserEntity;

//import javax.validation.Valid;

@Controller
@RequestMapping(value = "/login")
@SessionAttributes("sessionuser")
public class LoginController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/loginPage")
	public String showLoginPage(Model model) {
		model.addAttribute("sessionuser", "user has come to welcome page");
		return "EmpLogin";
	}

	@RequestMapping(value = "/{country}/loginPage")
	public String showCounrtyLoginPage(
			@PathVariable(value = "country") String mycountry) {
		System.out.println("Country is: " + mycountry);
		return "EmpLogin";
	}

	@RequestMapping(value = "/loginSubmit")
	public String loginSubmitted(HttpServletRequest request,
			@RequestParam String userName) {
		// String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		System.out.println("userName: " + userName);
		System.out.println("userPassword: " + userPassword);
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(userName);
		userEntity.setUserPassword(userPassword);
		boolean isRegisterdUser = getUserDao().loginSubmit(userEntity);
		System.out.println(isRegisterdUser);
		return "Welcome";
	}

	@RequestMapping(value = "/registerPage")
	public ModelAndView showRegisterPage(Model model,
			@RequestHeader(value = "Accept-Language") String AcceptLanguage,
			@CookieValue("JSESSIONID") String cookie, HttpSession session) {
		model.addAttribute("test", "test value");
		System.out.println("cookie: " + cookie);
		System.out.println("AcceptLanguage: " + AcceptLanguage);
		String welcomeMsg = (String) session.getAttribute("sessionuser");
		System.out.println("welcomeMsg: " + welcomeMsg);
		return new ModelAndView("registerPage", "user", new UserEntity());
	}

	@RequestMapping(value = "/registerSubmit")
	public ModelAndView registerSubmit(
			// @Validated
			@ModelAttribute(value = "user") UserEntity userEntity,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("registerPage", "user", userEntity);
		}
		getUserDao().registerUser(userEntity);
		return new ModelAndView("Welcome");
	}

	@ModelAttribute("cities")
	public List<City> cities() {
		List<City> cities = new ArrayList<City>();
		for (int i = 0; i < 5; i++) {
			City city = new City();
			city.setCityName("City " + i);
			cities.add(city);
		}
		return cities;
	}

	@ResponseBody
	@RequestMapping(value = "/registerInJson", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public UserEntity doSearch(@RequestBody UserEntity user) {
		System.out.println("name: " + user.getUserName());
		System.out.println("color: " + user.getEmailid());
		user.setUserName("gaurav");
		return user;
	}

	@ResponseBody
	@RequestMapping(value = "/getRegisterForJson", method = RequestMethod.GET, produces = "application/json")
	public UserEntity doGetSearch(@RequestParam String userName) {
		UserEntity user = new UserEntity();
		System.out.println("name: " + user.getUserName());
		System.out.println("color: " + user.getEmailid());
		user.setUserName("gaurav");
		return user;
	}

	@RequestMapping(value = "/firstReq")
	public String firstRequest(HttpServletRequest req) {
		System.out.println("first Request");
		return "redirect:secondReq";
		// return "forward:secondReq";
	}

	@RequestMapping(value = "/secondReq")
	public String seconRequest(Model model) {
		System.out.println("second Request");
		return "Welcome";
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
