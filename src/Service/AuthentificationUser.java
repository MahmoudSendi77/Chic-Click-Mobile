package Service;

//import Entity.User;
import Gui.HomePage;
import Gui.SignInForm;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;

import com.codename1.io.NetworkManager;

import com.codename1.ui.Dialog;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.Map;
import com.codename1.ui.util.Resources;


/**
 *
 * @author mahmoud
 */
public class AuthentificationUser {

  //  public static User user = new User();
  
     boolean istrue;
    
    public void login(Resources res) {
      String userlog = SignInForm.username.getText();
        String passlog = SignInForm.password.getText();
       CurrentUser cr= CurrentUser.getInstance();

        ConnectionRequest con;
        con= new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    if (data.get("isValid").toString().equals("notValidMail")) {
                        Dialog.show("error", "Email Wrong ! please retry ", "Cancel", "ok");
                    } else if (data.get("isValid").toString().equals("notValid")) {
                    
                    }else{           
                        cr.setIdcurrentuser((int) Float.parseFloat(data.get("id").toString()));
                        cr.setEmail(((String) data.get("email")));
                        cr.setPseudo(((String) data.get("username")));
                        setistrue(true);
                        System.out.println(cr.getEmail());
             
                    }
                } catch (IOException err) {
                    System.out.println(err);
                }
            }

            @Override
            protected void postResponse() {
               
                if (passlog.equals("")) {
                    Dialog.show("error", "Please put your password ! ", "cancel", "ok");
                    return;
                } 
                if (istrue==false) {
                    
                    Dialog.show("error", "Wrong password please retry! ", "cancel", "ok");
                    return;
                }
                 
                    if (istrue==true) {
 new HomePage(res).show();
             
                    }
                    
               }
        };

        con.setUrl("http://localhost/chick_click/web/app_dev.php/event/mobillogin?password=" +passlog +"&email="+userlog);
        NetworkManager.getInstance().addToQueue(con);
    }
    
    public void setistrue(boolean b){
    this.istrue=b;
    }


     
}