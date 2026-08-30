package com.pm.medicalwebsite.security.user;

import com.pm.medicalwebsite.dao.UsersDao;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersDetailsServiceImpl implements UserDetailsService {

    private final UsersDao usersDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserCustomDetails(usersDao.findByEmail(username));
    }
}
