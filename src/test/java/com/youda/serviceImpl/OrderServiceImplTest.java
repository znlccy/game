package com.youda.serviceImpl;

import com.youda.dao.GameMapper;
import com.youda.dao.OrderMapper;
import com.youda.dao.UserMapper;
import com.youda.model.Order;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
/**
 * @Author Chencongye
 * @Date 2017/12/12 18:25
 * @Version 1.0.0
 * @Instructions 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GameMapper gameMapper;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: createOrder(OrderRequest request)
    *
    */
    @Test
    public void testCreateOrder() throws Exception {
    //TODO: Test goes here...
        Order order = new Order();
        order.setOtherOrderId("2323412341234");
        order.setOrderTotalAmount("34530.3");
        order.setCreateOrderTime(new Timestamp(System.currentTimeMillis()));
        order.setUserId(userMapper.findByUserName("15900785383").getUserId());
        order.setGameId(gameMapper.findByGameName("shengui").getGameId());
        /*order.setUser(userMapper.findByUserName("15900785383"));
        order.setGame(gameMapper.findByGameName("nihao"));*/
        /*long userId = userMapper.findByUserName("15900785383").getUserId();
        long gameId = gameMapper.findByGameName("shengui").getGameId();*/
        /*System.err.println("用户主键"+userId+"游戏主键:"+gameId);*/
        orderMapper.createOrder(order);
    }

    /**
    *
    * Method: alipay(Long orderId)
    *
    */
    @Test
    public void testAlipay() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: wechatpay(Long orderId)
    *
    */
    @Test
    public void testWechatpay() throws Exception {
    //TODO: Test goes here...
    }


} 
