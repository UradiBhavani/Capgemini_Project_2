package com.cg.Servlet;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.dao.IUserDAO;
import com.cg.dao.UserDAOImpl;
import com.cg.dto.User;

@WebServlet("/CreateAccount")
public class CreateAccountServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserDAO userDAO = new UserDAOImpl();
		RequestDispatcher dispatcher=null;
		
		try{
			User user = new User();
			user.setUserName(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setUserType("usr");
			user.setEmail(request.getParameter("email"));
			user.setPhoneNumber(Integer.parseInt(request.getParameter("phoneNumber")));
			user.setState(request.getParameter("state"));
			
			if(userDAO.addCustomer(user) != 0){
				//Print a success message and ask to login again
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}catch(Exception e){
			System.out.println("In create account servlet");
			
		}
		
	}
	
}
