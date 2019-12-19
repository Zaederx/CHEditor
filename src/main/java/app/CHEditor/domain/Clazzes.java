package app.CHEditor.domain;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Class used as a JSON retrieval object.
 * Used by Jackson to serialise and deserialise
 * JSON objects.
 * @author Zachary Ishmael
 *
 */
@JsonRootName("classes")
public class Clazzes {
	private Clazz[] clazzes;
	private Clazz clazz;
	private boolean clazzesIsNull;
	private boolean clazzIsNull;
	
	public Clazzes () {
		this.clazzesIsNull = true;
		this.clazzIsNull = true;
	}

	/**
	 * @return the classes
	 */
	public Clazz[] getClazzes() {
		return clazzes;
	}

	/**
	 * @param classes the classes to set
	 */
	public void setClazzes(Clazz[] clazzes) {
		this.clazzes = clazzes;
		this.clazzesIsNull = false;
	}

	/**
	 * @return the clazz
	 */
	public Clazz getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
		this.clazzIsNull = false;
	}

	/**
	 * @return the isNull
	 */
	public boolean isClazzesIsNull() {
		return clazzesIsNull;
	}

	/**
	 * @param isNull the isNull to set
	 */
	public void setClazzesIsNull(boolean isNull) {
		this.clazzesIsNull = isNull;
	}

	/**
	 * @return the clazzIsNull
	 */
	public boolean isClazzIsNull() {
		return clazzIsNull;
	}

	/**
	 * @param clazzIsNull the clazzIsNull to set
	 */
	public void setClazzIsNull(boolean clazzIsNull) {
		this.clazzIsNull = clazzIsNull;
	}
	
}
