package net.xidlims.web.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.xidlims.dao.MessageDAO;

import net.xidlims.domain.Message;

import net.xidlims.service.message.MessageService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Message entities
 * 
 */

@Controller("MessageController")
public class MessageController {

	/**
	 * DAO injected by Spring that manages Message entities
	 * 
	 */
	@Autowired
	private MessageDAO messageDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Message entities
	 * 
	 */
	@Autowired
	private MessageService messageService;

	/**
	 * Show all Message entities
	 * 
	 */
	@RequestMapping("/indexMessage")
	public ModelAndView listMessages() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("messages", messageService.loadMessages());

		mav.setViewName("message/listMessages.jsp");

		return mav;
	}

	/**
	 * Register custom, context-specific property editors
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}

	/**
	 * Edit an existing Message entity
	 * 
	 */
	@RequestMapping("/editMessage")
	public ModelAndView editMessage(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("message", messageDAO.findMessageByPrimaryKey(idKey));
		mav.setViewName("message/editMessage.jsp");

		return mav;
	}

	/**
	 * Select the Message entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteMessage")
	public ModelAndView confirmDeleteMessage(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("message", messageDAO.findMessageByPrimaryKey(idKey));
		mav.setViewName("message/deleteMessage.jsp");

		return mav;
	}

	/**
	 * Select an existing Message entity
	 * 
	 */
	@RequestMapping("/selectMessage")
	public ModelAndView selectMessage(@RequestParam Integer idKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("message", messageDAO.findMessageByPrimaryKey(idKey));
		mav.setViewName("message/viewMessage.jsp");

		return mav;
	}

	/**
	 * Delete an existing Message entity
	 * 
	 */
	@RequestMapping("/deleteMessage")
	public @ResponseBody String deleteMessage(@RequestParam Integer idKey, HttpSession session) {
		Message message = messageDAO.findMessageByPrimaryKey(idKey);
		messageService.deleteMessage(message);
		session.setAttribute("messageNum", messageService.countmessage( ));
		return "success";
	}

	/**
	 * Save an existing Message entity
	 * 
	 */
	@RequestMapping("/saveMessage")
	public String saveMessage(@ModelAttribute Message message) {
		messageService.saveMessage(message);
		return "forward:/indexMessage";
	}

	/**
	 * Create a new Message entity
	 * 
	 */
	@RequestMapping("/newMessage")
	public ModelAndView newMessage() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("message", new Message());
		mav.addObject("newFlag", true);
		mav.setViewName("message/editMessage.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Message entities
	 * 
	 */
	public String indexMessage() {
		return "redirect:/indexMessage";
	}

	/**
	 */
	@RequestMapping("/messageController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}
}