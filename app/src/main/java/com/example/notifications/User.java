package com.example.notifications;

public class User {

    private String name;
    private String email;
    private double salary;
    private int image;

    public User(String name, String email, double salary, int image) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
