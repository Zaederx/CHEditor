package app.CHEditor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import app.CHEditor.formObjects.ClazzForm;

/**
 * Class used as a JSON retrieval object.
 * Used by Jackson to serialise and deserialise
 * JSON objects.
 * @author Zachary Ishmael
 *
 */
@JsonRootName("classes")
public class Clazzes extends AbstractClazz {
	
	private Clazz[] classes;
	/*Whether this object contains
	 * Clazz[]*/
	@JsonIgnore
	private boolean isNull;
	
	private boolean singleClass = true;
	
	public Clazzes () {
//		super();//TODO - REMOVEI FOUND UNECESSAYR IN REFACTROING
		this.isNull = true;
	}
	/*Note: getters MUST match JsonRootName to function correctly
	 * with Jackson parsing.
	 * i.e. class - getClasses - not getClazzes*/
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
		this.singleClass = false;
	}
	
	/**
	 * @return the isNull
	 */
	@JsonIgnore
	public boolean isNull() {
		return this.isNull;
	}
	/**
	 * @param isNull the isNull to set
	 */
	@JsonIgnore
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	
	/**
	 * For handling single clazz objects.
	 * @return
	 */
	@JsonIgnore
	public ClazzForm getClazzForm() {
		ClazzForm form = new ClazzForm();
		form.setName(name);
		form.setCid(cid);
		if (pid != null) {
		form.setPid(pid);
		}
		form.setAbstract_(_abstract);
		return form;
	}
	
	/*Explicitly Overriden to Allow better use with generics */
	@JsonIgnore
	@Override
	public boolean isSingleClazz() {
		return this.singleClass;
	}
	@JsonIgnore
	@Override
	public void setSingleClazz(boolean singleClazz) {
		this.singleClass = singleClazz;
	}
	@Override
	public int getId() {
		return this.id;
	}
	@Override
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public Integer getPid() {
		return this.pid;
	}
	@Override
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	@Override
	public Integer getCid() {
		this.singleClass = true;
		return this.cid;
	}
	@Override
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public boolean isAbstract_() {
		return this._abstract;
	}
	@Override
	public void setAbstract_(boolean _abstract) {
		this._abstract = _abstract;
	}
	@Override
	public void setClasses() {
	
	}

}
