package thehub.com.drive_by;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Sign_Up extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    ImageView back , signUp;

    EditText username, email, pass1 , pass2 , mobile;
    String usernamestr, emailstr, mobilestr , pass1str, pass2str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        back = (ImageView)findViewById(R.id.back);
        signUp = (ImageView)findViewById(R.id.signUp);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(Sign_Up.this,MainActivity.class);
                startActivity(it);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.signUp) {
                     username = (EditText)findViewById(R.id.edtusername);
                     email = (EditText)findViewById(R.id.edtemail);
                     pass1 = (EditText)findViewById(R.id.edtpass2);
                     pass2 = (EditText)findViewById(R.id.edtpass2);
                     mobile = (EditText)findViewById(R.id.edtmobile);

                    usernamestr  = username.getText().toString();
                    emailstr = email.getText().toString();
                    mobilestr = mobile.getText().toString();
                    pass1str = pass1.getText().toString();
                    pass2str = pass2.getText().toString();


                    if(!pass1str.equals(pass2str))
                    {
                        //echo msg
                        Toast password = Toast.makeText(Sign_Up.this , "Passwords don't match!", Toast.LENGTH_SHORT);
                        password.show();
                    }
                    else
                    {
                        //insert details into the database
                        User u = new User();
                        u.setUsername(usernamestr);
                        u.setEmail(emailstr);
                        u.setPassword(pass1str);
                        u.setMobile(mobilestr);

                        helper.insertUser(u);

                        Intent it= new Intent(Sign_Up.this,MapsActivity.class);
                        startActivity(it);

                        //echo msg
                        Toast password = Toast.makeText(Sign_Up.this , "Signed Up Successfully!", Toast.LENGTH_SHORT);
                        password.show();

                    }

                }


            }
        });
    }
}
