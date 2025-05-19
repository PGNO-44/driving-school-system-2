package com.example.driving_school.entity;

public class Instructor {
    private String id;
    private String name;
    private int experience;
    private boolean available;

    public Instructor() {}

    public Instructor(String id, String name, int experience, boolean available) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.available = available;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String toCsvString() {
        return id + "," + name + "," + experience + "," + available;
    }
}
