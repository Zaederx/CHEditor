package app.CHEditor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractClazz {
	
	//For part 2 validation by default - must be false for correct validation
	@JsonIgnore
	public boolean singleClazz = false;
	
	@JsonIgnore
	protected int id;

	protected Integer pid;
	
	protected Integer cid;
	
	protected String name;
	
	@JsonProperty("abstract")
	protected Boolean _abstract;
	
	
	/**
	 * @return the singleClazz
	 */
	public abstract boolean isSingleClazz();
	/**
	 * @param singleClazz the singleClazz to set
	 */
	public abstract void setSingleClazz(boolean singleClazz);
	/**
	 * @return the id
	 */
	public abstract int getId();
	/**
	 * @param id the id to set
	 */
	public abstract void setId(int id);

	/**
	 * @return the pid
	 */
	public abstract Integer getPid();
	/**
	 * @param pid the pid to set
	 */
	public abstract void setPid(Integer pid);
	/**
	 * @return the cid
	 */
	public abstract Integer getCid();
	/**
	 * @param cid the cid to set
	 */
	public abstract void setCid(Integer cid);
	/**
	 * @return the name
	 */
	public abstract String getName();
	/**
	 * @param name the name to set
	 */
	public abstract void setName(String name);
	/**
	 * @return the _abstract
	 */
//	@JsonProperty("abstract")
	public abstract String isAbstract_();
	/**
	 * @param _abstract the abstract_ to set
	 */
//	@JsonProperty("abstract")
	public abstract void setAbstract_(Boolean _abstract);
	
	public abstract Boolean isAbstractBoolean();
	
	public abstract Clazz[] getClasses();
	
	public abstract void setClasses();
}
