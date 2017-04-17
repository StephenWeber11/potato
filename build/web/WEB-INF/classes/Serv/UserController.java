/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv;

import Business.User;
import Data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Stephen Weber
 */
@WebServlet(name = "UserController", urlPatterns = {"/userController"})
public class UserController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
    
        String url = "/home.jsp";
        User user = (User) session.getAttribute("theUser");
        User admin = (User) session.getAttribute("theAdmin");
        
        String action = request.getParameter("action");
        if(action == null){
            action = "join";
        }
        if(action.equals("join")){
            url = "/home.jsp";
        }

        if(action.equals("login")){
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            user = UserDB.getUser(email);
            
            String msg = "";
            if (!UserDB.emailExists(email)){
                msg = "Sorry but this user does not exist. <br/>" + 
                        "Please try another email address.";
                url = "/login.jsp";
                //TODO: Validate password! Write methods to validate.
                //if(password )
            }else{
               if(user.getType().equals("participant")){
                   session.setAttribute("theUser", user);
                   url = "/main.jsp";
               }
               if(user.getType().equals("admin")){
                   session.setAttribute("theAdmin",user);
                   url = "/admin.jsp";
               }
            }
            //request.setAttribute("user", user);
            request.setAttribute("msg", msg);
        }
        if(action.equals("create")){
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String type = request.getParameter("type");
            String password = request.getParameter("password");
            String msg = "";
            
            //TODO
            //With JS, validate the above parameters!!
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setType("participant");
            user.setPassword(password);
            
            if(UserDB.emailExists(email)){
                msg = "Sorry, the email you entered already exists. <br/>" +
                        "Please enter another email address.";
                url = "/signup.jsp";
                session.setAttribute("msg", msg);
            }else{
                session.setAttribute("theUser", user);
                url = "/main.jsp";
                UserDB.insert(user);
            }
        }
        if(action.equals("how")){
            url = "/how.jsp";
            if(user != null){
                url = "/main.jsp";
            }
        }
        
        if(action.equals("about")){
            url = "/about.jsp";
            if(user != null){
                url="/aboutl.jsp";
            }
        }
        
        if(action.equals("home")){
            url = "/home.jsp";
            if(user != null){
                url = "/main.jsp";
            }            
        }
        
        if(action.equals("main")){
            url = "/login.jsp";
            if(user == null){
                url = "/main.jsp";
            }                 
        }
        
        if(action.equals("logout")){
            url = "/home.jsp";
            if(admin != null || user != null){
                session.invalidate();
                request.logout();
                url = "/home.jsp";
            }
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request,response);  
    }
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws
            ServletException, IOException{
        
        doPost(request,response);
    }
    
}
