package com.allen.neo4j.neo4jdemo.model;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

/**
 * 链接信息
 */
@Validated
@Builder
public class PaginationLinks {

    private RelType rel;

    private String link;

}

