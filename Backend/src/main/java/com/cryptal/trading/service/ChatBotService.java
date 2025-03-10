package com.cryptal.trading.service;

import com.cryptal.trading.model.CoinDto;
import com.cryptal.trading.response.ApiResponse;

public interface ChatBotService {

    ApiResponse getCoinDetails(String coinName);

    CoinDto getCoinByName(String coinName);

    String simpleChat(String prompt);
}
