package com.zaytsevp.authservice.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Pavel Zaytsev
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return username.equals("admin") ? new User("admin", "123456", AuthorityUtils.createAuthorityList("ROLE_ADMIN"))
                                        : new User("user", "123456", AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
