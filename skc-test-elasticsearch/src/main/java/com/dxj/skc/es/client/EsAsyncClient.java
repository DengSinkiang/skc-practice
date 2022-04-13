package com.dxj.skc.es.client;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import org.springframework.stereotype.Component;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
@Component
public class EsAsyncClient {

    private final EsTransport esTransport;

    public EsAsyncClient(EsTransport esTransport) {
        this.esTransport = esTransport;
    }

    public ElasticsearchAsyncClient getClient() {
        return new ElasticsearchAsyncClient(esTransport.getTransport());
    }

}
