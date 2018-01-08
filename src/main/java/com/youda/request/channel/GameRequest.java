package com.youda.request.channel;

import com.youda.request.BaseRequest;

/**
 * Created by chenshengyu
 * on 2017/12/8.
 */
public class GameRequest extends BaseRequest {
    private String gameName;
    private String gamePackage;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGamePackage() {
        return gamePackage;
    }

    public void setGamePackage(String gamePackage) {
        this.gamePackage = gamePackage;
    }

    @Override
    public boolean isEmpty() {
        return gameName == null || gameName.isEmpty()
                || gamePackage == null || gamePackage.isEmpty();
    }
}
