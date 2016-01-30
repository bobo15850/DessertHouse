package edu.nju.desserthouse.interceptor;

import org.apache.struts2.convention.annotation.InterceptorRef;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@InterceptorRef(value = "")
public class CheckLogin extends AbstractInterceptor {

	private static final long serialVersionUID = 3783213368547103519L;

	@Override
	public void init() {
		System.out.println("进入CheckLogin拦截器");
		super.init();
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("执行CheckLogin拦截器");
		return arg0.invoke();
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("销毁CheckLogin拦截器");
	}

}
