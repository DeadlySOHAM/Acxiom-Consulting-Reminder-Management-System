package soham.acxiom.consulting.reminder.application.fragments.sign_up_login_logout_confirm;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;

import java.util.regex.Pattern;

import soham.acxiom.consulting.reminder.application.Constants;
import soham.acxiom.consulting.reminder.application.MainViewModel;
import soham.acxiom.consulting.reminder.application.databinding.FragmentLoginBinding;

public class Login extends Fragment {

    private final String TAG = "test->Login";

    private FragmentLoginBinding _binding;
    private MainViewModel _mainViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this._binding = FragmentLoginBinding.inflate(inflater,container,false);
        this._binding.setFragmentLogIn(this);
        return this._binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._mainViewModel = new ViewModelProvider(this.getActivity()).get(MainViewModel.class);
    }

    public void goToLogOut(){
        NavDirections action = LoginDirections.actionLoginToLogout();
        findNavController(this).navigate(action);
    }

    public void goToSignUp(){
        NavDirections action = LoginDirections.actionLoginToSignUp();
        findNavController(this).navigate(action);
    }

    public void login(){

        this._binding.errorTextView.setVisibility(View.GONE);

        String username = this._binding.usernameInput.getText().toString(),
                password = this._binding.passwordInput.getText().toString();

        if(Pattern.compile(Constants.mailRegex).matcher(username).matches()){
            this._binding.createUsernameInputLayout.setError("Not email.");
            return;
        } else if(username.length()<1){
            this._binding.createUsernameInputLayout.setError("Cannot be empty.");
            return;
        }

        if(password.length()<1){
            this._binding.passwordInput.setError("Cannot be empty.");
            return;
        }

        Handler handler = new Handler(this.getActivity().getMainLooper());

        this._mainViewModel.auth.signInWithUsername(username,password,task -> {
            if(task.isSuccessful())
                handler.post(()->this.goToHome());
            else {
                Log.i(TAG, "Error : " + task.getException().getMessage());
                handler.post(()-> {
                    this._binding.errorTextView.setVisibility(View.VISIBLE);
                    this._binding.errorTextView.setText(task.getException().getMessage());
                });
            }
        });
    }

    public void goToHome(){
        NavDirections action = LoginDirections.actionLoginToHome2();
        findNavController(this).navigate(action);
    }

}