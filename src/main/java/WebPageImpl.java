import java.util.*;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.util.regex.*;

public class WebPageImpl implements WebPage {
    public static final String EMAIL_PATTERN = "\\b[a-z_.]{1,}@[a-z.]*[a-z]{1,3}\\b";
    private String url;
    private Document doc;
    private Set<String> emails;
    private Set<String> links;

    public WebPageImpl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Set<String> getLinks() {
        if(links == null) {
            String attr = "href";
            links = new HashSet<String>();
            Elements jLinks = getDocument().getElementsByAttribute(attr);
            for(Element link : jLinks) {
                links.add(link.attr(attr));
            }
            links = Collections.unmodifiableSet(links);
        }
        return links;
    }

    @Override
    public Set<String> getEmails() {
        if(emails == null) {
            emails = new HashSet<String>();
            Matcher matcher = getEmailMatcher();
            while(matcher.find()) {
                emails.add(matcher.group());
            }
            emails = Collections.unmodifiableSet(emails);
        }
        return emails;
    }

    @Override
    public Document getDocument() {
        if(doc == null) {
            try{
                doc = Jsoup.connect(getUrl()).get();
            } catch(IOException e) {
                System.out.println("IOException thrown: " + e);
                e.printStackTrace();
                System.exit(0);
            }
        }
        return doc;
    }

    private Matcher getEmailMatcher() {
        return Pattern
            .compile(WebPageImpl.EMAIL_PATTERN,
                     Pattern.CASE_INSENSITIVE)
            .matcher(getDocument().toString());
    }
}
