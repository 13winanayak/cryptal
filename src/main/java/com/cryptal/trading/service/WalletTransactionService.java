package com.cryptal.trading.service;

import com.cryptal.trading.domain.WalletTransactionType;
import com.cryptal.trading.model.Wallet;
import com.cryptal.trading.model.WalletTransaction;

import java.util.List;

public interface WalletTransactionService {

    WalletTransaction createTransaction(Wallet wallet,
                                        WalletTransactionType type,
                                        String transferId,
                                        String purpose,
                                        Long amount
    );

    List<WalletTransaction> getTransactions(Wallet wallet, WalletTransactionType type);
}
