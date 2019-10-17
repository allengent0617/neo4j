package com.allen.neo4j.neo4jdemo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/****
 *
 *
 *  用法示例:
 <code>


 Pagination pagination = Pagination.PageBuilder.start().setItemsPerPage(10).setPage(0).setRequestedPage(1).setItemsPerPage(100).addLinksItem(PaginationLinks.build(RelType.NEXT)).build();


 </code>
 *
 */
@Validated
@Builder
@Data
public class Pagination {

    /**
     * 请求的页码
     **/
    private int requestedPage;

    private int page;


    /**
     * 总页码数
     **/
    private int totalPage;


    /**
     * 每页条目数
     **/
    private int itemsPerPage;


    /**
     * 总条数
     **/
    private long totalCount;

    private List<PaginationLinks> links;

}

