/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service;

import net.tychecash.explorer.model.LastBlockInfo;

/**
 *
 * @author Jithin
 */
public interface LastBlockInfoService {

    public void createBlock(LastBlockInfo block);

    public void updateBlock(LastBlockInfo block);

    public LastBlockInfo findBlock(LastBlockInfo block);
    
    public LastBlockInfo findLastBlock();

}
