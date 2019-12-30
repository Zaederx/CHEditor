package app.CHEditor.formObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Response {

	Boolean ret; // FIXME - CHECK WHETHER ret must be string or boolean to meet criteria?? ask professor
	Boolean result;//FIXME - CHECK WITH PROFESSOR IF NECESSARY TO HAVE BOTH RET AND RESULT AS SHOUWN
	String message;
	
	public Response () {
		this.ret = false;
		this.result = null;
		this.message = "Default Error message";
	}
	/**
	 * @return the response
	 */
	/*
	 * Note: @JSONProperty needed as return type does not match ret field.
	 */
	@JsonProperty("ret")
	public String isRet() {
		if (ret == null) {
			return null;
		}
		return ret.toString();
	}
	/**
	 * @param ret the response to set
	 */
	public void setRet(Boolean ret) {
		this.ret = ret;
	}
	/**
	 * @return the result
	 */
	@JsonProperty("result")
	public String isResult() {
		if (result == null) {
			return null;
		}
		return result.toString();
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Boolean result) {
		this.result = result;
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
