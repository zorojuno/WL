package com.WL.web.dto;

import lombok.NoArgsConstructor;

//@NoArgsConstructor
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