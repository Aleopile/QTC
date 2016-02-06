package com.aleopile.odin.aprs_library.ax25;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AX25AddressTest {
    AX25Address address;

    @Before
    public void setUp() {
        address = new AX25Address();
    }


    @Test
    public void checkSetCallsign_getCallsign_equal() throws IllegalArgumentException {
        address.setCallsign("Kj6LXV");
        assertEquals("Kj6LXV", address.getCallsign());

    }

    @Test (expected = IllegalArgumentException.class)
    public void checkSetCallsign_getCallsign_nonASCII_fail() throws IllegalArgumentException {
        address.setCallsign("©©©©©");
        assertEquals("©©©©©", address.getCallsign());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkSetCallsign_getCallsign_nonAlphanumeric_fail() throws IllegalArgumentException {
        address.setCallsign("*%(@$)");
        assertEquals("*%(@$)", address.getCallsign());
    }



    @Test
    public void checkSetSSID_getSSID_equal_0to16() {
        for (int i = 0; i < 16; i++) {
            address.setStationSSID(i);
            assertEquals(i, address.getStationSSID());
        }
    }

    @Test
    public void checkSetSSID_getSSID_not_equal_16toMax() {
        for (int i = 16; i < Integer.MAX_VALUE; i++) {
            address.setStationSSID(i);
            assertEquals(0, address.getStationSSID());
        }
    }

    @Test
    public void checkSetSSID_getSSID_not_equal_Minto0() {
        for (int i = Integer.MIN_VALUE; i < 0; i++) {
            address.setStationSSID(i);
            assertEquals(0, address.getStationSSID());
        }
    }

    @Test
    public void checkSetControlResponse_getControlResponse_equal() {
        address.setControlResponse(true);
        assertEquals(true, address.getControlResponse());
        address.setControlResponse(false);
        assertEquals(false, address.getControlResponse());
    }

    @Test
    public void checkSetExtension_getExtension_equal() {
        address.setExtension(true);
        assertEquals(true, address.getExtension());
        address.setExtension(false);
        assertEquals(false, address.getExtension());
    }
}
