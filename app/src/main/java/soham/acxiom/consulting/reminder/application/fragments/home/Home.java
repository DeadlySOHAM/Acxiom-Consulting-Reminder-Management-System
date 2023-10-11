package soham.acxiom.consulting.reminder.application.fragments.home;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import soham.acxiom.consulting.reminder.application.MainViewModel;
import soham.acxiom.consulting.reminder.application.R;
import soham.acxiom.consulting.reminder.application.databinding.FragmentHomeBinding;

public class Home extends Fragment {

    private static final String TAG = "test->Home";
    private FragmentHomeBinding _binding;
    private MainViewModel _mainViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this._binding = FragmentHomeBinding.inflate(inflater, container, false);
        return this._binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._mainViewModel = new ViewModelProvider(this.getActivity()).get(MainViewModel.class);
        this.checkLoginStatus();
    }

    private void checkLoginStatus() {
        Log.i(TAG,"Checking login status");
        if( this._mainViewModel.auth.getCurrentUser() == null ) {
            goToLogin();
            return;
        }
        Log.i(TAG,"Logged in as "+ this._mainViewModel.auth.getCurrentUser().getEmail());
        this.showDetails();
    }

    private void goToLogin(){
        NavDirections action = HomeDirections.actionHome2ToLogin();
        findNavController(this).navigate(action);
    }

    private void showDetails(){
        String username = this._mainViewModel.auth.getCurrentUser().getEmail();
        username = username.substring(0,username.indexOf('@'));
        this._binding.welcomeUsernameView.setText(getString(R.string.welcome_message,username));

        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        String dayOfWeekString = new SimpleDateFormat("EEEE", Locale.getDefault())
                .format(calendar.getTime());

        String monthString = new SimpleDateFormat("MMMM", Locale.getDefault())
                .format(calendar.getTime());

        this._binding.todayTextView.setText(getString(R.string.date,dayOfWeekString,dayOfMonth,monthString,year));
    }
}