package com.hd.Filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="EncodeFilter",urlPatterns="/*")
public class EncodeFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		
		//HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
		//��ȡ��������
		//String method = request.getMethod();
		//������Ӧ���룬ʹTomcat�������ʹ����ͬ���뷽ʽ
		response.setContentType("application/json;charset=utf8");
		
		/**
		 * ����Ӧ���ж�
		 * �����get��������Ҫ��request���а�װ
		 * ��װ��Ҫ�̳�һ��HttpServletRequestWrapper��
		 */
		/*if(method.equals("GET")) {
			EncodeRequest encodeRequest = new EncodeRequest(request);
			chain.doFilter(encodeRequest, response);
		} else {
			request.setCharacterEncoding("utf-8");
			chain.doFilter(arg0, arg1);
		}*/
		chain.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}

class EncodeRequest extends HttpServletRequestWrapper {
	
	private HttpServletRequest request;
	
	public EncodeRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	//��дgetParameter����
	@Override
	public String getParameter(String name) {
		
		String param = request.getParameter(name);
		
		/**
		 * 	ISO8859-1 ת��Ϊ utf-8
		 */
		if(param != null && !param.isEmpty()) {
			try {
				param = new String(param.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return param;
		
	}
	
}