

package Gui;


import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Validator;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint ;
import Service.AuthentificationUser;

/**
 * 
 *
 * @author mahmoud
 */
public class SignInForm extends BaseForm {
    
    
    AuthentificationUser auth = new AuthentificationUser();
    public static TextField username,password;
    public static Resources res;
    public SignInForm(Resources res) {
        super(new BorderLayout());
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("ContainerWithPadding");
        center.getUnselectedStyle().setBgColor(654111);
        
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        username = new TextField("", "Email", 20, TextField.ANY);
        password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(true);
        password.setSingleLineTextArea(true);
        
        username.getUnselectedStyle().setFgColor(621200);
        password.getUnselectedStyle().setFgColor(251340);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");

        signIn.getUnselectedStyle().setFgColor(100000);
        signUp.getUnselectedStyle().setFgColor(100000);

        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        doneHaveAnAccount.getUnselectedStyle().setFgColor(0xff0000);
        Validator val = new Validator();

 
       val.addConstraint(password, new LengthConstraint(4));
       val.addConstraint(username,RegexConstraint.validEmail());

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        //e -> new NewsfeedForm(res).show()
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              auth.login(res);//verifier login

            }
        });
    }
    
}
