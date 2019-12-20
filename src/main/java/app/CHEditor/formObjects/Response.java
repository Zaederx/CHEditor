package app.CHEditor.formObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response {

	boolean ret;
	String message;
	
	public Response () {
		this.ret = false;
		this.message = "Default Error message";
	}
	/**
	 * @return the response
	 */
	public boolean isRet() {
		return ret;
	}
	/**
	 * @param ret the response to set
	 */
	public void setRet(boolean ret) {
		this.ret = ret;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
