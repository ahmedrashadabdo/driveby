package thehub.com.drive_by;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_In extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    TextView  signUp;
    ImageView back , signIn;
    EditText password;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        back = (ImageView)findViewById(R.id.back);

        signUp = (TextView)findViewById(R.id.signUp);

        signIn = (ImageView)findViewById(R.id.signIn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(Sign_In.this,MainActivity.class);
                startActivity(it);
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Sign_In.this,Sign_Up.class);
                startActivity(it);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(v.getId() == R.id.signIn)
               {
                   password = (EditText)findViewById(R.id.password);
                   str = password.getText().toString();


                   String pass = helper.searchPass(str);
                   if(password.equals(pass))
                   {
                       Intent it = new Intent(Sign_In.this,MapsActivity.class);
                       startActivity(it);

                       Toast password = Toast.makeText(Sign_In.this , "Login Successful!", Toast.LENGTH_SHORT);
                       password.show();
                   }
                   else
                   {
                       Toast password = Toast.makeText(Sign_In.this , "Wrong Username or Password!", Toast.LENGTH_SHORT);
                       password.show();
                   }
               }


            }
        });
    }
}
