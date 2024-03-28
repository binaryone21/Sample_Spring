package seunghee.event.apply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import seunghee.module.ResultVO;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/event")
public class ApplyEventController {

	@Autowired
	private ApplyEventService service;

	@RequestMapping("/page")
	public String eventPage() {
		return "/event/page";
	}

	@ResponseBody
	@RequestMapping("/apply.ajax")
	public ResultVO applyEvent(HttpServletRequest req) {
		return service.applyEvent(req);
	}
}
