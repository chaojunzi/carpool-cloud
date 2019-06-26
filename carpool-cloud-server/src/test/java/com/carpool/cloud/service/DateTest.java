package com.carpool.cloud.service;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 年月日转换。
 */
public class DateTest {

    @Test
    public  void main() {

        String  aa="@@表2901年9月3日今晚6：10@@软件园二期（近二小东门），后厂村路，亮甲店-永澄南路，北清路，六环，涿州北，国富，东兴北街，范阳路沿线，终点甲秀路南口";

        //判断是否用日期的格式来录入
        if( aa.indexOf("月") != -1 && aa.indexOf("日") != -1) {
            System.out.println("date= 月日 " );
            //月日处理的方法。
            Date  newdate=stringZhuanDate(aa);
            System.out.println(newdate);

        }else if(aa.indexOf("月") != -1 &&  aa.indexOf("号") != -1){
            System.out.println("date= 月号 " );

        }else{
            //判断 今天 今早 今晚 明天 明早 明晚 周一到周日 星期一到星期日
            if( aa.indexOf("今") != -1 && aa.indexOf("今天") != -1) {

            }else if(aa.indexOf("今") != -1 &&  aa.indexOf("今早") != -1){

            }else if(aa.indexOf("今") != -1 &&  aa.indexOf("今晚") != -1){
                System.out.println("今晚");
                if(aa.indexOf("点") != -1 &&  aa.indexOf("分") != -1 || aa.indexOf("点") != -1 ) {

                }else if (aa.indexOf(":") != -1 ||  aa.indexOf("：") != -1){
                    System.out.println("：");
                }else {

                    System.out.println("无");
                }
            }else if(aa.indexOf("明") != -1 &&  aa.indexOf("明晚") != -1){

            }else if(aa.indexOf("明") != -1 &&  aa.indexOf("明早") != -1){

            }else if(aa.indexOf("明") != -1 &&  aa.indexOf("今晚") != -1){

            }
        }
    }

    /**
     * 年月日
     * @param biaoti
     * @return
     */
    public Date  stringZhuanDate(String biaoti){
        String riqi=null;

        String nian=splitData(biaoti,"表","年").trim();
        System.out.println("获取字符串里的年=="+nian);
        String yue=splitData(biaoti,"年","月").trim();
        System.out.println("获取字符串里的月=="+yue);
        String ri=splitData(biaoti,"月","日").trim();
        System.out.println("获取字符串里的日=="+ri);
        if(yue.length()<2){
            yue="0"+yue;
        }
        if(ri.length()<2){
            ri="0"+ri;
        }
        riqi=nian+"-"+yue+"-"+ri;
        System.out.println("拼装=="+riqi);

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 注意格式需要与上面一致，不然会出现异常
            date = sdf.parse(riqi);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("字符串转换成时间:" + date);
        return date;
    }
    //    截取2个指定字符之间的字符串
    public String splitData(String str, String strStart, String strEnd) {
        String tempStr;
        tempStr = str.substring(str.indexOf(strStart) + 1, str.lastIndexOf(strEnd));
        return tempStr;
    }






}
