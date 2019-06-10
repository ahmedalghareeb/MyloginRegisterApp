package com.ghareeb.myloginregisterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ghareeb.myloginregisterapp.contector.FragmentEventListener;
import com.ghareeb.myloginregisterapp.dao.UserDao;
import com.ghareeb.myloginregisterapp.dao.UserFactory;
import com.ghareeb.myloginregisterapp.fragments.BuyAppFragment;
import com.ghareeb.myloginregisterapp.fragments.LoginFragment;
import com.ghareeb.myloginregisterapp.fragments.RegisterFragment;
import com.ghareeb.myloginregisterapp.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentEventListener {

    //ArrayList<User> users = new ArrayList<>();

    String REGISTER_FRAGMENT_TAG = "REGISTER_FRAGMENT";
    String LOGIN_FRAGMENT_TAG = "LOGIN_FRAGMENT";
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectButtons();

        userDao = UserFactory.getUserDao();
        userDao.addUser(new User("Ahmed","Ghareeb","test@gmail.com","a"));
        userDao.addUser(new User("user1","","user1@gmail.com","a"));
        userDao.addUser(new User("a","a","a","a"));

        launchAppFragmentPreference();

    }

    private void connectButtons() {

        Button registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterFragment();
            }
        });

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginFragment();
            }
        });

        Button resetPrefrencesBtn = findViewById(R.id.resetPreferencesBtn);
        resetPrefrencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                String key = "launchCounter";

                SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
                int value = sharedPreferences.getInt(key,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(key,0);
                editor.commit();
                Toast.makeText(context,"Preferences reseted successfully!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void launchAppFragmentPreference(){
        Context context = this;
        String key = "launchCounter";

        SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        int value = sharedPreferences.getInt(key,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //int intValue = Integer.valueOf(value);
        if (value == 0){
            showRegisterFragment();
            value++;
            editor.putInt(key,value);
        }else if (value > 0 && value < 2){
            showLoginFragment();
            value++;
            editor.putInt(key,value);
        }else{
            showBuyAppFragment();
        }
        editor.commit();

    }


    @Override
    public void onUserAdded(User user) {
        if(userDao.addUser(user)){
            System.out.println("User added successfully!");
        }else{
            Log.i("MIR", "ERROR ADDING USER!");
            System.out.println("Error adding user!");
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentManager.findFragmentByTag(REGISTER_FRAGMENT_TAG));
        fragmentTransaction.commit();
    }

    @Override
    public void onUserUpdated(User user, User newUser) {

    }

    @Override
    public void onUserListItemClicked(User user) {

    }

    public void showLoginFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.container,loginFragment);
        fragmentTransaction.commit();
    }

    private void showRegisterFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        RegisterFragment registerFragment = new RegisterFragment();
        fragmentTransaction.add(R.id.container,registerFragment,REGISTER_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    private void showBuyAppFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        BuyAppFragment buyAppFragment = new BuyAppFragment();
        fragmentTransaction.add(R.id.container,buyAppFragment);
        fragmentTransaction.commit();
    }
}
