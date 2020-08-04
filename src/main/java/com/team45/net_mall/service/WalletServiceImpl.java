package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.MoneyCode;
import com.team45.net_mall.common.domain.Wallet;
import com.team45.net_mall.common.domain.WalletExample;
import com.team45.net_mall.mapper.WalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class WalletServiceImpl implements WalletService{
     @Autowired
    WalletMapper walletMapper;
     @Autowired
     MoneyCodeService moneyCodeService;
    @Override
    public Wallet getWalletByMid(Integer id) {
        return walletMapper.getWalletByMid(id);
    }

    @Override
    public int insertWallet(Wallet wallet) {
        return walletMapper.insert(wallet);
    }

    @Override
    public String updateByUid(Integer id, String code) {
        String md5Code = DigestUtils.md5DigestAsHex(code.getBytes());
        MoneyCode moneyCode = moneyCodeService.selectByMd5(md5Code);
        if(moneyCode==null){
           return "充值码错误";
       }else {
          //充值码正确
           Wallet wallet = new Wallet();
           wallet.setBalance(moneyCode.getMoney());
           WalletExample walletExample = new WalletExample();
           walletExample.createCriteria().andIdEqualTo(id);
          int i = walletMapper.updateByExampleSelective(wallet,walletExample);
          if(i==1){
              return "充值成功";
          }else {
              return "充值失败，请联系管理员";
          }
       }

    }
}
