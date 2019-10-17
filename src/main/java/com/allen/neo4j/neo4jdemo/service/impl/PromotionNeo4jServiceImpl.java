package com.allen.neo4j.neo4jdemo.service.impl;

import com.allen.neo4j.neo4jdemo.entity.PromotionNode;
import com.allen.neo4j.neo4jdemo.repository.PromotionNeo4jRepository;
import com.allen.neo4j.neo4jdemo.service.PromotionNeo4jService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : allengent@163.com
 * @date : 2019/10/17 10:15
 * description :
 */
@Service
public class PromotionNeo4jServiceImpl implements PromotionNeo4jService {

    private  final PromotionNeo4jRepository promotionNeo4jRepository;

    public PromotionNeo4jServiceImpl(PromotionNeo4jRepository promotionNeo4jRepository) {
        this.promotionNeo4jRepository = promotionNeo4jRepository;
    }


    @Override
    public PromotionNode getPromotion(long userId) {

        return null;
    }

    @Override
    public PromotionNode getNode(long userId) {
        return promotionNeo4jRepository.findPromotionNode(userId);
    }

    @Override
    public PromotionNode addNode(long userId, long promoter,int level) {
        promotionNeo4jRepository.addNodeUser(userId,promoter, level);
        return null;
    }

    @Override
    public PromotionNode addPromotion(long userId, long promoter) {
        promotionNeo4jRepository.addRelation(userId,promoter);
        return null;
    }

    @Override
    public List<PromotionNode> getDirectSubPromotionList(long userId) {
        return null;
    }

    @Override
    public List<PromotionNode> getAllSubPromotionList(long userId) {
        return null;
    }

    @Override
    public Page<PromotionNode> getAllSubPromotionListByPage(long userId, Pageable pageable) {
        return promotionNeo4jRepository.getPromotionAllList(userId,pageable);
    }

    @Override
    public int getDirectSubPromotionCount(long userId) {
        return promotionNeo4jRepository.sumDirectRelationUser(userId);
    }

    @Override
    public int getAllSubPromotionCount(long userId) {
        return promotionNeo4jRepository.sumRelationUser(userId);
    }

    @Override
    public int countAll() {

        return promotionNeo4jRepository.totalSum();
    }

    @Override
    public void drop() {
        promotionNeo4jRepository.deleteAll();
    }

    @Override
    public List getLevelList(int level) {
        return promotionNeo4jRepository.findPromotionLevelList(level);
    }

    @Override
    public boolean hasRelation(long uid, long promoterId) {

        int cnt= promotionNeo4jRepository.isExistRelationship(uid,promoterId);
        return cnt==0?false:true;
    }

    @Override
    public int countByRalation(long uid, boolean flag) {
        /**
         * 如果是true，则查询 直接 下属 ，否则查询所有 下属，包含孙子节点。。
         */
        if (flag)
        {
            return promotionNeo4jRepository.sumDirectRelationUser(uid);
        }

        return promotionNeo4jRepository.sumRelationUser(uid);
    }
}
