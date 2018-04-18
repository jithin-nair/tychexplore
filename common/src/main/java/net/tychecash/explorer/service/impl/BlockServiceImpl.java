/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service.impl;

import java.util.List;
import net.tychecash.explorer.dao.BlockDao;
import net.tychecash.explorer.model.Block;
import net.tychecash.explorer.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jithin
 */
@Service
@Transactional
public class BlockServiceImpl implements BlockService{
    
    @Autowired
    BlockDao blockDao;

    @Override
    public void createBlock(Block block) {
        blockDao.createBlock(block);
    }

    @Override
    public Block findBlock(Block block) {
        return blockDao.findBlock(block);
    }

    @Override
    public List<Block> findAllBlock() {
        return blockDao.findAllBlock();
    }

    @Override
    public Block findLastBlock() {
        return blockDao.findLastBlock();
    }
    
}
