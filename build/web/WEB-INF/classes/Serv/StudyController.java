/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv;

import Business.Study;
import Business.User;
import Business.Answer;
import Data.StudyDB;
import Data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Stephen Weber
 */
@WebServlet(name = "StudyController", urlPatterns = {"/studyController"})
public class StudyController extends HttpServlet {

        @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
     
        HttpSession session = request.getSession();
        String url = "/home.jsp";
        User user = new User();
        User admin = new User();
        user = (User) session.getAttribute("theUser");
        admin = (User) session.getAttribute("theAdmin");
        String action = request.getParameter("action"); 
        
        if(action == null){
            action = "join";
            url = "/main.jsp";
            if(user != null){
                url = "/main.jsp";
            }
            if(admin != null){
                url = "/admin.jsp";
            }
        }
        
        if(admin != null){
            List<Study> reportedStudies = new <Study>ArrayList();
            if(action.equals("reportedques")){
                List<Study> reported = StudyDB.getStudies();
                for(Study i : reported){
                    if(i.getStatus().equals("reported")){
                            reportedStudies.add(i);
                    }
                }
                url="/reportques.jsp";
            }
            request.setAttribute("reportedStudies",reportedStudies);
        }
        
        //Allowing studies to be shown when first entering My Studies
        if(user != null){
            List<Study> studies = new <Study>ArrayList();
            if(action.equals("play")){
                studies = StudyDB.getStudies();
                url="/participate.jsp";
            }
              
            if(action.equals("studies")){
                studies = StudyDB.getStudies(user.getEmail());
                url = "/studies.jsp";
            }
            request.setAttribute("studies",studies);
            
            List<Study> myReportedStudies = new <Study>ArrayList();
            if(action.equals("reporth")){
                List<Study> reported = StudyDB.getStudies();
                for(Study i : reported){
                    if(i.getStatus().equals("reported") || i.getStatus().equals("approved") || i.getStatus().equals("disapproved")&& i.getEmail().equals(user.getEmail())){
                            myReportedStudies.add(i);
                    }
                }
                url="/reporth.jsp";
            }
            request.setAttribute("myReportedStudies",myReportedStudies);
        }
        
        if(action.equals("participate")){
                url = "/login.jsp";
                String sCode = request.getParameter("StudyCode");
                if(user != null){
                    if(sCode.isEmpty()){
                        List<Study> openStudies = StudyDB.getOpenStudies("start");
                        request.setAttribute("studies", openStudies);
                        url = "/participate.jsp";
                    } else {
                        Study study = StudyDB.getStudy(sCode);
                        int part = study.getNumofParticipants() + 1;
                        study.setNumofParticipants(part);
                        session.setAttribute("part",part);
                        request.setAttribute("study",study);
                        url = "/question.jsp";
                }
            }
        }
        
        if(action.equals("edit")){
            url = "/login.jsp";
            String sCode = request.getParameter("StudyCode");
            if(user != null){
                if(!sCode.isEmpty() && sCode != null){
                    Study study = StudyDB.getStudy(sCode);
                    request.setAttribute("study", study);
                    }
                    url = "/editstudy.jsp";
                
            }
        }
        
        if(action.equals("report")){
            url = "/login.jsp";
            String sCode = request.getParameter("StudyCode");
            if(user != null){
                if(!sCode.isEmpty()){
                    String reporterEmail = request.getParameter("ReporterEmail");
                    Study study = StudyDB.getStudy(sCode);
                    study.setStatus("reported");
                    Date date = new Date();
                    study.setDateCreated(date);
                    StudyDB.updateStudy(sCode, study);
                    url = "/confirmrep.jsp";
                }else{
                    List<Study> studies = StudyDB.getStudies(user.getEmail());
                    for(Study i : studies){
                        List<Study> reported = new <Study>ArrayList();
                        if(i.getStatus().equals("reported")){
                            reported.add(i);
                        }
                        request.setAttribute("ReportedStudies",reported);
                    }
                    url = "/report.jsp";
                }
            }
        }
        
        if(action.equals("approve")){
            url = "/login.jsp";
            String sCode = request.getParameter("StudyCode");
            if(admin != null){
                if(!sCode.isEmpty()){
                    Study study = StudyDB.getStudy(sCode);
                    study.setStatus("approved");
                    StudyDB.updateStudy(sCode, study);
                    
                    List<Study> requests = new ArrayList<Study>();
                    requests = StudyDB.getOpenStudies("requested");
                    request.setAttribute("requests",requests);
                    
                    url = "/reportques.jsp";
                }
            }
        }
        
        if(action.equals("disapprove")){
            url = "/login.jsp";
            String sCode = request.getParameter("StudyCode");
            if(admin != null){
                if(!sCode.isEmpty()){
                    Study study = new Study();
                    study = StudyDB.getStudy(sCode);
                    study.setStatus("disapproved");
                    StudyDB.updateStudy(sCode, study);
                    
                    List<Study> requests = new ArrayList<Study>();
                    requests = StudyDB.getOpenStudies("requested");
                    request.setAttribute("requests",requests);
                    
                    url = "/reportques.jsp";
                }
            }
        }
        
