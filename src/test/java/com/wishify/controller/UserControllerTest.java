package com.wishify.controller;

import com.wishify.model.User;
import com.wishify.model.WishListItem;
import com.wishify.repo.UserRepo;
import com.wishify.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSignUp() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userService.signUpUser(user)).thenReturn("User signed up successfully");

        ResponseEntity<String> response = userController.SingUp(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User signed up successfully", response.getBody());
    }

    @Test
    public void testLogIn() {
        User user = new User();
        user.setEmail("test@example.com");

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null);

        when(userService.getUserDetailsByEmail(user.getEmail())).thenReturn(user);

        ResponseEntity<String> response = userController.logIn(authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(" Logged In successfully ", response.getBody());
    }

    @Test
    public void testAddWishList() {
        User user = new User();
        user.setEmail("test@example.com");
        WishListItem item = new WishListItem(1, "I phone", 65000.00, "apple", user);
        String userEmail = "test@example.com";

        // Mock behavior
        when(userService.addWishListItem(item, userEmail)).thenReturn(item);

        // Perform the action
        ResponseEntity<WishListItem> response = userController.addWishList(item);

        // Verify the result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(item, response.getBody());

    }

    @Test
    public void testGetWishList() {
        List<WishListItem> wishList = new ArrayList<>();

        when(userService.getAllWishList()).thenReturn(wishList);

        ResponseEntity<List<WishListItem>> response = userController.getWishList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wishList, response.getBody());
    }

    @Test
    public void testDeleteWishList() {
        int id = 1;

        when(userService.deleteWishListById(id)).thenReturn("Wish list item deleted successfully");

        ResponseEntity<String> response = userController.deleteWishList(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Wish list item deleted successfully", response.getBody());
    }
}

