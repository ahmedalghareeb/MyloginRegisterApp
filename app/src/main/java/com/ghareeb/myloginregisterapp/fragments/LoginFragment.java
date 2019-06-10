package com.ghareeb.myloginregisterapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ghareeb.myloginregisterapp.MainWindowActivity;
import com.ghareeb.myloginregisterapp.R;
import com.ghareeb.myloginregisterapp.contector.FragmentEventListener;
import com.ghareeb.myloginregisterapp.dao.UserDao;
import com.ghareeb.myloginregisterapp.dao.UserFactory;
import com.ghareeb.myloginregisterapp.model.User;

import java.util.ArrayList;

public class LoginFragment extends Fragment {


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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.login_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userDao = UserFactory.getUserDao();

        Button loginBtn = view.findViewById(R.id.fragmentLoginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailTextField = getView().findViewById(R.id.loginEditTextEmail);
                EditText passwordTextField = getView().findViewById(R.id.loginEditTextPassword);

                String email = emailTextField.getText().toString();
                String password = passwordTextField.getText().toString();

               // System.out.println(User.users.size());

                User user = userDao.getUserByEmail(email);
                if (user != null) {

                    if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                        System.out.println("User found");
                        Intent intent = new Intent(getView().getContext(), MainWindowActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }
                    else if (emailTextField.getText().toString()==null){
                        Toast.makeText(getContext(),"User is not found please register first",Toast.LENGTH_SHORT).show();
                        //System.out.println("user not found");
                    }
                }
            }
        });
    }
}
