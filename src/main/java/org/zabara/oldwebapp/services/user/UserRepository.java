package org.zabara.oldwebapp.services.user;

import org.zabara.oldwebapp.domain.User;

import java.util.List;

/**
 * Created by Yaroslav on 06.07.2014.
 */
public interface UserRepository {

    public List<User> getUsers();

    public  User getUser(String name);
}
