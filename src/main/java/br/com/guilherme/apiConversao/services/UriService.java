package br.com.guilherme.apiConversao.services;

import br.com.guilherme.apiConversao.exceptionHandler.UrlPadraoException;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class UriService {
    public static URI geraUriCorretamente (String uriRequest){
        try {
            validaUrl(uriRequest);
            String myUrl = limpa(uriRequest);
            System.out.println(myUrl);
            URL url = new URL(myUrl);
            String nullFragment = null;
            URI uri = new URI(url.getProtocol(),url.getUserInfo(), url.getHost(),url.getPort(), url.getPath(), url.getQuery(), nullFragment);
            System.out.println("URI " + uri.toString() + " is OK");
            return uri;
        } catch (MalformedURLException e) {
            System.out.println("URL " + uriRequest + " is a malformed URL");
        } catch (URISyntaxException e) {
            System.out.println("URI " + uriRequest + " is a malformed URL");
        }
        return null;
    }

    private static void validaUrl(String url){
        if(url == null || !url.startsWith("url=")){
            throw new UrlPadraoException("O parametro url= nao foi passado Ou esta incorreto");
        }
    }

    private static String limpa(String url) {
        return url.replace("url=","").replace("%20"," ");
    }
}
