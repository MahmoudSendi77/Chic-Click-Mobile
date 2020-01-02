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
public class SearchEvent {
    private String id_event;
     private String image;
      private String titre;
       private String adress;

    public SearchEvent() {
    }

    public SearchEvent(String id_event, String image, String titre, String adress) {
        this.id_event = id_event;
        this.image = image;
        this.titre = titre;
        this.adress = adress;
    }

    public String getId_event() {
        return id_event;
    }

    public void setId_event(String id_event) {
        this.id_event = id_event;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
       
    
}
