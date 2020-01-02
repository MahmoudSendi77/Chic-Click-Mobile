
package Service;

import Entity.SearchEvent;
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
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author mahmoud
 */
public class ServiceEvent {
 
    int idevent=0;
    public int ajoutEvent(event ev) throws IOException {
   
      
   CurrentUser cu = CurrentUser.getInstance();
        InfiniteProgress progressIndicator = new InfiniteProgress();
        
Dialog dialog = progressIndicator.showInifiniteBlocking();
  
        ConnectionRequest con = new ConnectionRequest(){
            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser json = new JSONParser();
                
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    System.out.println("oooooooooooooooooooo45545");
                        System.out.println(data);
                        idevent =(int) Float.parseFloat(data.get("id").toString());
                      //  CurrentUser.getInstance().setListReservation((ArrayList<Integer>) data.get("root"));
                System.out.println(CurrentUser.getInstance().getListReservation());
            }
        };
            SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
                String datedeb = tempss.format(ev.getEvent_date());
                String datefin = tempss.format(ev.getEvent_end_date());
          String Url = "http://localhost/chick_click/web/app_dev.php/event/mobileevent/add"
                +"?titreEvent=" + ev.getEvent_title()
                +"&coutryEvent=" + ev.getCountry()
                + "&addressEvent=" + ev.getAddress()+ 
                "&categoryEvent=" + ev.getCategories()+ 
                "&dateEvent=" + datedeb+
                "&datefinEvent=" +datefin+
                "&userid="+cu.getIdcurrentuser()+
                
                "&nbplaceEvent=" + ev.getEvent_nbrolace()+
                
                "&houreEvent=" +ev.getEvent_houre()+
                
                "&descriptionEvent=" + ev.getEvent_description()+
                
