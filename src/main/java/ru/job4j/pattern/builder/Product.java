package ru.job4j.pattern.builder;

import java.util.Objects;

public class Product {

    private int age;
    private  int size;
    private long time;
    private String name;
    private String text;
    private char charCode;

    public Product() {
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCharCode(char charCode) {
        this.charCode = charCode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "age=" + age +
                ", size=" + size +
                ", time=" + time +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", charCode=" + charCode +
                '}';
    }

    public static void main(String[] args) {
        Product product = new BuilderProduct().name("Anna").age(20).name("11").build();
        Product product01 = new Builder().age(198).name("Anna").build();
        System.out.println(product);
    }

    public static class Builder {
        private int age;
        private  int size;
        private long time;
        private String name;
        private String text;
        private char charCode;

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Builder time(long time) {
            this.time = time;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder charCode(char charCode) {
            this.charCode = charCode;
            return this;
        }

        public Product build() {
            Product product = new Product();
            if (Objects.isNull(name) || age == 0) {
                throw new IllegalArgumentException("The \"name\" and \"age\" fields are required to be added.");
            }
            product.setAge(age);
            product.setName(name);
            product.setSize(size);
            product.setText(text);
            product.setTime(time);
            product.setCharCode(charCode);
            return new Product();
        }

    }

}
