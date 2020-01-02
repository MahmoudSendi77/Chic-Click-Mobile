
package Gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;


/**
 *
 * @author mahmoud
 */
public class BaseForm extends Form {
    

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("signinchick.png");
       // Image image=  Image.createImage(img);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.png"), "PictureWhiteBackgrond"))
        ));

        tb.addMaterialCommandToSideMenu("Espace_Events", FontImage.MATERIAL_ACCESSIBILITY_NEW, e -> new HomePage(res).show());
        tb.addMaterialCommandToSideMenu("Show_Events", FontImage.MATERIAL_BOOKMARKS, e -> new ShowEvents(res).show());
       

        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new SignInForm(res).show());

    }
    
    protected void addButtonBar(Resources res, int i){
        
        ButtonGroup barGroup = new ButtonGroup();
        
        RadioButton envents = RadioButton.createToggle("Events", barGroup); 
        envents.setUIID("SelectBar");
        
        RadioButton addevent = RadioButton.createToggle("AddEvent", barGroup);
        addevent.setUIID("SelectBar");
        
        RadioButton listReservation = RadioButton.createToggle("Reservation", barGroup);
        listReservation.setUIID("SelectBar");
        
        RadioButton myevent = RadioButton.createToggle("MyEvents", barGroup);
        myevent.setUIID("SelectBar");
                
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, envents, addevent, listReservation, myevent),
                FlowLayout.encloseBottom(arrow)
        ));
        
        bindButtonSelection(envents, arrow);
        bindButtonSelection(addevent, arrow);
        bindButtonSelection(listReservation, arrow);
        bindButtonSelection(myevent, arrow);

        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        arrow.setVisible(false);
        if(i==1){
        envents.setSelected(true);
        arrow.setVisible(false);
        
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(envents, arrow);
        });
        }
        if(i==2){
        addevent.setSelected(true);
        arrow.setVisible(false);
        
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(addevent, arrow);
        });
        }
        if(i==3){
        listReservation.setSelected(true);
        arrow.setVisible(false);
        
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(listReservation, arrow);
        });
        }
        if(i==4){
        myevent.setSelected(true);
        arrow.setVisible(false);
        
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(myevent, arrow);
        });
        }
        
        envents.addActionListener((ActionListener) (ActionEvent evt) -> {
            new ShowEvents(res).show();
        
        });
        addevent.addActionListener((ActionListener) (ActionEvent evt) -> {
            new AddEvent(res).show();
        
        });
        
        listReservation.addActionListener((ActionListener) (ActionEvent evt) -> {
            new Reservation(res).show();
        listReservation.setSelected(true);
       
        });
        myevent.addActionListener((ActionListener) (ActionEvent evt) -> {
            new MyEvents(res).show();
             myevent.setSelected(true);
       
            
        });
    }
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();    
    }
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
      public Component myImageStyle(Container swipe, Image img,Resources res) {
         
         Container page1 ;
         try {
             int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
             if (img.getHeight() < size) {
                     System.out.println(img);
                     System.out.println(size);
                     img = img.scaledHeight(size);
                 }
                
             
             if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
                 img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
             }
             ScaleImageLabel image = new ScaleImageLabel(img);
             image.setUIID("Container");
             image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
             Label overlay = new Label(" ", "ImageOverlay");
             
             page1
                     = LayeredLayout.encloseIn(
                             image, overlay
                     //              
                     );
         } catch (Exception e) {
             Image imge = res.getImage("notfoundimage.jpg");
             int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
             if (imge.getHeight() < size) {
                     
                     imge = imge.scaledHeight(size);
                 }
                
             
             if (imge.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
                 imge = imge.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
             }
             ScaleImageLabel image = new ScaleImageLabel(imge);
             image.setUIID("Container");
             image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
             Label overlay = new Label(" ", "ImageOverlay");
             
             page1
                     = LayeredLayout.encloseIn(
                             image, overlay
                     //              
                     );
         }

        return page1;
        
   
    }
}