                "&afficheEvent=" + ev.getEvent_picture()
                 ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.setDisposeOnCompletion(dialog);
        
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    
    return idevent;
    }
  
    
    
     public void modifierEvent(event ev) throws IOException {

        InfiniteProgress progressIndicator = new InfiniteProgress();
Dialog dialog = progressIndicator.showInifiniteBlocking();
        ConnectionRequest con = new ConnectionRequest();
        MultipartRequest conn= new MultipartRequest();
 SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
                String datedeb = tempss.format(ev.getEvent_date());
                String datefin = tempss.format(ev.getEvent_end_date());
        String Url = "http://localhost/chick_click/web/app_dev.php/event/mobileeventmodify"
                +"?titreEvent=" + ev.getEvent_title()
                +"&coutryEvent=" + ev.getCountry()
                +"&coutryEvent=" + ev.getEvent_id()
                + "&addressEvent=" + ev.getAddress()+ 
                "&categoryEvent=" + ev.getCategories()+ 
                "&dateEvent=" + datedeb+
                "&datefinEvent=" +datefin+
                "&id="+ev.getEvent_id()+
                "&nbplaceEvent=" + ev.getEvent_nbrolace()+
                "&houreEvent=" +ev.getEvent_houre()+
                
                "&descriptionEvent=" + ev.getEvent_description()+
                
                "&afficheEvent=" + ev.getEvent_picture()
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
     
      public ArrayList<event> getListEvents(String json) {
         System.out.println(json);
        ArrayList<event> listEvents = new ArrayList<>();
        try {

            JSONParser j = new JSONParser();
            
            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));

       
            List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");
            if(list!=null){
            for (Map<String, Object> obj : list) {
                if(obj!=null){
                event carte = new event();//id, json, status);
               carte.setEvent_id((int) Float.parseFloat(obj.get("id").toString()));
              // carte.setUser_id((int) Float.parseFloat(obj.get("id").toString()));
               carte.setEvent_nbrolace((int) Float.parseFloat(obj.get("eventNBRPlace").toString()));
       
               carte.setEvent_title(obj.get("eventTitle").toString());
               carte.setEvent_picture(obj.get("eventPicture").toString());
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

               // carte.setEvent_date( sdf.parse((() (obj.get("eventStartDate")).getClass("timestamp")).toString()));
               
               carte.setEvent_date(new Date( (long)  Float.parseFloat(((Map<String, Object>) obj.get("eventStartDate")).get("timestamp").toString())));
               carte.setEvent_end_date(new Date( (long)  Float.parseFloat(((Map<String, Object>) obj.get("eventEndDate")).get("timestamp").toString())));
              
          
               System.out.println(carte.getEvent_date());
   
               
                carte.setEvent_description(obj.get("eventDescription").toString());
                
                carte.setAddress(obj.get("eventAdress").toString());
               
                carte.setCountry(obj.get("eventCountry").toString());
               
                carte.setEvent_houre(obj.get("eventHoure").toString());
                
                 carte.setCategories(obj.get("eventCategory").toString());
               
               listEvents.add(carte);

            }}}

        } catch (IOException ex) {
        }
        return listEvents;

    }

    
     ArrayList<event> listTasks = new ArrayList<>();
     public ArrayList<event> getListEvent(){    
          InfiniteProgress progressIndicator = new InfiniteProgress();
        
Dialog dialog = progressIndicator.showInifiniteBlocking();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/chick_click/web/app_dev.php/event/mobileshowEvents");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent ser = new ServiceEvent();
                listTasks = ser.getListEvents(new String(con.getResponseData()));
            }
        });
        con.setDisposeOnCompletion(dialog);
        
            NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
     
     
     ArrayList<event> listTasks3 = new ArrayList<>();
     public ArrayList<event> getlastEvent(){    
          InfiniteProgress progressIndicator = new InfiniteProgress();
        
Dialog dialog = progressIndicator.showInifiniteBlocking();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/chick_click/web/app_dev.php/event/mobileeventlastadded");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent ser = new ServiceEvent();
                listTasks3 = ser.getListEvents(new String(con.getResponseData()));
            }
        });
        con.setDisposeOnCompletion(dialog);
        
            NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks3;
    }
     
     
     ArrayList<event> listTasks1 = new ArrayList<>();
      public ArrayList<event> getEventById(int id){  
          InfiniteProgress progressIndicator = new InfiniteProgress();
        
Dialog dialog = progressIndicator.showInifiniteBlocking();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/chick_click/web/app_dev.php/event/mobileshowEvents/"+id+"");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent ser = new ServiceEvent();
                listTasks1 = ser.getListEvents(new String(con.getResponseData()));
            }
        });
        con.setDisposeOnCompletion(dialog);
        
            NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks1;
    }
      
      ArrayList<String> res=new ArrayList<>();
       public ArrayList<String> getEventStat(int id){       
        ConnectionRequest con = new ConnectionRequest(){
            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    
                    
                             res.add(data.get("id").toString());
                             res.add(data.get("nbrAllSubscriber").toString());
                             res.add(data.get("nbrplace").toString());
                             res.add(data.get("nbrSubscribeByEvent").toString());
  
                    
                } catch (IOException err) {
                    System.out.println(err);
                }
            }
             @Override
            protected void postResponse() {
                 System.out.println("llkllklk");      
               }
            
            
        };
        con.setUrl("http://localhost/chick_click/web/app_dev.php/event/mobilestatEvents/"+id+"");  
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                ServiceEvent ser = new ServiceEvent();
//                listTasks1 = ser.getListEvents(new String(con.getResponseData()));
//            }
//        });
        
            NetworkManager.getInstance().addToQueueAndWait(con);
            System.out.println(res);
        return res;
    }
     
      
      ArrayList<event> listTasks2 = new ArrayList<>();
 
      public ArrayList<event> getEventByUser(int id){ 
          InfiniteProgress progressIndicator = new InfiniteProgress();
        
Dialog dialog = progressIndicator.showInifiniteBlocking();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/chick_click/web/app_dev.php/event/mobileEventsbyuser/"+id+"");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent ser = new ServiceEvent();
                listTasks2 = ser.getListEvents(new String(con.getResponseData()));
            }
        });
        con.setDisposeOnCompletion(dialog);
            NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks2;
    }
      
      

    
      ArrayList<SearchEvent> searchRes= new ArrayList<>();
         public ArrayList<SearchEvent> search( String search) throws IOException {

//   CurrentUser cu = CurrentUser.getInstance();
//        InfiniteProgress progressIndicator = new InfiniteProgress();
//        
//Dialog dialog = progressIndicator.showInifiniteBlocking();
  
        ConnectionRequest con = new ConnectionRequest(){
            @Override
            protected void readResponse(InputStream input) throws IOException {
 try {
                JSONParser json = new JSONParser();
                
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> dataa = json.parseJSON(reader);
                    System.out.println("oooooooooooooooooooo45545");
                        System.out.println(dataa);
                        List<Map<String, Object>> data = (List<Map<String, Object>>) dataa.get("root");
                       
                    
                
                        if(data!=null){
                if(  ((Map<String, Object>)data.get(0)).get("message").toString().equals("true")){
                            for(int i=0 ;i<data.size();i++){                           
                                SearchEvent sea =new SearchEvent();
                                sea.setId_event(((Map<String, Object>)data.get(i)).get("id").toString());
                                sea.setTitre(((Map<String, Object>)data.get(i)).get("title").toString());
                                sea.setImage(((Map<String, Object>)data.get(i)).get("img").toString());
                                sea.setAdress(((Map<String, Object>)data.get(i)).get("adresse").toString());
                                searchRes.add(sea);
                            } }
                        }
                        } catch (Exception e) {
              searchRes=null;
                        }
                       
                       // idevent =(int) Float.parseFloat(data.get("id").toString());
                      //  CurrentUser.getInstance().setListReservation((ArrayList<Integer>) data.get("root"));
                System.out.println(CurrentUser.getInstance().getListReservation());
            }

            @Override
            protected void postResponse() {
                
              //  super.postResponse(); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
//            SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
//                String datedeb = tempss.format(ev.getEvent_date());
//                String datefin = tempss.format(ev.getEvent_end_date());
          String Url = "http://localhost/chick_click/web/app_dev.php/event/searchreMobile"
                +"?search=" +search ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    
    return searchRes;
    }
    
    
      
      
}



