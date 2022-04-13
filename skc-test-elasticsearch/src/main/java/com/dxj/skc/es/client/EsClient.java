package com.dxj.skc.es.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.stereotype.Component;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
@Component
public class EsClient {

    private final EsTransport esTransport;

    public EsClient(EsTransport esTransport) {
        this.esTransport = esTransport;
    }

    public ElasticsearchClient getClient() {
        return new ElasticsearchClient(esTransport.getTransport());
    }

}
