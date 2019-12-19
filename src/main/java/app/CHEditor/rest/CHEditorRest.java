package app.CHEditor.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.CHEditor.domain.Clazz;
import app.CHEditor.formObjects.Response;
import app.CHEditor.repositories.ClazzRepository;

@RestController
@RequestMapping("cheditor/api/")
public class CHEditorRest {
	
	@Autowired
	ClazzRepository cRepo;
	
	@GetMapping("addclass")
	public Response createClass(@RequestParam(required = true) Integer pid, @RequestParam Integer cid,
		@RequestParam String name, @RequestParam(name = "abstract") boolean _abstract) {
		Clazz c = new Clazz();
		
		c.setPid(pid);
		c.setCid(cid);
		c.setName(name);
		c.setAbstract_(_abstract);
		
		//TODO - EXCEPTION HANDLNIG FOR THIS METHOD
		cRepo.save(c);
		
		Response res = new Response();
		res.setRet(true);
		res.setMessage("Class "+c.getName()+" added.");
		return res;
	}
	
	@PostMapping(value = "addclassJSON", consumes = "application/json", produces = "application/json")
	public Response createClass(@RequestBody Clazz c) {
//		Clazz c = new Clazz();
		Response res = new Response();
		cRepo.save(c);
		res.setRet(true);
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
