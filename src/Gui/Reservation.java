/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.event;
import Service.CurrentUser;

import Service.ServiceEvenrReservation;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

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
public class Reservation extends BaseForm{
    
    public Reservation(Resources res ) {
       super("ReservationPAGE", BoxLayout.y()); 
        super.addSideMenu(res);
        super.addButtonBar(res,3);
        
        ServiceEvenrReservation sv= new ServiceEvenrReservation();
 
         UIBuilder ui = new UIBuilder(); 
        
        
          
           ArrayList<Entity.Reservation> l = sv.getReservationByUser(CurrentUser.getInstance().getIdcurrentuser());
          
           if(l==null||l.size()==0){
               Label jk=new Label("Vous n'avez pas reserver à aucun evenement ");
               add(jk);
           }else{
                   for (Entity.Reservation ev : l) {
                   
                
       
            Image img = res.getImage("signinchick.png");
                
                String imageData = ev.getImage();
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
//                Container container = new Container();
//               C1.add(super.myImageStyle(container, img));
           } catch (IOException ex) {
               System.out.println(ex);
           }
             
                            Button btnn = new Button("Annuler Reservation "); 
                            
                            btnn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent o) {
                  ServiceEvenrReservation sev=new ServiceEvenrReservation();
                    try {
                        sev.delete(ev.getId());
                        CurrentUser.getInstance().deleteRes(ev.getEvent_id());
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            });
                            
                            
              Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              Container Cb = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Label titreE = new Label("" + ev.getTitre());
                Label adress = new Label("@ " + ev.getAdresse());
                Label user = new Label("" + ev.getUsername());
                Label date = new Label("Le : " + ev.getDate() +" à : "+ ev.getHeure());
                 //Label heure = new Label("" + ev.getHeure());
                Label code = new Label("" + ev.getCode());               
                           
                
                            
                           
    C1.add(titreE);
    C1.add(adress);
    C1.add(user);
    //C1.add(heure);
    C1.add(date);
    C1.add(code);
    Cb.add(img);
    Cb.add(C1);
    C2.add(Cb);
    C2.add(btnn);
     C2.add(super.createLineSeparator(-16777216 ));
   // C2.add(super);
    add(C2);
                   
                   }
    }}
}
