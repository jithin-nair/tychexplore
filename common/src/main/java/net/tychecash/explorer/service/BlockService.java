/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service;

import java.util.List;
import net.tychecash.explorer.model.Block;

/**
 *
 * @author Jithin
 */
public interface BlockService {

    public void createBlock(Block block);

    public Block findBlock(Block block);

    public List<Block> findAllBlock();
}
