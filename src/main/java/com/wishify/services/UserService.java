package com.wishify.services;

import com.wishify.model.User;
import com.wishify.model.WishListItem;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    public String SignUpUser(User user);

    public WishListItem addWishListItem(WishListItem item);

    public User getUserDetailsByEmail(String email);
}
