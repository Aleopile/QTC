package com.aleopile.odin.aprs_library.ax25;

import com.google.common.base.CharMatcher;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Minimal Implementation of AX.25 UI Frames
 * Only covers parts of AX.25 pertinent to the APRS Spec
 */

public class AX25Frame {
    // These constants are parts of a AX.25 Frame that are constant in the APRS spec
    public static final int flag = 0x7e; // Flag that specifies the beginning and end of a transmission
    public static final int controlField = 0x03; // AX.25 (UI-frame)
    public static final int protcolId = 0xf0; // No layer 3 protocol

    private AX25Address destAddress;
    private AX25Address sourceAddress;
    private ArrayList<AX25Address> repeaterAddresses;

    private String information;

    private int frameCheckSequence; // The FCS is a sequence of 16 bits used for checking the integrity of a received frame.

    public AX25Frame() {
        this.repeaterAddresses = new ArrayList<AX25Address>();
    }

    public AX25Address getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(AX25Address destAddress) {
        this.destAddress = destAddress;
    }

    public AX25Address getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(AX25Address sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public ArrayList<AX25Address> getRepeaterAddresses() {
        return repeaterAddresses;
    }

    public void setRepeaterAddresses(ArrayList<AX25Address> repeaterAddresses) throws IllegalArgumentException {
        if(repeaterAddresses.size() <= 8)
            this.repeaterAddresses = repeaterAddresses;
        else
            throw new IllegalArgumentException("No more than 8 repeaterAddresses are permitted");
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) throws IllegalArgumentException {
        boolean isAscii = CharMatcher.ASCII.matchesAllOf(information);

        if (isAscii)
            this.information = information;
        else
            throw new IllegalArgumentException("Information Field Must be ASCII");
    }

    public int getFrameCheckSequence() {
        return frameCheckSequence;
    }

    public void calculateFrameCheckSequence() {
        //TODO FCS
    }
}
