package vn.com.r2s.fms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import vn.com.r2s.fms.model.UserModel;

@Component
public class AuthorizationFilter implements Filter {

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession httpSession = request.getSession();
		String url = request.getRequestURI();

		UserModel user = (UserModel) httpSession.getAttribute("USER");
		
		if (url.startsWith("/admin")) {
			if (user != null) {
				if (user.getRole().equals("admin")) {
					filterChain.doFilter(servletRequest, servletResponse);
				} else {
					response.sendRedirect(request.getContextPath() + "/403");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/403");
			}
		} else if (url.startsWith("/trainer")) {
			if (user != null) {
				if (user.getRole().equals("trainer")) {
					filterChain.doFilter(servletRequest, servletResponse);
				} else {
					response.sendRedirect(request.getContextPath() + "/403");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/403");
			}
		} else if (url.startsWith("/trainee")) {
			if (user != null) {
				if (user.getRole().equals("trainee")) {
					filterChain.doFilter(servletRequest, servletResponse);
				} else {
					response.sendRedirect(request.getContextPath() + "/403");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/403");
			}
		}  else if (url.startsWith("/login")) {
			if (user != null) {
				response.sendRedirect(request.getContextPath() + user.getRole() + "/dashboard");
			} else {
				filterChain.doFilter(servletRequest, servletResponse);
			}
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {

	}

}
