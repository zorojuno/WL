package com.WL.web.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;

//@Setter //Query String인 경우엔 항상 Setter 필요

@NoArgsConstructor //Setter 불필요 & 기본 생성자 필요
public class Info {
    private String name;
    private String id;
    public Info(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
}