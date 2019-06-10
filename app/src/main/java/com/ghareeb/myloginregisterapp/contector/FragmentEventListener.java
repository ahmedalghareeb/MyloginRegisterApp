package com.ghareeb.myloginregisterapp.contector;

import com.ghareeb.myloginregisterapp.model.User;

public interface FragmentEventListener {

    void onUserAdded(User user);
    void onUserUpdated(User user, User newUser);
    void onUserListItemClicked(User user);
}
