package com.itwill.springboot2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagingDto {
    private int page;
    private int startPage;
    private int endPage;
    private int totPages;
    private int pagesInBar;
}
