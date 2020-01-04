package app.CHEditor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import app.CHEditor.formObjects.ClazzForm;

/**
 * Entity class used to represented classes.
 * Named clazz to avoid keyword conflicts.
 * 
 * @author Zachary Ishmael
 *
 */
@Entity
@JsonRootName("class")
@JsonInclude(Include.NON_NULL)
public class Clazz extends AbstractClazz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	int id;
	
	@Column(nullable = true)
	Integer pid;
	
	@Column(nullable = false, unique = true)
	Integer cid;
	
	@Column(nullable = false)
	String name;
	
	@Column
	@JsonProperty("abstract")
	Boolean _abstract;
	

	/*Required by 	Spring entity management*/
	public Clazz () {
		this._abstract = false;
	}
	
	public Clazz (Integer pid, Integer cid, String name, Boolean _abstract) {
		this.pid = pid;
		this.cid = cid;
		this.name = name;
		this._abstract = _abstract;

	}
	
	public Clazz (ClazzForm form) {
		this.pid = form.getPid();
		this.cid = form.getCid();
		this.name = form.getName();
		this._abstract = form.isAbstractBoolean();

	}
	
	public Clazz (Clazzes c) {
		this.pid = c.getPid();
		this.cid = c.getCid();
		this.name = c.getName();
		this._abstract = c.isAbstractBoolean();
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
//		this.isNull = false;
	}
	/**
	 * @return the _abstract
	 */
	@JsonProperty("abstract")
	public String isAbstract_() {
		return _abstract.toString();
	}
	/**
	 * @param _abstract the abstract_ to set
	 */
	@JsonProperty("abstract")
	public void setAbstract_(Boolean _abstract) {
		this._abstract = _abstract;
	}


	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	/*Reqiured to satisfy Abstract ClazzDefintion*/
	@JsonIgnore
	@Override
	public boolean isSingleClazz() {
		return true;
	}

	@JsonIgnore
	@Override
	public void setSingleClazz(boolean singleClazz) {}

	@Override
	public Clazz[] getClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClasses() {
		// TODO Auto-generated method stub
		
	}

	@JsonIgnore
	@Override
	public Boolean isAbstractBoolean() {
		return this._abstract;
	}

	
}
