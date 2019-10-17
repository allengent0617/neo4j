package com.allen.neo4j.neo4jdemo.repository;

/**
 * @author : allengent@163.com
 * @date : 2019/10/17 09:09
 * description :
 */

import com.allen.neo4j.neo4jdemo.entity.PromotionNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface PromotionNeo4jRepository extends Neo4jRepository<PromotionNode, Long> {


    //查询一个普通用户信息
    @Query("MATCH (cc:PromotionNode)where cc.uid={uid} RETURN cc")
    PromotionNode findPromotionNode(@Param("uid") long uid);

    //按照level 关键字 查询所有的 ----类似关系数据库。。。
    @Query("MATCH (cc:PromotionNode)where cc.level={level} RETURN  cc")
    List<PromotionNode> findPromotionLevelList(@Param("level") int  level);

    @Query("MATCH (cc:PromotionNode)where cc.level={level} RETURN  count(cc)")
    int findPromotionLevelCount(@Param("level") int  level);

    //添加普通用户
    @Query("create (n:PromotionNode{uid:{uid},promoterId:{promoterId},level:{level}}) RETURN n ")
    PromotionNode addNodeUser(@Param("uid") long uid, @Param("promoterId") long promoterId ,@Param("level") int level);

    //添加关系, 先定义2个节点的匹配规则（满足某个条件的2个节点），然后 创建这2个节点的关系   格式：  (e)-[]->(cc)  ,这里的e和cc是上面定义的2个变量  ,r:downline ,r 定义一个关系变量 ，downline是关系名称
    @Query("MATCH (e:PromotionNode),(cc:PromotionNode) WHERE e.uid ={promoterId} AND cc.uid={uid} CREATE (e)-[r:downline]->(cc) RETURN r")
    List<PromotionNode> addRelation(@Param("uid") long uid, @Param("promoterId") long promoterId);


    //查询下线总共人数
    @Query(value = "MATCH (na:PromotionNode{uid:{uid}})-[*1..1]->(nb:PromotionNode) where 1=1    return nb", countQuery = "MATCH (na:PromotionNode{uid:{uid}})-[*1..1]->(nb:PromotionNode)  return  count(nb)")
    Page<PromotionNode> getPromotionDirectList(@Param("uid") Long uid, Pageable pageable);

    @Query(value = "MATCH (na:PromotionNode{uid:{uid}})-[*1..1000]->(nb:PromotionNode) where 1=1 and nb.level>0   return nb", countQuery = "MATCH (na:PromotionNode{uid:{uid}})-[*1..1000]->(nb:PromotionNode)  return  count(nb)")
    Page<PromotionNode> getPromotionAllList(@Param("uid") Long uid,  Pageable pageable);

    //查询下线 包含孙子 总共人数 ,关系 最大 2000层？
    @Query("match (na:PromotionNode{uid:{uid}})-[*1..2000]->(nb:PromotionNode) return count(nb)")
    int sumRelationUser(@Param("uid") long uid);

    //查询直接下线总共人数  ，匹配关系  ()-[]->()
    //例12:关系深度匹配.匹配从n到m，任意关系，深度1到5的节点   MATCH p=(n)-[*1..5]->(m) RETURN p
    @Query("MATCH (na:PromotionNode{uid:{uid}})-[*1..1]->(nb:PromotionNode) return count(nb)")
    int sumDirectRelationUser(@Param("uid") long uid);


    //查询库里的总记录
    @Query("MATCH (n) RETURN  count(n)")
    int totalSum();

    @Query("MATCH (n:PromotionNode{uid:{uid}}),(n1:PromotionNode{uid:{promoterId}}), p=(n)-[]-(n1) return count(p)")
    int isExistRelationship(@Param("uid") long uid ,@Param("promoterId") long promoterId);






    //查询数据库总共人数
    // @Query(value = "MATCH (n) RETURN  n", countQuery = "MATCH (n) RETURN  count(n)")
    // int getAllPromotion(Pageable pageable);
    // Page<PromotionNode>   findAll();

}
