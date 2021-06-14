package website.opinion.opinion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import website.opinion.opinion.dao.UserDao;
import website.opinion.opinion.model.User;
import website.opinion.opinion.security.UserDetailImpl;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //we use username since it ius unique
        User user = userDao.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("No user with email: " + email));
        return new UserDetailImpl(user);
    }
}
