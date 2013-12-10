import java.util.*;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.util.regex.*;

public class WebPageImpl implements WebPage {
    //    public static final String EMAIL_PATTERN = "^[a-zA-Z](?!.*\\s).{4,15}@[a-z]{3,10}[.][a-z]{2,5}";
    public static final String EMAIL_PATTERN = "asdfgsdf@sfgsfb.com";
    private String url;
    private Document doc;

    public WebPageImpl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Set<String> getLinks() {
        doc = getDocument();
        Set<String> links = new HashSet<String>();
        Elements jLinks = doc.getElementsByAttribute("href");
        for(Element link : jLinks) {
            links.add(link.attr("href"));
        }
        return Collections.unmodifiableSet(links);
    }

    @Override
    public Set<String> getEmails() {
        doc = getDocument();
        Set<String> emails = new HashSet<String>();
        Matcher matcher = Pattern.compile(WebPageImpl.EMAIL_PATTERN).matcher(doc.toString());

        while(matcher.find()) {
            emails.add(matcher.group());
        }
        return Collections.unmodifiableSet(emails);
    }

    @Override
    public Document getDocument() {
        if(doc == null) {
            try{
                doc = Jsoup.connect(getUrl()).get();
            } catch(IOException e) {}
        }
        return doc;
    }
}
