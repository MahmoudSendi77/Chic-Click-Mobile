/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.event;
import Service.CurrentUser;
import Service.ServiceEvenrReservation;
import Service.ServiceEvent;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;





/**
 *
 * @author mahmoud
 */
public class ShowEvents extends BaseForm{
    Resources res;
    public ShowEvents(Resources res) {
       super("ShowPAGE", BoxLayout.y()); 
        super.addSideMenu(res);
 
        super.addButtonBar(res,1);
        this.res=res;
        
     Container container ;
         UIBuilder ui = new UIBuilder(); 

     Picker p = new Picker();
           
           ServiceEvent sevent = new ServiceEvent();
           
           List<event> l = sevent.getListEvent();
           if(l==null||l.size()==0){
               Label tt =new Label("aucun évènemenet ajouté ");
               add(tt);
           }else{
               
          
                   for (event ev : l) {
                      
                    Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container Cb = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Label titreE = new Label("Titre: " + ev.getEvent_title());
                Label animateurE = new Label("Country: " + ev.getCountry());
                Label lieuE = new Label("Adresse: " + ev.getAddress());
                Label lienE = new Label("Categorie: " + ev.getCategories());
                Label datedebE = new Label("Date debut: " + ev.getEvent_date().toString().substring(0, 10));
                Label datefinE = new Label("Date fin: " + ev.getEvent_end_date().toString().substring(0, 10));
                
                Label nbpplace = new Label("Nbr.place: " + ev.getEvent_nbrolace());
                Label heure = new Label("Houre: " + ev.getEvent_houre());
                
                Button seemore=new RadioButton(" Lire de plus ... " );
                seemore.animate();
                seemore.addLongPressListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            
                       new DetailEvent(res,ev.getEvent_id()).show();
                        }
                    });
                Label descriptionE = new Label("Description: " + ev.getEvent_description()); 

                Image img ;
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
               container=new Container();
               container.add(myImageStyle(container, img,res));
            C1.add( container);
           } catch (IOException ex) {
               System.out.println(ex);
           }
             
                            Button btnn = new Button("Reserverver "); 
                            
                            btnn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent o) {

                    ServiceEvenrReservation sev=new ServiceEvenrReservation();
                    try {
            
sev.ajoutReservation(ev.getEvent_id());

                    } catch (IOException ex) {
                        System.out.println(ex);    }
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
                           
                            Cb.add(btn3);
        
            
    C1.add(titreE);
    
    C1.add(animateurE);
    C1.add(lieuE);
    C1.add(lienE);
    C1.add(datedebE);
    C1.add(datefinE);
    C1.add(nbpplace);
    C1.add(heure);
    C1.add(descriptionE);
    C1.add(seemore);
    C1.add(Cb);
    C1.add(super.createLineSeparator(-16777216 ));
    add(C1);
      }
                   
                   }
    }
    
    public boolean haveReservation(int id){
        boolean res=false;
        ArrayList<Integer> list=CurrentUser.getInstance().getListReservation();
        for(int i=0;i<list.size();i++){
            if(((int) list.get(i))==id){
                res=true;
                System.out.println(res);}
        }
        return res;
    }
   
}
