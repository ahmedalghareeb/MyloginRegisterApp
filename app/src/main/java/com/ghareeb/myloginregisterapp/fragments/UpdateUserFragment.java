package com.ghareeb.myloginregisterapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.ghareeb.myloginregisterapp.dao.UserDao;
import com.ghareeb.myloginregisterapp.dao.UserFactory;
import com.ghareeb.myloginregisterapp.model.User;

public class UpdateUserFragment extends Fragment {

    private FragmentEventListener fragmentEventListener;
    UserDao userDao;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentEventListener = (FragmentEventListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDao = UserFactory.getUserDao();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.update_user_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button saveBtn = view.findViewById(R.id.fragmentUpdateBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fNameTextField = getView().findViewById(R.id.updateEditTextFName);
                EditText lNameTextField = getView().findViewById(R.id.updateEditTextLName);
                EditText emailTextField = getView().findViewById(R.id.updateEditTextEmail);
                EditText passwordTextField = getView().findViewById(R.id.updateEditTextPassword);
                String fName = fNameTextField.getText().toString();
                String lName = lNameTextField.getText().toString();
                String email = emailTextField.getText().toString();
                String password = passwordTextField.getText().toString();

                String oldEmail = getArguments().getString("email");
                System.out.println(oldEmail);

                User newUser = new User(fName,lName,email,password);
                Log.i("MIR", oldEmail);
                Log.i("MIR", userDao.getUserByEmail(oldEmail).toString());
//                User oldUser = new User("a", "a", "a", "a");
                User oldUser = userDao.getUserByEmail(oldEmail);

                System.out.println();
                System.out.println("user to be updated "+oldUser.toString());
                System.out.println("new user is: "+newUser.toString());

                fragmentEventListener.onUserUpdated(oldUser, newUser);


            }
        });
    }
}
