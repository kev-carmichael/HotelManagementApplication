package com.kev.HotelManagementApplication.filter;

import com.kev.HotelManagementApplication.customer.CustomerService;
import com.kev.HotelManagementApplication.entity.Staff;
import com.kev.HotelManagementApplication.staff.StaffService;
import lombok.AllArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@WebFilter(urlPatterns = {"/*"})
public class AuthorisationFilter implements Filter
{
    private final CustomerService customerService;
    private final StaffService staffService;

    @Override
    public void doFilter
            (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
             throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String requestURI = request.getRequestURI().toLowerCase();

        if (requestURI.contains("checkcredentials")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if (staffIsAuthorised(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private boolean staffIsAuthorised(HttpServletRequest request) {
        String requestURI = request.getRequestURI().toLowerCase();

        if (requestURI.startsWith("/staff/logout/")) {
            String[] parts = requestURI.substring(1).split("/");
            int id = Integer.parseInt(parts[2].substring(1));
            String token = parts[3].substring(1);

            Staff staff = staffService.checkCredentials(token);
            return staff.getStaffId() == id;
        } else {
            return false;
        }
    }

}
