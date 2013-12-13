import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.*;

import static org.mockito.Mockito.*;

import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSpamBot{
    SpamBot sbr;
    String urlStr ="http://www.google.com"; //"something that isnt a URL"
    int numOfThreads = 8;

    @Before
    public void beforeTest(){
        sbr = spy(new SpamBotImpl());
        sbr.setSeed(urlStr);
    }

    /**
     * Sets the seed.
     *
     * The seed is the very first URL that has to be given to the
     * system. The associated web page is the starting point for
     * the whole process of fetching web pages, extracting their
     * links and email addresses, and fetching more web pages.
     *
     * @param seedUrl the first URL to fetch and analyse
     */
    @Test
    public void testSetSeed() throws MalformedURLException {
        assertEquals(sbr.getSeed(), urlStr);
    }

    /**
     * Sets the number of threads.
     *
     * The user should be able to set the number of threads to be
     * used for running the crawlers.
     *
     * @param count the number of threads (i.e. crawlers) to start in parallel
     */
    @Test
    public void testSetThreads(){
        sbr.setThreads(numOfThreads);
        assertEquals(sbr.getNumOfThreads(), numOfThreads);
    }

    /**
     * Initiates the scanning process.
     */
    @Test
    public void shouldBeAbleToScanASite() throws FileNotFoundException, IOException {
        String fileDir = "./emails.txt";
        File emails = new File(fileDir);
        assertTrue(emails.isFile());

        WebPage wp = mock(WebPage.class);
        Set<String> testLinks = new HashSet<String>();
        Set<String> testEmails = new HashSet<String>();
        testEmails.add("foo");
        testEmails.add("bar");
        doReturn(wp).when(sbr).getWebPage();
        when(wp.getLinks()).thenReturn(testLinks);
        when(wp.getEmails()).thenReturn(testEmails);

        sbr.scanSite();
        String[] emailArray = wp.getEmails().toArray(new String[testEmails.size()]);

        assertEquals(emailArray, getFromFile(fileDir));
    }

    /**
     * Returns all the emails gathered.
     *
     * This method should be executed only after the last crawlers
     * have stopped. If it is called before that point, its
     * behaviour is not defined.
     */
    @Test
    public void testGetEMails(){
        //Set<String>
        //assertEquals(get and populate array with each line on .rtf);
        //              assertEquals(1,2);
    }
    //@cleanup destroy the file

    private String[] getFromFile(String fileDir) throws FileNotFoundException, IOException{
        FileInputStream fin =  new FileInputStream(fileDir);
        BufferedReader myInput = new BufferedReader(new InputStreamReader(fin));
        ArrayList<String> sb = new ArrayList<String>();
        String thisLine = null;
        while ((thisLine = myInput.readLine()) != null) {
            sb.add(thisLine);
        }
        return sb.toArray(new String[sb.size()]);
    }
}
