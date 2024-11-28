package com.itachialy.moji_store.repository;

import com.itachialy.moji_store.model.Cart;
import com.itachialy.moji_store.model.CartItem;
import com.itachialy.moji_store.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("select i from CartItem i where i.cart = :cart order by i.updated_at desc")
    List<CartItem> getCartItemsByCartOrderByUpdated_atDesc(@Param("cart") Cart cart);
    List<CartItem> getCartItemsByCart(Cart cart);
    void deleteAllByCart(Cart cart);
}
