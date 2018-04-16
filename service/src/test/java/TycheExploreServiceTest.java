
import net.tychecash.explorer.config.TycheExploreConfig;
import net.tychecash.explorer.model.CountVO;
import net.tychecash.explorer.service.TycheExploreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jithin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TycheExploreConfig.class)
public class TycheExploreServiceTest {
    
    @Autowired
    TycheExploreService tycheExploreService;
    
    @Test
    public void testGetBlockCount(){
        CountVO countVO = tycheExploreService.getBlockCount();
        System.out.println("TycheExploreServiceTest.testGetBlockCount()"+countVO.toString());
    }
}
