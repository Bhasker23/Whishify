package com.wishify.services;

import com.wishify.exception.UserException;
import com.wishify.model.User;
import com.wishify.model.WishListItem;
import com.wishify.repo.UserRepo;
import com.wishify.repo.WishListItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    private final WishListItemRepo wishListItemRepo;
    @Override
    public String SignUpUser(User user) {
        userRepo.save(user);
        return "Congratulations !! you have Successfully Signup with registered email " + user.getEmail();
    }

    @Override
    public WishListItem addWishListItem(WishListItem item) {
        System.out.println("item");
        return wishListItemRepo.save(item);

    }

    @Override
    public User getUserDetailsByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new UserException("Customer not found with given Email :" + email));
    }
}
