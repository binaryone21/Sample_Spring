package seunghee.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
	@Autowired
	private ViewService service;

	@RequestMapping("/number")
	public String number() {
		return "/view/number";
	}

	@RequestMapping("/date")
	public String date() {
		return "/view/date";
	}

	@RequestMapping("/scroll")
	public String scroll() {
		return "/view/scroll";
	}
}
