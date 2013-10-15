/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author citysky
 */
public class AppAction extends Action {

	protected Log logger = LogFactory.getLog(this.getClass());

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String operateName = request.getParameter("operate");
		if (operateName == null) {
			operateName = "init";
		}

		Method method = getClass().getMethod(operateName, ActionMapping.class, ActionForm.class, HttpServletRequest.class, HttpServletResponse.class);
		return (ActionForward) method.invoke(this, mapping, form, request, response);
	}
}
