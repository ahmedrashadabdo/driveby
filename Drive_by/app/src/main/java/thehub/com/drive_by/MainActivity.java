package thehub.com.drive_by;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    TextView signUp;
    ImageView next;
    EditText username;
    String strusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_main);

        signUp =(TextView)findViewById(R.id.signUp);
        next = (ImageView)findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == R.id.next)
                {
                    username = (EditText)findViewById(R.id.username);
                    strusername = username.getText().toString();
                }

                Intent it = new Intent(MainActivity.this, Sign_In.class);
                startActivity(it);




            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,Sign_Up.class);
                startActivity(it);
            }
        });

    }
}
