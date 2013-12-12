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
	
	public Set<String> getEmails(){
		return this.emails;
	}
	
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
	public void scanSite() throws FileNotFoundException, IOException{
		String emailsPath = "."+File.separator + "emails.txt";
		File file = new File(emailsPath);
		try{
			file.createNewFile();
		} catch(IOException e){
			System.out.println("File already exists or failed to create file");
		}
		
		WebPage wp = getWebPage();
		Set<String> emails = wp.getEmails();
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		String[] emailArray = emails.toArray(new String[emails.size()]);
		
		for(int s = 0; s < emailArray.length; s++){
			bw.write(emailArray[s]+"\n");
		} 
		bw.close();
		
	}

	public WebPage getWebPage(){
		return new WebPageImpl(getSeed());
	}
//	@Override
//	public Set<String> getEMails(){
//	}
}