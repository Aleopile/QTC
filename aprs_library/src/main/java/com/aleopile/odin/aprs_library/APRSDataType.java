package com.aleopile.odin.aprs_library;

/**
 * Interface for APRS Data-types to extend
 * This interface is for data-types without Data Extensions
 * If the data-type has an optional Data Extension then
 * use the APRSDataTypeWithExtension interface in this package
 */
public interface APRSDataType {

    char getDataTypeID();

    String getStringifiedData();
    String getStringifiedComment();

    String serialize();
}
