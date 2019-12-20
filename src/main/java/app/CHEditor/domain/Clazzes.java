package app.CHEditor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Class used as a JSON retrieval object.
 * Used by Jackson to serialise and deserialise
 * JSON objects.
 * @author Zachary Ishmael
 *
 */
@JsonRootName("classes")
public class Clazzes extends Clazz {
	
	private Clazz[] classes;

	/**
	 * @return the clazzes
	 */
	public Clazz[] getClasses() {
		return classes;
	}

	/**
	 * @param clazzes the clazzes to set
	 */
	public void setClasses(Clazz[] clazzes) {
		this.classes = clazzes;
	}
	

	public Clazz getClazz() {
		Clazz clazz = new Clazz();
		clazz.setName(name);
		clazz.setCid(cid);
		if (pid != 0) {
		clazz.setPid(pid);
		}
		clazz.setAbstract_(_abstract);
		clazz.setNull(false);
		return clazz;
	}
}
