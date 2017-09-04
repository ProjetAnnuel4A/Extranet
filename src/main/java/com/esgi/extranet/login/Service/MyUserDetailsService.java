package com.esgi.extranet.login.Service;

import com.esgi.extranet.login.UserEntity;
import com.esgi.extranet.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author timotheearnauld
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByPseudo(s);
        return new org.springframework.security.core.userdetails.User(userEntity.getPseudo(), userEntity.getPassword(), getAuthorities(userEntity));
    }

    private Set<GrantedAuthority> getAuthorities(UserEntity userEntity){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userEntity.getRole().toString());
        authorities.add(grantedAuthority);
        return authorities;
    }
}
