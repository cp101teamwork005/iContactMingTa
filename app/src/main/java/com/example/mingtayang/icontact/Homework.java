package com.example.mingtayang.icontact;

public class Homework { //存著"科目"跟"是否完成"兩個參數
    private boolean isCompleted = true;
    private String subject;

    public Homework(String subject, boolean isCompleted) {

        this.subject = subject;
        this.isCompleted = isCompleted;

    }


    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}
