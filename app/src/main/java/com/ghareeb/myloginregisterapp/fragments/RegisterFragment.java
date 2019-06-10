package com.ghareeb.myloginregisterapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ghareeb.myloginregisterapp.R;
import com.ghareeb.myloginregisterapp.contector.FragmentEventListener;
import com.ghareeb.myloginregisterapp.model.User;

public class RegisterFragment extends Fragment {

    private FragmentEventListener fragmentEventListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentEventListener = (FragmentEventListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button saveBtn = view.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getRootView().getWindowToken(), 0);

                EditText fNameTextField = getView().findViewById(R.id.editTextFName);
                EditText lNameTextField = getView().findViewById(R.id.editTextLName);
                EditText emailTextField = getView().findViewById(R.id.editTextEmail);
                EditText passwordTextField = getView().findViewById(R.id.editTextPassword);
                String fName = fNameTextField.getText().toString();
                String lName = lNameTextField.getText().toString();
                String email = emailTextField.getText().toString();
                String password = passwordTextField.getText().toString();

                fragmentEventListener.onUserAdded(new User(fName,lName,email,password));
            }
        });
    }
}
