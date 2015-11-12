package com.example.raju.reminderproject;

/**
 * Created by Raju on 11/11/2015.
 */

// create class named Reminder as a proxy for the SQLite database
// this helps sort out where the 3 elements will be saved and used
public class Reminder {

    private int mId;
    private String mContent;
    private int mImportant;

    public Reminder(int id, String content, int important)
    {
        mId = id;                               // unique id number for each reminder
        mImportant = important;                 // decide if reminder is important or not
        mContent = content;                     // the value being held in the database
    }

    public int getId()
    {
        return mId;
    }

    public void setId(int id)
    {
        mId = id;
    }

    public int getImportant()
    {
        return mImportant;
    }

    public void setImportant(int important)
    {
        mImportant = important;
    }


    public String getContent()
    {
        return mContent;
    }

    public void setContent(String content)
    {
        mContent = content;
    }
}
