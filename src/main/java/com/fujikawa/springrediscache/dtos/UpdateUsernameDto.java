package com.fujikawa.springrediscache.dtos;

public class UpdateUsernameDto {

    private String name;

    protected UpdateUsernameDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
