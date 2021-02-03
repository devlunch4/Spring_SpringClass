package kr.or.ddit.mvc.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@RequestMapping("ajax")
@Controller
public class AjaxController {

	@ModelAttribute(name = "rangers")
	public List<String> rangers() {
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("james");
		rangers.add("cony");
		rangers.add("moon");

		return rangers;

	}

	// localhost/ajax/joinView
//	@RequestMapping("jsonView")
//	public String jsonView(Model model) {
//	List<String> rangers = new ArrayList<String>();
//	rangers.add("brown");
//	rangers.add("sally");
//	rangers.add("james");
//	rangers.add("cony");
//	rangers.add("moon");
//		model.addAttribute("rangers", rangers);
//		return "jsonView";
//	}

	//위의 @ModelAttribute 활용으로 model에 ranger가 이미 있다.
	// localhost/ajax/joinView
	@RequestMapping("jsonView")
	public String jsonView() {
		return "jsonView";
	}
	
	// localhost/ajax/jsonViewViewObj
	@RequestMapping("jsonViewViewObj")
	public View jsonViewViewObj() {
		return new MappingJackson2JsonView();
	}
	
	//과거 스타일 요즘엔 미사용 
	// localhost/ajax/jsonViewMav
	@RequestMapping("jsonViewMav")
	public ModelAndView jsonViewMav() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		
		return mav;
	}
	
	//public void json
}
