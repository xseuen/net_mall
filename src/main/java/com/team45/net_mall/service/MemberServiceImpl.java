package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.common.domain.MemberExample;
import com.team45.net_mall.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;

    @Override
    public Member login(Member member) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andUsernameEqualTo(member.getUsername()).andPasswordEqualTo(member.getPassword());
        List<Member> members = memberMapper.selectByExample(memberExample);
        return (members.size()>0?members.get(0):null);
    }

    @Override
    public boolean selectByName(String name) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andUsernameEqualTo(name);
        List<Member> members = memberMapper.selectByExample(memberExample);
        return members.size()==0?true:false;
    }

    @Override
    public int insert(Member member) {
        return memberMapper.insert(member);
    }

    @Override
    public int update(Member member) {
        return memberMapper.updateByPrimaryKey(member);
    }
}
