import static org.junit.Assert.*;

import java.net.URL;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSpamBot{
	SpamBot sbr; 
	String urlStr ="http://www.google.com"; //"something that isnt a URL" 
	int numOfThreads = 8;
	@Before
	public void beforeTest(){
		sbr = new SpamBotImpl();
		sbr.setSeed(urlStr);
	}
	@After
	public void afterTest(){
		sbr = null;
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
    public void testSetSeed() {
		try {
				URL url = new URL(urlStr);
				assertEquals(sbr.getSeed(), urlStr);
		} catch (MalformedURLException e) {
				assertEquals(sbr.getSeed(), "not a URL");
		}
		
		
	}

    /**
     * Returns the seed URL. * @return the seed URL.
     */
	@Test
    public void testGetSeed(){
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
    public void testScanSite(){
		//assertEquals(WebPageImpl is running);
		//assertEquals(.rtf has been created and properly populated)
		assertEquals(1,2);
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
		assertEquals(1,2);
	}
}