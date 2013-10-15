/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.business.t1001;

import com.myapp.struts.AppAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author citysky
 */
public class T1001Action extends AppAction {

	private T1001Svr t1001svr;

	public T1001Svr getT1001svr() {
		return t1001svr;
	}

	public void setT1001svr(T1001Svr t1001svr) {
		this.t1001svr = t1001svr;
	}

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("user", "key");
		return mapping.findForward("main");
	}
}
