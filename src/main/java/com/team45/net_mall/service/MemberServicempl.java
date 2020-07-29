package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.common.domain.MemberExample;
import com.team45.net_mall.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberServicempl implements MemberService{
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Member login(Member member) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andUsernameEqualTo(member.getUsername()).andPasswordEqualTo(member.getPassword());
        //List<Member> members = MemberMapper.(memberExample);//selectByExample
        //List<Admin> admins = adminMapper.selectByExample(adminExample);
        List<Member> members=memberMapper.selectByExample(memberExample);
        return (members.size()>0?members.get(0):null);
    }

    @Override
    public boolean selectByName(String name) {
        MemberExample memberExample =new MemberExample();
        memberExample.createCriteria().andUsernameEqualTo(name);
        List<Member> members=memberMapper.selectByExample(memberExample);
        return members.size()==0?true:false;
    }

    @Override
    public int insert(Member member) {
        return memberMapper.insert(member);
    }
}
