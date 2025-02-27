package com.cryptal.trading.service;

import com.cryptal.trading.model.PaymentDetails;
import com.cryptal.trading.model.User;

public interface PaymentDetailsService {

    public PaymentDetails addPaymentDetails(String accountNumber,
                                            String accountHolderName,
                                            String ifsc,
                                            String bankName,
                                            User user
    );

    public PaymentDetails getUsersPaymentDetails(User user);

}
