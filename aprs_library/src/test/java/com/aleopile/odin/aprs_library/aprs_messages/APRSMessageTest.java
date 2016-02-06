package com.aleopile.odin.aprs_library.aprs_messages;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class APRSMessageTest {
    APRSMessage message;

    @Before
    public void setUp() {
        message = new APRSMessage();
    }

    @Test
    public void check_constants() {
        assertEquals(':', message.getDataTypeID());
        assertEquals(':', message.data_type_ID);
        assertEquals(67, message.max_text_length);
    }

    @Test
    public void checkSetMessage_getMessage_equal() {
        message.setMessageText("CQ CQ CQ this is KJ6LXV");
        assertEquals("CQ CQ CQ this is KJ6LXV", message.getMessageText());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkSetMessage_getMessage_too_long_fail() {
        message.setMessageText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vehicula hendrerit purus maximus metus.");
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vehicula hendrerit purus maximus metus.", message.getMessageText());
    }

    @Test
    public void checkSetMessageID_getMessageID_equal() {
        message.setMessageID("AB55");
        assertEquals("AB55", message.getMessageID());
        message.setMessageID("65");
        assertEquals("65", message.getMessageID());
        message.setMessageID("93243");
        assertEquals("93243", message.getMessageID());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkSetMessageID_getMessageID_nonASCII_fail() {
        message.setMessageID("©©©");
        assertEquals("©©©", message.getMessageID());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkSetMessageID_getMessageID_nonAlphanumeric_fail() {
        message.setMessageID("*&^&(");
        assertEquals("*&^&(", message.getMessageID());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkSetMessageID_getMessageID_tooLong_fail() {
        message.setMessageID("fkdsahflkhdsi");
        assertEquals("fkdsahflkhdsi", message.getMessageID());
    }

    @Test
    public void checkGetSetComment_equal() {
        message.setComment("TEST TEST TEST");
        assertEquals("TEST TEST TEST", message.getStringifiedComment());
    }

    @Test
    public void check_addressee_padding() {
        message.setAddressee("HI");
        assertEquals(9, message.getAddressee().length());
        assertEquals("HI_______", message.getAddressee());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkAddressee_getAddressee_too_long_fail() {
        message.setAddressee("Lorem ipsum dolor sit amet.");
        assertEquals("Lorem ipsum dolor sit amet.", message.getAddressee());
    }

    @Test
    public void checkSerialize_nullComment_noID() {
        message.setAddressee("KJ6LXV");
        message.setMessageText("Test Message");
        message.setComment(null);
        String data = message.serialize();
        assertEquals(":KJ6LXV___:Test Message", data);
    }

    @Test
    public void checkSerialize_nullComment_withID() {
        message.setAddressee("KJ6LXV");
        message.setMessageText("Test Message");
        message.setComment(null);
        message.setMessageID("CUP55");
        String data = message.serialize();
        assertEquals(":KJ6LXV___:Test Message{CUP55", data);
    }

    @Test
    public void checkSerialize_empty_comment_noID() {
        message.setAddressee("KJ6LXV");
        message.setMessageText("Test Message");
        message.setComment("");
        String data = message.serialize();
        assertEquals(":KJ6LXV___:Test Message", data);
    }

    @Test
    public void checkSerialize_empty_comment_withID() {
        message.setAddressee("KJ6LXV");
        message.setMessageText("Test Message");
        message.setComment("");
        message.setMessageID("CUP55");
        String data = message.serialize();
        assertEquals(":KJ6LXV___:Test Message{CUP55", data);
    }

    @Test
    public void checkSerialize_with_comment_noID() {
        message.setAddressee("KJ6LXV");
        message.setMessageText("Test Message");
        message.setComment("Test Comment");
        String data = message.serialize();
        assertEquals(":KJ6LXV___:Test MessageTest Comment", data);
    }

    @Test
    public void checkSerialize_with_comment_withID() {
        message.setAddressee("KJ6LXV");
        message.setMessageText("Test Message");
        message.setComment("Test Comment");
        message.setMessageID("CUP55");
        String data = message.serialize();
        assertEquals(":KJ6LXV___:Test Message{CUP55Test Comment", data);
    }
}
