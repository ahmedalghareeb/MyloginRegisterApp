package com.ghareeb.myloginregisterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ghareeb.myloginregisterapp.contector.FragmentEventListener;
import com.ghareeb.myloginregisterapp.dao.UserDao;
import com.ghareeb.myloginregisterapp.dao.UserFactory;
import com.ghareeb.myloginregisterapp.fragments.ListUsersFragment;
import com.ghareeb.myloginregisterapp.fragments.LoginFragment;
import com.ghareeb.myloginregisterapp.fragments.UpdateUserFragment;
import com.ghareeb.myloginregisterapp.model.User;

public class MainWindowActivity extends AppCompatActivity implements FragmentEventListener {

    private UserDao userDao;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);
        userDao = UserFactory.getUserDao();
        user = (User) getIntent().getExtras().getSerializable("user");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ListUsersFragment listUsersFragment= new ListUsersFragment();
        fragmentTransaction.add(R.id.mainContainer,listUsersFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onUserAdded(User user) {

    }



    @Override
    public void onUserUpdated(User user, User newUser) {
        if(userDao.updateUser(user, newUser)){
            System.out.println("User added successfully!");
        }else{
            System.out.println("Error adding user!");
        }
    }

    @Override
    public void onUserListItemClicked(User user) {
        Bundle bundle = new Bundle();
        bundle.putString("email", user.getEmail());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        UpdateUserFragment updateUserFragment= new UpdateUserFragment();
        updateUserFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.mainContainer,updateUserFragment);
        fragmentTransaction.commit();
    }
}
