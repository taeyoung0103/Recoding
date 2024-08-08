package com.recoding.project.repository;

import com.recoding.project.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    // 이 repository는 DB와 연관되있다.
    private final SqlSessionTemplate sql; //sqlsessiontemplate이라는 세션클래스를 주입받아야한다.
    public int join(MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        return sql.insert("Member.join", memberDTO);
        //넘겨받는 memberDTO를 memberMapper.xml에 Member라는 namespace를 가진것중에
        //id가 save인 부분을 호출하면서 넘긴다.
    }

    public MemberDTO login(MemberDTO memberDTO) {
        return sql.selectOne("Member.login", memberDTO);
        //return sql.selectList("Member.login");
        //mapper에서 id=login을 호출하도록하고, DTO객체를 넘겨준다.
        //selectone은 조회시에 값이 하나일경우 dto타입으로 넘기겠다.
        //조회결과가 여러개일경우에는 500에러가 나올수있다.
    }

    public List<MemberDTO> findAll() {
        return sql.selectList("Member.findAll");
        //selectone과 다르게 값을 여러개를 가져와야함으로 selectlist를 사용
        //mapper에 id=findall을 호출하겠다.
    }

    public MemberDTO findById(Long id) {
        return sql.selectOne("Member.findById", id);
    }

    public void delete(Long id) {
        sql.delete(("Member.delete"), id);
        // 매개변수값 id를 보내준다.
    }

    public MemberDTO findByMemberEmail(String id) {
        return sql.selectOne("Member.findByMemberEmail", id);
        //mapper에 findbymemberemail이라는 id값을 정의하고, loginemail값을 넘겨준다.
    }

    public int update(MemberDTO memberDTO) {
        return sql.update("Member.update", memberDTO);
        // update라는 메소드는 int타입의 return을 주는데, sql문이 돌았을때 처리된 개수를 알려주는것
        // 1이상이면 정상적으로 작동했다고 판단하고, 0이면 작동이 안됐다고 판단한다.
        // 그래서 memberservice에서도 int result으로 받는것이다.
        //update뿐 아니라 insert나 delete도 마찬가지이다.
    }

    public MemberDTO bringId(String id) {
        return sql.selectOne("Member.bringid", id);
    }
}
