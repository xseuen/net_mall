package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Wallet;

public interface WalletService {

    Wallet getWalletByMid(Integer id);
    int insertWallet(Wallet wallet);

    String  updateByUid(Integer id, String code);
}
