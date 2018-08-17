package cucumberGradle;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import org.apache.xmlbeans.XmlException;
import org.junit.Assert;
import org.junit.Test;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.support.SoapUIException;
import com.eviware.soapui.tools.SoapUITestCaseRunner;

public class SoapProjects {
		
	public void runSoapUI(String projectloc, String testsuitename, String testcasename, String setProjProperties) 
	{
		System.out.println("Inside runSoapUI");	
		
		try {
			 SoapUITestCaseRunner SoapUITestCaseRunner= new SoapUITestCaseRunner();
			 SoapUITestCaseRunner.setProjectFile(projectloc);
		     SoapUITestCaseRunner.setTestSuite(testsuitename);
		     SoapUITestCaseRunner.setTestCase(testcasename);
		     
		     //***** Set project properties    
			 List<String> properties=new ArrayList<>(Arrays.asList(setProjProperties.split("!")));             
			 for (String a : properties)
			     System.out.println(a);                       
			 SoapUITestCaseRunner.setProjectProperties(properties.toArray(new String[properties.size()]));                                       
			 //SoapUITestCaseRunner.setPrintReport(true);
			 SoapUITestCaseRunner.run();            
		     
		 } catch (Exception e) {
		
		     System.err.println("soapui execution failed!");            
			 //e.printStackTrace();
			 //Assert.fail("SOAP Test: FAILED: Exception:" + e.toString());
			 Assert.fail("SOAP Test: FAILED: Exception:");
			 //assertFalse(true);
		 }	
	}
	
	public String[] getValueFromSoapUI(String projectloc, String testsuitename, String testcasename, String setProjProperties) 
	{
		System.out.println("Inside getFromSoapUI");	
		
		try {
			 SoapUITestCaseRunner SoapUITestCaseRunner= new SoapUITestCaseRunner();
			 SoapUITestCaseRunner.setProjectFile(projectloc);
		     SoapUITestCaseRunner.setTestSuite(testsuitename);
		     SoapUITestCaseRunner.setTestCase(testcasename);
		     
		     //***** Set project properties    
			 List<String> properties=new ArrayList<>(Arrays.asList(setProjProperties.split("!")));             
			 for (String a : properties)
			     System.out.println(a);                       
			 SoapUITestCaseRunner.setProjectProperties(properties.toArray(new String[properties.size()]));                                       
			 SoapUITestCaseRunner.run();
		 
			 //***** Get project properties  
		     String[] projproperties  = SoapUITestCaseRunner.getProjectProperties();
		     return projproperties;
		
		 } catch (Exception e) {
			 System.err.println("soapui execution failed!");            
			 Assert.fail("SOAP Test: FAILED: Exception:");
			 return null;
			 }			
	}
	
    public void executeSoapTestCase(String projectName, String testSuite, String testName)
    {
        try
        {
            modifyTrustStore();
            //ResourceHelper resourceHelper = new ResourceHelper();
            //WsdlProject project = new WsdlProject(this.getXMLFileResourceList("soapui/", projectName).get(0));
            WsdlProject project = new WsdlProject(projectName);
            project.setAbortOnError(true);
            project.setTimeout(120);
            TestSuite suite = project.getTestSuiteByName(testSuite);
            TestCase testCase = suite.getTestCaseByName(testName);
            runTestCase(testCase, project);
            project.release();
        }
        catch (IOException e)
        {
            Assert.fail("SOAP Test: FAILED: Exception:" + e.toString());
        }
        catch (XmlException e)
        {
            Assert.fail("SOAP Test: FAILED: Exception:" + e.toString());
        }
        catch (SoapUIException e)
        {
            Assert.fail("SOAP Test: FAILED: Exception:" + e.toString());
        }
        catch (KeyManagementException e)
        {
            Assert.fail("SOAP Test: FAILED: Exception:" + e.toString());
        }
        catch (NoSuchAlgorithmException e)
        {
            Assert.fail("SOAP Test: FAILED: Exception:" + e.toString());
        }
    }

	
	
    //Modifies Trust store to allow Unsigned Certificates.
    public void modifyTrustStore() throws NoSuchAlgorithmException, KeyManagementException
    {
        TrustManager[] trustAllCerts = new TrustManager[]
        {new X509TrustManager()
        {
            public java.security.cert.X509Certificate[] getAcceptedIssuers()
            {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
            {
            }

            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
            {
            }
        } };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
    
    /**
     * Method to obtain a list of xml files within a jar for a given folder.
     * Method will traverse sub folders. A Specific file name can be provided,
     * and if found is the only file added to the list.
     * 
     * @param resourceFolderPath
     * @param fileToTest
     * @return List
     */
    public List<String> getXMLFileResourceList(String resourceFolderPath, String fileToTest)
    {
        List<String> listOfFiles = new ArrayList<String>();

        try
        {
            CodeSource src = getClass().getProtectionDomain().getCodeSource();
            if (src != null)
            {
                URL jar = src.getLocation();
                ZipInputStream zip = new ZipInputStream(jar.openStream());

                while (true)
                {
                    ZipEntry e = zip.getNextEntry();

                    if (e == null)
                    {
                        break;
                    }

                    String name = e.getName();

                    if (name.startsWith(resourceFolderPath) && name.endsWith(".xml"))
                    {
                        if (name.contains(fileToTest + ".xml"))
                        {
                            if (fileToTest != null && !fileToTest.isEmpty())
                            {
                                if (name.contains(fileToTest))
                                {
                                    listOfFiles.add(name);
                                }
                            }
                            else
                            {
                                listOfFiles.add(name);
                            }
                        }
                    }
                }
                zip.close();
            }

        }
        catch (IOException e)
        {
            //LOG.info("Could not find Test Properties. Error: " + e);
        }

        return listOfFiles;
    }
    
    public void runTestCase(TestCase testCase, WsdlProject project)
    {
        
        //project.setPropertyValue("Environment", getEnvironment());
        //project.getProperty("ESBEnvironment")

        com.eviware.soapui.model.testsuite.TestCaseRunner testRunner = testCase.run(new PropertiesMap(), false);

        if (testRunner != null && testRunner.getStatus() == TestRunner.Status.RUNNING)
        {
            testRunner.waitUntilFinished();
        }

        com.eviware.soapui.model.testsuite.TestRunner.Status status = testRunner.getStatus();
        String reason = testRunner.getReason();

        testRunner = null;

        Assert.assertEquals(reason, TestRunner.Status.FINISHED, status);
    }

}
	