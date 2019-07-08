package com.carpool.cloud.service;

import org.junit.Test;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取 用户类型和手机号
 */
public class Test1 {

    String info = "" ;

    @Test
    public void main() {
         info = "车找人：今晚6：10 软件园二期（近二小东门），" +
                "后厂村路，亮甲店-永澄南路，北清路，六环，涿州北，国富，东兴北街，" +
                "范阳路沿线，终点甲秀路南口 联系电话：18531290330";

        //获取用户类型。
        HashMap<String, String> userTypeHashMap = geiUserType(info);
        System.out.println("userType = " + userTypeHashMap.get("userType"));
        info = userTypeHashMap.get("info") ;

        //获取用户电话。
        HashMap<String, String> userTelHashMap = geiUserTel(info);
        System.out.println("userTel = " + userTelHashMap.get("userTel"));
        info = userTelHashMap.get("info") ;

        //获取用户发布日期。
        HashMap<String, String> userDateHashMap = geiUserDate(info);
        System.out.println("userDate = " + userTelHashMap.get("userDate"));
        info = userTelHashMap.get("info") ;

        //区分值。
        System.out.println("-----------------------------------------");
        System.out.println("info = " + info);


    }
    /**
     *  获取用户发布日期。
     * @param info
     * @return
     */
    public HashMap<String, String> geiUserDate(String info) {
        HashMap<String, String> userDateHashMap = new HashMap<>();

        return userDateHashMap;
    }

    /**
     *  获取用户电话
     * @param info
     * @return
     */
    public HashMap<String, String> geiUserTel(String info) {
        HashMap<String, String> userTelHashMap = new HashMap<>();
        String userTel = "";
        Pattern pattern = Pattern.compile("((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}");
        // 创建匹配给定输入与此模式的匹配器。
        Matcher matcher = pattern.matcher(info);
        //查找字符串中是否有符合的子字符串
        while(matcher.find()){
            //查找到符合的即输出
            userTel = matcher.group();
            info = info.replace(userTel, "");
        }
        userTelHashMap.put("userTel",userTel);
        userTelHashMap.put("info",info);
        return userTelHashMap;
    }

    /**
     *  获取用户类型
     * @param info
     * @return
     */
    public   HashMap<String, String>  geiUserType(String info) {
        HashMap<String, String> userTypeHashMap = new HashMap<>();
        String userType = "";
        if(info.contains("车寻人") || info.contains("车找人") || info.contains("找人")){
        	 userType = "车主";
        }else if (info.contains("人寻车") || info.contains("人找车") || info.contains("找车")){
//            System.out.println("乘客");
            userType = "乘客";
            //从 feitianbenyue 中移除 tian
            String removeStr = "人找车";
            info = info.replace(removeStr, "");
        }else{
            System.out.println("乘客");
        }
//        System.out.println(info);
        userTypeHashMap.put("userType",userType);
        userTypeHashMap.put("info",info);
        return userTypeHashMap;
    }


}
