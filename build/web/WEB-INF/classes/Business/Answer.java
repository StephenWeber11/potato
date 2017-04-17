/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Stephen Weber
 */
@Entity
public class Answer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String email; //email of the participant
    private String choice;
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDate;
    
    public void setEmail(String email){
        this.email = email;
    }
   
    public String getEmail(){
        return email;
    }
    
    public void setChoice(String choice){
        this.choice = choice;
    }
    
    public String getChoice(){
        return choice;
    }
    
    public void setSubmissionDate(Date submissionDate){
        this.submissionDate = submissionDate;
    }
    
    public Date getSubmissionDate(){
        return submissionDate;
    }
}
