package com.cryptal.trading.repository;

import com.cryptal.trading.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Coinrepository extends JpaRepository<Coin, String > {
}
