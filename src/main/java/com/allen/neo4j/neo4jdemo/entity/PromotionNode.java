package com.allen.neo4j.neo4jdemo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import javax.validation.constraints.NotNull;

/**
 * @author : allengent@163.com
 * @date : 2019/10/17 09:01
 * description : （好友）关系表（邀请人）
 */

@NodeEntity
@NoArgsConstructor
@Data
public class PromotionNode {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 用户id
     */
    @NotNull
    @Property(name = "uid")
    private Long uid;


    //邀请人ID
    @Property(name = "promoter_id")
    private long promoterId;


    /**
     * 所在层级
     */
    private int level;
    /**
     * 父id
     */

    @Property(name = "parent_id")
    private Long parentId;


}

