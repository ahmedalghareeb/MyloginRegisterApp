package com.ghareeb.myloginregisterapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.ghareeb.myloginregisterapp.R;
import com.ghareeb.myloginregisterapp.contector.FragmentEventListener;
import com.ghareeb.myloginregisterapp.dao.UserDao;
import com.ghareeb.myloginregisterapp.dao.UserFactory;
import com.ghareeb.myloginregisterapp.model.User;

import java.util.ArrayList;

public class ListUsersFragment extends ListFragment implements AdapterView.OnItemClickListener{

    UserDao userDao;
    FragmentEventListener fragmentEventListener;

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
        return inflater.inflate(R.layout.list_users_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<User> userArrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        userArrayAdapter.addAll(userDao.getAll());
        setListAdapter(userArrayAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        fragmentEventListener.onUserListItemClicked(userDao.getUserById(position));
    }
}
