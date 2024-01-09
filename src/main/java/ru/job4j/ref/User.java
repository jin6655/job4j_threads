package ru.job4j.ref;

public class User {

    private int id;
    private String name;

    /*public User(User newUser) {
        User u = new User();
        u.setName(newUser.getName());
        u.setId(newUser.getId());
    }
     */

    public static User of(String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    public User () {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
