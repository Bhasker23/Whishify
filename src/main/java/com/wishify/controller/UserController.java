package com.wishify.controller;

import com.wishify.model.User;
import com.wishify.model.WishListItem;
import com.wishify.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/hello")
    public String testHandler() {
        return "Welcome to Spring Security";
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> SingUp(@RequestBody User user){

        return new ResponseEntity<>(userService.SignUpUser(user), HttpStatus.CREATED);
    }

//    @GetMapping("/loginIn")
//    public ResponseEntity<String> logIn(Authentication auth){
//
//        User user = userService.getUserDetailsByEmail(auth.getName());
//
//        return  new ResponseEntity<>(" Logged In successfully ", HttpStatus.OK);
//    }

    @PostMapping("/addWishListItem")
    public ResponseEntity<WishListItem> addWishList(@RequestBody WishListItem item){

        return  new ResponseEntity<>(userService.addWishListItem(item),HttpStatus.CREATED);
    }

    @GetMapping("/getWishList")
    public ResponseEntity<List<WishListItem>> getWishList(){
        return  new ResponseEntity<>(userService.getAllWishList(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/wishList/{id}")
    public ResponseEntity<String> deleteWishList(@PathVariable Integer id){
        return new ResponseEntity<>(userService.deleteWishListById(id), HttpStatus.OK);
    }
}
