/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.dao;

import net.tychecash.explorer.model.LastBlockInfo;

/**
 *
 * @author Jithin
 */
public interface LastBlockInfoDao {

    public void createBlock(LastBlockInfo block);
    
    public void updateBlock(LastBlockInfo block);

    public LastBlockInfo findBlock(LastBlockInfo block);

}
