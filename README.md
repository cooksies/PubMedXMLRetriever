# PubMedXMLRetriever
 This Java code will complete the following:
  1. Read request information from an XML file
  2. Send requests to a remote server through the user API provided by the PubMed server
  3. Get the returned XML files from the remote server, and then analyze the files and extract information
  4. Write the useful information received from the remote server to the local XML file
  
  
<------------------------------------------------------------------------------------------------------------>

The main class will be the PubMedRetriever.java
The helper classes will be Parse.java, HttpRequester.java, and the XMLresult.java

You may need to replace the file path for the "4020a1-datasets.xml" in PubMedRetriever.java(line: 25) to the
appropriate file path of the xml file on your local machine.
Similarly, you may also need to replace the file path for the "group5_result.xml" in XMLresult.java(line: 18) to the
appropriate file path of the xml file on your local machine.

Running the entire program to successfully fill the "group5_result.xml" file may take a long time to process.
We advise creating small tests by decreasing the number of iterations for the for-loop in the HttpRequester.java file.

<------------------------------------------------------------------------------------------------------------>

To run PubMedXMLRetriever in CMD:

1. Open a CMD window and cd to the directory of the java code (cd "path")
2. Compile all the java classes (javac "classname.java")
3. Enter in java PubMedXMLRetriever.java to run the main class
