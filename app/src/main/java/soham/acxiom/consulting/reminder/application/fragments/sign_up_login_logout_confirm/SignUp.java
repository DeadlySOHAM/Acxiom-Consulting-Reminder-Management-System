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
import soham.acxiom.consulting.reminder.application.databinding.FragmentSignUpBinding;

public class SignUp extends Fragment {

    private final String TAG = "test->SignUp";
    private FragmentSignUpBinding _binding;
    private MainViewModel _mainViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this._binding = FragmentSignUpBinding.inflate(inflater,container, false);
        this._binding.setFragmentSignUp(this);
        return this._binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._mainViewModel = new ViewModelProvider(this.getActivity()).get(MainViewModel.class);
    }

    public void signUp(){
        String username = this._binding.createUsernameInput.getText().toString(),
                password = this._binding.createPasswordInput.getText().toString();

        if(Pattern.compile(Constants.mailRegex).matcher(username).matches()){
            this._binding.createUsernameInputLayout.setError("Not email.");
            return;
        } else if(username.length()<1){
            this._binding.createUsernameInputLayout.setError("Cannot be empty.");
            return;
        }

        if(password.length()<7){
            this._binding.createPasswordInput.setError("Cannot be empty and atleast 6 characters");
            return;
        }

        Handler handler = new Handler(this.getActivity().getMainLooper());

        this._mainViewModel.auth.registerUserWithUsername(username,password,task -> {
            if(task.isSuccessful())
                handler.post(()->this.goToHomeScreen());
            else {
                Log.i(TAG, "Error : " + task.getException().getMessage());
                handler.post(()->this._binding.createPasswordInput.setError(task.getException().getMessage()));
            }
        });
    }

    public void goToLogout(){
        NavDirections action = SignUpDirections.actionSignUpToLogout();
        findNavController(this).navigate(action);
    }

    private void goToHomeScreen(){
        NavDirections action = SignUpDirections.actionSignUpToHome2();
        findNavController(this).navigate(action);
    }

}