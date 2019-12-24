package app.CHEditor.formObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import app.CHEditor.domain.AbstractClazz;
import app.CHEditor.domain.Clazz;

@JsonRootName("class")
@JsonInclude(Include.NON_NULL)
public class ClazzForm extends AbstractClazz{
	
	/**
	 * @return the singleClazz
	 */
	public boolean isSingleClazz() {
		return singleClazz;
	}
	/**
	 * @param singleClazz the singleClazz to set
	 */
	public void setSingleClazz(boolean singleClazz) {
		this.singleClazz = singleClazz;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the pid
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * @return the cid
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
//			this.isNull = false;
	}
	/**
	 * @return the _abstract
	 */
	@JsonProperty("abstract")
	public boolean isAbstract_() {
		return _abstract;
	}
	/**
	 * @param _abstract the abstract_ to set
	 */
	@JsonProperty("abstract")
	public void setAbstract_(boolean _abstract) {
		this._abstract = _abstract;
	}
	@Override
	public Clazz[] getClasses() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setClasses() {
		// TODO Auto-generated method stub
		
	}
	


}
