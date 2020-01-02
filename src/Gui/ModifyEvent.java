/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.event;
import Service.ServiceEvent;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.util.regex.RE;


/**
 *
 * @author mahmoud
 */
public class ModifyEvent extends BaseForm{

    TextField ttitre;
    TextField tcoutry; //Coutry
    TextField tadress;// address
    TextField idd;
    ComboBox<String> category ;
    TextField tcategory;//Category
    TextField thoure;//houre event
    TextField nbplaceeLabel;
    TextArea descriptionArea;    
    Picker datedeb;
    Picker datefin ;
    Button btnajout,btnCancel;
    int id;
 

    public ModifyEvent(Resources theme,int id)  {
       
        
       super("ModifyEventPage", BoxLayout.y());
      super.addSideMenu(theme);
    
    
      ServiceEvent sv = new ServiceEvent();
      ArrayList<event> l =sv.getEventById(id);
      event even = l.get(0);
        System.out.println(even);
   ///     f = new Form("home");
        ttitre = new TextField(""+even.getEvent_title(),"titre");
        
        tcoutry = new TextField(""+even.getCountry(),"country");
        tadress = new TextField(""+even.getAddress(),"adresse");
        tcategory = new TextField(""+even.getCategories(),"Category");
       
       
        thoure = new TextField(""+even.getEvent_houre(),"Heure hh:mm");
        nbplaceeLabel = new TextField(""+even.getEvent_nbrolace(),"nbplaceeLabel");
        btnajout = new Button("modifier");
        btnCancel=new Button("Cancel");
        
        Container dateContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        datedeb = new Picker();
        Label temp = new Label("date de debut :");
        dateContainer.add(temp);
        dateContainer.add(datedeb);
        
        Container dateContainer2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        datefin = new Picker();
        Label temp2 = new Label("date de fin  :");
        dateContainer2.add(temp2);
        dateContainer2.add(datefin);
      
       SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
       
           Image img = theme.getImage("signinchick.png");
                String imageData = even.getEvent_picture();
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
       
        
        
        Label descriptionLabel = new Label("Description : ");
        descriptionArea = new TextArea(3, 20);
        Container descriptionContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        descriptionContainer.add(descriptionLabel);
        descriptionArea.setText(even.getEvent_description());
        descriptionContainer.add(descriptionArea);
        
        
        Label photoLabel = new Label("Photo");
        Button selectPhoto = new Button("parcourir");
        TextField photoField = new TextField("", "Importer une photo", 10, TextArea.ANY);
        photoField.setEditable(false);
        selectPhoto.addActionListener((evt) -> {
                Display.getInstance().openGallery((e) -> {
                    if (e != null && e.getSource() != null) {
                        ImageIO imgIO = ImageIO.getImageIO();
ByteArrayOutputStream out = new ByteArrayOutputStream();
                        try {
                           imgIO.save((Image) e.getSource(), out, ImageIO.FORMAT_JPEG, 1);
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
byte[] ba = out.toByteArray();
String imagecode = Base64.encode(ba);
                        String file = (String) e.getSource();
                       // photoField.setText(file.substring(file.lastIndexOf('/') + 1));
                        photoField.setText(imagecode);
                    }
                }, Display.GALLERY_IMAGE);
            
        });
        Container photoContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        photoContainer.add(photoLabel);
        photoContainer.add(photoField);
        photoContainer.add(selectPhoto);

         Container container = new Container();
          
        add(super.myImageStyle(container, img,theme));
        add(ttitre);
        add(tcoutry);
        add(tadress);
        add(tcategory);
        add(dateContainer);
        add(dateContainer2);
        
        add(nbplaceeLabel);
        add(thoure);
        add(descriptionContainer);
        add(photoContainer);
        add(btnajout);
        add(btnCancel);
         
        btnajout.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              if (ttitre.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","TITRE VIDE","OK","ANNULER");
            }
            
            else if (tcoutry.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","Country VIDE","OK","ANNULER");
            }
            else if (tadress.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","Adress VIDE","OK","ANNULER");
            }
            else if (tcategory.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","Category VIDE","OK","ANNULER");
            }
            
             else if (!isValidHour(thoure.getText())) {
                
                Dialog.show("ERREUR SAISIE","Heure Non Valide hh:mm ","OK","ANNULER");
            }
             else if (nbplaceeLabel.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","Nbplaces Vide","OK","ANNULER");
            }
             
             else if (!isAEntier(nbplaceeLabel.getText())) {
                
                Dialog.show("ERREUR SAISIE","nbplaces doit etre numeric","OK","ANNULER");
            }
            else if (descriptionArea.getText().equals("")) {
                
                Dialog.show("ERREUR SAISIE","Description VIDE","OK","ANNULER");
            }
               else if (photoField.getText().equals("")) {
                   photoField.setText("00");
                   //Dialog.show("ERREUR SAISIE","Choisisez une image","OK","ANNULER");
               }
               
               else{
                   
                   ServiceEvent ser = new ServiceEvent();
                   event ev = new event(id, datedeb.getDate(), tadress.getText(),
                           descriptionArea.getText(),ttitre.getText(),photoField.getText(),datefin.getDate(),
                           tcoutry.getText(), tcategory.getText(),thoure.getText(),
                           Integer.parseInt(nbplaceeLabel.getText()) 
                   );
                   ev.setEvent_id(id);
                   System.out.println(ev.getEvent_id());
                   try {
                       ser.modifierEvent(ev);
                       Dialog.show("felicitation", " votre evenement a ete modifier", "ok", null);
                   } catch (IOException ex) {
                       Dialog.show("Error", " desole une erreure et survenue !!!!", "ok", null);
                   }
                   // Dialog.show("felicitation", " votre evenement a ete ajoute", "ok", null);
               }  }
       });
       
        btnCancel.addActionListener((e)->{
        MyEvents a =new MyEvents(theme);
        a.show();
        });
    }
//
//    public Form getF() {
//        return f;
//    }
//
//    public void setF(Form f) {
//        this.f = f;
//    }

   

    public TextField getTtitre() {
        return ttitre;
    }

    public void setTtitre(TextField ttitre) {
        this.ttitre = ttitre;
    }

    public TextField getTCountry() {
        return tcoutry;
    }

    public void setTCountry(TextField tanim) {
        this.tcoutry = tanim;
    }

    public TextField getTAdress() {
        return tadress;
    }

    public void setTlieu(TextField tlieu) {
        this.tadress = tlieu;
    }

    public TextField getTlien() {
        return tcategory;
    }

    public void setTlien(TextField tlien) {
        this.tcategory = tlien;
    }

    public boolean isAFloat(String x) {
        try {
            Float.parseFloat(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    
public boolean isAEntier(String x) {
        try {
            Integer.parseInt(x);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

private static final String heure= "^(?:\\d|[01]\\d|2[0-3]):[0-5]\\d$";

     public static boolean isValidHour(String txt){
          System.out.println(txt);
          RE r = new RE(heure);
          r.match(txt);
      
         
        return r.match(txt);
        
     }

}
