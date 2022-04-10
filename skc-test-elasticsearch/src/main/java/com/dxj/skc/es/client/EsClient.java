package com.dxj.skc.es.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
public class EsClient {

    private volatile static ElasticsearchClient client;

    private EsClient() {

    }
    public static ElasticsearchClient getClient() {
        if (client == null) {
            synchronized (ElasticsearchClient.class) {
                if (client == null) {
                    // And create the API client
                    client = new ElasticsearchClient(EsTransport.getTransport());
                }
            }
        }
        return client;
    }

}
