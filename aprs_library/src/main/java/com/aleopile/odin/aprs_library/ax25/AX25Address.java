package com.aleopile.odin.aprs_library.ax25;

import com.google.common.base.CharMatcher;


/**
 * Implementation of a AX.25 Address
 * This is used for the Source and Destination Fields of a AX.25 Frame
 * It is also used for Layer-2 Repeater Addresses
 *
 * More information here http://www.tapr.org/pub_ax25.html#2.2.13
 * AX.25 Version 2.2 section 2.2.13
 */
public class AX25Address {
    private String callsign;
    private byte SSID;

    // SSID Format (MSB to LSB) CRRSSID(0|1)
    // C is Control/Response bit
    // R is Reserved
    // SSID is a 4 bit int for the SSID of station
    // Last bit is extension bit, it is 1 only on the last address in address field

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) throws IllegalArgumentException {
        boolean isAscii = CharMatcher.ASCII.matchesAllOf(callsign);
        boolean isAlphaNumeric = CharMatcher.JAVA_LETTER_OR_DIGIT.matchesAllOf(callsign);

        if (isAscii && isAlphaNumeric)
            this.callsign = callsign;
        else
            throw new IllegalArgumentException("Callsign Must be ASCII and Alphanumeric");
    }

    public int getStationSSID() {
        return (this.SSID & 0b00011110) >> 1;
    }

    public void setStationSSID(int stationSSID) {
        if (stationSSID < 16 && stationSSID >= 0) {
            this.SSID = (byte) ((this.SSID & 0b11100001) | (stationSSID << 1));
        }
        else {
            this.SSID = (byte) ((this.SSID & 0b11100001) | (0 << 1));
        }
    }

    public boolean getControlResponse(){
        if ((this.SSID & 0b10000000) == 0b10000000) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setControlResponse(boolean controlResponse){
        if(controlResponse) {
            this.SSID = (byte) ((this.SSID & 0b01111111) | 0b10000000);
        }
        else {
            this.SSID = (byte) ((this.SSID & 0b01111111) | 0b00000000);
        }
    }

    public boolean getExtension(){
        if ((this.SSID & 0b00000001) == 0b00000001) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setExtension(boolean extension){
        if(extension) {
            this.SSID = (byte) ((this.SSID & 0b11111110) | 0b00000001);
        }
        else {
            this.SSID = (byte) ((this.SSID & 0b11111110) | 0b00000000);
        }
    }

}
