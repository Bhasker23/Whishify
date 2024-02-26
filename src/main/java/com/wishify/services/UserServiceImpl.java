package com.wishify.services;

import com.wishify.exception.UserException;
import com.wishify.exception.WishListException;
import com.wishify.model.User;
import com.wishify.model.WishListItem;
import com.wishify.repo.UserRepo;
import com.wishify.repo.WishListItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final WishListItemRepo wishListItemRepo;

    @Override
    public String signUpUser(User user) {
        userRepo.save(user);
        return "Congratulations !! you have Successfully Signup with registered email " + user.getEmail();
    }

    @Override
    public WishListItem addWishListItem(WishListItem item, String email) {

        User user =  userRepo.findByEmail(email).get();
        item.setUser(user);
        return wishListItemRepo.save(item);
    }

    @Override
    public List<WishListItem> getAllWishList() {

        List<WishListItem> list = wishListItemRepo.findAll();
        if (list.isEmpty()) {
            throw new WishListException("OPPS !! Wish List empty.");
        } else {
            return list;
        }

    }

    @Override
    public User getUserDetailsByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new UserException("Customer not found with given Email :" + email));
    }

    @Override
    public String deleteWishListById(Integer id) {
        if (wishListItemRepo.findById(id).isPresent()) {
            wishListItemRepo.deleteById(id);
        } else {
            throw new WishListException("No item found with given id " + id);
        }
        return "Wish List Item Deleted with given Id " + id;
    }
}
