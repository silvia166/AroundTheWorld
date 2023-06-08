package com.example.aroundtheworld.bean;

public class FamilyMemberBean {
    private String name;
    private int age;
    private String parenthood;

    public FamilyMemberBean(String name, int age, String parenthood){
        this.name = name;
        this.age = age;
        this.parenthood = parenthood;
    }
    public String getParenthood() {
        return parenthood;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public void setParenthood(String parenthood) {
        this.parenthood = parenthood;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
