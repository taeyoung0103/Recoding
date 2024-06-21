package com.recoding.project.controller;

import com.recoding.project.dto.MemberDTO;
import com.recoding.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member")//@RequestMapping("/member")는 이 컨트롤러의 기본 URL 경로를 /member로 설정
@RequiredArgsConstructor //객체를 사용할때, 의존성을 주입받아야하는데, 이를 위해서 이 annotation사용
public class MemberController {
    private final MemberService memberService;
    //이 방식으로 의존성을 주입받고, 서비스 클래스를 이용하도록

    @GetMapping("/join")
    public String saveForm(){
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberDTO memberDTO){
        int saveResult = memberService.join(memberDTO);
        if (saveResult > 0) {
            return "login";
        } else {
            return "join";
        }
        //저장한 정보를 int타입으로 return받고, return받은 값이 0보다 큰지, 작은지를 비교후 결정
    }

    @GetMapping("/login")
    public String loginform() {
        return "login"; //로그인페이지를 띄우는 메소드
    }
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        //post형식은 데이터를 가져와서 전송하는것이므로 @ModelAttribute 사용
        // @ModelAttribute = HTTP 요청 파라미터를 바인딩하여 모델 객체로 변환하고, 이를 컨트롤러 메서드에서 사용할 수 있도록 하는 도구
        //로그인처리하는 메소드
        //성공실패에 따라서 처리해야하는데, 로그인성공후에는 내 정보가 계속 따라다녀야한다.
        //그걸 위해서 session을 활용해야한다, 매개변수에 session을 받는 이유
        boolean loginResult = memberService.login(memberDTO);
        //이거는 memberMapper에서 id=login을 돌렸을때 결과값이 있는지 없는지를 체크하는것
        //
        if(loginResult) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            session.setAttribute("loginName", memberDTO.getMemberName());
            return "main";
            //session에 login한 사용자의 계정정보를 입력하도록 설정, 그 후에 main.jsp로 이동
        } else {
            return "login";
        }
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "list";
        //리스트같은 경우는 데이터를 가지고가는 부분이기 때문에 모두의 객체를 활용한다.
        //service의 findall메소드를 호출해서, 결과를 "memberlist"에 담아서
        //list.jsp로 가도록한다.
    }

    @GetMapping
    public String findById(@RequestParam("id") Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @GetMapping("/delete")
    //링크방식이기 때문에 get방식을 사용한다.
    //requestparam을 사용해서 삭제처리를 수행하고
    public String delete(@RequestParam("id") Long id) {
        memberService.delete(id);
        return "redirect:/member/";
        //memberService.delete(id); 를 수행하고, 그 밑에 리스트를 다시 재요청 한다는 의미이다.
    }

    //수정화면 요청
    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) {
        //세션에 저장된 나의 이메일 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");
        //loginEmail이라는 이름의 파라미터를 가져온다.
        //가져올때 받으려는건 string인데, loginemail이라는 이름의 파라미터는 object구조이다.
        //object구조가 string보다 더 큰 구조이므로, 앞에 형변환을 시켜주게끔 (string)을 추가해준다.
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        //findbymemberemail이라는 email로 조회하는 메소드를 하나 생성한다음 그걸로 찾아서 값을 쭉 가져온다.
        model.addAttribute("member", memberDTO);
        return "update";
    }

    //수정처리
    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        boolean result = memberService.update(memberDTO);
        if (result) {
            return "redirect:/member?id=" + memberDTO.getId();
        } else {
            return "index";
        }
    }
    //boolean result로 값이 있는지 없는지를 확인해서 있으면 redirect, 재요청으로 페이지를 열고,
    //없으면 index페이지로 가게끔 만든다.
    //redirect사용할때는 주소값이 필요한데, 아이디값을 필요로하는걸로 설정했고, 그 id값은
    //dto에 있는 id값을 가져오게끔

    @PostMapping("/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        //ajax를 처리할때는 responsebody나 responseentity를 추가해줘야한다.
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
        //db에서 체크한 결과를 string값으로 받아와서 그 string값을 return한다.
    }

}
