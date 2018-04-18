/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.dao.impl;

import net.tychecash.explorer.dao.LastBlockInfoDao;
import net.tychecash.explorer.model.Block;
import net.tychecash.explorer.model.LastBlockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jithin
 */
@Repository
public class LastBlockInfoDaoImpl implements LastBlockInfoDao {

    @Autowired
    MongoTemplate mongoTemplate;

    final String COLLECTION = "lastblockinfo";

    @Override
    public void createBlock(LastBlockInfo block) {
        mongoTemplate.insert(block);
    }

    @Override
    public void updateBlock(LastBlockInfo block) {
        mongoTemplate.save(block);
    }

    @Override
    public LastBlockInfo findBlock(LastBlockInfo block) {
        Query query = new Query(Criteria.where("_id").is(block.getId()));
        return mongoTemplate.findOne(query, LastBlockInfo.class, COLLECTION);
    }

    @Override
    public LastBlockInfo findLastBlock() {
        Query query = new Query();
        query.limit(1);
        return mongoTemplate.findOne(query, LastBlockInfo.class);
    }

}
