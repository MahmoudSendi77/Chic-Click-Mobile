
package Service;

import Entity.EventLocation;
import Entity.event;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Map;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author mahmoud
 */
public class ServiceEventLocation {
 
    
    public void ajoutEventLocation(EventLocation ev) throws IOException {
        
        
   
        InfiniteProgress progressIndicator = new InfiniteProgress();
Dialog dialog = progressIndicator.showInifiniteBlocking();
        ConnectionRequest con = new ConnectionRequest();
        MultipartRequest conn= new MultipartRequest();
        String Url = "http://localhost/chick_click/web/app_dev.php/event/mobileevent/addLocation"
                +"?EventId=" + ev.getEvent_id()
                +"&longitude=" + ev.getLongitude()
                + "&lattitude=" + ev.getLattitude()
                 ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.setDisposeOnCompletion(dialog);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
             Dialog.show("felicitation", " votre evenement map location a ete ajoute", "ok", null);            

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    
    
    }
    
     public void modifierEventLocation(EventLocation ev) throws IOException {
  
        InfiniteProgress progressIndicator = new InfiniteProgress();
Dialog dialog = progressIndicator.showInifiniteBlocking();
        ConnectionRequest con = new ConnectionRequest();
       String Url = "http://localhost/chick_click/web/app_dev.php/event/mobilemodiflocation"
                +"?id=" + ev.getLocation_id()
                +"&longitude=" + ev.getLongitude()
                + "&lattitude=" + ev.getLattitude()
                 ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.setDisposeOnCompletion(dialog);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
             Dialog.show("felicitation", " votre evenement a ete modifier", "ok", null);            

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    
    
    }
    
     public void delete(int id) throws IOException {
        

        InfiniteProgress progressIndicator = new InfiniteProgress();
        Dialog dialog = progressIndicator.showInifiniteBlocking();
        ConnectionRequest con = new ConnectionRequest();
    
        String Url = "http://localhost/chick_click/web/app_dev.php/event/mobiledeleteevent/"+
                 id ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.setDisposeOnCompletion(dialog);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
             Dialog.show("felicitation", " votre evenement a ete suprrimer", "ok", null);            

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    
    
    }
     
     
     ArrayList<event> listTasks1 = new ArrayList<>();

      public EventLocation getEventLocation(int id){    
          EventLocation listTasks2 = new EventLocation();
        ConnectionRequest con = new ConnectionRequest(){
            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser json = new JSONParser();
                
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    
                        
                        //Service.CurrentUser cr= Service.CurrentUser.getInstance();
                                
               listTasks2.setEvent_id((int) Float.parseFloat(data.get("eventId").toString()));
               listTasks2.setLattitude((double) Float.parseFloat(data.get("lattitude").toString()));
               listTasks2.setLongitude((double) Float.parseFloat(data.get("longitude").toString()));
  
                   
            }
        };
        con.setUrl("http://localhost/chick_click/web/app_dev.php/event/mobileeventgetLocation?id="+id);  
        
            NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks2;
    }
}



