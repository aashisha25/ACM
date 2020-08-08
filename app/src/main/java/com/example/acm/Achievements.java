package com.example.acm;

public class Achievements {
    private String achieveDes;

    public Achievements(){}

    public Achievements(String AchieveDes)
    {
        achieveDes = AchieveDes;
    }

    public String getAchieveDes() {
        return achieveDes;
    }

    public void setAchieveDes(String achieveDes) {
        this.achieveDes = achieveDes;
    }
}
