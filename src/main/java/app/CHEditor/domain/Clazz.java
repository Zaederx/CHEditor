package app.CHEditor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Clazz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(nullable = true)
	Integer pid;
	
	@Column(unique = true)
	int cid;
	
	@Column
	String name;
	
	@Column
	boolean _abstract;
	
	/*Required by 	Spring entity management*/
	public Clazz () {
		this._abstract = false;
	}
	
	public Clazz (int pid, int cid, String name, boolean _abstract) {
		this.pid = pid;
		this.cid = cid;
		this.name = name;
		this._abstract = _abstract;
	}
	
	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
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
	}
	/**
	 * @return the _abstract
	 */
	public boolean isAbstract_() {
		return _abstract;
	}
	/**
	 * @param _abstract the abstract_ to set
	 */
	public void setAbstract_(boolean _abstract) {
		this._abstract = _abstract;
	}
}
