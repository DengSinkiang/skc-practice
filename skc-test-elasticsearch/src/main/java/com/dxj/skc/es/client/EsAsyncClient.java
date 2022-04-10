package com.dxj.skc.es.client;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
public class EsAsyncClient {

    private volatile static ElasticsearchAsyncClient client;

    private EsAsyncClient() {

    }
    public static ElasticsearchAsyncClient getClient() {
        if (client == null) {
            synchronized (ElasticsearchAsyncClient.class) {
                if (client == null) {
                    // And create the API client
                    client = new ElasticsearchAsyncClient(EsTransport.getTransport());
                }
            }
        }
        return client;
    }

}
