package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.MoneyCode;

import java.util.List;

public interface MoneyCodeService {
    MoneyCode selectByMd5(String md5Code);

    List<MoneyCode> list(int pageNum,int pageSize);

    int delete(Integer id);

    int insert(String code,Double money);
}
