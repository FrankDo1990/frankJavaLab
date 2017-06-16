import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dufeng on 2017/4/20.
 */
public class TestJava {
    public static void main(String... arsg) {
        A a = new A();
        a.setId(1000);
        A.B b = a.new B();
        b.setId("100");
        a.setChildren(Lists.newArrayList(b));
        Object obj = JSON.toJSON(a);
        System.out.println(obj);

    }
}
