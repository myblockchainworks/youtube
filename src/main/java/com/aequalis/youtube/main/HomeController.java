/**
 * 
 */
package com.aequalis.youtube.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aequalis.youtube.blockchainapi.WebAPICall;
import com.aequalis.youtube.dto.ChannelDTO;
import com.aequalis.youtube.model.User;
import com.aequalis.youtube.service.UserService;

/**
 * @author anand
 *
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired(required = true)
	public HttpServletRequest request;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	public HttpSession getSession() {
		if (request != null) {
			return request.getSession();
		}
		return null;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginScreen(Model model) {
	    return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutScreen(Model model) {
		HttpSession session = getSession();
		session.removeAttribute("loginUser");
	    return "home";
	}
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(Model model, HttpServletRequest httpServletRequest) {
		Date loginTime = new Date();
		String userName = httpServletRequest.getParameter("userName");
		String password = httpServletRequest.getParameter("password");
		
		User user = userService.loginUser(userName, password);
		if (user != null) {
			
			HttpSession session = getSession();
			session.setAttribute("loginUser", user.getUserid());
			user.setLastLogin(user.getCurrentLogin());
			user.setCurrentLogin(loginTime);
			userService.addUser(user);
			
			if (user.getIsadminuser() != null && user.getIsadminuser()) {
				return new ModelAndView("redirect:/adminhome");
			} else {
				// Normal user login
				return new ModelAndView("redirect:/myhome");
			}
		}
		
		model.addAttribute("errormsg", "Invalid user name or password. Please try again.");
		return new ModelAndView("redirect:/");	
		
	}
	
	@RequestMapping(value = "/adminhome", method = RequestMethod.GET)
	public String transactionListScreen(Model model) {
	    
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			List<ChannelDTO> channels = WebAPICall.listChannels();
			model.addAttribute("channels", channels);
			
			return "adminhome";
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/newchannel", method = RequestMethod.GET)
	public String newCoin(Model model, HttpServletRequest request) {
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			
			return "newchannel";
		} else {
			return "home";
		}
	}
	
	@RequestMapping(value = "/addNewChannel", method = RequestMethod.POST)
	public ModelAndView addNewCoin(Model model, HttpServletRequest httpServletRequest) {
		
		String channelId = httpServletRequest.getParameter("channelId");
		String ownerAddress = httpServletRequest.getParameter("ownerAddress");
		
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			if (WebAPICall.isChannelAvailable(channelId)) {
				ChannelDTO channelDTO = WebAPICall.getChannelDetailFromYoutube(channelId);
				if (channelDTO != null) {
					WebAPICall.addNewChannel(channelDTO.getTitle(), channelId, ownerAddress, channelDTO.getSubscriberCount());
					return new ModelAndView("redirect:/adminhome");
				} else {
					model.addAttribute("errormsg", "Invaild Channel Id, Please try again!");
				}
				
			} else {
				model.addAttribute("errormsg", "Channel is already added, Please try again!");
			}
			
			return new ModelAndView("redirect:/newchannel");
		} else {
			return new ModelAndView("redirect:/");
		}
	}
}
