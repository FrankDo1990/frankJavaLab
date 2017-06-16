import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

class A implements Serializable {
    private long id;
    private List<B> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<B> getChildren() {
        return children;
    }

    public void setChildren(List<B> children) {
        this.children = children;
    }

    class B{
        private String id;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


}