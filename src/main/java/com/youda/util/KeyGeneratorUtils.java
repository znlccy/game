package com.youda.util;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;

import javax.imageio.spi.ServiceRegistry;
import javax.xml.ws.Service;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @CreateTime:2018/3/11 14:43
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 主键生成工具
 */

public class KeyGeneratorUtils {

    public String k;

    /**
     * @comment: configure
     * @param: [type, properties, serviceRegistry]
     * @return: void
     */
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
        k = properties.getProperty("k");
    }

    /**
     * @comment: generate 生成主键策略
     * @param: [sessionImplementor, o]
     * @return: java.io.Serializable
     */
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return k + sdf.format(new Date());
    }
}
