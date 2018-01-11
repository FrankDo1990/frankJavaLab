package com.frank.effective.java;

/**
 * Created by Frank on 2017/7/9.
 */
public class CloneTest {
    class Element {
        private long id;

        public Element(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public Element setId(long id) {
            this.id = id;
            return this;
        }

//        @Override
//        public String toString() {
//            return String.valueOf(id);
//        }
    }
    class Number implements Cloneable{
        Element[] elements = new Element[10];

        public void setElements(){
            for (int i = 0; i < elements.length; i++){
                elements[i] = new Element(i);
            }
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (elements != null){
                for (Element i : elements){
                    sb.append(i);
                    sb.append(" ");
                }
                sb.append("]");
            }
            return sb.toString();
        }
        public void setI(int i, int num){
            elements[i].setId(num);
        }

        @Override
        protected Number clone() throws CloneNotSupportedException {
            Number number = (Number) super.clone();
            number.elements = elements.clone();
            return number;
        }
    }

    public static void main(String...args) throws CloneNotSupportedException {
        Number number = new CloneTest().new Number();
        number.setElements();
        System.out.println(number.elements);
        Number clone = number.clone();
        System.out.println(clone.elements);
        clone.setI(1, 100);
        System.out.println(number);

    }
}

