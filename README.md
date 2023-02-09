# PubMedXMLRetriever
 This Java code will complete the following:
  1. Read request information from an XML file
  2. Send requests to a remote server through the user API provided by the PubMed server
  3. Get the returned XML files from the remote server, and then analyze the files and extract information
  4. Write the useful information received from the remote server to the local XML file
  
  
<------------------------------------------------------------------------------------------------------------>

To run and compile the PubMedXMLRetriever, you wil first have to download the .zip file of the code:
[PubMedXMLRetriever-main.zip](https://github.com/cooksies/PubMedXMLRetriever/files/10691591/PubMedXMLRetriever-main.zip)

The main class will be the PubMedRetriever.java
The helper classes will be Parse.java, HttpRequester.java, and the XMLresult.java

You may need to replace the file path for the "4020a1-datasets.xml" in PubMedRetriever.java(line: 25) to the
appropriate file path of the xml file on your local machine.
Similarly, you may also need to replace the file path for the "group5_result.xml" in XMLresult.java(line: 18) to the
appropriate file path of the xml file on your local machine.
