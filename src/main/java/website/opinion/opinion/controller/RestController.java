package website.opinion.opinion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import website.opinion.opinion.dao.UserDao;
import website.opinion.opinion.model.User;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(
            value = "/sign-up")
    @ResponseBody
    public ResponseEntity<Object> addUser(@RequestParam("username") String username,
                                          @RequestParam("password") String password, @RequestParam("email") String email)
    {
        //ResponseEntity<String>
        User n = new User();

        //check if user exists
        if (userDao.existsByUsername(username)){
            return ResponseEntity.ok("username_is_taken");
        }

        //check if user exists
        if (userDao.existsByEmail(email)){
            return ResponseEntity.ok("email_is_taken");
        }

        n.setUsername(username);
        n.setEmail(email);
        n.setRole("ROLE_USER");
        n.setPassword(passwordEncoder.encode(password));
        userDao.save(n);

        return ResponseEntity.ok("registered");

    }

    @PostMapping(
            value = "/sign-in")
    @ResponseBody
    public ResponseEntity<Object> signIn(@RequestParam("username") String username,
                                         @RequestParam("password") String password)
    {
        try {
            UsernamePasswordAuthenticationToken authReq
                    = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(authReq);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }
}
