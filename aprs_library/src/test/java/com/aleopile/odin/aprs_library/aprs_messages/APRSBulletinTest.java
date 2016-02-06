package com.aleopile.odin.aprs_library.aprs_messages;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class APRSBulletinTest {
    APRSBulletin bulletin;

    @Before
    public void setUp(){
        bulletin = new APRSBulletin();
    }

    @Test
    public void check_constants() {
        assertEquals(':', bulletin.getDataTypeID());
        assertEquals(':', bulletin.data_type_ID);
        assertEquals(67, bulletin.max_text_length);
    }

    @Test
    public void checkSetBulletinText_getBulletinText_equal() {
        bulletin.setBulletinText("CQ CQ CQ this is KJ6LXV");
        assertEquals("CQ CQ CQ this is KJ6LXV", bulletin.getBulletinText());
    }

    @Test
    public void checkSetBulletinID_getBulletinID_equal() {
        bulletin.setBulletinID('5');
        assertEquals('5', bulletin.getBulletinID());
    }

    @Test
    public void checkSetComment_getComment_equal() {
        bulletin.setComment("CQ CQ CQ this is KJ6LXV");
        assertEquals("CQ CQ CQ this is KJ6LXV", bulletin.getStringifiedComment());
    }
}
