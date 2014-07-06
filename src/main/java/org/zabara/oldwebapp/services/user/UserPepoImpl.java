package org.zabara.oldwebapp.services.user;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.apache.log4j.Logger;
import org.zabara.oldwebapp.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Yaroslav on 06.07.2014.
 */
public class UserPepoImpl implements UserRepository {

    private static UserPepoImpl instance = null;
    private static List<User> users;
    private static final Logger logger = Logger.getLogger(UserPepoImpl.class.getName());

    static {
        instance = new UserPepoImpl();
    }

    private UserPepoImpl() {
        users = new ArrayList<User>();
        users.add(new User(1, "user", "user"));
        logger.info("UserPepoImpl was initialized");
    }

    public static UserPepoImpl getInstance() {
        if (instance == null) {
            instance = new UserPepoImpl();
        }
        return instance;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUser(final String name) {
        try {
            Predicate<User> filter = new Predicate<User>() {
                @Override
                public boolean apply(User group) {
                    return group.getName().equals(name);
                }
            };

            return Iterables.find(users, filter);
        } catch (NoSuchElementException ex) {
            return null;
        }
    }
}
