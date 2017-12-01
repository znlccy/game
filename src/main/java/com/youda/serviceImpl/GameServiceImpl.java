
package com.youda.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youda.dao.GameMapper;
import com.youda.model.Game;
import com.youda.service.GameService;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 实现游戏服务接口
 */

@Service
public class GameServiceImpl implements GameService {

	/**
	 * 实现游戏映射接口的自动依赖注入
	 */
	@Autowired
	private GameMapper gameMapper;
	
	/* 
	 * 实现创建游戏的方法
	 * (non-Javadoc)
	 * @see com.youda.service.GameService#createGame()
	 */
	@Override
	public boolean addGame() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
