package app.CHEditor.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;



public class Container {

	Clazzes clazzes;
	
	Clazz clazz;

	/**
	 * @return the clazzes
	 */
	@JsonProperty("classes")
	public Clazzes getClazzes() {
		return clazzes;
	}

	/**
	 * @param clazzes the clazzes to set
	 */
	@JsonProperty("classes")
	public void setClazzes(Clazzes clazzes) {
		this.clazzes = clazzes;
	}

	/**
	 * @return the clazz
	 */
	@JsonAnyGetter
	public Clazz getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	@JsonAnySetter
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
}
