/**
 * FileRecord.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package zju.onlinedoc.meta.stubs;

public class FileRecord  implements java.io.Serializable {
    private java.lang.String fileCreateDate;
    private int fileId;
    private java.lang.String fileName;
    private int fileType;

    public FileRecord() {
    }

    public FileRecord(
           java.lang.String fileCreateDate,
           int fileId,
           java.lang.String fileName,
           int fileType) {
           this.fileCreateDate = fileCreateDate;
           this.fileId = fileId;
           this.fileName = fileName;
           this.fileType = fileType;
    }


    /**
     * Gets the fileCreateDate value for this FileRecord.
     * 
     * @return fileCreateDate
     */
    public java.lang.String getFileCreateDate() {
        return fileCreateDate;
    }


    /**
     * Sets the fileCreateDate value for this FileRecord.
     * 
     * @param fileCreateDate
     */
    public void setFileCreateDate(java.lang.String fileCreateDate) {
        this.fileCreateDate = fileCreateDate;
    }


    /**
     * Gets the fileId value for this FileRecord.
     * 
     * @return fileId
     */
    public int getFileId() {
        return fileId;
    }


    /**
     * Sets the fileId value for this FileRecord.
     * 
     * @param fileId
     */
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }


    /**
     * Gets the fileName value for this FileRecord.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this FileRecord.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the fileType value for this FileRecord.
     * 
     * @return fileType
     */
    public int getFileType() {
        return fileType;
    }


    /**
     * Sets the fileType value for this FileRecord.
     * 
     * @param fileType
     */
    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FileRecord)) return false;
        FileRecord other = (FileRecord) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fileCreateDate==null && other.getFileCreateDate()==null) || 
             (this.fileCreateDate!=null &&
              this.fileCreateDate.equals(other.getFileCreateDate()))) &&
            this.fileId == other.getFileId() &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            this.fileType == other.getFileType();
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
        if (getFileCreateDate() != null) {
            _hashCode += getFileCreateDate().hashCode();
        }
        _hashCode += getFileId();
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        _hashCode += getFileType();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FileRecord.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://meta.onlinedoc.zju", "FileRecord"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileCreateDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://meta.onlinedoc.zju", "fileCreateDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://meta.onlinedoc.zju", "fileId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://meta.onlinedoc.zju", "fileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://meta.onlinedoc.zju", "fileType"));
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
