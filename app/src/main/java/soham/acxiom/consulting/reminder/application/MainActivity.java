package soham.acxiom.consulting.reminder.application;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import soham.acxiom.consulting.reminder.application.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "test->MainAct";
    private ActivityMainBinding _binding;
    private NavController _navController;
    
    private MainViewModel _mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this._binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this._binding.getRoot());
        this._mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setNavHost();
    }


    private void setNavHost() {
        NavHostFragment navHost = (NavHostFragment)getSupportFragmentManager().findFragmentById(this._binding.navHost.getId());
        this._navController = navHost.getNavController();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return this._navController.navigateUp() || super.onSupportNavigateUp();
    }
}