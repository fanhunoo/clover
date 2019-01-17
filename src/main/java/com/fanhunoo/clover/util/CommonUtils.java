package com.fanhunoo.clover.util;

import com.fanhunoo.clover.entity.Resources;
import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.entity.vo.ResourceTreeNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");



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

    public static String formatNowDateTime(){
        return LocalDateTime.now().format(dtf);
    }

    public static String formatDateTime(LocalDateTime localDateTime){
        return localDateTime.format(dtf);
    }

    public String gettypeCode(){

        return "";
    }
//    /**
//     * 加密密码
//     */
//    public static void encryptPassword(User user){
//        user.setPassWord(new BCryptPasswordEncoder().encode(user.getPassWord()));
//    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
        Integer a = 1;
        Integer b = 2;

        System.out.println("a=" + a + ",b=" + b);
        swap(a, b);
        System.out.println("a=" + a + ",b=" + b);
    }

    private static void swap(Integer x, Integer y) {
        int tmp = x.intValue();
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            field.set(x, y);
            field.set(y,new Integer(tmp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static String encodeDefaultPassword() {
        String defaultPassword = "123456";//初始密码123456
        return encodePassword(defaultPassword);
    }

    /**
     * 将资源集合转换成树结构
     */
    public static List<Resources> resourcesToTree(List<Resources> resourcesList){
        List<Resources> trees = new ArrayList<>();
        for (Resources treeNode : resourcesList) {
            if ("".equals(treeNode.getParentId())) {
                trees.add(findResourceChildren(treeNode,resourcesList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     */
    public static Resources findResourceChildren(Resources treeNode,List<Resources> treeNodes) {
        treeNode.setChildren(new ArrayList<>());
        for (Resources it : treeNodes) {
            if(treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findResourceChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

}
