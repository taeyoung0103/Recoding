package com.recoding.project.service;

import com.recoding.project.dto.MemberDTO;
import com.recoding.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//서비스는 @service라는 각각에 맞는 annotation들이 붙어줘야한다.
@Service
@RequiredArgsConstructor
// 생성자 주입 annotation이며, 이 annotation을 사용할때는 private final을 사용해야한다.
public class MemberService {
    private final MemberRepository memberRepository;

    public int join(MemberDTO memberDTO) {
        return memberRepository.join(memberDTO);
        //service클래스에 이 join메소드는 repository의 join을 호출해서 이를 연결하는 역할
    }


    public boolean login(MemberDTO memberDTO){
        MemberDTO loginMember = memberRepository.login(memberDTO);
        //repository로부터 로그인을 위한 쿼리를 수행하고, 결과를 dto객체로 받아오도록함.
        //이 결과가 null인지, 아닌지를 구분하고, 이게 null이면 false를 return
        if (loginMember != null) {
            return true;
        } else {
            return false;
        }
    }


    public List<MemberDTO> findAll() {
        return memberRepository.findAll();
    }

    public MemberDTO findById(Long id) {
        return memberRepository.findById(id);
    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public MemberDTO findByMemberEmail(String loginEmail) {
        return memberRepository.findByMemberEmail(loginEmail);
    }

    public boolean update(MemberDTO memberDTO) {
        int result = memberRepository.update(memberDTO);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String emailCheck(String memberEmail) {
        MemberDTO memberDTO = memberRepository.findByMemberEmail(memberEmail);
        //이메일을 입력했을때 입력한 이메일을 db에서 select문으로 조회해본다
        //조회 결과를 memberDto에 담는다.
        if (memberDTO == null) {
            return "ok";
        } else {
            return "no";
        }
    }

}
