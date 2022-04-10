package com.dxj.skc.es.client;

import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;

class EsTransport {

    static ElasticsearchTransport getTransport() {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200))
                .setMetaHeaderEnabled(false)
                .setHttpClientConfigCallback(httpAsyncClientBuilder -> {
                    // 账密设置
                    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                    // es账号密码
                    credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "123456"));
                    httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    return httpAsyncClientBuilder;
                }).build();

        // Create the transport with a Jackson mapper
        return new RestClientTransport(restClient, new JacksonJsonpMapper());
    }
}
