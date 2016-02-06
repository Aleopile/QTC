package com.aleopile.odin.aprs_library;

public interface APRSDataTypeWithExtension extends APRSDataType {

    boolean hasDataExtension();
    String getStringifiedDataExtension();

}
