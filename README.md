# PubMedXMLRetriever
 This Java code will complete the following:
  1. Read request information from an XML file
  2. Send requests to a remote server through the user API provided by the PubMed server
  3. Get the returned XML files from the remote server, and then analyze the files and extract information
  4. Write the useful information received from the remote server to the local XML file
  
  
<------------------------------------------------------------------------------------------------------------>

 Certain ideas to add
  1. Split method into object classes to reduce time complexity, limit coupling, and avoid iteration
  2. Have the main method take in input for the query and place that query in the CGI language to obtain specified ArticleTitles based on query

  ***Need extract PMID from pubmed to be able to link attributes and prevent errors from missing attributes***
  
       
