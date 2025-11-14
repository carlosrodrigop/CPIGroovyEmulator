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


GroovyShell shell = new GroovyShell()

Script script = shell.parse(new File("C:/Users/z003xkny/CPIGroovyEmulator/ProcessCpiMessages/XMLParser.groovy"))

def body = new File("C:/Users/z003xkny/CPIGroovyEmulator/payloadExamples/TesteXML.xml").text
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
 static Message processData(Message msg){

  //fill your logic here
    def breader =  msg.getBody(Reader)
     def root = new XmlSlurper().parse(breader)

    root.CHILDCOMPLEX.TABLEDATA.each{
        println(" TextField = ${it.FIELD.text()} / NumField = ${it.NUM.text()}")

    }

  return msg
}