package web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Home extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	
	   private HttpServletRequest request;
	   private HttpServletResponse response;
	   
		@Override
		public void setServletRequest(HttpServletRequest arg0) {
			this.request = arg0;
			
		}

		@Override
		public void setServletResponse(HttpServletResponse arg0) {
			this.response = arg0;
			
		}
		
		public String execute(){
			return Action.SUCCESS;
		}

}
