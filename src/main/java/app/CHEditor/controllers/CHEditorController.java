package app.CHEditor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping({"cheditor/",""})
public class CHEditorController {

	@GetMapping("hierarchies")
	public String viewHierarchyPage() {
		return "add-delete-class";
	}
	
	@GetMapping({"home","/"})
	public String viewHome() {
		return "home";
	}
	
	@GetMapping("edit")
	public String editHierarchyPage() {
		return "edit-hierarchy";
	}
	
	@GetMapping("search")
	public String search() {
		return "search-hierarchies";
	}
	
	
}
