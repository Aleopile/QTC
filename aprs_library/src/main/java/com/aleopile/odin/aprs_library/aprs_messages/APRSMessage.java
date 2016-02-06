package com.aleopile.odin.aprs_library.aprs_messages;


import com.aleopile.odin.aprs_library.APRSDataType;
import com.google.common.base.CharMatcher;

public class APRSMessage implements APRSDataType {
    public static final char data_type_ID = ':'; // First Character
    public static final int max_text_length = 67;

    private String addressee = "_________";
    private String message_text;

    private String message_id;

    private String comment; // Is comment allowed on APRS Message Data-types ??



    public String getMessageText() {
        return message_text;
    }
    public void setMessageText(String messgage_text) throws IllegalArgumentException {
        if(messgage_text.length() <= max_text_length) {
            this.message_text = messgage_text;
        }
        else
            throw new IllegalArgumentException("Message can't be longer than 67 characters");
    }

    public String getAddressee() {
        return addressee;
    }
    public void setAddressee(String addressee) throws IllegalArgumentException{
        if(addressee.length() <= 9) {
            int length = addressee.length();

            for (int i = length; i<9; i++){
                addressee += "_";
            }
            this.addressee = addressee;
        }
        else
            throw new IllegalArgumentException("Addressee has to be less than 9 characters");
    }

    public String getMessageID() {
        return message_id;
    }
    public void setMessageID(String message_id) throws IllegalArgumentException {
        boolean isAscii = CharMatcher.ASCII.matchesAllOf(message_id);
        boolean isAlphaNumeric = CharMatcher.JAVA_LETTER_OR_DIGIT.matchesAllOf(message_id);

        if(isAscii && isAlphaNumeric) {
            if(message_id.length() <= 5)
                this.message_id = message_id;
            else {
                throw new IllegalArgumentException("Message ID length exceeds maximum length 5");
            }
        }
        else {
            throw new IllegalArgumentException("Message ID must be ASCII and Alphanumeric");
        }
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
        if (message_id != null) {
            return data_type_ID + addressee + ":" + message_text + "{" + message_id;
        }
        else {
            return data_type_ID + addressee + ":" + message_text;
        }
    }

    @Override
    public String getStringifiedComment() {
        return comment;
    }

    @Override
    public String serialize() {
        if (this.getStringifiedComment() != null) {
            return this.getStringifiedData() + this.getStringifiedComment();
        }
        else {
            return this.getStringifiedData();
        }
    }
}
