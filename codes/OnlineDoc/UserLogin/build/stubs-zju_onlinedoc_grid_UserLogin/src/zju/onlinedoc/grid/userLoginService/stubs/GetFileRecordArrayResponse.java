/**
 * GetFileRecordArrayResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package zju.onlinedoc.grid.userLoginService.stubs;

public class GetFileRecordArrayResponse  implements java.io.Serializable {
    private zju.onlinedoc.meta.stubs.FileRecord[] getFileRecordArrayReturn;

    public GetFileRecordArrayResponse() {
    }

    public GetFileRecordArrayResponse(
           zju.onlinedoc.meta.stubs.FileRecord[] getFileRecordArrayReturn) {
           this.getFileRecordArrayReturn = getFileRecordArrayReturn;
    }


    /**
     * Gets the getFileRecordArrayReturn value for this GetFileRecordArrayResponse.
     * 
     * @return getFileRecordArrayReturn
     */
    public zju.onlinedoc.meta.stubs.FileRecord[] getGetFileRecordArrayReturn() {
        return getFileRecordArrayReturn;
    }


    /**
     * Sets the getFileRecordArrayReturn value for this GetFileRecordArrayResponse.
     * 
     * @param getFileRecordArrayReturn
     */
    public void setGetFileRecordArrayReturn(zju.onlinedoc.meta.stubs.FileRecord[] getFileRecordArrayReturn) {
        this.getFileRecordArrayReturn = getFileRecordArrayReturn;
    }

    public zju.onlinedoc.meta.stubs.FileRecord getGetFileRecordArrayReturn(int i) {
        return this.getFileRecordArrayReturn[i];
    }

    public void setGetFileRecordArrayReturn(int i, zju.onlinedoc.meta.stubs.FileRecord _value) {
        this.getFileRecordArrayReturn[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetFileRecordArrayResponse)) return false;
        GetFileRecordArrayResponse other = (GetFileRecordArrayResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getFileRecordArrayReturn==null && other.getGetFileRecordArrayReturn()==null) || 
             (this.getFileRecordArrayReturn!=null &&
              java.util.Arrays.equals(this.getFileRecordArrayReturn, other.getGetFileRecordArrayReturn())));
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
        if (getGetFileRecordArrayReturn() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGetFileRecordArrayReturn());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGetFileRecordArrayReturn(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetFileRecordArrayResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", ">getFileRecordArrayResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getFileRecordArrayReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", "getFileRecordArrayReturn"));
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
