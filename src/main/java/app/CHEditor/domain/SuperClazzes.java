package app.CHEditor.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


public class SuperClazzes {
	
	@JsonProperty("list")
	private List<Clazz> parents;
	
	public SuperClazzes() {
		parents = new ArrayList<Clazz>();
	}

	/**
	 * @return the parents
	 */
	@JsonProperty("list")
	public List<Clazz> getParents() {
		return parents;
	}

	/**
	 * @param parents the parents to set
	 */
	@JsonProperty("list")
	public void setParents(List<Clazz> parents) {
		this.parents = parents;
	}
	
}
