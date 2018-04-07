package com.zz.service.impl;

import com.zz.mapper.GzMapper;
import com.zz.model.Gz;
import com.zz.service.GzDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 將me年的工作日显示
 * Created by dell on 2017/10/20.
 */
@Service
public class GzDayServiceImpl implements GzDayService{
    //节假日列表
    private static List<String> holidayList = new ArrayList<String>();
    //周末为工作日
    private static List<String> weekendList = new ArrayList<String>();


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    @Autowired
    private GzMapper gzMapper;

    public  String workDay( String s) {

        holidayList.add("20170915");//初始节假日
        holidayList.add("20170916");//初始节假日
        holidayList.add("20170917");//初始节假日

        weekendList.add("20170918");//初始周末为工作日
        List<String> ld = new ArrayList<String>();
        int count = 0;
        try {
            List<Date> lists = wordList(s);

            if (!lists.isEmpty()){
                for (Date date : lists){
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    System.out.println(sdf.format(date));
                    if (((cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                            ) || weekendList.contains(sdf.format(date))
                    ) && !holidayList.contains(sdf.format(date))) {
                        count++;
                        ld.add(sdf.format(date));
                        System.out.println(sdf.format(date));
                    }

                }
            }
            System.out.println("count"+count);
            System.out.println("lists.size()"+lists.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ld.toString()+" "+ld.size();
    }

    /**
     * 保存到数据库
     * @param s
     * @return
     */
    @Override
    public boolean saveWorkDay(String s) {

        holidayList.add("20170915");//初始节假日
        holidayList.add("20170916");//初始节假日
        holidayList.add("20170917");//初始节假日

        weekendList.add("20170918");//初始周末为工作日
        List<String> ld = new ArrayList<String>();
        int count = 0;
        try {
            List<Date> lists = wordList(s);
            Gz gz = new Gz();
            if (!lists.isEmpty()){
                for (Date date : lists){
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    System.out.println(sdf.format(date));
                    if (((cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                    ) || weekendList.contains(sdf.format(date))
                    ) && !holidayList.contains(sdf.format(date))) {
                        gz.setRiqi(sdf.format(date));
                        gz.setVlues("0");
                        gzMapper.intsert(gz);
                    }else {
                        gz.setRiqi(sdf.format(date));
                        gz.setVlues("1");
                        gzMapper.intsert(gz);
                    }
                }
            }
            System.out.println("count"+count);
            System.out.println("lists.size()"+lists.size());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static List<Date> wordList(String s) throws Exception{
            List<Date> wordList = new ArrayList<Date>();
            java.util.Date date1 = sdf.parse(s+"0101");
            java.util.Date date2 = sdf.parse(s+"1231");
            Long l = date2.getTime()-date1.getTime();
            Long l2 = l/(24*60*60*1000);
            wordList.add(date2);
            for(int i = 1;i <= l2 ;i++) {
                wordList.add(new Date(wordList.get(i - 1).getTime() - (24 * 60 * 60 * 1000)));
                //System.out.println(new Date(wordList.get(i - 1).getTime() - (24 * 61 * 61 * 1000)) );
            }
            return  wordList;
       }





}
