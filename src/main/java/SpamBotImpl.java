import java.util.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.*;

public class SpamBotImpl implements SpamBot{
	private Set<String> links;
	private Set<String> emails;
	private String seedUrl="";	
	private int threads; // needs to be private = get a getter;
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
		String emals = "./emails.txt";
		File file = new File(emals);
		try{
			file.createNewFile();
		} catch(IOException e){
			System.out.println("File already exists or failed to create file");
		}
		
		WebPage wp = new WebPageImpl(getSeed());
		Set<String> links = wp.getLinks();
		Set<String> emails = wp.getEmails();
	}	
//	@Override
//	public Set<String> getEMails(){
//	}
}