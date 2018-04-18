/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.dao.impl;

import java.util.List;
import net.tychecash.explorer.dao.BlockDao;
import net.tychecash.explorer.model.Block;
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
public class BlockDaoImpl implements BlockDao {

    @Autowired
    MongoTemplate mongoTemplate;

    final String COLLECTION = "blocks";

    @Override
    public void createBlock(Block block) {
        mongoTemplate.insert(block);
    }

    @Override
    public Block findBlock(Block block) {
        Query query = new Query(Criteria.where("_id").is(block.getId()));
        return mongoTemplate.findOne(query, Block.class, COLLECTION);
    }

    @Override
    public List<Block> findAllBlock() {
        return (List<Block>) mongoTemplate.findAll(Block.class);
    }

    @Override
    public Block findLastBlock() {
        Query query = new Query();
        query.limit(1);
        query.with(new Sort(Sort.Direction.DESC, "blockResponse.result.block_header.height"));
        return mongoTemplate.findOne(query, Block.class);
    }

}
