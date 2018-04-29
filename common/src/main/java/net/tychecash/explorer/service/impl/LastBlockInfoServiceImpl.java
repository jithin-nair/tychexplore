/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service.impl;

import net.tychecash.explorer.dao.LastBlockInfoDao;
import net.tychecash.explorer.model.LastBlockInfo;
import net.tychecash.explorer.service.LastBlockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jithin
 */
@Service
@Transactional
public class LastBlockInfoServiceImpl implements LastBlockInfoService {

    @Autowired
    LastBlockInfoDao lastBlockInfoDao;

    @Override
    public void createBlock(LastBlockInfo block) {
        lastBlockInfoDao.createBlock(block);
    }

    @Override
    public void updateBlock(LastBlockInfo block) {
        lastBlockInfoDao.updateBlock(block);
    }

    @Override
    public LastBlockInfo findBlock(LastBlockInfo block) {
        return lastBlockInfoDao.findBlock(block);
    }

    @Override
    public LastBlockInfo findLastBlock() {
        return lastBlockInfoDao.findLastBlock();
    }

}
