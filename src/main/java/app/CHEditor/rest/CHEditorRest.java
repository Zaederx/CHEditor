package app.CHEditor.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
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
import app.CHEditor.formObjects.DeleteForm;
import app.CHEditor.formObjects.Response;
import app.CHEditor.repositories.ClazzRepository;
import app.CHEditor.validation.ClazzValidator;
import app.CHEditor.validation.CreateClazzValidator;

@RestController
@RequestMapping({"cheditor/api/",""})
public class CHEditorRest {
	
	@Autowired
	ClazzRepository cRepo;
	
	@InitBinder("clazzes")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new CreateClazzValidator(cRepo));
	}
	
	List<Clazz> parents = new ArrayList<Clazz>();
	String message = "";
	
	@Transactional
	@GetMapping("addclass")
	public Response createClass(@RequestParam(required = true) Integer pid, @RequestParam Integer cid,
		@RequestParam String name, @RequestParam(name = "abstract", required = false) Boolean _abstract) {
		Clazz c = new Clazz();
		Response res = new Response();
		String message = "";
//		https://localhost:8090/addclass?name=People&cid=13&pid=12&abstract=true
		boolean cidCheck = false;
		boolean pidCheck = false;
		boolean nameCheck = false;
		
		Clazz check = null;
		
		//TODO Set pid if does not already exist
		if (pid!=null) {
			check = cRepo.findByCid(pid);
			if (check == null) {
				message += "pid "+pid+" not found.";
				System.out.println("message:"+message);
			} else {
			pidCheck = true;
			}
		} else {
			pidCheck = true;
		}
		
		
		//Set CID IF Does not already exist
		if (cid != null) {
			check = null;
			check = cRepo.findByCid(cid);
			if (check != null) {
				message += "cid "+cid+" already exists.";
				System.out.println("message:"+message);
			} else {
				cidCheck = true;
			}
		}
		
		//Abstract
		if (_abstract == null) {
			_abstract = false;
		}
		
		//TODO - EXCEPTION HANDLNIG FOR THIS METHOD
		if (cidCheck && pidCheck) {
			c = new Clazz(pid,cid,name,_abstract);
			cRepo.save(c);
			System.out.println("*******Class Added ********");
		} else {
			res.setRet(false);
			res.setMessage(message);
			return res;
		}
		
		
		res.setRet(true);
		res.setMessage(null);
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
	
		String messagePid = "pid '"+ c.getPid()+ "' not found.";
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
	

	@PostMapping(value = {"deleteclassesJSON"}, consumes = {"application/json"})
	public Response deleteClassesJSON (@RequestBody DeleteForm json) {
		Response res = new Response();
		
		if (json.getToDeleteList() == null) {
			res.setRet(false);
			res.setMessage("no class selected.");
			return res;
		}
		for (Integer cid : json.getToDeleteList()) {
			Clazz c = null;
			c = cRepo.findByCid(cid);
			
			//if c exists - delete
			if (c!=null) {
				cRepo.delete(c);
				res.setRet(true);
				res.setMessage(null);
				return  res;
			} else {
				res.setRet(false);
				res.setMessage("cid "+cid+" does not exist");
				return res;
			}
		}
		res.setRet(false);
		res.setMessage("Internal Server Error");
		
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
			parents.remove(0);//remove initial class
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
	
	/**
	 * Returns the subclazzes of one given clazz.
	 * @param clazz - class you want subclazzes of 
	 * @return SubClazzContainer - with all subclazzes
	 */
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
					subs.getChildren().add(getSub(child));
					System.out.println("Subclass:*****"+subs.getChildren().get(0).getName());
				}
			}
			//TODO RETURN 
//			return subs;
		}

		return subs;
		
	}
	/**
	 * Returns subclazzes for multiple clazzes.
	 * Note: SubClazzContainer contain information for just one 
	 * hierarchy. A list SubClazzContainers  means essentially 
	 * multiple start nodes being returned.
	 * @param clazzes
	 * @return List<SubClazzContainer>
	 */
	public List<SubClazzContainer> getMultipleSubs(List<Clazz> clazzes) {
		
		List<SubClazzContainer> subsList = new ArrayList<SubClazzContainer>();
		
		//TODO - Performs getSub for each clazz
		if (clazzes != null) {
			for (Clazz c : clazzes) {
				subsList.add(getSub(c));
			}
		}
		
		return subsList;
		
	}
	
	/**
	 * Return hierachy of subclazzes from each base class.
	 * @return List<SubClazzContainer>
	 */
	@GetMapping("browse")
	public List<SubClazzContainer> browse () {
		//FIND list of all top level classes
		List<Clazz> clazzes = null;
		List<SubClazzContainer> subsList = new ArrayList<SubClazzContainer>();
		
		clazzes = cRepo.findListByPid(null);
		
		//TODO - get all top level clazzes sub clazzes
		if (clazzes != null) {
			subsList = getMultipleSubs(clazzes);
		}
		
		return subsList;
	}
	
	/**isValidName***/
	@GetMapping("isValid/name/{name}")
	public Response isValidName(@PathVariable String name) {
		Response res = new Response();
		res.setMessage(null);
		
		if (name.isBlank()) {
			res.setRet(false);
			res.setMessage("name is blank");
			return res;
		}
		
		Clazz c = null;
		c = cRepo.findByName(name);
		
		if (c != null) {
			res.setRet(false);
			res.setMessage("class name "+name+" already exists.");
			return res;
		}
		
		res.setRet(true);
		
		return res;
	}
	
	/**isValidPid***/
	@GetMapping("isValid/pid/{pid}")
	public Response isValidPid(@PathVariable Integer pid) {
		Response res = new Response();
		
		
		if (pid != null) {
			Clazz c = null;
			c = cRepo.findByCid(pid);
			if (c == null) {
				res.setRet(false);
				res.setMessage("pid "+pid+" is not found.");
				return res;
			}
		}
		
		res.setRet(true);
		
		return res;
	}
	
	/*isValidCid*/
	@GetMapping("isValid/cid/{cid}")
	public Response isValidCid(@PathVariable Integer cid) {
		Response res = new Response();
		res.setMessage(null);
		if (cid == null) {
			res.setRet(false);
			res.setMessage("cid is blank");
			return res;
		}
		
		Clazz c = null;
		c = cRepo.findByCid(cid);
		
		if (c != null) {
			res.setRet(false);
			res.setMessage("cid "+cid+" already exists.");
			return res;
		}
		res.setRet(true);
		return res;
	}
	

	
	
	
}
