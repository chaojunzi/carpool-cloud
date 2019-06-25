package com.nxin.etposvr.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

    public static void main(String[] args) {

        String  aa="我们9月2日今晚八点半就可以下班了  ";
        //今天 今早 今晚 明天 明早 明晚 周一到周日 星期一到星期日 月日 月号
        Date newdate=stringZhuanDate(aa);

        SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String   date   =   sDateFormat.format(new   java.util.Date());
        System.out.println("当前 date= " + date);
        //判断是否用日期的格式来录入
        if( aa.indexOf("月") != -1 && aa.indexOf("日") != -1) {
            System.out.println("date= 月日 " );
            Date newdate1=stringZhuanDate(aa);
        }else if(aa.indexOf("月") != -1 &&  aa.indexOf("号") != -1){
            System.out.println("date= 月号 " );
            Date newdate2=stringZhuanDate(aa);
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


    //字符串里的时间转换成date
    public static Date stringZhuanDate(String biaoti){
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
//    //    截取2个指定字符之间的字符串
    public static String splitData(String str, String strStart, String strEnd) {
        String tempStr;
        tempStr = str.substring(str.indexOf(strStart) + 1, str.lastIndexOf(strEnd));
        return tempStr;
    }
}
