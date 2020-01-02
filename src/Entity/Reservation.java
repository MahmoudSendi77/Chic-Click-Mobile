/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author mahmoud
 */
public class Reservation {
    private int id;
    private int event_id;
    private int user_id;
    private String code;
    private String image;
    private String date;
    private String heure;
    private String titre;
    private String Adresse;
    private String username;

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", event_id=" + event_id + ", user_id=" + user_id + ", code=" + code + ", image=" + image + ", date=" + date + ", heure=" + heure + ", titre=" + titre + ", Adresse=" + Adresse + ", username=" + username + '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public Reservation(int event_id, int user_id, String code) {
        this.event_id = event_id;
        this.user_id = user_id;
        this.code = code;
    }

    public Reservation(int id, int event_id, int user_id, String code) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Reservation(int id, int event_id, int user_id, String code, String image, String date, String heure, String titre) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
        this.code = code;
        this.image = image;
        this.date = date;
        this.heure = heure;
        this.titre = titre;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
    
}
