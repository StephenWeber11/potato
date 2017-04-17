/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Stephen Weber
 */
@Entity
public class User implements Serializable {
    
    private String name;
    private String email;
    private String type; //will be either participant or admin
    private int numCoins = 0;
    private int numPostedStudies = 0;
    private int numParticipation = 0; //1 participation = 1 coin
    private String password;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getName(){
        return name;
    }    
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getType(){
        return type;
    }
    
    public void setNumCoins(int numCoins){
        this.numCoins = numCoins;
    }
    
    public int getNumCoins(){
        return numCoins;
    }
    
    public void setnumPostedStudies(int numPostedStudies){
        this.numPostedStudies = numPostedStudies;
    }
    
    public int getnumPostedStudies(){
        return numPostedStudies;
    }
    
    public void setnumParticipation(int numParticipation){
        this.numParticipation = numParticipation;
    }
    
    public int getnumParticipation(){
        return numParticipation;
    }
    
    public User(){

    }
    
}
