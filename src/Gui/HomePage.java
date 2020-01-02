

package Gui;


import Entity.SearchEvent;
import Entity.event;
import Service.ServiceEvent;
import com.codename1.ui.Button;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

import com.codename1.ui.Image;
import com.codename1.ui.Label;

import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;

import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




/**
 * 
 *
 * @author mahmoud
 */
public class HomePage extends BaseForm {
int count=0;
 Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 Container Cb = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 Container Cbb = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    
    Resources res;
    public HomePage(Resources res) {
        
        super("HOMEPAGE", BoxLayout.y());
        super.addSideMenu(res);
        super.getToolbar().addSearchCommand(e -> {
         this.res=res;
           System.out.println(e.getSource().toString());
           ServiceEvent sevv=new ServiceEvent();
           if(! (e.getSource().toString().length()<=2)){
            try {
                ArrayList<SearchEvent> ss=  sevv.search( e.getSource().toString());
                System.out.println(ss);
                Cb.setVisible(false);
                C2.setVisible(true);
                C1.revalidate();
                addsearchResult(ss);
                
            } catch (IOException ex) {
                 }
           }
           else{
               Cb.setVisible(true);
                C2.setVisible(false);
                C1.revalidate();
           }
       });
 
        Cb.setVisible(true);
        ServiceEvent sevent = new ServiceEvent();
           
        List<event> l = sevent.getlastEvent();        
        System.out.println(l);
         Container container;
                     Label titre;
                     Container tcont ;
                      FlowLayout flow ;
                      flow = new FlowLayout(CENTER);
                      Label separate =new Label("\n \n   \n\n \n  \n\n \n LAST 5 ADDED EVENTS ");
                      Cb.add(separate);
                     
                      Button show = new Button("HIDE LAST 5 ADDED EVENTS");
                       
                      show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               count++;
                if(count==1){
                     Cbb.setVisible(false);
                     show.setText("SHOW LAST 5 ADDED EVENTS");
                     C1.revalidate();
                     
                }if(count==2){
                     
                     Cbb.setVisible(true);
                     show.setText("HIDE LAST 5 ADDED EVENTS");
                      C1.revalidate();
                     count=0;
                }
                
            }
        });
                 
                       Cb.add(show);
                
       for(int i=0 ;i<l.size();i++){
                container = new Container();

        
                Image img ;
                String imageData = l.get(i).getEvent_picture();
                String base64Data = imageData.substring(imageData.indexOf(",")+1, imageData.length());
                byte[] decodedBytes = Base64.decode(base64Data.getBytes());
                ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
           try {
               img=  Image.createImage(bis);
           
        titre=new Label(l.get(i).getEvent_title());
        titre.getUnselectedStyle().setFgColor(621200);
        container.add(super.myImageStyle(container, img ,res));
        
        flow.setValign(TOP);
        tcont = new Container(flow);
        tcont.add(titre);
        Cbb.add(LayeredLayout.encloseIn(container, tcont));
        
        } catch (IOException ex) {
               System.out.println(ex);
         }         
          }
          Cb.add(Cbb);
          C1.add(C2);
          C1.add(Cb);
          
          C2.setVisible(false);
          add(C1);
        
    }

   
    public void addsearchResult( ArrayList<SearchEvent> ev){
        C2.removeAll();
         Label jk;
         Container ccc ;
         Image img;
          String imageData;
       String base64Data;
       ByteArrayInputStream bis;
       Container container1;
        Label titreE;
         Label adress;
          Label adresss;
         if(ev==null||ev.size()==0){
                jk=new Label("aucun de Resultats trouver ");
               jk.getUnselectedStyle().setFgColor(621200);
               C2.add(jk);
           }else{
               
                 for(int i=0 ;i<ev.size();i++){
                    
       
        ccc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               imageData  = ev.get(i).getImage();
               base64Data = imageData.substring(imageData.indexOf(",")+1, imageData.length());
                
                byte[] decodedBytes = Base64.decode(base64Data.getBytes());
               
                 bis = new ByteArrayInputStream(decodedBytes);
               
           try {
               img=  Image.createImage(bis);
                container1 = new Container();
               ccc.add(super.myImageStyle(container1, img,res));
           } catch (IOException ex) {
               System.out.println(ex);
           }

          titreE = new Label("Titre: " + ev.get(i).getTitre());
          adress = new Label("Adresse: " + ev.get(i).getAdress());
          adresss = new Label("  " );
         System.out.println("ldjvhdslvkhdsklvhdsklvjhdskjvhdkjvhd");
         ccc.add(titreE);
         ccc.add(adress);
         ccc.add(super.createLineSeparator(-16777216 ));
         ccc.add(adresss);
         C2.add(ccc);
         
                 }
         }
    }
    
    
}
