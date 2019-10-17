package com.allen.neo4j.neo4jdemo.controller;

import com.allen.neo4j.neo4jdemo.entity.PromotionNode;
import com.allen.neo4j.neo4jdemo.model.GetPromotionListResponse;
import com.allen.neo4j.neo4jdemo.model.GetPromotionListResponseItems;
import com.allen.neo4j.neo4jdemo.model.Pagination;
import com.allen.neo4j.neo4jdemo.service.PromotionNeo4jService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : allengent@163.com
 * @date : 2019/10/17 10:18
 * description :
 */
@RestController
public class PromotionApi {

    private  final PromotionNeo4jService promotionNeo4jService;


    public PromotionApi(PromotionNeo4jService promotionNeo4jService) {
        this.promotionNeo4jService = promotionNeo4jService;
    }

    @PostMapping("/add")
    public void addPro(long uid,long promoter)
    {
        promotionNeo4jService.addPromotion(uid,promoter);
    }


    @GetMapping("/getNode")
    public Object getOne(long uid)
    {
        return promotionNeo4jService.getNode(uid);

    }

    @GetMapping("/getPro")
    public Object getPro(long uid)
    {
        return promotionNeo4jService.getPromotion(uid);

    }

    @GetMapping("/getAll")
    public List getAll(long uid)
    {
       return promotionNeo4jService.getAllSubPromotionList(uid);

    }

    @GetMapping("/getPage")
    public GetPromotionListResponse getPage(long uid, Integer page, Integer pageSize) {

        if (page == null) {
            page = Integer.valueOf(1);
        }
        if (pageSize == null) {
            pageSize = Integer.valueOf(2);
        }

        //注意 排序 properties 要用到 @Query 里面定义的。。
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.Direction.ASC, "nb.level");


        Page<PromotionNode> pages = promotionNeo4jService.getAllSubPromotionListByPage(uid, pageRequest);
        GetPromotionListResponse response = new GetPromotionListResponse();

        List<GetPromotionListResponseItems> items = new ArrayList<>();

        for (PromotionNode pr : pages)
        {
            GetPromotionListResponseItems item=new GetPromotionListResponseItems();
            item.setUid(pr.getUid());
            item.setPromoterId(pr.getPromoterId());
            item.setLevel(pr.getLevel());
            items.add(item);

        }

        Pagination pagination = Pagination.builder()
                .itemsPerPage(pageSize)
                .page(pages.getNumber() )
                .requestedPage(pages.getNumber() + 1)
                .totalPage(pages.getTotalPages())
                .totalCount(pages.getTotalElements())
                .build();
        response.setMeta(pagination);

        response.setItems(items);


        return response;


    }


    @GetMapping("/getDi")
    public List getDirectSubPro(long uid)

    {
        return promotionNeo4jService.getDirectSubPromotionList(uid);

    }


    @GetMapping("/cntAll")
    public int countAll()
    {
        return promotionNeo4jService.countAll();

    }

    @GetMapping("/cnt")
    public int countByUid(long uid,boolean flag)
    {
        return promotionNeo4jService.countByRalation(uid,flag);

    }


    @GetMapping("/getLevel")
    public List getLevelList(int level)

    {
        return promotionNeo4jService.getLevelList(level);

    }


    @GetMapping("/has")
    public boolean hasRelation(long uid,long promoterId)

    {
        return promotionNeo4jService.hasRelation(uid,promoterId);

    }

}
