package com.example.Backend.controller;

import com.example.Backend.dto.CartDto;
import com.example.Backend.entity.Cart;
import com.example.Backend.entity.CartItem;
import com.example.Backend.services.CartItemService;
import com.example.Backend.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/addProduct")
    public ResponseEntity <List<CartDto>> addProductToCart(@RequestBody CartDto cartDto,@RequestHeader("userId")int userId) {

        return new ResponseEntity<>(cartService.addProductToCart(cartDto,userId), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartDto>> getCartProducts(@PathVariable("userId") int userId) {

        return new ResponseEntity<>(cartService.getCartProducts(userId),HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<List<CartDto>> removeProductFromCart( @RequestHeader("userId") int userId, @PathVariable("productId") int productId) {

        return new ResponseEntity<>(cartService.removeProductFromCart(productId, userId),HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart( @RequestHeader("userId") int userId) {

        return new ResponseEntity<>(cartService.clearCart(userId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/total/{userId}")
    public ResponseEntity<Double> getCartTotal(@PathVariable("userId") int userId) {

        return new ResponseEntity<>(cartService.getCartTotal(userId),HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateQuantity/{productId}")
    public ResponseEntity<String> updateCartItemQuantity(@RequestHeader("userId") int userId, @PathVariable("productId") int productId, @RequestHeader("quantity") int quantity) {

        return new ResponseEntity<>(cartItemService.updateCartItemQuantity(userId,productId,quantity),HttpStatus.ACCEPTED);
    }


}