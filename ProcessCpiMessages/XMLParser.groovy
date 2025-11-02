/*------------------------------------------------------------------------------------------------*/
/* Please copy this one and create a new with a new name always that you will create a new script */
/*------------------------------------------------------------------------------------------------*/
/* the below scripts are obligatory just in a local IDE not copy these imports to SAP CI */

import groovy.xml.XmlSlurper
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

import java.nio.file.Paths
import java.nio.file.Files


/*END LOCAL IMPORTS----------------------------------------------------------------------------------------*/
/* add below here the imports that you will need on sap CI and not forget to cpy them when you will move it
 ----------------------------------------------------------------------------------------------------------*/


//********************************************************************
//--- Main Class ( not copy it to CPI ---
//  just fill the properties or change the payload that you need use to  process
// and fill properties or headers according your process

static void main(String[] args) {
    Message message
    //def path = Paths.get("C://teste//idoc_tanker.xml")
    def body =  new File("C:/teste/idoc_tanker.xml").text
    CamelContext context = new DefaultCamelContext()
    Exchange exchange = new DefaultExchange(context)


    exchange.getIn().setBody(body)


    message = new Message(exchange)
    Map properties = ["propertyExample1":"value1", "propertyExample2":"value2"]
    Map headers = ["Content-Type":"application/xml", "customHeader":"custom value"]

    message.setHeaders(headers)
    message.setProperties(properties)
    def messageOut = processData(message)

    println(messageOut)
}
//-----END Main class ( remember to not use it on CPI ) --------
//********************************************************************

//------START THE POINT THAT NEED BE COPIED TO CPI ( Attention to the imports that will need be on CPI too )
//----------------------------------------------------------------------------------------------------------
 static Message processData(Message msg){

  //fill your logic here
    def breader =  msg.getBody(Reader)
     def root = new XmlSlurper().parse(breader)

    root."**".findAll{it.name() == "ZTANKER_HEADER"}.each{
        println("${it}")
    }

  return msg
}