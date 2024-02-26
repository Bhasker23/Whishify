package com.wishify.services;

import com.wishify.model.User;
import com.wishify.model.WishListItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public String signUpUser(User user);

    public WishListItem addWishListItem(WishListItem item, String email);

    public List<WishListItem> getAllWishList();

    public User getUserDetailsByEmail(String email);

    public String deleteWishListById(Integer id);
}
