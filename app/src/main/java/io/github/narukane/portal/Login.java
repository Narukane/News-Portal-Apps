package io.github.narukane.portal;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login extends Activity {
    private Button regis_page,login;
    private TextInputLayout e,p;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regis_page = (Button) findViewById(R.id.regis);
        login = (Button) findViewById(R.id.loginbtn);
        e = findViewById(R.id.email);
        p = findViewById(R.id.password);
        db = new DatabaseHelper(this);

        regis_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }

    private boolean validasi_email(){
        String checkemail = e.getEditText().getText().toString().trim();
        if(checkemail.isEmpty()){
            e.setError("Field can't be empty");
            return false;
        }else{
            e.setError(null);
            return true;
        }
    }

    private boolean validasi_password(){
        String checkpass = p.getEditText().getText().toString().trim();
        if(checkpass.isEmpty()){
            p.setError("Field can't be empty");
            return false;
        }else{
            p.setError(null);
            return true;
        }
    }
    public void masuk(View v){
        String mail = e.getEditText().getText().toString().trim();
        String pass = p.getEditText().getText().toString().trim();
        if (!validasi_email()|!validasi_password()){
            return;
        }else{
            Boolean login = db.login_user(mail,pass);
            if(login==true){
                Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                openNews();
            }else{
                Toast.makeText(getApplicationContext(),"Login Failed, Email or Password is wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }

    public void openNews(){
        Intent intent = new Intent(this, News.class);
        startActivity(intent);
    }
    boolean twice;
    @Override
    public void onBackPressed(){


        if(twice==true){
            finish();
        }
        twice=true;

        Toast.makeText(Login.this,"Press BACK again to Exit",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice=false;

            }
        },3000);

    }
}
