/**
 * CreateShareFile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package zju.onlinedoc.grid.userLoginService.stubs;

public class CreateShareFile  implements java.io.Serializable {
    private int[] fileIds;
    private java.lang.String[] userNames;
    private int type;

    public CreateShareFile() {
    }

    public CreateShareFile(
           int[] fileIds,
           int type,
           java.lang.String[] userNames) {
           this.fileIds = fileIds;
           this.userNames = userNames;
           this.type = type;
    }


    /**
     * Gets the fileIds value for this CreateShareFile.
     * 
     * @return fileIds
     */
    public int[] getFileIds() {
        return fileIds;
    }


    /**
     * Sets the fileIds value for this CreateShareFile.
     * 
     * @param fileIds
     */
    public void setFileIds(int[] fileIds) {
        this.fileIds = fileIds;
    }

    public int getFileIds(int i) {
        return this.fileIds[i];
    }

    public void setFileIds(int i, int _value) {
        this.fileIds[i] = _value;
    }


    /**
     * Gets the userNames value for this CreateShareFile.
     * 
     * @return userNames
     */
    public java.lang.String[] getUserNames() {
        return userNames;
    }


    /**
     * Sets the userNames value for this CreateShareFile.
     * 
     * @param userNames
     */
    public void setUserNames(java.lang.String[] userNames) {
        this.userNames = userNames;
    }

    public java.lang.String getUserNames(int i) {
        return this.userNames[i];
    }

    public void setUserNames(int i, java.lang.String _value) {
        this.userNames[i] = _value;
    }


    /**
     * Gets the type value for this CreateShareFile.
     * 
     * @return type
     */
    public int getType() {
        return type;
    }


    /**
     * Sets the type value for this CreateShareFile.
     * 
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateShareFile)) return false;
        CreateShareFile other = (CreateShareFile) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fileIds==null && other.getFileIds()==null) || 
             (this.fileIds!=null &&
              java.util.Arrays.equals(this.fileIds, other.getFileIds()))) &&
            ((this.userNames==null && other.getUserNames()==null) || 
             (this.userNames!=null &&
              java.util.Arrays.equals(this.userNames, other.getUserNames()))) &&
            this.type == other.getType();
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
        if (getFileIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFileIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFileIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUserNames() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUserNames());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUserNames(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getType();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateShareFile.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", ">createShareFile"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", "fileIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userNames");
        elemField.setXmlName(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", "userNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://zju.edu/onlinedoc/grid/userlogin/UserLogin", "type"));
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
