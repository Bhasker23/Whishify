package com.wishify.services;

import com.wishify.model.User;
import com.wishify.model.WishListItem;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    public String SignUpUser(User user);

    public WishListItem addWishListItem(WishListItem item);

    public List<WishListItem> getAllWishList();

    public User getUserDetailsByEmail(String email);

    public String deleteWishListById(Integer id);
}
