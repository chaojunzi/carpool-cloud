package com.carpool.cloud.service;

import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * java 根据 小时数加 算时间。
 */
public class Test2 {

       @Test
        public  void main() {
           SimpleDateFormat sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
           String   date   =   sDateFormat.format(new   java.util.Date());
           System.out.println("当前 date= " + date);

           String aa = "登记编号今晚8点10正在审批过程中7。";
           System.out.println("当前信息= " + aa);
           if(aa.indexOf("点") != -1 &&  aa.indexOf("分") != -1 || aa.indexOf("点") != -1 ) {

                String nian=splitData(aa,"晚","点").trim();
                System.out.println("获取字符串里的时=="+nian);
                //时 判断如果是汉字， 把这个汉字转换数字。
//                    12+8 = 20  今晚二十点。
                //20 是变量，这里是模拟

                StringBuffer buffer = new StringBuffer(date);

                buffer.replace(11, 13, "20");
                System.out.println("修改后的日期=" + buffer.toString());//输出



            }else if (aa.indexOf(":") != -1 ||  aa.indexOf("：") != -1){
                System.out.println("：");
            }else {

                System.out.println("无");
            }

        }

//        截取2个指定字符之间的字符串
    public static String splitData(String str, String strStart, String strEnd) {
        String tempStr;
        tempStr = str.substring(str.indexOf(strStart) + 1, str.lastIndexOf(strEnd));
        return tempStr;
    }




}
