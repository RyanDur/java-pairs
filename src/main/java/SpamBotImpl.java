import java.util.*;
import java.net.URL;
import java.net.MalformedURLException;

public class SpamBotImpl implements SpamBot{
	private String seedUrl="";	
	protected int threads; // needs to be private = get a getter;
	private int numOfThreads;
	
	public int getNumOfThreads(){
		return this.numOfThreads;
	}
	
	@Override
	public String getSeed(){
		return this.seedUrl;
	}
	
	@Override
	public void setSeed(String seedUrl) {
		try {
			URL url = new URL(seedUrl);
			this.seedUrl = seedUrl;
		} catch (MalformedURLException e) {
			this.seedUrl = "not a URL";
		}
	}
	
	@Override
	public void setThreads(int count){
		this.numOfThreads = count;
	}
	
	@Override
	public void scanSite(){
		WebPage[] wpThreads = new WebPageImpl(numOfThreads);
		Thread[] threadArray = new Thread(numOfThreads);
		int i = 0;
		for(WebPage wp : wpThreads){
			wp = new WebPageImpl();
			threadArray[i] = new Thread(wp);
		}
	}	
	//@Override
	//public Set<String> getEMails(){
	//}
}