package com.aleopile.odin.aprs_library.aprs_messages;

import com.aleopile.odin.aprs_library.APRSDataType;

public class APRSBulletin implements APRSDataType {
    public static final char data_type_ID = ':'; // First Character
    public static final int max_text_length = 67;

    private char bulletin_id; // single digit number
    private String bulletin_text; // 67 character Message

    private String comment; // Is comment allowed on APRS Message Data-types ??



    public String getBulletinText() {
        return bulletin_text;
    }
    public void setBulletinText(String bulletin_text) throws IllegalArgumentException {
        if(bulletin_text.length() <= max_text_length) {
            this.bulletin_text = bulletin_text;
        }
        else
            throw new IllegalArgumentException("Message can't be longer than 67 characters");
    }

    public char getBulletinID() {
        return bulletin_id;
    }
    public void setBulletinID(char bulletin_id) {
        this.bulletin_id = bulletin_id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }




    // Implements APRSDataType
    @Override
    public char getDataTypeID() {
        return data_type_ID;
    }

    @Override
    public String getStringifiedData() {
        return null;
    }

    @Override
    public String getStringifiedComment() {
        return comment;
    }

    @Override
    public String serialize() {
        return null;
    }
}
