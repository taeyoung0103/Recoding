package com.recoding.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
    private Long Id;
    private String memberPassword;
    private String memberName;
    private String memberPhonenumber;
    private String memberEmail;
    private int memberAge;

}
