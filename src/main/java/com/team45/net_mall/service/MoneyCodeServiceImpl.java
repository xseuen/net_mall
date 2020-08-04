package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.MoneyCode;
import com.team45.net_mall.common.domain.MoneyCodeExample;
import com.team45.net_mall.mapper.MoneyCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyCodeServiceImpl implements MoneyCodeService{
    @Autowired
    MoneyCodeMapper moneyCodeMapper;
    @Override
    public MoneyCode selectByMd5(String md5Code) {
        MoneyCodeExample moneyCodeExample = new MoneyCodeExample();
        moneyCodeExample.createCriteria().andMd5EqualTo(md5Code);
        List<MoneyCode> moneyCodes = moneyCodeMapper.selectByExample(moneyCodeExample);
        return moneyCodes.get(0);


    }
}
