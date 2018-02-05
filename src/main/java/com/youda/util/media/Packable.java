package com.youda.util.media;

/**
 * Created by Li on 10/1/2016.
 */
public interface Packable {
    ByteBuf marshall(ByteBuf out);
}
