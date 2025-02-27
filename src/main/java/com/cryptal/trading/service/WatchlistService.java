package com.cryptal.trading.service;

import com.cryptal.trading.model.Coin;
import com.cryptal.trading.model.User;
import com.cryptal.trading.model.Watchlist;

public interface WatchlistService {

    Watchlist findUserWatchlist(Long userId) throws Exception;

    Watchlist createWatchList(User user);

    Watchlist findById(Long id) throws Exception;

    Coin addItemToWatchlist(Coin coin, User user) throws Exception;
}
