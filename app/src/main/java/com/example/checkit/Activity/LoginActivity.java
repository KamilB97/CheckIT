package com.example.checkit.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkit.ConnectionClasses.ServerConnector;
import com.example.checkit.ConnectionClasses.fetchData;
import com.example.checkit.R;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmailView, mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEmailView= (EditText)(findViewById(R.id.editText_username));
        mPasswordView = (EditText)(findViewById(R.id.editText_pass));

        View loginButton = (View)(findViewById(R.id.loginButton));
        TextView signUp = (TextView)(findViewById(R.id.textView_enroll));
        final ServerConnector connector =new ServerConnector(this);




        final Intent mainIntent = new Intent(this, MainActivity.class);
        final Intent signInIntent = new Intent(this, RegisterActivity.class);


        boolean userValidation = false;



       loginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String mail = mEmailView.getText().toString();
               String password = mPasswordView.getText().toString();
               Log.d("KB","mail "+ mEmailView.getText().toString() + "pass "+ mPasswordView.getText().toString() );

              // connector.sendRequestAndGetResponse();
               fetchData process = new fetchData();
               process.execute();

               if (signInExistingUser(mail, password)){
                   startActivity(mainIntent);
               }
              // if(id.length()== 0 || pass.length() == 0){
                //   mEmailView.setError();

              // }
               // userValidation = serverConnection.validate(username,password);
               //Toast.makeText(getApplication().getBaseContext(),"username: "+ id + "password: "+ pass,Toast.LENGTH_SHORT ).show();


               }
           });

       signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               startActivity(signInIntent);

           }
       });



    }

    public boolean signInExistingUser(String mail, String password)   {
        // TODO: Call attemptLogin() here
        return attemptLogin(mail, password);
    }

    private boolean attemptLogin(String mail, String password) {

      boolean isEmailEmpty = TextUtils.isEmpty(mail);
      boolean isPasswordEmpty = TextUtils.isEmpty(password);
      boolean isFieldIncorrect = false;
        if(isEmailEmpty ) {
            mEmailView.setError("this field cannot be empty");
            isFieldIncorrect = true;
            }
        if(isPasswordEmpty){
            mPasswordView.setError("this field cannot be empty");
            isFieldIncorrect = true;
        }
        if(!mail.contains("@")){
            mEmailView.setError("Wrong email");
            isFieldIncorrect = true;
        }
        if(isFieldIncorrect){
            return false;
        }


        Toast.makeText(this,"Loggin in progress...",Toast.LENGTH_SHORT).show();

        //TODO: server verification

        // if user doesnt exist
        //showErrorDialog("There was a problem signing in");

        return true;
    }

    // TODO: Show error on screen with an alert dialog
//    private void showErrorDialog(String message){
//
//        new AlertDialog.Builder(this)
//                .setTitle("Oops")
//                .setMessage(message)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .setPositiveButton(android.R.string.ok,null)
//                .show();
//
//
//
  // }
}
