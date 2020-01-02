/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entity.EventLocation;
import Service.ServiceEventLocation;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.maps.providers.OpenStreetMapProvider;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author mahmoud
 * 
 */

public class Map extends BaseForm{
    private Form main;
    private Coord lastLocation;
    EventLocation ev;
    String titre="";
    Coord location;

public Map(Resources theme , int id, String titre){    
    super("MapPage", BoxLayout.y());
      super.addSideMenu(theme);
      super.addButtonBar(theme,8);
      this.titre=titre;
        //Enable Toolbar to all Forms by default
        Toolbar.setGlobalToolbar(true);
       ServiceEventLocation sv=new ServiceEventLocation();
       ev=sv.getEventLocation(id);
       this.location = new Coord(ev.getLattitude() ,ev.getLongitude());
       
             showMeOnMap();
 
    }

    private void showMeOnMap() {
      
        Form map = new Form("Adresse De "+titre);
                     
        map.setLayout(new BorderLayout());
        map.setScrollable(false);
        Button in =new Button("+");
        Button out =new Button("-");
       
        final MapComponent mc = new MapComponent(new OpenStreetMapProvider(), location, 8);

        putMeOnMap(mc);
        Container cc = new Container(new BoxLayout(BoxLayout.X_AXIS));
       
        map.addComponent(BorderLayout.CENTER, mc);
        
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        
         mc.setZoomLevel(8);
         in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            mc.zoomIn();
            }
        });
         
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            mc.zoomOut();
               
            }
        });
     
        Dimension d =new Dimension(96, 125);
       in.setPreferredSize(d);
       out.setPreferredSize(d);
       cc.add(in);
       cc.add(out);
       C1.add(cc);
        C1.add(map);
        add(C1);

    }

    private void putMeOnMap(MapComponent map) {
        try {
             lastLocation = new Coord(ev.getLattitude() ,ev.getLongitude());
            Image i = Image.createImage("/pin.png");
            PointsLayer pl = new PointsLayer();
            pl.setPointIcon(i);
            PointLayer p = new PointLayer(lastLocation, titre , i);
            p.setDisplayName(true);
            pl.addPoint(p);
            pl.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    PointLayer p = (PointLayer) evt.getSource();
                    System.out.println("pressed " + p);

                  //  Dialog.show("Details", "Tu es là!" + "\n" +titre+  "Ok", null);
                }
            });
            map.addLayer(pl);
            map.setZoomLevel(4);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


  

  

}

