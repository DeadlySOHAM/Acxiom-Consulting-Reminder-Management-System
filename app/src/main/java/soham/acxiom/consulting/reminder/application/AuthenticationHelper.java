package soham.acxiom.consulting.reminder.application;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationHelper {

    private final String TAG = "test->Auth";
    private FirebaseAuth mAuth;

    public AuthenticationHelper() {
        mAuth = FirebaseAuth.getInstance();
    }


    public void registerUserWithUsername(String username, String password, OnCompleteListener<AuthResult> onCompleteListener) {

        // if(Pattern.compile(mailRegex).matcher(m).matches()){
        // }
        final String email = username + Constants.mailDomain;

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener);
    }


    public void signInWithUsername(String username, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        final String email = username + Constants.mailDomain;

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener);
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public void signOut() {
        mAuth.signOut();
    }
}

