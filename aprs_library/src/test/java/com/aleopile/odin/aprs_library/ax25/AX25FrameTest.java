package com.aleopile.odin.aprs_library.ax25;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class AX25FrameTest {
    private AX25Frame frame;
    AX25Address address;

    @Before
    public void setUp() {
        this.frame = new AX25Frame();
        this.address = new AX25Address();
    }

    @Test
    public void checkConstants() {
        assertEquals(0x7e, frame.flag);
        assertEquals(0x03, frame.controlField);
        assertEquals(0xf0, frame.protcolId);
    }

    @Test
    public void check_setSource_getSource_equal () throws IllegalArgumentException {
        address.setCallsign("KJ6LXV");
        frame.setSourceAddress(address);
        assertEquals(address.getCallsign(), frame.getSourceAddress().getCallsign());
    }

    @Test
    public void check_setDest_getDest_equal () throws IllegalArgumentException {
        address.setCallsign("WJ6XXV");
        frame.setDestAddress(address);
        assertEquals(address.getCallsign(), frame.getDestAddress().getCallsign());
    }

    @Test
    public void check_setInformation_getInformation_equal(){
        frame.setInformation("Test Info");
        assertEquals("Test Info", frame.getInformation());
    }

    @Test
    public void check_setInformation_getInformation_nonASII_fail(){
        frame.setInformation("Test Info");
        assertEquals("Test Info", frame.getInformation());
    }

    @Test
    public void check_getRepeaters_setRepeaters_equal () throws IllegalArgumentException {
        ArrayList<AX25Address> addresses = frame.getRepeaterAddresses();
        address.setCallsign("WB4JD");
        addresses.add(address);
        address.setCallsign("KK2YAV");
        addresses.add(address);

        frame.setRepeaterAddresses(addresses);
    }

    @Test (expected = IllegalArgumentException.class)
    public void check_getRepeaters_setRepeaters_fail ()  throws IllegalArgumentException {
        ArrayList<AX25Address> addresses = frame.getRepeaterAddresses();
        address.setCallsign("WB4JD");
        addresses.add(address);
        address.setCallsign("KK2YAV");
        addresses.add(address);
        address.setCallsign("WB4JD");
        addresses.add(address);
        address.setCallsign("KK2YAV");
        addresses.add(address);
        address.setCallsign("WB4JD");
        addresses.add(address);
        address.setCallsign("KK2YAV");
        addresses.add(address);
        address.setCallsign("WB4JD");
        addresses.add(address);
        address.setCallsign("KK2YAV");
        addresses.add(address);
        address.setCallsign("WB4JD");
        addresses.add(address);
        address.setCallsign("KK2YAV");
        addresses.add(address);

        frame.setRepeaterAddresses(addresses);
    }


}
