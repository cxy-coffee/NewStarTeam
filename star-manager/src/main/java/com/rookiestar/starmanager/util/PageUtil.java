package com.rookiestar.starmanager.util;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/21
 */
public class PageUtil {
    public static int PAGE_SIZE = 5;
    private PageUtil(){}
    public static Pageable getPageable(int page,String...properties){
        return PageRequest.of(page,PAGE_SIZE, Sort.by(Sort.Direction.ASC,properties));
    }
}
