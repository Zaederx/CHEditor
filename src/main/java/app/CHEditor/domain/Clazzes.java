package app.CHEditor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private boolean isNull;
	
	
	public Clazzes () {
		super();
		this.isNull = true;
	}
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
		this.isNull = false;
	}
	
	/**
	 * @return the isNull
	 */
	public boolean isNull() {
		return this.isNull;
	}
	/**
	 * @param isNull the isNull to set
	 */
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	
	/**
	 * For handling single clazz objects.
	 * @return
	 */
	@JsonIgnore
	public Clazz getClazz() {
		Clazz clazz = new Clazz();
		clazz.setName(name);
		clazz.setCid(cid);
		if (pid != 0) {
		clazz.setPid(pid);
		}
		clazz.setAbstract_(_abstract);
		return clazz;
	}
}
