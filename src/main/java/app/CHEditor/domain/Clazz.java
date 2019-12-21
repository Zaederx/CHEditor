package app.CHEditor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@Entity
@JsonRootName("class")
@JsonInclude(Include.NON_NULL)
public class Clazz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	int id;
	
	@Column(nullable = true)
	Integer pid;
	
	@Column(nullable = false, unique = true)
	int cid;
	
	@Column(nullable = false)
	String name;
	
	@Column
	@JsonProperty("abstract")
	boolean _abstract;
	
//	@Transient
//	@JsonIgnore
//	boolean isNull = true;
	
	/*Required by 	Spring entity management*/
	public Clazz () {
		this._abstract = false;
	}
	
	public Clazz (int pid, int cid, String name, boolean _abstract) {
		this.pid = pid;
		this.cid = cid;
		this.name = name;
		this._abstract = _abstract;
//		this.isNull = false;
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
	public int getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(int cid) {
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

//	/**
//	 * @return the isNull
//	 */
//	@JsonIgnore
//	public boolean isNull() {
//		return isNull;
//	}

//	/**
//	 * @param isNull the isNull to set
//	 */
//	@JsonIgnore
//	public void setNull(boolean isNull) {
//		this.isNull = isNull;
//	}
}
