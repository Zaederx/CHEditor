package app.CHEditor.domain;

public class Clazz {
	int pid;
	int cid;
	String name;
	boolean abstract_;
	
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
		return abstract_;
	}
	/**
	 * @param abstract_ the abstract_ to set
	 */
	public void setAbstract_(boolean abstract_) {
		this.abstract_ = abstract_;
	}
}
