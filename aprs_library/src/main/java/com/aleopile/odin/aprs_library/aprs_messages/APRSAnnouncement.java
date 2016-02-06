package com.aleopile.odin.aprs_library.aprs_messages;

import com.aleopile.odin.aprs_library.APRSDataType;

public class APRSAnnouncement implements APRSDataType {
    @Override
    public char getDataTypeID() {
        return 0;
    }

    @Override
    public String getStringifiedData() {
        return null;
    }

    @Override
    public String getStringifiedComment() {
        return null;
    }

    @Override
    public String serialize() {
        return null;
    }
}
