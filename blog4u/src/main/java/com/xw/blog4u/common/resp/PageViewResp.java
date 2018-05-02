package com.xw.blog4u.common.resp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author xuwei
 * @date 2018/5/2
 */
@Getter
@Setter
public class PageViewResp {
    private List<String> dates;
    private List<Integer> counts;
}
