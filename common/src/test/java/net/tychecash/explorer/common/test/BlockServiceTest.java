/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.common.test;

import junit.framework.TestCase;
import net.tychecash.explorer.common.config.ApplicationConfiguration;
import net.tychecash.explorer.common.model.Block;
import net.tychecash.explorer.common.service.BlockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author jithin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class BlockServiceTest extends TestCase {

    @Autowired
    BlockService blockService;

    @Test
    public void testFindLastBlock() {
        Block block = blockService.findLastBlock();
        System.out.println("net.tychecash.explorer.test.BlockServiceTest.testFindLastBlock()" + block.toString());
        assertNotNull(block);
    }
}
