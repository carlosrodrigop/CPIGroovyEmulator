import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

//********************************************************************
//--- Main Class ( not copy it to CPI ---
//  just fill the properties or change the payload that you need use to  process
// and fill properties or headers according your process

def body = new File("c://teste//idoc_tanker.xml")
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
def Message processData( Message msg){
  //fill your logic here
    msg.setProperty("newP", "new value")
    msg.setHeader("newH","new value")
    msg.setBody("new body made!!!")
  return msg
}