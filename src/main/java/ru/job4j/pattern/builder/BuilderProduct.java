package ru.job4j.pattern.builder;

import java.util.Objects;

public class BuilderProduct {

    private int age;
    private  int size;
    private long time;
    private String name;
    private String text;
    private char charCode;

    public BuilderProduct() {
    }

    public BuilderProduct age(int age) {
        this.age = age;
        return this;
    }

    public BuilderProduct size(int size) {
        this.size = size;
        return this;
    }

    public BuilderProduct time(long time) {
        this.time = time;
        return this;
    }

    public BuilderProduct name(String name) {
        this.name = name;
        return this;
    }

    public BuilderProduct text(String text) {
        this.text = text;
        return this;
    }

    public BuilderProduct charCode(char charCode) {
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
