package blog.controller.zuma;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("zuma")
public class Zuma {
	@RequestMapping("zuma.action")
	public String zuma() {
		return "zuma/zuma";
		
	}
	
}
