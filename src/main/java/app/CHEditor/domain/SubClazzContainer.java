package app.CHEditor.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubClazzContainer {
	
	private Integer cid;
	
	private String name;
	
	@JsonProperty("superclassOf")
	private List<SubClazzContainer> children;
	
	public SubClazzContainer () {
		children = new ArrayList<SubClazzContainer>();
	}
	
	public SubClazzContainer (Clazz c) {
		this.cid = c.getCid();
		this.name = c.getName();
		children = new ArrayList<SubClazzContainer>();
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
	}

	/**
	 * @return the children
	 */
	public List<SubClazzContainer> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<SubClazzContainer> children) {
		this.children = children;
	}
	
	/**
	 * @param children the children to set
	 */
	public void setChildrenFromClazzes(List<Clazz> children) {
		List<SubClazzContainer> container = new ArrayList<SubClazzContainer>();
		for(Clazz c : children) {
			container.add(new SubClazzContainer (c));
		}
		this.children = container;
	}
	
	
}
