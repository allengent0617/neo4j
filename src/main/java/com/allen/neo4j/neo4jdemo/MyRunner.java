package com.allen.neo4j.neo4jdemo;

import com.allen.neo4j.neo4jdemo.service.PromotionNeo4jService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author : allengent@163.com
 * @date : 2019/10/17 10:29
 * description :
 */
@Component
public class MyRunner  implements CommandLineRunner{

    private  final PromotionNeo4jService promotionNeo4jService;

    public MyRunner(PromotionNeo4jService promotionNeo4jService) {
        this.promotionNeo4jService = promotionNeo4jService;
    }


    @Override
    public void run(String... args) throws Exception {

        /**
         *                               1
         *                   2           3          4
         *           5     6      7                  8
         *        9  10
         *
         *
         *  用户1 推荐用户 2 ，3， 4，  用户2推荐5，6，7
         */
        promotionNeo4jService.drop();
        promotionNeo4jService.addNode(1,0,1);
        promotionNeo4jService.addNode(2,1,2);
        promotionNeo4jService.addNode(3,1,2);
        promotionNeo4jService.addNode(4,1,2);
        promotionNeo4jService.addNode(5,2,3);
        promotionNeo4jService.addNode(6,2,3);
        promotionNeo4jService.addNode(7,2,3);
        promotionNeo4jService.addNode(8,4,3);
        promotionNeo4jService.addNode(9,5,4);
        promotionNeo4jService.addNode(10,5,4);


        promotionNeo4jService.addPromotion(1,0);
        promotionNeo4jService.addPromotion(2,1);
        promotionNeo4jService.addPromotion(3,1);
        promotionNeo4jService.addPromotion(4,1);
        promotionNeo4jService.addPromotion(5,2);
        promotionNeo4jService.addPromotion(6,2);
        promotionNeo4jService.addPromotion(7,2);
        promotionNeo4jService.addPromotion(8,4);
        promotionNeo4jService.addPromotion(9,5);
        promotionNeo4jService.addPromotion(10,5);



    }
}
