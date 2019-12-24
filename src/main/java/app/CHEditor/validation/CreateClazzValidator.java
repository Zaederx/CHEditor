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
	
	/**
	 * A method to allow validation of Clazz forms.
	 * Uses java generics in order to work with any 
	 * class that extends and implments the {@link AbstractClazz}.
	 * @param <C> - any class that extends from AbstractClazz
	 * @param clazzes
	 * @param errors
	 */
	public <C extends AbstractClazz>void validateClazz (C c, Errors errors) {
	//Required Fields - to be used for second part
//		System.out.println("c.getName():"+c.getName());
		
		Clazz cTest = null;
		cTest = cRepo.findByCid(c.getCid());
		
		if (c != null) {
			errors.rejectValue("cid", "", "cid '"+c.getCid()+"' is already assigned");
		}
		
		Clazz c2 = cRepo.findByName(c.getName());
		
		if (c2 != null) {
			errors.rejectValue("name", "", "Clazz '" +c.getName() + "' name is already in use");
		}
		
		if (c.getPid() != null) {
			Clazz c3 = cRepo.findByPid(c.getPid());
			if (c3 == null) {
				errors.rejectValue("pid", "", "pid '"+c.getPid()+"' already does not exist");
			}
		}
	}
	
	public void validateClazzes(Clazz c, Errors errors) {
		if (c.getName() == null) {
			errors.rejectValue("name", "", "name is empty.");
		}
	}
	@Override
	public void validate(Object target, Errors errors) {
		AbstractClazz clazzes = (AbstractClazz) target;
		
		//works for single clazz
		if (clazzes.isSingleClazz()) {
			System.out.println("\n *******Class is single class******\n");
			validateClazz(clazzes, errors);
		} else {
			//name validation does not work for clazzes
			System.out.println("\n ******class is not single class*****\n");
			for (Clazz c : clazzes.getClasses()) {
				validateClazzes(c,errors);
			}
		}
	}
}
