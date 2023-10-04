package com.gokecms.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gokecms.model.ApiToken;
import com.gokecms.repository.ApiTokenRepository;

public class ApiAuthenticationFilter implements Filter{
	private ServletContext context;
	private ApiTokenRepository tokenRepository;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		tokenRepository = new ApiTokenRepository();
		System.out.println("AuthenticationFilter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String auth = req.getHeader("Authorization");
		String token = "none";
		if( null != auth ) {
			if( auth.contains( "Bearer " ) ) {
				token = auth.split(" ")[1];
			}
		}
		
		List<ApiToken> match = tokenRepository.find( "token", token);
		if( null != match && match.size() > 0) {
			
			chain.doFilter(request, response);
			
		}else {
			
			String responseJsonString = "{\n"
					+ "    \"message\": \"Authorization Token could not be authenticated. Please try again.\"\n"
					+ "}";
			PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(responseJsonString);
            out.flush();   
            
		}
		
		
	}

	public void destroy() {
		//close any resources here
	}
}
