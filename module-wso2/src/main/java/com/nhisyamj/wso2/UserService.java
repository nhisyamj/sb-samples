package com.nhisyamj.wso2;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.net.ssl.HostnameVerifier;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Slf4j
@Service
public class UserService {

    @Value("${iam.adminservice.createuser.restapi.url}")
    private String iamAdminCreateUserRestAPIServiceUrl;

    @Value("${iam.admin.base64.credentials}")
    private String iamCredentials;

    public Boolean createUser(UserDTO req) throws Exception {
        UserCreation userCreation = new UserCreation();
        userCreation.setUserName(req.getUsername());
        userCreation.setPassword(req.getPassword());
        userCreation.setRealm("PRIMARY");
        userCreation.addClaim(new Claim("http://wso2.org/claims/emailaddress", req.getEmailAddress()));
        userCreation.addClaim(new Claim("http://wso2.org/claims/mobile", req.getMobileNumber()));
        userCreation.addClaim(new Claim("http://wso2.org/claims/givenname", req.getFullName()));

        try (CloseableHttpClient httpClient = createAcceptSelfSignedCertificateClient()) {
            HttpPost post = new HttpPost(iamAdminCreateUserRestAPIServiceUrl);

            post.addHeader("Content-Type", "application/json");
            post.addHeader("Authorization", "Basic "+iamCredentials);

            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(userCreation);
            StringEntity requestEntity = new StringEntity(jsonInString, ContentType.APPLICATION_JSON);
            post.setEntity(requestEntity);

            HttpResponse response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            log.info("Returned status code is " + statusCode);

            if (HttpStatus.valueOf(statusCode).is2xxSuccessful()) {
                return true;
            } else {
                String errorMsg = EntityUtils.toString(response.getEntity());
                log.info("JsonResponse:"+errorMsg);
                log.error("Failed to create user: "+response.getStatusLine().getStatusCode()+": " +response.getStatusLine().getReasonPhrase());
                throw new IllegalArgumentException(errorMsg);
            }
        }
    }

    private CloseableHttpClient createAcceptSelfSignedCertificateClient() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) {
                return true;
            }
        });

        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();

        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(builder.build(), allowAllHosts);

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", connectionFactory).build();

        HttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        return HttpClients
                .custom()
                .setSSLSocketFactory(connectionFactory)
                .setConnectionManager(cm)
                .build();
    }
}
