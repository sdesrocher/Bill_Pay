package css.cis3334.bill_pay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * Created by sdesrocher on 4/30/17
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    EditText etEmail, etPassword;
    Button btnLogin, btnCreate;
    private FirebaseAuth mAuth;

    /**
     * Basic onCreate to connect layout to Login page. connects email and password with widgets
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //connect with widgets in activity_login
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etPassword = (EditText) findViewById(R.id.editTextPassword);
        mAuth = FirebaseAuth.getInstance();  //declare object for Firebase
        setupCreateButton();
        setupLoginButton();

    }

    /**
     * setting up the login button. When clicked, it gets the email and password entered.
     */
    private void setupLoginButton() {
        // Set up the button to add a new fish using a seperate activity
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("CIS3334", "Signing in the user");
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                signIn(email, password);
            }
        });
    }

    /**
     * setting up create button. It creates account with email and password when clicked.
     */
    private void setupCreateButton() {
        // Set up the button to add a new fish using a seperate activity
        btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("CIS3334", "Creating a new user account");
                //create account for new users
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                createAccount(email,password);

                Toast.makeText(getApplicationContext(), "Account has been created", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    /**
     * signing in with the email and password. Error checks if wrong info entered. Produces a toast pop up if fails.
     * @param email -the email entered
     * @param password - the password entered
     */
    private void signIn(String email, String password){
        //sign in the recurrent user with email and password previously created.
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() { //add to listener
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) { //when failed
                    Toast.makeText(LoginActivity.this, "SignIn--Authentication failed.",Toast.LENGTH_LONG).show();
                } else {
                    //return to MainActivity if login works
                    finish();
                    //if signed in
                    Toast.makeText(getApplicationContext(), "You have been signed in", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }



    /**
     * creating the account if not recurrent user. Toast if creating account failed
     * @param email -email being used
     * @param password - password being used
     */
    private void createAccount(String email, String password) {
        //create account for new users
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {  //update listener.
                if (!task.isSuccessful()) { //when failed
                    Toast.makeText(LoginActivity.this, "createAccount--Authentication failed.",Toast.LENGTH_LONG).show();
                } else {
                    //return to MainActivity if login works
                    FirebaseUser user = mAuth.getCurrentUser();

                    finish();
                }
            }
        });
    }

}//end main



