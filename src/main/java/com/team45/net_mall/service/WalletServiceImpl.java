package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Wallet;
import com.team45.net_mall.mapper.WalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService{
     @Autowired
    WalletMapper walletMapper;
    @Override
    public Wallet getWalletByMid(Integer id) {
        return walletMapper.getWalletByMid(id);
    }

    @Override
    public int insertWallet(Wallet wallet) {
        return walletMapper.insert(wallet);
    }
}
