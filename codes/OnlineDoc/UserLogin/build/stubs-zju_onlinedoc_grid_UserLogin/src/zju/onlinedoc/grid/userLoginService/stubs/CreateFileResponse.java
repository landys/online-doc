/**
 * CreateFileResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package zju.onlinedoc.grid.userLoginService.stubs;

public class CreateFileResponse  implements java.io.Serializable {
    private int createFileReturn;

    public CreateFileResponse() {
    }

    public CreateFileResponse(
           int createFileReturn) {
           this.createFileReturn = createFileReturn;
    }


    /**
     * Gets the createFileReturn value for this CreateFileResponse.
     * 
     * @return createFileReturn
     */
    public int getCreateFileReturn() {
        return createFileReturn;
    }


    /**
     * Sets the createFileReturn value for this CreateFileResponse.
     * 
     * @param createFileReturn
     */
    public void setCreateFileReturn(int createFileReturn) {
        this.createFileReturn = createFileReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateFileResponse)) return false;
        CreateFileResponse other = (CreateFileResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.createFileReturn == other.getCreateFileReturn();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getCreateFileReturn();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateFileResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", ">createFileResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createFileReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", "createFileReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
