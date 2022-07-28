package com.wqm.utils;

import com.wqm.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

public class webUtils {

    public static <T> T copyParamToBean(Map map, T bean){
        try {
            System.out.println("注入之前:"+bean);
            BeanUtils.populate(bean,map);
            System.out.println("注入之后:"+bean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    /**
     * 将字符串转换为int类型，转换失败时返回defaultValue
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue){
        try{
            return Integer.parseInt(strInt);
        }catch (Exception e){
            //e.printStackTrace();
        }
        return defaultValue;
    }
}
