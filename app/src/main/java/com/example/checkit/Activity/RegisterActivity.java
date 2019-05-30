package com.example.checkit.Activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.checkit.Data.User;
import com.example.checkit.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
//TODO uzupełnić !

    private EditText mNameView, mSurnameView, mAuthorityView,
            mEmailView, mPasswordView, mConfirmPasswordView;
    private Spinner mSpinner_index;
    private ImageView mImageView;
    private ConstraintLayout mainConstraintLayout;
    private String selectedAuthority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mNameView = (EditText) findViewById(R.id.editTtext_name);
        mSurnameView = (EditText) findViewById(R.id.editText_surname);
        mAuthorityView = (EditText) findViewById(R.id.editText_authority);
        mEmailView = (EditText) findViewById(R.id.editText_mail);
        mPasswordView = (EditText) findViewById(R.id.editText_pass);
        mConfirmPasswordView = (EditText) findViewById(R.id.editText_confirm_password);
        mImageView = (ImageView) findViewById(R.id.imageView2) ;
        mainConstraintLayout = (ConstraintLayout)findViewById(R.id.mainConstraintLayout) ;

        mSpinner_index = (Spinner)findViewById(R.id.spinner_authority);

        List<String> authorityList = new ArrayList<>();
        authorityList.add("Indeks");
        authorityList.add("Telefon");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,authorityList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner_index.setAdapter(adapter);

        mSpinner_index.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                getSelectedAuthority(view);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        View mRegisterButton = (View) (findViewById(R.id.cardView_register));


        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: ADD attempRegistration


               boolean registrationSuccesful = attemptRegistration();

               if (!registrationSuccesful){
                   Toast.makeText(getApplicationContext(),"Ups, something went wrong", Toast.LENGTH_SHORT).show();
               }


            }
        });




    }
    public void getSelectedAuthority(View v){
        String authority = (String) mSpinner_index.getSelectedItem();
        selectedAuthority = authority;
        if(authority.equals("Indeks")){
            mAuthorityView.setHint("Wpisz indeks");
        }else if (authority.equals("Telefon")){
            mAuthorityView.setHint("Wpisz numer Telefonu");
        }
        Toast.makeText(this,authority,Toast.LENGTH_SHORT).show();
    }

    private boolean attemptRegistration() {

        boolean isDataValid = true;
        // Reset errors displayed in the form
        mNameView.setError(null);
        mSurnameView.setError(null);
        mAuthorityView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mConfirmPasswordView.setError(null);

        String name = mNameView.getText().toString();
        String surname = mSurnameView.getText().toString();
        String authority = mAuthorityView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmPasswordView.getText().toString();

        // Check for a valid password, if the user entered one.

        if(!fieldsNotEmpty(name,surname,authority,email,password,confirmPassword )
                || !isEmailValid(email) || !isPasswordValid(password,confirmPassword)
                || !isAuthorityCorrect(selectedAuthority, authority) ){
            Toast.makeText(this,"Oups",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(this,"Przeszło",Toast.LENGTH_SHORT).show();
            // TODO: Call create User() here
           // boolean isUserCreated = createNewUser(name,surname,country,province,email,password);

            //if(!isUserCreated) return false;
            return true;
        }
    }

    private boolean isEmailValid(String email) {

        if(email.contains("@")){
            return true;
        }
        else{
            mEmailView.setError("This email is incorrect");
            return false;
        }
    }

    private boolean isPasswordValid(String password, String confirmPassword) {
        final Pattern [] inputRegexes = new Pattern[3];

            inputRegexes[0] = Pattern.compile(".*[A-Z].*");
            inputRegexes[1] = Pattern.compile(".*[a-z].*");
            inputRegexes[2] = Pattern.compile(".*[0-9].*");
        boolean isCorrect=false;
        if (password.length()>=7){
            if (password.equals(confirmPassword)){
                if(isMatchingRegex(password,inputRegexes)){
                    isCorrect=true;
                }else{
                    mPasswordView.setError("password should contain at least one low case letter," +
                            " one upper case letter and number and size higher than 7 characters");
                }
            }else {
                mConfirmPasswordView.setError("Password doesn't match");
            }
        }else{
            mPasswordView.setError("password is too short");
        }

        return isCorrect;

        //TODO: Add logic to check for a valid password

    }
    private static boolean isMatchingRegex(String input, Pattern[] inputRegexes) {
        boolean inputMatches = true;
        for (Pattern inputRegex : inputRegexes) {
            if (!inputRegex.matcher(input).matches()) {
                inputMatches = false;
            }
        }
        return inputMatches;
    }

    private boolean fieldsNotEmpty(String name,String  surname,String country,
                                  String email,String  password,String  confirmPassword){

        boolean areFieldsNotEmpty = true;
        if(TextUtils.isEmpty(name)){
            mNameView.setError("This field is required");
            areFieldsNotEmpty = false;
        }
        if(TextUtils.isEmpty(surname)){
            mSurnameView.setError("This field is required");
            areFieldsNotEmpty=false;
        }
        if(TextUtils.isEmpty(country)){
            mAuthorityView.setError("This field is required");
            areFieldsNotEmpty= false;
        }
        if (TextUtils.isEmpty(email)){
            mEmailView.setError("This field is required");
            areFieldsNotEmpty=false;
        }
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("This field is required and should have at least 7 characters");
            areFieldsNotEmpty = false;
        }
        if (TextUtils.isEmpty(confirmPassword)){
            mConfirmPasswordView.setError("This is required");
            areFieldsNotEmpty = false;
        }
        return areFieldsNotEmpty;

    }
    public boolean isAuthorityCorrect(String kindOfAuthority, String authority){
        if(kindOfAuthority.equals("Indeks studenta")){
            if (authority.length() == 6 ){
                return true;
            }else{
                mAuthorityView.setError("this field should contain 6 numbers");
                return false;}
        }
        else if (kindOfAuthority.equals("nr. telefonu")){
            if(authority.length() == 9){
                return true;
            }else {
                mAuthorityView.setError("this field should contain 9 numbers");
                return false;
            }

        }
        return false;
    }

    private User createNewUser(String name, String surname, String authority, String mail, String password){

        User user = new User(name,surname,authority,mail,password);
        return user;

    }

}
