package com.rest.domain;

import org.springframework.data.domain.Sort;

public class PageRequest {

    private int page;
    private int size;
    private Sort.Direction direction;


    // 0보다 작은 페이지를 요청했을 경우 1페이지로 설정한다.
    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    // 요청 사이즈가 50보다 크면 기본 사이즈인 10으로 바인딩한다.
    public void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    // of() 메소드를 통해서 PageRequest 객체를 응답해준다. 페이지는 0부터 시작하니 -1한다.
    public org.springframework.data.domain.PageRequest of(){
        return org.springframework.data.domain.PageRequest.of(page -1, size, direction, "boardIdx");
    }
}
