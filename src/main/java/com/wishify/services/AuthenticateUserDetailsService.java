package com.wishify.services;

import com.wishify.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticateUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<com.wishify.model.User> opt = userRepo.findByEmail(username);

        if (opt.isPresent()) {

            com.wishify.model.User customer = opt.get();

            List<GrantedAuthority> authorities = new ArrayList<>();

            return new User(customer.getEmail(), customer.getPassword(), authorities);

        } else {
            throw new BadCredentialsException("User Details not found with this user name : " + username);
        }
    }
}
