package br.com.guilherme.apiConversao.services;

import br.com.guilherme.apiConversao.exceptionHandler.UrlPadraoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class UriService {

    private static final Logger logger = LoggerFactory.getLogger("URL");

    public static URI geraUriCorretamente (String uriRequest){
        try {
            logger.info("URI Decodada: {}", uriRequest);
            validaUrl(uriRequest);
            String myUrl = limpa(uriRequest);
            logger.info("URI Limpa: {}",myUrl);
            URL url = new URL(myUrl);
            String nullFragment = null;
            URI uri = new URI(url.getProtocol(),url.getUserInfo(), url.getHost(),url.getPort(), url.getPath(), url.getQuery(), nullFragment);
            logger.info("URI encodada: {} is OK", uri);
            return uri;
        } catch (MalformedURLException e) {
            logger.error("URI encodada: {} esta mal-formada",uriRequest);
            logger.error("Motivo: {}", e.getMessage());
        } catch (URISyntaxException e) {
            logger.error("URI encodada: {} esta com erro de sintaxe",uriRequest);
            logger.error("Motivo: {}", e.getMessage());
        }
        return null;
    }

    private static void validaUrl(String url){
        if(url == null || !url.startsWith("url=") || url.endsWith("url=")){
            throw new UrlPadraoException("O parametro url= nao foi passado Ou esta incorreto");
        }
    }

    private static String limpa(String url) {
        return url.replace("url=","").replace("%20"," ");
    }
}