        if(action.equals("update")){
            url = "/login.jsp";
            if(user != null){
                String sCode = request.getParameter("StudyCode");
                Study study = StudyDB.getStudy(sCode);

                String studyName = request.getParameter("studyName");
                String question = request.getParameter("question");
                String imageURL = request.getParameter("image");
                int requestedParticipants = Integer.parseInt(request.getParameter("participants"));
                String description = request.getParameter("description");

                String answer[] = request.getParameterValues("answer");
                List<String> answers = new ArrayList<String>();
                for(String val: answer){
                    study.setAnswerType("text");
                    if(val.matches("^\\d+(\\.\\d+)?")){
                        study.setAnswerType("numeric");
                    }
                    answers.add(val);
                }
                
                study.setStudyName(studyName);
                study.setQuestion(question);
                study.setImageURL(imageURL);
                study.setRequestedParticipants(requestedParticipants);
                study.setStatus("open");
                study.setDescription(description);
                study.setEmail(user.getEmail());
                Date date = new Date();
                study.setDateCreated(date);
                study.setStudyCode(sCode);
                
                StudyDB.updateStudy(sCode,study);
                
                List<Study> studies;
                studies = StudyDB.getStudies(user.getEmail());
                request.setAttribute("studies",studies);
                
                url = "/studies.jsp";
            }
        }
        
        
        if(action.equals("add")){
            Study study = new Study();
            String studyName = request.getParameter("studyName");
            String question = request.getParameter("question");
            String imageURL = request.getParameter("image");
            int requestedParticipants = Integer.parseInt(request.getParameter("participants"));
            String answer[] = request.getParameterValues("answer");
            String description = request.getParameter("description");
            
            List<String> answers = new ArrayList<String>();
            for(String val : answer){
                if(val.matches("^\\d+(\\.\\d+)?")){
                    study.setAnswerType("numeric");
                }else{
                    study.setAnswerType("text");
                }
                answers.add(val);
            }
            
            
            if(user != null){
                study.setStudyName(studyName);
                study.setQuestion(question);
                study.setImageURL("images/imageNewStudy.jpg");
                study.setRequestedParticipants(requestedParticipants);
                study.setStatus("new");
                study.setDescription(description);
                study.setEmail(user.getEmail());
                Date date = new Date();
                study.setDateCreated(date);
               
                
                //Generate study code based on random number..
                StringBuilder sb = new StringBuilder();
                sb.append((int) Math.floor(Math.random()));
                String str = sb.toString();
                study.setStudyCode(str);
               
                StudyDB.addStudy(study);
                
                //Add one coin to user for their submission
                user = UserDB.getUser(user.getEmail());
                user.setNumCoins(user.getNumCoins() + 1);
                UserDB.update(user);
                
                
                List<Study> studies = new ArrayList<Study>();
                studies = StudyDB.getStudies(user.getEmail());
                request.setAttribute("studies",studies);
                url = "/studies.jsp";                
            }
        }
        
        if(action.equals("start")){
            url = "/login.jsp";
            String sCode = request.getParameter("StudyCode");
            if(user != null){
                if(!sCode.isEmpty()){
                    Study study = StudyDB.getStudy(sCode);
                    study.setStatus("start");
                    StudyDB.updateStudy(sCode,study);
                    List<Study> studies = StudyDB.getStudies(user.getEmail());
                    request.setAttribute("studies",studies);
                    url = "/studies.jsp";
                }
            }
        }
        
        if(action.equals("stop")){
            url = "/login.jsp";
            String sCode = request.getParameter("StudyCode");
            if(user != null){
                if(!sCode.isEmpty()){
                    Study study = StudyDB.getStudy(sCode);
                    study.setStatus("stop");
                    StudyDB.updateStudy(sCode,study);
                    List<Study> studies = StudyDB.getStudies(user.getEmail());
                    request.setAttribute("studies",studies);
                    url = "/studies.jsp";
                }
            }
        }
        
        if(action.equals("answer")){
            url="/login.jsp";
            String sCode = request.getParameter("StudyCode");
            if(user != null){
                String choice = request.getParameter("choice");            
                if(!sCode.isEmpty()){
                    Date date = new Date();
                    Answer answer = new Answer();
                    answer.setChoice(choice);
                    answer.setEmail(user.getEmail());
                    answer.setSubmissionDate(date);
                    
                    Study study = StudyDB.getStudy(sCode);
                    study.setAnswers(answer);
                    
                    user.setNumCoins(user.getNumCoins() + 1);
                    user.setnumParticipation(user.getnumParticipation() + 1);
                    UserDB.update(user);

                    List<Study> theStudies = StudyDB.getStudies();
                    List<Study> studies = new <Study>ArrayList();
                    for(Study i : theStudies){
                        if(i.getStatus().equals("start")){
                            studies.add(i);
                        }
                    }
                    request.setAttribute("studies",studies);
                    url = "/participate.jsp";
                }
            }
        }
        
        if(action.equals("studies")){
            url = "/login.jsp";
            if(user != null){
                List<Study> studies = new ArrayList<Study>();
                studies = StudyDB.getStudies(user.getEmail());
                request.setAttribute("studies", studies);
                url = "/studies.jsp";
            }
        }
       
        
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request,response);  
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws
            ServletException, IOException{
        
        doPost(request,response);
    }
}
