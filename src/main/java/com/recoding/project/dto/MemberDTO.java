package com.recoding.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
    private int no;
    private String id;
    private String password;
    private String name;
    private String email;

}
