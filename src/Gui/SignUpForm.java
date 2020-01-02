

package Gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Validator;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint ;




/**
 * 
 *
 * @author mahmoud
 */
public class SignUpForm extends BaseForm {
    public static TextField username,email,password,confirmPassword,numtel,adresee;
    public static ComboBox box;
    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        username = new TextField("", "Username", 20, TextField.ANY);
        email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        password = new TextField("", "Password", 20, TextField.PASSWORD);
        confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        numtel = new TextField("", "Numéro de telephone", 20, TextField.PHONENUMBER);
        adresee = new TextField("", "Adresse", 20, TextField.ANY);
        box = new ComboBox();
        username.setSingleLineTextArea(true);
        email.setSingleLineTextArea(true);
        password.setSingleLineTextArea(true);
        confirmPassword.setSingleLineTextArea(true);
        numtel.setSingleLineTextArea(true);
        adresee.setSingleLineTextArea(true);
        
        Validator val = new Validator();
        
                val.addConstraint(username, new LengthConstraint(4));
                String testusername="^\\(?([a-z]{3})\\)?";
               val.addConstraint(username, new RegexConstraint(testusername, ""));
               
                val.addConstraint(password, new LengthConstraint(4));
                val.addConstraint(confirmPassword, new LengthConstraint(4));

                val.addConstraint(adresee, new LengthConstraint(20));
               String testaddress="^\\(?([Tunisia ]{8})\\)?";
               val.addConstraint(adresee, new RegexConstraint(testaddress, "Must be valid phone number"));
                
                
                 val.addConstraint(numtel, new LengthConstraint(11));
                
             String testtel="^\\(?([216]{3})\\)?([ .-]?)([0-9]{8})";
               val.addConstraint(numtel, new RegexConstraint(testtel, "Must be valid phone number"));
                        
        val.addConstraint(email, RegexConstraint.validEmail());

        
        
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
      
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                
                createLineSeparator(),
                new FloatingHint(numtel),
                createLineSeparator(),
                new FloatingHint(adresee),
                createLineSeparator(),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              //
               new AddMap(res, 1,"test").show();
            }
        });
    }
    
}
