package app.CHEditor.rest;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.CHEditor.domain.Clazz;
import app.CHEditor.domain.Clazzes;
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
	
//	@PostMapping(value = "addclassJSON", consumes = "application/json", produces = "application/json")
	@Transactional
	public Response createClass(@RequestBody Clazz c) {
		Response res = new Response();
		try {
		cRepo.save(c);
		res.setRet(true);
		} catch (Exception e) {
			Clazz existingClass = cRepo.findByCid(c.getCid());
			e.getMessage();
			res.setRet(false);
			res.setMessage("A class named \""+ existingClass.getName()+"\" with cid \"" + c.getCid() + "\" already exists.");
		}
		return res;
	}
	
	public Response create(Clazz c) {
		Response res = new Response();
		try {
		cRepo.save(c);
		res.setRet(true);
		} catch (Exception e) {
			Clazz existingClass = cRepo.findByCid(c.getCid());
			e.getMessage();
			res.setRet(false);
			res.setMessage("A class named \""+ existingClass.getName()+"\" with cid \"" + c.getCid() + "\" already exists.");
		}
		return res;
	}

//	@Transactional
	@PostMapping(value = "addclassJSON", consumes = "application/json", produces = "application/json")
	public List<Response> createClass( @RequestBody(required = false) Clazzes clazzes) {
		List<Response> res = new ArrayList<Response>();
	
		if (!clazzes.isClazzesIsNull()) {
			try {
				for (Clazz c : clazzes.getClazzes()) {
					res.add(create(c));
				}
			} catch (Exception e) {
				e.printStackTrace();
				Response r = new Response();
				r.setRet(false);
				r.setMessage("Error 1 adding classes to hierarchy.");
				res.add(r);
			}
		} else if (!clazzes.isClazzIsNull()) {
			try {
				res.add(create(clazzes.getClazz()));
			} catch (Exception e) {
				e.printStackTrace();
				Response r = new Response();
				r.setRet(false);
				r.setMessage("Error 2 adding class to hierachy.");
				res.add(r);
			}
		}
		
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
