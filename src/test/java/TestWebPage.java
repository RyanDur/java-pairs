import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import java.util.*;

public class TestWebPage {
    private WebPage webPage;
    private String url = "http://en.wikipedia.org/";
    private Document document;
    private String link = "http://example.com/image.ico";
    private String link1 = "http://clojurescriptkoans.com/";
    private String email = "asdfgsdf@sfgsfb.com";
    private String email1 = "csfgsdfg@sfgsdfgd.com";

    private String PageProvider1() {

        StringBuffer html = new StringBuffer();

        html.append("<!DOCTYPE html>");
        html.append("<html lang=\"en\">");
        html.append("<head>");
        html.append("<link rel=\"icon\" href=\""+ link +"\" />");
        html.append("</head>");
        html.append("<body>");
        html.append("something " + email);
        html.append("<a href=\""+ link1 +"\"></a>");
        html.append("<div data-email=\""+ email1 +"\"></a>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    @Before
    public void setup() {
        webPage = spy(new WebPageImpl(url));
    }

    @Test
    public void shouldRetrieveAllTheLinksOnthePage() {
        doReturn(Jsoup.parse(PageProvider1())).when(webPage).getDocument();
        Set<String> links = new HashSet<String>();
        links.add(link);
        links.add(link1);

        assertThat(links, is(equalTo(webPage.getLinks())));
    }

    @Test
    public void shouldRetrieveAllTheEmailsOnthePage() {
        doReturn(Jsoup.parse(PageProvider1())).when(webPage).getDocument();
        Set<String> emails = new HashSet<String>();
        emails.add(email);
        emails.add(email1);

        assertEquals(emails, webPage.getEmails());
    }
}
