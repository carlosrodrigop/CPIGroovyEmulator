
import org.apache.camel.TypeConversionException
import org.apache.camel.Exchange




class Message {
    private Map properties =[:]
    private Map headers = [:]
    private def bodyPayload
    Exchange exchange

    Message(exchange) {
        this.exchange = exchange
    }


    def <T> T getBody(Class<T> aClass) {
        try{
            def body = this.exchange.getIn().getBody(aClass)
        }catch(TypeConversionException e){
            println(e)
        }
        return body ?:null
/*        String className = aClass.getName()
        switch(className){
            case "java.lang.String":
                return new String(this.bodyPayload)
                break
            case "java.io.Reader":
                return new InputStreamReader(new ByteArrayInputStream(this.bodyPayload))
                break
            default:
                return bodyPayload
        }
        return bodyPayload*/
    }


    Object getBody() {
        return this.bodyPayload
    }


    void setBody(Object o) {
       bodyPayload = o
    }

   /* @Override
    Map<String, DataHandler> getAttachments() {
        return null
    }

    @Override
    void setAttachments(Map<String, DataHandler> map) {

    }*/


    Map<String, Object> getHeaders() {
        return this.headers
    }


    def <T> T getHeader(String s, Class<T> aClass) {
        return null
    }



    void setHeaders(Map<String, Object> map) {
        this.headers.putAll(map)
    }


    void setHeader(String s, Object o) {
        headers."$s" = o
    }

    @Override
    Map<String, Object> getProperties() {
        return properties
    }


    void setProperties(Map<String, Object> map) {
        properties.putAll(map)
    }

    void setProperty(String key, def value){
        properties."$key" = value
    }



    def getProperty(String pName){
        return properties."$pName"
    }


    long getBodySize() {
        return 0
    }


    long getAttachmentsSize() {
        return 0
    }

    /*Override
    void addAttachmentHeader(String s, String s1, AttachmentWrapper attachmentWrapper) {

    }

    @Override
    void setAttachmentHeader(String s, String s1, AttachmentWrapper attachmentWrapper) {

    }

    @Override
    String getAttachmentHeader(String s, AttachmentWrapper attachmentWrapper) {
        return null
    }

    @Override
    void removeAttachmentHeader(String s, AttachmentWrapper attachmentWrapper) {

    }

    @Override
    Map<String, AttachmentWrapper> getAttachmentWrapperObjects() {
        return null
    }

    @Override
    void setAttachmentWrapperObjects(Map<String, AttachmentWrapper> map) {

    }

    @Override
    void addAttachmentObject(String s, AttachmentWrapper attachmentWrapper) {

    }

    @Override
    List<SoapHeader> getSoapHeaders() {
        return null
    }

    @Override
    void setSoapHeaders(List<SoapHeader> list) {

    }

    @Override
    void clearSoapHeaders() {

    }

    @Override
    @Deprecated
    void addAttachmentHeader(String s, String s1, org.apache.camel.Attachment attachment) {

    }

    @Override
    @Deprecated
    void setAttachmentHeader(String s, String s1, org.apache.camel.Attachment attachment) {

    }

    @Override
    @Deprecated
    String getAttachmentHeader(String s, org.apache.camel.Attachment attachment) {
        return null
    }

    @Override
    void removeAttachmentHeader(String s, org.apache.camel.Attachment attachment) {

    }

    @Override
    @Deprecated
    Map<String, org.apache.camel.Attachment> getAttachmentObjects() {
        return null
    }

    @Override
    @Deprecated
    void setAttachmentObjects(Map<String, org.apache.camel.Attachment> map) {

    }

    @Override
    @Deprecated
    void addAttachmentObject(String s, org.apache.camel.Attachment attachment) {

    }
    */

    public String toString() {
        return "{" +
                "\t\"properties\": \"$properties\",\r\n" +
                "\t\"headers\": \"$headers\",\r\n" +
                "\t\"bodyPayload\": \"$bodyPayload\"\r\n" +
                '}'
    }
}
