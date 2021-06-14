package website.opinion.opinion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import website.opinion.opinion.dao.UserDao;
import website.opinion.opinion.model.User;

import java.util.Optional;

@Service
public class SecurityUtil {
    @Autowired
    private UserDao userDao;

    //get logged in user from security context
    public User getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        UserDetails loggedInUser;
        try {
            loggedInUser = (UserDetails) principal;
        }
        catch (Exception e){
            return null;
        }

        Optional<User> optionalUser = userDao.findByEmail(loggedInUser.getUsername());
        return optionalUser.orElse(null);
    }
}
