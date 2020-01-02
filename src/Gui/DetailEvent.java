
package Gui;

import Entity.event;
import Service.CurrentUser;
import Service.ServiceEvent;
import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
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
public class DetailEvent extends BaseForm{
    
    public DetailEvent(Resources res , int id) {
       super("DetailPAGE", BoxLayout.y()); 
        super.addSideMenu(res);
        super.addButtonBar(res,8);
 
      
           ServiceEvent sevent = new ServiceEvent();
           
           List<event> l = sevent.getEventById(id);
           System.out.println(l);
                   for (event ev : l) {
                
                SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
                String datedeb = tempss.format(ev.getEvent_date());
                String datefin = tempss.format(ev.getEvent_end_date());
                       
                Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container Cb = new Container();
              
                Label titreE = new Label("Titre: " + ev.getEvent_title());
                Label tCountry = new Label("Country: " + ev.getCountry());
                Label tadress = new Label("Adresse: " + ev.getAddress());
                Label tcategory = new Label("Categorie: " + ev.getCategories());
                Label datedebE = new Label("Date debut: " + datedeb);
                Label datefinE = new Label("Date fin: " + datefin);
                
                Label nbpE = new Label("Nbr.place: " + ev.getEvent_nbrolace());
                Label fraisE = new Label("Houre: " + ev.getEvent_houre());              
                
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
                
           } catch (IOException ex) {
               System.out.println(ex);
           }
             
                  
               Button btn3 = new Button("itineraire");                         
                            btn3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent o) {
                Map me= new Map(res, ev.getEvent_id(),ev.getEvent_title());
                       me.show();
                }
            });
                            
                            
                          
                            
                            
    ArrayList<String> stat = sevent.getEventStat(id);
                   
    int nbrAllSubscriber = (int) Float.parseFloat(stat.get(1));  
    int nbrplace = (int) Float.parseFloat(stat.get(2));  
    int nbrSubscribeByEvent = (int) Float.parseFloat(stat.get(3));  
    
    int disp=nbrplace-nbrSubscribeByEvent;
    Label dispo = new Label("Nombres Des Places Encore \n Disponibles : " + disp );
    float pera= 100- (nbrSubscribeByEvent*100/nbrplace);
    float pers=100-( nbrSubscribeByEvent*100/nbrAllSubscriber);
    
    Label avail = new Label("pourcentage Des Places Encore \n Disponibles : " + pera );
    Label subs = new Label("pourcentage reservation parmis \n tous les evenements " + pers );
   
    Image progressOverlayImage = res.getImage("transparent.png");
    Image progressOverlayImage1 = res.getImage("transparent.png");
    progressOverlayImage.scale(200, 200);
    
   //pourcentage place encore disponible 
    Container progress = new Container(new LayeredLayout());
Label percent = new Label(pera+"%");
percent.getUnselectedStyle().setAlignment(Component.CENTER);
progress.add(new Label(progressOverlayImage, "Container")).
        add(FlowLayout.encloseCenterBottom(percent));
progress.getUnselectedStyle().setBgPainter((Graphics g, Rectangle rect) -> {
    g.setColor(0xff0000);
                    float currentProgress360=pera*360/100;
    g.fillArc(progress.getX(), progress.getY(), progressOverlayImage.getWidth(), progressOverlayImage.getHeight(), 0,(int) currentProgress360);
});

//pourcentage reservation parmis tous les evenement 

  Container progress1 = new Container(new LayeredLayout());
Label percent1 = new Label(pers+"%");
percent1.getUnselectedStyle().setAlignment(Component.CENTER);
progress1.add(new Label(progressOverlayImage1, "Container")).
        add(FlowLayout.encloseCenterBottom(percent1));
progress1.getUnselectedStyle().setBgPainter((Graphics g, Rectangle rect) -> {
    g.setColor(0xff0000);
                    float currentProgress2=pers*360/100;
    g.fillArc(progress1.getX(), progress1.getY(), progressOverlayImage1.getWidth()-20, progressOverlayImage1.getHeight(), 0,(int) currentProgress2);
});

Button bbb=new Button("Statistic");



    Container container = new Container();
               C1.add(super.myImageStyle(container, img,res));               
    C1.add(titreE);
    
   
    C1.add(tCountry);
    C1.add(tadress);
    C1.add(tcategory);
    C1.add(datedebE);
    C1.add(datefinE);
    C1.add(nbpE);
    C1.add(fraisE);
    C1.add(descriptionE);
    C1.add(btn3);
    C1.add(bbb);
    C1.add(new Label("   \n "));
    C1.add(dispo);
    C1.add(new Label("   \n "));
    C1.add(avail);
    C1.add(progress);
     
     C1.add(subs);
     C1.add(progress1);
     add(C1);
                   
                   }
    }
}