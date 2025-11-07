/*************************************************************************************************/
//                     <<< IMPORTANT POST CLONE ACTIVITIES >>>>
// RUN THE BELOW COMMANDS IN THE MAVEN TO ADD THE LOCAL LIBRARIES FROM SAP changing the <YOURLOCALPROJECTDIRECTORYPATH> for your local path folder to the project
//mvn install:install-file -Dfile=<YOURLOCALPROJECTDIRECTORYPATH>\CPIGroovyEmulator\SAPLibs\cloud.integration.script.apis-2.7.1.jar  -DgroupId=com.sap.cloud.integration  -DartifactId=script-apis  -Dversion=2.7.1  -Dpackaging=jar
//mvn install:install-file -Dfile=<YOURLOCALPROJECTDIRECTORYPATH>\CPIGroovyEmulator\SAPLibs\com.sap.it.public.generic.api-2.25.0.jar  -DgroupId=com.sap.gateway.ip.core.customdev.util  -DartifactId=generic-api  -Dversion=2.25.0  -Dpackaging=jar
//mvn install:install-file -Dfile=<YOURLOCALPROJECTDIRECTORYPATH>\CPIGroovyEmulator\SAPLibs\javax.activation_1.1.0.v201211130549.jar  -DgroupId=javax.activation  -DartifactId=activation  -Dversion=1.1.0.v201211130549  -Dpackaging=jar
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

def body = new File("C:/teste/LMSBulkEntitlementCreation.xml").text
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
  //fill your logic here
    msg.setProperty("newP", "new value")
    msg.setHeader("newH","new value")
    msg.setBody("new body made!!!")
  return msg
}