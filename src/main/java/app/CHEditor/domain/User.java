package app.CHEditor.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@OneToMany(mappedBy = "id")
	List<Clazz> clazzes;

	
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
	 * @return the clazzes
	 */
	public List<Clazz> getClazzes() {
		return clazzes;
	}

	/**
	 * @param clazzes the clazzes to set
	 */
	public void setClazzes(List<Clazz> clazzes) {
		this.clazzes = clazzes;
	}
	
	
}
