package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.MoneyCode;

public interface MoneyCodeService {
    MoneyCode selectByMd5(String md5Code);
}
