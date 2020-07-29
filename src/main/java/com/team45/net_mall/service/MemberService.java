package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Member;

public interface MemberService {

    public Member login(Member member);

    boolean selectByName(String name);

    int insert(Member member);
}
