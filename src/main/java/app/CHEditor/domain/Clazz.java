package app.CHEditor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Clazz {
	@Column
	int pid;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int cid;
	@Column
	String name;
	@Column
	boolean _abstract;
	
	public Clazz () {
		
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
	 * @return the abstract_
	 */
	public boolean isAbstract_() {
		return _abstract;
	}
	/**
	 * @param abstract_ the abstract_ to set
	 */
	public void setAbstract_(boolean abstract_) {
		this._abstract = abstract_;
	}
}
