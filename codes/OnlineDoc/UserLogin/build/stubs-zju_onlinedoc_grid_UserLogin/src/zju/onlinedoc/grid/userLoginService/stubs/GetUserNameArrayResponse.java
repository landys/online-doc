/**
 * GetUserNameArrayResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package zju.onlinedoc.grid.userLoginService.stubs;

public class GetUserNameArrayResponse  implements java.io.Serializable {
    private zju.onlinedoc.meta.stubs.UserInfo[] getUserNameArrayReturn;

    public GetUserNameArrayResponse() {
    }

    public GetUserNameArrayResponse(
           zju.onlinedoc.meta.stubs.UserInfo[] getUserNameArrayReturn) {
           this.getUserNameArrayReturn = getUserNameArrayReturn;
    }


    /**
     * Gets the getUserNameArrayReturn value for this GetUserNameArrayResponse.
     * 
     * @return getUserNameArrayReturn
     */
    public zju.onlinedoc.meta.stubs.UserInfo[] getGetUserNameArrayReturn() {
        return getUserNameArrayReturn;
    }


    /**
     * Sets the getUserNameArrayReturn value for this GetUserNameArrayResponse.
     * 
     * @param getUserNameArrayReturn
     */
    public void setGetUserNameArrayReturn(zju.onlinedoc.meta.stubs.UserInfo[] getUserNameArrayReturn) {
        this.getUserNameArrayReturn = getUserNameArrayReturn;
    }

    public zju.onlinedoc.meta.stubs.UserInfo getGetUserNameArrayReturn(int i) {
        return this.getUserNameArrayReturn[i];
    }

    public void setGetUserNameArrayReturn(int i, zju.onlinedoc.meta.stubs.UserInfo _value) {
        this.getUserNameArrayReturn[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetUserNameArrayResponse)) return false;
        GetUserNameArrayResponse other = (GetUserNameArrayResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getUserNameArrayReturn==null && other.getGetUserNameArrayReturn()==null) || 
             (this.getUserNameArrayReturn!=null &&
              java.util.Arrays.equals(this.getUserNameArrayReturn, other.getGetUserNameArrayReturn())));
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
        if (getGetUserNameArrayReturn() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGetUserNameArrayReturn());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGetUserNameArrayReturn(), i);
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
        new org.apache.axis.description.TypeDesc(GetUserNameArrayResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", ">getUserNameArrayResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getUserNameArrayReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", "getUserNameArrayReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://meta.onlinedoc.zju", "UserInfo"));
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
