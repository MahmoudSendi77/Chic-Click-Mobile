
package Gui;

import Entity.event;
import Service.CurrentUser;
import Service.ServiceEvent;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.IOException;


import java.util.List;
 
 
 
 
/**
 *
 * @author mahmoud
 */
public class MyEvents extends BaseForm{
    
    public MyEvents(Resources res ) {
       super("MyEventPAGE", BoxLayout.y()); 
        super.addSideMenu(res);
        super.addButtonBar(res,4);
                   
 
      
           ServiceEvent sevent = new ServiceEvent();
           
           List<event> l = sevent.getEventByUser(CurrentUser.getInstance().getIdcurrentuser());
           if(l==null||l.size()==0){
               Label  lll= new Label(" Vous n'avez pas creer des evenements  " );
               add(lll);
           }
           else{
           System.out.println(l);
                   for (event ev : l) {
                
 
                       
                Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container Cb = new Container(new BoxLayout(BoxLayout.X_AXIS));
              
                Label titreE = new Label("Titre: " + ev.getEvent_title());
                Label animateurE = new Label("Country: " + ev.getCountry());
                Label lieuE = new Label("Adresse: " + ev.getAddress());
                Label lienE = new Label("Categorie: " + ev.getCategories());
                Label datedebE = new Label("Date debut: " + ev.getEvent_date().toString().substring(0, 10));
                Label datefinE = new Label("Date fin: " + ev.getEvent_end_date().toString().substring(0, 10));
                
                Label nbpE = new Label("Nbr.place: " + ev.getEvent_nbrolace());
                Label fraisE = new Label("Houre: " + ev.getEvent_houre());
                
                Label seemore=new Label(" Lire de plus ... " );
                seemore.addLongPressListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                       //new DetailEvent(res).show();
                        }
                    });
                
                Label descriptionE = new Label("Description: " + ev.getEvent_description()); 
 
                   Image img = res.getImage("signinchick.png");
               
                String imageData = ev.getEvent_picture();
                String base64Data = imageData.substring(imageData.indexOf(",")+1, imageData.length());
                System.out.println("uuuuuuuuuuuuuuuu");
                System.out.println(imageData.length());
                System.out.println(base64Data.length());
                System.out.println(base64Data);
                System.out.println(base64Data.getBytes());
                byte[] decodedBytes = Base64.decode(base64Data.getBytes());
                System.out.println(decodedBytes);
                ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
                //  byte[] imagedata = ev.getEvent_description().getBytes();
           try {
               img=  Image.createImage(bis);
                Container container = new Container();
               C1.add(super.myImageStyle(container, img,res));
           } catch (IOException ex) {
               System.out.println(ex);
           }
             
                            Button btnn = new Button("Supprimer "); 
                            
                            btnn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent o) {
 
                  ServiceEvent sev=new ServiceEvent();
                    try {
                        sev.delete(ev.getEvent_id());
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            });
                             Button btn2 = new Button("Modifier"); 
                            
                            btn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent o) {
                ModifyEvent me= new ModifyEvent(res, ev.getEvent_id());
                       me.show();
                }
            });
                                     Button btn3 = new Button("itineraire");                         
                            btn3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent o) {
                Map me= new Map(res, ev.getEvent_id(),ev.getEvent_title());
                       me.show();
                }
            });
                            
                            Cb.add(btnn);
                            Cb.add(btn2);
                            Cb.add(btn3);
                           
   
    
    C1.add(titreE);
    C1.add(animateurE);
    C1.add(lieuE);
    C1.add(lienE);
    C1.add(datedebE);
    C1.add(datefinE);
    C1.add(nbpE);
    C1.add(fraisE);
    C1.add(descriptionE);
    
    C1.add(Cb);
     C1.add(super.createLineSeparator(-16777216 ));
    add(C1);
                   
                   }
           }
    }
}