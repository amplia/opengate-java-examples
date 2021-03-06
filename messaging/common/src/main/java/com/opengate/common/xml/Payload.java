//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.27 at 12:54:12 PM CEST 
//


package com.opengate.common.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{}bool"/>
 *           &lt;element ref="{}int8"/>
 *           &lt;element ref="{}int16"/>
 *           &lt;element ref="{}int32"/>
 *           &lt;element ref="{}uint8"/>
 *           &lt;element ref="{}uint16"/>
 *           &lt;element ref="{}uint32"/>
 *           &lt;element ref="{}float32"/>
 *           &lt;element ref="{}float64"/>
 *           &lt;element ref="{}str"/>
 *           &lt;element ref="{}boolArray"/>
 *           &lt;element ref="{}int8Array"/>
 *           &lt;element ref="{}int16Array"/>
 *           &lt;element ref="{}int32Array"/>
 *           &lt;element ref="{}uint8Array"/>
 *           &lt;element ref="{}uint16Array"/>
 *           &lt;element ref="{}uint32Array"/>
 *           &lt;element ref="{}float32Array"/>
 *           &lt;element ref="{}float64Array"/>
 *           &lt;element ref="{}strArray"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "boolOrInt8OrInt16"
})
@XmlRootElement(name = "payload")
public class Payload {

    @XmlElements({
        @XmlElement(name = "int32Array", type = Int32Array.class),
        @XmlElement(name = "int32", type = Int32 .class),
        @XmlElement(name = "float32Array", type = Float32Array.class),
        @XmlElement(name = "uint8Array", type = Uint8Array.class),
        @XmlElement(name = "int16", type = Int16 .class),
        @XmlElement(name = "boolArray", type = BoolArray.class),
        @XmlElement(name = "float64Array", type = Float64Array.class),
        @XmlElement(name = "bool", type = Bool.class),
        @XmlElement(name = "uint32Array", type = Uint32Array.class),
        @XmlElement(name = "int16Array", type = Int16Array.class),
        @XmlElement(name = "float32", type = Float32 .class),
        @XmlElement(name = "strArray", type = StrArray.class),
        @XmlElement(name = "uint16", type = Uint16 .class),
        @XmlElement(name = "uint32", type = Uint32 .class),
        @XmlElement(name = "str", type = Str.class),
        @XmlElement(name = "uint16Array", type = Uint16Array.class),
        @XmlElement(name = "float64", type = Float64 .class),
        @XmlElement(name = "uint8", type = Uint8 .class),
        @XmlElement(name = "int8", type = Int8 .class),
        @XmlElement(name = "int8Array", type = Int8Array.class)
    })
    protected List<Object> boolOrInt8OrInt16;

    /**
     * Gets the value of the boolOrInt8OrInt16 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the boolOrInt8OrInt16 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBoolOrInt8OrInt16().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Int32Array }
     * {@link Int32 }
     * {@link Float32Array }
     * {@link Uint8Array }
     * {@link Int16 }
     * {@link BoolArray }
     * {@link Float64Array }
     * {@link Bool }
     * {@link Uint32Array }
     * {@link Int16Array }
     * {@link Float32 }
     * {@link StrArray }
     * {@link Uint16 }
     * {@link Uint32 }
     * {@link Str }
     * {@link Uint16Array }
     * {@link Float64 }
     * {@link Uint8 }
     * {@link Int8 }
     * {@link Int8Array }
     * 
     * 
     */
    public List<Object> getBoolOrInt8OrInt16() {
        if (boolOrInt8OrInt16 == null) {
            boolOrInt8OrInt16 = new ArrayList<Object>();
        }
        return this.boolOrInt8OrInt16;
    }

}
