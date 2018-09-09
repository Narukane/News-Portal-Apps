package io.github.narukane.portal;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
    DatabaseHelper db;
    private Button loginPage,Regis;
    private TextInputLayout e1,p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        loginPage=(Button) findViewById(R.id.log);
        Regis=(Button) findViewById(R.id.reg);
        e1 = findViewById(R.id.email2);
        p1 = findViewById(R.id.pass2);


        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageLogin();
            }
        });
    }
    private boolean validasi_email(){
        String checkemail = e1.getEditText().getText().toString().trim();
        if(checkemail.isEmpty()){
            e1.setError("Field can't be empty");
            return false;
        }else{
            e1.setError(null);
            return true;
        }
    }

    private boolean validasi_password(){
        String checkpass = p1.getEditText().getText().toString().trim();
        if(checkpass.isEmpty()){
            p1.setError("Field can't be empty");
            return false;
        }else{
            p1.setError(null);
            return true;
        }
    }
    public void daftar(View v){
        String mail = e1.getEditText().getText().toString().trim();
        String pass = p1.getEditText().getText().toString().trim();
        if (!validasi_email()|!validasi_password()){
            return;
        }else{
            Boolean cek_mail = db.check_email(mail);
            if(cek_mail==true){
                Boolean insert = db.insert(mail,pass);
                if(insert==true){
                    Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                    openPageLogin();
                }
            }else{
                Toast.makeText(getApplicationContext(),"Email Already Exists",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openPageLogin(){
        Intent intent =  new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }


}
