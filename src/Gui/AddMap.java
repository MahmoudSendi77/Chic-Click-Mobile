
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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;


/**
 *
 * @author mahmoud
 * 
 */
public class AddMap extends BaseForm{
    private Coord lastLocation;
    int count =0;
    int id ;
    Resources theme;
    String titre;

public AddMap(Resources theme ,int id,String titre){
    
    super("AddMap", BoxLayout.y());
      super.addSideMenu(theme);
      this.id =id;
        
        this.theme=theme;
        this.titre=titre;
        
      
             initMap();
                    
    }

    private void initMap() {
      
       Form map = new Form("Adresse De " +titre);
                     
   TextField log =new TextField("","longitude");
   TextField lat =new TextField("","latitude");

   Button save =new Button("Ajouter emplacement");
       // Container map=new Container();
        map.setLayout(new BorderLayout());
        map.setScrollable(false);
        Toolbar tmp=new Toolbar(true);
     //   map.setToolbar(tmp);
       // map.getToolbar().addCommandToLeftBar(new AddMap.BackCommand());
        final MapComponent mc = new MapComponent(new OpenStreetMapProvider());
        map.addComponent(BorderLayout.NORTH, mc);
        EventLocation el= new EventLocation();
        mc.setWidth(80);
        mc.setHeight(80);
        map.setWidth(80);
        map.setHeight(80);
     //   map.setBackCommand(new AddMap.BackCommand());
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
      
        map.addLongPressListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              Coord center=  mc.getCoordFromPosition(evt.getX(), evt.getY());
                addMarker(mc,center);
                lat.setText(""+center.getLatitude());
                log.setText(""+center.getLongitude());
                el.setEvent_id(id);
                el.setLattitude(center.getLatitude());
                el.setLongitude(center.getLongitude());
                System.out.println(center); 
        }});
        Button in =new Button("+");
        Button out =new Button("-");
        
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
        Container cc = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Dimension d =new Dimension(96, 125);
       in.setPreferredSize(d);
       out.setPreferredSize(d);
       cc.add(in);
       cc.add(out);
       
        C1.add(log);
        C1.add(lat);
        C1.add(save);
        Label ll=new Label();
        Label ll2=new Label();
        C1.add(ll);
        C1.add(ll2);
        add(C1);
        C1.add(cc);
        add(map);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceEventLocation sel =new ServiceEventLocation();
                try {
                    sel.ajoutEventLocation(el);
                    new ShowEvents(theme).show();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        });
    }



    private void addMarker(MapComponent map, Coord c) {
        try {
                        
            map.removeAllLayers();
            lastLocation = new Coord(c);
            Image i = Image.createImage("/pin.png");
            PointsLayer pl = new PointsLayer();
            pl.setPointIcon(i);
            PointLayer p = new PointLayer(lastLocation, "position", i);
            p.setDisplayName(true);
           
           
            pl.addPoint(p);
            map.addLayer(pl);
          
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    class BackCommand extends Command {

        public BackCommand() {
            super("");
            FontImage img = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));
            setIcon(img);
        }

      
    }

  

}

