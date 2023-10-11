package soham.acxiom.consulting.reminder.application.fragments.sign_up_login_logout_confirm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import soham.acxiom.consulting.reminder.application.MainViewModel;
import soham.acxiom.consulting.reminder.application.databinding.FragmentLogoutBinding;

public class Logout extends Fragment {

    private final String TAG = "test->Logout";

    private FragmentLogoutBinding _binding;
    private MainViewModel _mainViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this._binding = FragmentLogoutBinding.inflate(inflater,container,false);
        return this._binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._mainViewModel = new ViewModelProvider(this.getActivity()).get(MainViewModel.class);
    }

}