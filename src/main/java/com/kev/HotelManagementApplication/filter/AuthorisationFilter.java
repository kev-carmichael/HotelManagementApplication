package com.kev.HotelManagementApplication.filter;

import com.kev.HotelManagementApplication.customer.CustomerService;
import com.kev.HotelManagementApplication.entity.Customer;
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

        if (requestURI.contains("checkcredentials") ||
                requestURI.startsWith("/customer/add/")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if (staffIsAuthorised(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if (customerIsAuthorised(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private boolean staffIsAuthorised(HttpServletRequest request) {
        String requestURI = request.getRequestURI().toLowerCase();
        System.out.println("REQUEST URI " + requestURI);
        String token = request.getHeader("Authorization");

        Staff staff = staffService.checkCredentials(token);

        if(staff != null) {
            if (requestURI.startsWith("/customer/all") ||
                    requestURI.startsWith("/booking/all") ||
                    requestURI.startsWith("/room/all") ||
                    requestURI.startsWith("/roomtype/all") ||
                    requestURI.startsWith("/roomtype/update/") ||
                    requestURI.startsWith("/roomtype/add/") ||
                    requestURI.startsWith("/roomtype/delete/")){
                return true;
            } else if (requestURI.startsWith("/staff/logout/")) {
                String[] parts = requestURI.substring(1).split("/");
                int id = Integer.parseInt(parts[2]);
                System.out.println("SUBSTRING: " + id);
                return staff.getStaffId() == id;
            }
        }
        return false;
    }

    private boolean customerIsAuthorised(HttpServletRequest request) {
        String requestURI = request.getRequestURI().toLowerCase();
        String token = request.getHeader("AUTHORIZATION");

        Customer customer = customerService.checkCustomerCredentials(token);

        if (customer != null)
        {
            if (requestURI.startsWith("/customer/logout/") ||
                    requestURI.startsWith("/booking/add/") ||
                    requestURI.startsWith("/booking/delete/") ||
                    requestURI.startsWith("/booking/update/") ||
                    requestURI.startsWith("/customer/allbookings/")) {
                String[] parts = requestURI.substring(1).split("/");
                int id = Integer.parseInt(parts[2]);
                return customer.getCustomerId() == id;
            }
        }
        return false;
    }


}
