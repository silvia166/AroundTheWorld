package com.example.aroundtheworld.model;

public class FamilyMember {
    private String name;
    private int age;
    private String parenthood;

    public FamilyMember(String name, int age, String parenthood){
        this.name = name;
        this.age = age;
        this.parenthood = parenthood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getParenthood() {
        return parenthood;
    }

    public void setParenthood(String parenthood) {
        this.parenthood = parenthood;
    }
}
