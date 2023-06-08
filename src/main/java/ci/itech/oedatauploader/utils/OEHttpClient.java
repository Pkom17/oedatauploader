package ci.itech.oedatauploader.utils;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import ci.itech.oedatauploader.entities.VlAnalysisRecord;

@Component
public class OEHttpClient {

    public static HttpResponse sendData(String uriPath,String username,String password, List<VlAnalysisRecord> analysis){
        HttpResponse response = null;
        JSONArray jsonArray = new JSONArray(analysis);
        
        // Création des informations d'authentification
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        
        HttpClient client = HttpClientBuilder.create()
        .setDefaultCredentialsProvider(credentialsProvider)
        .build();
        HttpPost httpPost = new HttpPost(uriPath);

        try {
            StringEntity reqEntity = new StringEntity(jsonArray.toString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(reqEntity);
            response = client.execute(httpPost);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
}
