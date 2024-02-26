package com.wishify.services;

import com.wishify.exception.UserException;
import com.wishify.exception.WishListException;
import com.wishify.model.User;
import com.wishify.model.WishListItem;
import com.wishify.repo.UserRepo;
import com.wishify.repo.WishListItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private WishListItemRepo wishListItemRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSignUpUser() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepo.save(user)).thenReturn(user);

        String result = userService.signUpUser(user);

        assertEquals("Congratulations !! you have Successfully Signup with registered email " + user.getEmail(), result);
    }

    @Test
    public void testAddWishListItem() {
        WishListItem item = new WishListItem();
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepo.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(wishListItemRepo.save(item)).thenReturn(item);

        WishListItem result = userService.addWishListItem(item, user.getEmail());

        assertEquals(item, result);
        assertEquals(user, item.getUser());
    }

    @Test
    public void testGetAllWishListNotEmpty() {
        List<WishListItem> mockWishList = new ArrayList<>();
        mockWishList.add(new WishListItem());

        when(wishListItemRepo.findAll()).thenReturn(mockWishList);

        List<WishListItem> result = userService.getAllWishList();

        assertEquals(mockWishList, result);
    }

    @Test
    public void testGetAllWishListEmpty() {
        when(wishListItemRepo.findAll()).thenReturn(new ArrayList<>());

        assertThrows(WishListException.class, () -> userService.getAllWishList());
    }

    @Test
    public void testGetUserDetailsByEmail() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepo.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User result = userService.getUserDetailsByEmail(user.getEmail());

        assertEquals(user, result);
    }

    @Test
    public void testGetUserDetailsByEmailNotFound() {
        String email = "nonexistent@example.com";

        when(userRepo.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> userService.getUserDetailsByEmail(email));
    }

    @Test
    public void testDeleteWishListById() {
        int id = 1;

        when(wishListItemRepo.findById(id)).thenReturn(Optional.of(new WishListItem()));

        String result = userService.deleteWishListById(id);

        assertEquals("Wish List Item Deleted with given Id " + id, result);
        verify(wishListItemRepo, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteWishListByIdNotFound() {
        int id = 1;

        when(wishListItemRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(WishListException.class, () -> userService.deleteWishListById(id));
    }
}
