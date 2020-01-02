
package Service;

import Entity.EventLocation;
import Entity.Reservation;
import Entity.event;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author mahmoud
 */
public class ServiceEvenrReservation {
    
    
    
 
    
    public void ajoutReservation(int id) throws IOException {
        
        
   
        InfiniteProgress progressIndicator = new InfiniteProgress();
        Dialog dialog = progressIndicator.showInifiniteBlocking();
        ConnectionRequest con = new ConnectionRequest();
        MultipartRequest conn= new MultipartRequest()
//        {
//            @Override
//            protected void readResponse(InputStream input) throws IOException {
//
//                JSONParser json = new JSONParser();
//                
//                    Reader reader = new InputStreamReader(input, "UTF-8");
//                    Map<String, Object> data = json.parseJSON(reader);
//                    System.out.println(data);
//                  if ( ((int) Float.parseFloat(data.get("id").toString()))>0){
//                       Dialog.show("felicitation", " votre Reservation a ete ajouter avec successé", "ok", null);  
//                  }
//                  else{
//                Dialog.show("Desolé", " votre êtes deja  Reserver cette évènement", "ok", null);  
//           
//            }
//            }
//            
//        }
                ;
        String Url = "http://localhost/chick_click/web/app_dev.php/event/mobileaddreservation?idevent="
                +id+"&iduser=1";
                
  //+CurrentUser.getInstance().getIdcurrentuser()
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
          con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 
                JSONParser j = new JSONParser();
            
            Map<String, Object> events;
                try {
                    events = j.parseJSON(new CharArrayReader((new String(con.getResponseData())).toCharArray()));
                      System.out.println(events.get(id));
                       if ( ((int) Float.parseFloat(events.get("id").toString()))>0){
                       Dialog.show("felicitation", " votre Reservation a ete ajouter avec successé", "ok", null);  
                  }
                  else{
                Dialog.show("Desolé", " votre êtes deja  Reserver cette évènement", "ok", null);  
           
            }
                    
                } catch (IOException ex) {
                    System.out.println(ex);  }

       
           
         
              //  System.out.println(new String(con.getResponseData()));
            }
        });
        con.setDisposeOnCompletion(dialog);
        
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    
    
    }
    
    
   
    
     public void delete(int id) throws IOException {
        

        InfiniteProgress progressIndicator = new InfiniteProgress();
        Dialog dialog = progressIndicator.showInifiniteBlocking();
        ConnectionRequest con = new ConnectionRequest();
    
        String Url = "http://localhost/chick_click/web/app_dev.php/event/mobiledeletereservation/"+
                 id ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.setDisposeOnCompletion(dialog);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console 

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    
    
    }
     
     
     
     
     
     
      public ArrayList<Reservation> getListReservation(String json)   {
        
        ArrayList<Reservation> listEvents = new ArrayList<>();
        try {

            JSONParser j = new JSONParser();
            
            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));

       
            List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");

            if(list!=null){
                
            
            for (Map<String, Object> obj : list) {
                if(obj!=null){
                //id, json, status);
                Reservation carte = new Reservation();
                System.out.println(obj);
                carte.setCode(obj.get("code").toString());
                
                carte.setImage((String) ((Map<String, Object>) obj.get("eventId")).get("eventPicture"));
                carte.setHeure((String) ((Map<String, Object>) obj.get("eventId")).get("eventHoure"));
               Date deb= new Date( (long)  Float.parseFloat(((Map<String, Object>) ((Map<String, Object>) obj.get("eventId")).get("eventStartDate")).get("timestamp").toString()));
               Date fin= new Date( (long)  Float.parseFloat(((Map<String, Object>) ((Map<String, Object>) obj.get("eventId")).get("eventEndDate")).get("timestamp").toString()));
            carte.setDate("de  : "+deb +"\n à  :"+fin+" \n ");
//                String deb= ((Map<String, Object>) obj.get("eventId")).get("eventStartDate").toString();
//                String fin= ((Map<String, Object>) obj.get("eventId")).get("eventEndDate").toString();
                carte.setTitre((String) ((Map<String, Object>) obj.get("eventId")).get("eventTitle"));
                carte.setAdresse((String) ((Map<String, Object>) obj.get("eventId")).get("eventAdresse"));
                carte.setUsername((String) ((Map<String, Object>) obj.get("userId")).get("username"));
                carte.setId((int) Float.parseFloat(obj.get("id").toString()));
                carte.setEvent_id((int) Float.parseFloat(((Map<String, Object>) obj.get("eventId")).get("id").toString()));
               
                
                
               
              
               listEvents.add(carte);
                System.out.println(listEvents.toString());
                }
            }}

        } catch (IOException ex) {
        }
        return listEvents;

    }
     
     ArrayList<Reservation> listTasks1 = new ArrayList<>();
   
      public ArrayList<Reservation> getReservationByUser(int id){       
        ConnectionRequest con = new ConnectionRequest();
          InfiniteProgress progressIndicator = new InfiniteProgress();
        
Dialog dialog = progressIndicator.showInifiniteBlocking();
        con.setUrl("http://localhost/chick_click/web/app_dev.php/event/mobilereservationbyuser/"+id ); 
             //  +CurrentUser.getInstance().getIdcurrentuser());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                listTasks1 = getListReservation(new String(con.getResponseData()));
            }
        });
         con.setDisposeOnCompletion(dialog);
            NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks1;
    }

      
      
      
      public void getReservation(int id){    
          
        ConnectionRequest con = new ConnectionRequest(){
            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser json = new JSONParser();
                
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    System.out.println("oooooooooooooooooooo");
                        System.out.println(data);
                        if(data.get("root")!=null){
                        CurrentUser.getInstance().setListReservation((ArrayList<Integer>) data.get("root"));
                System.out.println(CurrentUser.getInstance().getListReservation());
            }
            }
        };
        con.setUrl("http://localhost/chick_click/web/app_dev.php/event/mobilereservationbyuser?id="+id);  
        
            NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
}



