package app.CHEditor.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.CHEditor.domain.Clazz;
import app.CHEditor.formObjects.Response;
import app.CHEditor.repositories.ClazzRepository;

@RestController
@RequestMapping("cheditor/rest/")
public class CHEditorRest {
	
	@Autowired
	ClazzRepository cRepo;
	
	@PostMapping({"addClass/{pid}/{cid}/{name}/{abstract}", "addClass/{pid}/{name}/{abstract}"})
	public Response createClass(@PathVariable int pid, @PathVariable int cid,
			@PathVariable String name, @PathVariable boolean _abstract) {
		Clazz c = new Clazz(pid,cid,name,_abstract);
		cRepo.save(c);
		Response res = new Response();
		res.setResponse(true);
		res.setMessage("Class "+c.getName()+" added.");
		return res;
	}
	
	/*TODO ADD REQUEST MAPPING*/
	public Response readClass() {
		Response res = new Response();
		
		return res;
	}
	
	/*TODO ADD REQUEST MAPPING - POST MAPPING*/
	public Response updateClass() {
		Response res = new Response();
		
		return res;
	}
	
	/*TODO ADD REQUEST MAPPING*/
	public Response deleteClass() {
		Response res = new Response();
		
		return res;
	}
}
