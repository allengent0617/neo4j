package com.allen.neo4j.neo4jdemo.service;

import com.allen.neo4j.neo4jdemo.entity.PromotionNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : allengent@163.com
 * @date : 2019/10/17 10:10
 * description :
 */
public interface PromotionNeo4jService {

    PromotionNode getPromotion(long userId);

    PromotionNode getNode(long userId);


    PromotionNode addNode(long userId,long promoter,int level);

    PromotionNode addPromotion(long userId,long promoter);


    List<PromotionNode>  getDirectSubPromotionList(long userId);


    List<PromotionNode>  getAllSubPromotionList(long userId);


    Page<PromotionNode> getAllSubPromotionListByPage(long userId, Pageable pageable );


    int  getDirectSubPromotionCount(long userId);

    int  getAllSubPromotionCount(long userId);

    int  countAll();

    void drop();


    List getLevelList(int level);

    boolean hasRelation(long uid, long promoterId);

    int countByRalation(long uid, boolean flag);
}
