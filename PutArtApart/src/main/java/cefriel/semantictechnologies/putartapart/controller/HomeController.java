package cefriel.semantictechnologies.putartapart.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	protected Logger LOG = LogManager.getLogger();

	@RequestMapping("/")
	public ModelAndView homepage(ModelAndView model) {
		
		model.setViewName("home");

		return model;
	}
}
