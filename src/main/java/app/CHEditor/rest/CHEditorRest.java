package app.CHEditor.rest;




import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import app.CHEditor.domain.AbstractClazz;
import app.CHEditor.domain.Clazz;
import app.CHEditor.domain.Clazzes;
import app.CHEditor.domain.SubClazzContainer;
import app.CHEditor.domain.SuperClazzContainer;
import app.CHEditor.formObjects.ClazzForm;
import app.CHEditor.formObjects.Response;
import app.CHEditor.repositories.ClazzRepository;
import app.CHEditor.validation.ClazzValidator;
import app.CHEditor.validation.CreateClazzValidator;

@RestController
@RequestMapping("cheditor/api/")
public class CHEditorRest {
	
	@Autowired
	ClazzRepository cRepo;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new CreateClazzValidator(cRepo));
	}
	
	List<Clazz> parents = new ArrayList<Clazz>();
	String message = "";
	
	@Transactional
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
		res.setMessage("Class '"+c.getName()+"' added.");
		return res;
	}
	
	

//	@Transactional
	/**
	 * Designed to handle Clazz and ClassForm objects
	 * @param c
	 * @param result
	 * @return
	 */
	
//	<C extends AbstractClazz>

	public Response create(Clazz c, BindingResult result) {
		Response res = new Response();
//		res.setMessage("Testing 123");
		/*Using DTO to allow even invalid fields to be stored
		 * Otherwise number info is lost / not stored not complianted to entity annotation constraints.
		 * */
		System.out.println("create c getName:"+c.getName());
		/*Validate ClazzForm*/
	
		String messagePid = "pid '"+ c.getPid()+ "' not found. ";
		String messageCid = "cid '"+c.getCid()+"' already exists. ";
		String messageName = "name '"+c.getName()+"' already exists. ";
		
		if (c.getName() == null) {
			message += "name is empty.";
		}
		
		if (c.getCid() == null) {
			message += "cid is empty.";
		}
		
		if (c.getPid () == null) {
			message += "pid is empty";
		}
		
		
		if (result.hasFieldErrors("pid")) {
			res.setRet(false);
			message += messagePid;
			
	
		}
		
		if (result.hasFieldErrors("cid")) {
			res.setRet(false);
			message += messageCid;
		
		}
		
		if (result.hasFieldErrors("name")) {
			res.setRet(false);
			message += messageName;
		}
		
		if (result.hasErrors()) {
			res.setRet(false);
			res.setMessage(message);
			return res;
		}

		System.out.println("Clazz == ClazzForm");
		
		try {
			cRepo.save(c);
			res.setRet(true);
			res.setMessage(null);
		} catch (Exception e) { //Not necessary, but added for defensive programming style
			e.printStackTrace();
			res.setRet(false);
			res.setMessage("cid '" + c.getCid() + "' already exists.");
			return res;
		}
		System.out.println(res.getMessage());
		return res;
	}


//	@Transactional//provies rollback functionality if something goes  wrong
	@PostMapping(value = "addclassJSON", consumes = "application/json", produces = "application/json")
	public Response createClass(@RequestBody @Valid Clazzes clazzes, BindingResult result) {
		Response res = new Response();
		message = "";
		if (!clazzes.isNull()) {//if it does contain anything in Clazz[]
			for (Clazz c : clazzes.getClasses()) {//Clazzes
				System.out.println(c.getName());
				res = create(c,result);
			}
		}
		else {//if does not contain something in Clazz[]
				Clazz c2 = new Clazz(clazzes.getClazzForm());//ClazzForm
				res = create(c2, result);
		}
		return res;
	}
	
	@GetMapping("getclass/{cid}")
	public Object readClass(@PathVariable int cid) {
		Response res = new Response();
		Clazz c = null;
		
		c = cRepo.findByCid(cid);
		
		if (c == null) {
			res.setRet(false);
			res.setMessage("cid '"+cid+"' not exist");
			return res;
		} else {
			return c;
		}
	}
	
	/*TODO ADD REQUEST MAPPING - POST MAPPING*/
	public Response updateClass() {
		Response res = new Response();
		
		return res;
	}
	
	/*TODO ADD REQUEST MAPPING*/
	@GetMapping("deleteclass/{cid}")
	public Response deleteClass(@PathVariable int cid) {
		Response res = new Response();
		Clazz c = cRepo.findByCid(cid);
		try {
		cRepo.delete(c);
		res.setRet(true);
		res.setMessage(null);
		} catch (Exception e) {
			e.printStackTrace();
			res.setRet(false);
			res.setMessage("cid "+cid+" does not exist");
			return res;
		}
		
		return res;
	}
	
	@GetMapping("superclasses/{cid}")
	public Object getSuperclasses(@PathVariable Integer cid) {
		Response res = new Response();
		res.setRet(null);
		res.setResult(false);
		res.setMessage("cid '"+cid+"' does not exist");
		List<Clazz> parents = new ArrayList<Clazz>();
		SuperClazzContainer clazzes = new SuperClazzContainer();
		boolean search = true;
		while (search) {
			Clazz c = null;
			if (cid != null) {
				c = cRepo.findByCid(cid);
			}
			if (c == null) { search = false; System.out.println(search);}
			else {
				parents.add(c);
				if (cid != null) {
				cid = c.getPid();
				}
			}
		}
		if (!parents.isEmpty()) {
			clazzes.setParents(parents);
			return clazzes;
		}
		return res;
	}
	
	@GetMapping("subclasses/{cid}")
	public Object getSubClasses(@PathVariable Integer cid) {
		Response res = new Response();
		res.setRet(null);
		res.setResult(null);
		res.setMessage(null);
		
		SubClazzContainer subs = null;
		
		if (cid != null) {
			
			Clazz clazz = null;
			clazz = cRepo.findByCid(cid);
			
			if (clazz != null) {
				subs = getSub(clazz);
			}
			
			return subs;
		}
		
		return res;
	}
	
	public SubClazzContainer getSub(Clazz clazz) {
		
		List<Clazz> children = null;
		SubClazzContainer subs = new SubClazzContainer();
		subs.setCid(clazz.getCid());
		subs.setName(clazz.getName());
		
		//TODO - ELSE return SubClazzContainer of children to previous SubClazzContainer
		
		//TODO - CHECK IF HAS CHILD
		if (clazz != null) {
			children = cRepo.findListByPid(clazz.getCid());
			if (children != null) {
			//TODO - CHECK IF Child had child
				for (Clazz child : children) {
					int childCid = child.getCid();
					SubClazzContainer s = getSub(child);
					subs.getChildren().add(s);
					System.out.println("Subclass:*****"+subs.getChildren().get(0).getName());
				}
			}
			//TODO RETURN 
			return subs;
		}

		return subs;
		
	}
	
}
