public class TestSpamBot{
	SpamBotImpl sbr;
	
	@BeforeClass
	public static beforeTest() throws Exception{
		sbr = new SpamBotImpl();
	}
	@AfterClass
	public static afterTest() throws Exception{
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
	@test
    public void testSetSeed() {
		sbr.setSeed("http://www.google.com");
		if(  "setseed()"  throwsException){
			assertEquals(sbr.seedUrl, null)
		} else {
		assertEquals(sbr.seedUrl, "http://www.google.com");
		//throws MalformedURLException;
		}
	}

    /**
     * Returns the seed URL. * @return the seed URL.
     */
	@test
    public String testGetSeed(){
		assertEquals(sbr.getSeed(), "http://www.google.com")
	}

    /**
     * Sets the number of threads.
     *
     * The user should be able to set the number of threads to be
     * used for running the crawlers.
     *
     * @param count the number of threads (i.e. crawlers) to start in parallel
     */
	@test
    public void testSetThreads(){
		sbr.setThreads(numThreads);
		if(sbr.getThreads()=null){
			assertEquals(calculate viability of limit threads available to false, 'unmanageableBySystemException' has been thrown);
		} else {
			assertEquals(sbr.getThreads(),  numThreads);
		}
		
	}

    /**
     * Initiates the scanning process.
     */
	@test
    public void testScanSite(){
		assertEquals(WebPageImpl is running);
		assertEquals(.rtf has been created and propperly populated)
		
	}

    /**
     * Returns all the emails gathered.
     *
     * This method should be executed only after the last crawlers
     * have stopped. If it is called before that point, its
     * behaviour is not defined.
     */
	@test
    public void testGetEMails(){
		Set<String>
		assertEquals(get and populate array with each line on .rtf);
	}
}