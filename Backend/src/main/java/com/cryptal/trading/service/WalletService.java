package com.cryptal.trading.service;

import com.cryptal.trading.exception.WalletException;
import com.cryptal.trading.model.Order;
import com.cryptal.trading.model.User;
import com.cryptal.trading.model.Wallet;

public interface WalletService {

    Wallet getUserWallet(User user) throws WalletException;

    public Wallet addBalanceToWallet(Wallet wallet, Long money) throws WalletException;

    public Wallet findWalletById(Long id) throws WalletException;

    public Wallet walletToWalletTransfer(User sender,Wallet receiverWallet, Long amount) throws WalletException;

    public Wallet payOrderPayment(Order order, User user) throws WalletException;
}
