package com.example.todo2;

public class Task {

    private String title;
    private String note;
    private String timeDate;
    private boolean status;

    public Task() {
    }

    public Task( String title, String note, String timeDate, boolean status) {

        this.title = title;
        this.note = note;
        this.timeDate = timeDate;
        this.status = status;
    }



    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", timeDate='" + timeDate + '\'' +
                ", status=" + status +
                '}';
    }
}
