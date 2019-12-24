package app.CHEditor.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.CHEditor.domain.AbstractClazz;
import app.CHEditor.domain.Clazz;
import app.CHEditor.domain.Clazzes;
import app.CHEditor.formObjects.ClazzForm;
import app.CHEditor.repositories.ClazzRepository;

public class CreateClazzValidator implements Validator{

	@Autowired
	ClazzRepository cRepo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		 return AbstractClazz.class.isAssignableFrom(clazz);
	}

	public CreateClazzValidator(ClazzRepository cRepo) {
		this.cRepo = cRepo;
	}
	
	public <C extends AbstractClazz>void validateClazz (C clazzes, Errors errors) {
	//Required Fields - to be used for second part
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cid", "","cid is empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "name is empty.");
		
		
		Clazz c = cRepo.findByCid(clazzes.getCid());
		
		if (c != null) {
			errors.rejectValue("cid", "", "cid '"+clazzes.getCid()+"' is already assigned");
		}
		
		Clazz c2 = cRepo.findByName(clazzes.getName());
		
		if (c2 != null) {
			errors.rejectValue("name", "", "Clazz '" +clazzes.getName() + "' name is already in use");
		}
		
		if (clazzes.getPid() != null) {
			Clazz c3 = cRepo.findByPid(clazzes.getPid());
	
			if (c3 == null) {
				errors.rejectValue("pid", "", "pid '"+clazzes.getPid()+"' already does not exist");
			}
		}
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		AbstractClazz clazzes = (AbstractClazz) target;
		
		if (clazzes.isSingleClazz()) {
			validateClazz(clazzes, errors);
		}
		 else {	
			for (Clazz c : clazzes.getClasses()) {
				validateClazz(c,errors);
			}
		}	
	}
}
