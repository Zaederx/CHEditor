package app.CHEditor.controllers;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controller used to handle errors.
 * Returns an error page dependent on error code.
 * @author Zachary Ishmael
 *
 */
@Controller
public class CHEditorErrorController implements ErrorController{

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	@RequestMapping("/error")
	public String defaultError(HttpServletRequest request) {
	System.out.println("defaultError page");
	Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

	if (status !=null) {
		Integer statusInteger = Integer.valueOf(status.toString());
		if (statusInteger == HttpStatus.NOT_FOUND.value()) {
			return "error-pages/not-found";//404
		}
		
		if (statusInteger == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			return "error-pages/internal-server";//500
		}
	}
	
	return "error-pages/error-default";
	}
}
