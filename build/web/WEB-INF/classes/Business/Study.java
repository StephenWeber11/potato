/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToMany;
import java.util.Date;

/**
 *
 * @author Stephen Weber
 */
@Entity
public class Study implements Serializable{
    
    private String studyName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String studyCode;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    private String email; //The creator
    private String question;
    private String imageURL;
    private int requestedParticipants;
    private int numofParticipants;
    private String description;
    private String status;
    private String answerType;
    @OneToMany
    private List<Answer> answers;
    
    
    public Study(){
        
    }
    

    public void setStudyName(String studyName){
        this.studyName = studyName;
    }
    
    public String getStudyName(){
        return studyName;
    }
    
    public void setStudyCode(String studyCode){
        this.studyCode = studyCode;
    }
    
    public String getStudyCode(){
        return studyCode;
    }
    
    public void setDateCreated(Date dateCreated){
        this.dateCreated = dateCreated;
    }
    
    public Date getDateCreated(){
        return dateCreated;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setQuestion(String question){
        this.question = question;
    }
    
    public String getQuestion(){
        return question;
    }
    
    public void setRequestedParticipants(int requestedParticipants){
        this.requestedParticipants = requestedParticipants;
    }
    
    public int getRequestedParticipants(){
        return requestedParticipants;
    }
    
    public void setNumofParticipants(int numofParticipants){
        this.numofParticipants = numofParticipants;
    }
    
    public int getNumofParticipants(){
        return numofParticipants;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setAnswerType(String answerType){
        this.answerType = answerType;
    }
    
    public String getAnswerType(){
        return answerType;
    }
    
    public void setAnswers(Answer answers){
        this.answers = new <Answer>ArrayList();
        this.answers.add(answers);
        
    }
    
    public List<Answer> getAnswers(){
        return answers;
    }
    
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }
    
    public String getImageURL(){
       return imageURL;
    }
    
    public int getAverage(){
        return 0;
    }
    
    public int getMinimum(){
        return 0;
    }
    
    //Get standard deviation
    public int getSD(){
        return 0;
    }
    
    
}
