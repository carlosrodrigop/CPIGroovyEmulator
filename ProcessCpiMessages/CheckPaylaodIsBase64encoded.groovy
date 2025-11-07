/*------------------------------------------------------------------------------------------------*/
/* Please copy this one and create a new with a new name always that you will create a new script */
/*------------------------------------------------------------------------------------------------*/
/* the below scripts are obligatory just in a local IDE not copy these imports to SAP CI */
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange
import Message
/*END LOCAL IMPORTS----------------------------------------------------------------------------------------*/
/* add below here the imports that you will need on sap CI and not forget to cpy them when you will move it
 ----------------------------------------------------------------------------------------------------------*/


//********************************************************************
//--- Main Class ( not copy it to CPI ---
//  just fill the properties or change the payload that you need use to  process
// and fill properties or headers according your process

def body = new File("C:/teste/encodedb64.txt").text
CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)


exchange.getIn().setBody(body)

Message message = new Message(exchange)
Map properties = ["propertyExample1":"value1", "propertyExample2":"value2"]
Map headers = ["Content-Type":"application/xml", "customHeader":"custom value"]

message.setHeaders(headers)
message.setProperties(properties)
message = processData(message)

println(message)

//-----END Main class ( remember to not use it on CPI ) --------
//********************************************************************

//------START THE POINT THAT NEED BE COPIED TO CPI ( Attention to the imports that will need be on CPI too )
//----------------------------------------------------------------------------------------------------------
def Message processData(Message msg){
    def body = msg.getBody(Reader)
    def input = body.text

    if (!input || input.length() % 4 != 0) return false
    if (!input.matches('^[A-Za-z0-9+/]+={0,2}$')) return false

    try {
        Base64.decoder.decode(input)
        msg.setProperty("BASE64encoded","yes")
    } catch (IllegalArgumentException e) {
        msg.setProperty("BASE64encoded","no")
    }


  return msg
}