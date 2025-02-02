package com.ecommenrce.project.serviceImpl;

import com.ecommenrce.project.model.Users;
import com.ecommenrce.project.repository.UserRepository;
import com.ecommenrce.project.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByName(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not found with username : " + username));

        return UserDetailsImpl.build(users);
    }
}
