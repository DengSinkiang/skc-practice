package com.dxj.skc.config;

import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class EsClient {

    public static void main(String[] args) throws IOException {
        RestClient restClient = RestClient.builder(new HttpHost("xxxx", 9200))
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
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);
        GetRequest getRequest = new GetRequest.Builder().index("index").id("1").build();
        GetResponse<EsInsuPlanMarketAssoEntity> personGetResponse = client.get(getRequest, EsInsuPlanMarketAssoEntity.class);
        EsInsuPlanMarketAssoEntity person = personGetResponse.source();
        System.out.println(person);
    }

}
