/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.event;
import Service.CurrentUser;
import Service.ServiceEvent;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import com.codename1.ui.util.Resources;

import java.io.IOException;

import com.codename1.util.regex.RE;


/**
 *
 * @author mahmoud
 */
public class AddEvent extends BaseForm{

    TextField ttitre;
    TextField tcoutry; //Coutry
    TextField tadress;// address
    ComboBox<String> category ;
    TextField tcategory;//Category
    TextField thoure;//houre event
    TextField nbplaceeLabel;
    TextArea descriptionArea;    
    Picker datedeb;
    Picker datefin ;
    Button btnajout;
    

    public AddEvent(Resources theme)  {
       
       super("AddEvent", BoxLayout.y());
      super.addSideMenu(theme);
      super.addButtonBar(theme,2);
      
   ///     f = new Form("home");
   
        ttitre = new TextField("","titre");
        tcoutry = new TextField("","country");
        tadress = new TextField("","adresse");
        tcategory = new TextField("","Category");
        
        
       
        thoure = new TextField("","Heure");
        nbplaceeLabel = new TextField("","nbplacee");
        btnajout = new Button("ajouter");
       
        
        Container dateContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        datedeb = new Picker();
        Label temp = new Label("date de debut");
        dateContainer.add(temp);
        dateContainer.add(datedeb);
        
        Container dateContainer2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        datefin = new Picker();
        Label temp2 = new Label("date de fin");
        dateContainer2.add(temp2);
        dateContainer2.add(datefin);
      
       SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
       
        
        
        Label descriptionLabel = new Label("Description : ");
        descriptionArea = new TextArea(3, 20);
        Container descriptionContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        descriptionContainer.add(descriptionLabel);
        descriptionContainer.add(descriptionArea);
        
        
        Label photoLabel = new Label("Photo");
        Button selectPhoto = new Button("parcourir");
        TextField photoField = new TextField("", "Importer une photo", 10, TextArea.ANY);
        photoField.setEditable(false);
       
        selectPhoto.addActionListener((evt) -> {
            if (Dialog.show("Photo!", "une annonce avec des  photos est 10 fois plus visible", "app photo", "Gallerie") == false) {
                Display.getInstance().openGallery((e) -> {
                    if (e != null && e.getSource() != null) {

                        System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiii\n");
                        
                        String file = (String) e.getSource();
                        System.out.println(file);
                        photoField.setText(file);
                       
                    }
                }, Display.GALLERY_IMAGE);
            } else {
                System.out.println("ici on va accerder à l'appareille photo");
            }
        });
        Container photoContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        photoContainer.add(photoLabel);
        photoContainer.add(photoField);
        photoContainer.add(selectPhoto);

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
       // add(btnaff);
         
        btnajout.addActionListener((ActionEvent e) -> {
            
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
                
                Dialog.show("ERREUR SAISIE","Choisisez une image","OK","ANNULER");
            }
             
            else{

            ServiceEvent ser = new ServiceEvent();
            event ev = new event(CurrentUser.getInstance().getIdcurrentuser(), datedeb.getDate(), tadress.getText(),
            descriptionArea.getText(),ttitre.getText(),photoField.getText(),datefin.getDate(),
                    tcoutry.getText(), tcategory.getText(),thoure.getText(),
            Integer.parseInt(nbplaceeLabel.getText())
     
            );
                try {
                   
                   int o =ser.ajoutEvent(ev);;
                   
                   if(o>0){
                       Dialog.show("felicitation", " votre evenement a ete ajoute \n Ajouter location on map ?", "ok", null);            
                       new AddMap(theme, o,ttitre.getText()).show();
                   }
                } catch (IOException ex) {
                    System.out.println(ex);
                }            
        }
        });
       
        
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
