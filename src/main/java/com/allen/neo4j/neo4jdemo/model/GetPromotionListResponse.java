package com.allen.neo4j.neo4jdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author : allengent@163.com
 * @date : 2019/10/17 14:38
 * description :
 */
@Data
public class GetPromotionListResponse {
    @JsonProperty("meta")
    private Pagination meta = null;

    @JsonProperty("items")
    private List<GetPromotionListResponseItems> items = null;
}