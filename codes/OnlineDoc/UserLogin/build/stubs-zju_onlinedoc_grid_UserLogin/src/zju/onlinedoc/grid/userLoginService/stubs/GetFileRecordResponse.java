/**
 * GetFileRecordResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package zju.onlinedoc.grid.userLoginService.stubs;

public class GetFileRecordResponse  implements java.io.Serializable {
    private zju.onlinedoc.meta.stubs.FileRecord getFileRecordReturn;

    public GetFileRecordResponse() {
    }

    public GetFileRecordResponse(
           zju.onlinedoc.meta.stubs.FileRecord getFileRecordReturn) {
           this.getFileRecordReturn = getFileRecordReturn;
    }


    /**
     * Gets the getFileRecordReturn value for this GetFileRecordResponse.
     * 
     * @return getFileRecordReturn
     */
    public zju.onlinedoc.meta.stubs.FileRecord getGetFileRecordReturn() {
        return getFileRecordReturn;
    }


    /**
     * Sets the getFileRecordReturn value for this GetFileRecordResponse.
     * 
     * @param getFileRecordReturn
     */
    public void setGetFileRecordReturn(zju.onlinedoc.meta.stubs.FileRecord getFileRecordReturn) {
        this.getFileRecordReturn = getFileRecordReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetFileRecordResponse)) return false;
        GetFileRecordResponse other = (GetFileRecordResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getFileRecordReturn==null && other.getGetFileRecordReturn()==null) || 
             (this.getFileRecordReturn!=null &&
              this.getFileRecordReturn.equals(other.getGetFileRecordReturn())));
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
        if (getGetFileRecordReturn() != null) {
            _hashCode += getGetFileRecordReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetFileRecordResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", ">getFileRecordResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getFileRecordReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", "getFileRecordReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://meta.onlinedoc.zju", "FileRecord"));
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
