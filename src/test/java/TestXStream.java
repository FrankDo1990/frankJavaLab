import com.google.common.collect.Lists;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by dufeng on 2017/5/23.
 */

public class TestXStream {
    @XStreamAlias("tagInfo")
    static class TagInfo {
        @XStreamImplicit(itemFieldName="names")
        List<Name> names = Lists.newArrayList(new Name(), new Name());
        String info = "info";
    }
    static class Name{
        @XStreamAlias("name")
        String name = "1";
    }

    public static void main(String...args){
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        String xml = xstream.toXML(new TagInfo());
        System.out.println(xml);
    }
}
