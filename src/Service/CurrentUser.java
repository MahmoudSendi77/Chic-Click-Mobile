/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.ArrayList;

/**
 *
 * @author mahmoud
 */
public class CurrentUser {
     private static CurrentUser currentuser ;
     private int idcurrentuser;
     private String email;
     private String pseudo;
     private String gender;
     private String role;
     private String photo_profile;
     private String phone;
     ArrayList<Integer> listReservation= new ArrayList<>();
     public void deleteRes(Integer id){
         listReservation.remove((Integer) id);
     }
     
     public void addRes(Integer id){
         listReservation.add((Integer) id);
     }
     

    public ArrayList<Integer> getListReservation() {
        return listReservation;
    }

    public void setListReservation(ArrayList<Integer> listReservation) {
        this.listReservation = listReservation;
    }
     
      public CurrentUser() {
    }

    public int getIdcurrentuser() {
        return idcurrentuser;
    }

    public void setIdcurrentuser(int idcurrentuser) {
        this.idcurrentuser = idcurrentuser;
    }
      
       public static CurrentUser getInstance() {
        if (currentuser == null) {
            currentuser = new CurrentUser();
        }
        return currentuser;
    }
       public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto_profile() {
        return photo_profile;
    }

    public void setPhoto_profile(String photo_profile) {
        this.photo_profile = photo_profile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
       
}
