//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.27 at 12:54:12 PM CEST 
//


package com.opengate.common.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="code">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INTERNAL_ERROR"/>
 *     &lt;enumeration value="FORMAT_ERROR"/>
 *     &lt;enumeration value="URL_ERROR"/>
 *     &lt;enumeration value="DESTINATION_ERROR"/>
 *     &lt;enumeration value="VALIDATION_ERROR"/>
 *     &lt;enumeration value="OK"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "code")
@XmlEnum
public enum Code {

    INTERNAL_ERROR,
    FORMAT_ERROR,
    URL_ERROR,
    DESTINATION_ERROR,
    VALIDATION_ERROR,
    OK;

    public String value() {
        return name();
    }

    public static Code fromValue(String v) {
        return valueOf(v);
    }

}
