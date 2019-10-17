package com.allen.neo4j.neo4jdemo.model;

import lombok.Data;

/**
 * @author : allengent@163.com
 * @date : 2019/10/17 14:39
 * description :
 */
@Data
public class GetPromotionListResponseItems {

    private   long uid;
    private  long promoterId;

    private   int level;
}
