import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dufeng on 2017/3/31.
 */
public class ClassA implements TestInterface {
    @Override
    public void printName() {
    }

    public static void main(String...args){
        testData();
    }

    public static void testData(){
        Multimap<Integer, Long> groupQQ = getMap();
        int qqNum = 0;
        for (Integer g : groupQQ.keySet()){
            List<Long> qqs = Lists.newArrayList(groupQQ.get(g));
            String sql = "insert into school_user(qq, group_id,status) values ";
            List<String> values = Lists.newArrayList();
//            for (long qq : qqs){
//            }
            qqNum += qqs.size();
        }
        System.out.println(qqNum);

    }

    public static Multimap<Integer, Long> getMap(){
        ClassLoader.getSystemClassLoader();
        return ArrayListMultimap.create();
    }
}
