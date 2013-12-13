import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class TestSpamBot{
    private SpamBot spamBot;
    private String urlStr ="http://www.google.com"; //"something that isnt a URL"
    private int numOfThreads = 8;

    @Before
    public void beforeTest(){
        spamBot = spy(new SpamBotImpl());
        spamBot.setSeed(urlStr);
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
        assertThat(spamBot.getSeed(), is(equalTo(urlStr)));
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
        spamBot.setThreads(numOfThreads);
        assertEquals(spamBot.getNumOfThreads(), numOfThreads);
    }

    /**
     * Initiates the scanning process.
     */
    @Test
    public void shouldBeAbleToScanASite() throws FileNotFoundException, IOException {
        String fileDir = "./emails.txt";
        Set<String> testLinks = new HashSet<String>();
        Set<String> testEmails = new HashSet<String>();
        testEmails.add("foo");
        testEmails.add("bar");

        String[] emailArray = testEmails.toArray(new String[testEmails.size()]);

        WebPage mockWebPage = mock(WebPage.class);
        doReturn(mockWebPage).when(spamBot).getWebPage();
        when(mockWebPage.getLinks()).thenReturn(testLinks);
        when(mockWebPage.getEmails()).thenReturn(testEmails);

        spamBot.scanSite();
        File emails = getFile(fileDir);
        assertEquals(emailArray, getFromFile(fileDir));
        removeFile(emails);
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

    private File getFile(String filePath) {
        File file = new File(filePath);
        assertTrue(file.isFile());
        return file;
    }

    private void removeFile(File file) {
        file.delete();
        assertFalse(file.isFile());
    }

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
