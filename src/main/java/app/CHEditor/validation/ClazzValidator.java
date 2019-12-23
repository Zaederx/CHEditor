package app.CHEditor.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.CHEditor.domain.AbstractClazz;
import app.CHEditor.domain.Clazz;
import app.CHEditor.domain.Clazzes;
import app.CHEditor.repositories.ClazzRepository;

public class ClazzValidator implements Validator{
	@Autowired
	ClazzRepository cRepo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		 return Clazz.class.equals(clazz);
	}

	public ClazzValidator(ClazzRepository cRepo) {
		this.cRepo = cRepo;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Clazz clazz = (Clazz) target;
		validateClazz(clazz,errors);
	}
	
	public void validateClazz (Clazz clazz, Errors errors) {
		//Required Fields - to be used for second part
			ValidationUtils.rejectIfEmptyOrWhitespace( errors, "cid", "","cid is empty.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "name is empty.");
			
			Clazz c = cRepo.findByCid(clazz.getCid());
			
			if (c != null) {
				errors.rejectValue("cid", "", "cid '"+clazz.getCid()+"' is already assigned");
			}
			
			Clazz c2 = cRepo.findByName(clazz.getName());
			
			if (c2 != null) {
				errors.rejectValue("name", "", "Clazz '" +clazz.getName() + "' name is already in use");
			}
			
			Clazz c3 = cRepo.findByPid(clazz.getPid());
			
			if (c3 == null) {
				errors.rejectValue("pid", "", "pid '"+clazz.getPid()+"' already does not exist");
			}
		}
}
