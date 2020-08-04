package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.MoneyCode;
import com.team45.net_mall.common.domain.MoneyCodeExample;
import com.team45.net_mall.mapper.MoneyCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
        return moneyCodes.size()<1?null:moneyCodes.get(0);
    }

    @Override
    public List<MoneyCode> list() {
        MoneyCodeExample moneyCodeExample = new MoneyCodeExample();
        return moneyCodeMapper.selectByExample(moneyCodeExample);
    }

    @Override
    public int delete(Integer id) {
        return moneyCodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(String code,Double money) {
        String md5Code = DigestUtils.md5DigestAsHex(code.getBytes());
        MoneyCode moneyCode = new MoneyCode();
        moneyCode.setStatus(1);
        moneyCode.setCode(code);
        moneyCode.setMd5(md5Code);
        moneyCode.setMoney(money);
        return moneyCodeMapper.insert(moneyCode);
    }
}
