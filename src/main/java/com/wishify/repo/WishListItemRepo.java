package com.wishify.repo;

import com.wishify.model.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListItemRepo extends JpaRepository<WishListItem, Integer> {
}
