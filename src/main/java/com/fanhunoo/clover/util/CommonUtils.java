package com.fanhunoo.clover.util;

import com.fanhunoo.clover.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String objectToJson(Object object){
        if(object==null){
            return null;
        }
        String json = null;//返回字符串，输出thirdMenus;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

//    /**
//     * 加密密码
//     */
//    public static void encryptPassword(User user){
//        user.setPassWord(new BCryptPasswordEncoder().encode(user.getPassWord()));
//    }
}
