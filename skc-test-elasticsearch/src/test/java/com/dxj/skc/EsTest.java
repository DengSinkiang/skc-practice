package com.dxj.skc;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.*;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.*;
import com.alibaba.fastjson.JSON;
import com.dxj.skc.es.client.EsClient;
import com.dxj.skc.es.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
@SpringBootTest(classes = ElasticsearchApplication.class)
public class EsTest {

    private final ElasticsearchClient client = EsClient.getClient();

    private final static String INDEX_NAME = "es_person";
    private final static String ALIAS_INDEX_NAME = "alias_es_person";

    /**
     * 创建索引
     *
     * @throws IOException
     */
    @Test
    void createIndex() throws IOException {

        Map<String, Property> propertyMap = new HashMap<>();
        propertyMap.put("name", new Property(new TextProperty.Builder().index(true).store(true).build()));
        propertyMap.put("age", new Property(new IntegerNumberProperty.Builder().index(false).build()));
        propertyMap.put("sex", new Property(new BooleanProperty.Builder().index(false).build()));

        TypeMapping typeMapping = new TypeMapping.Builder().properties(propertyMap).build();
        IndexSettings indexSettings = new IndexSettings.Builder().numberOfShards(String.valueOf(1)).numberOfReplicas(String.valueOf(0)).build();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                .index(INDEX_NAME)
                .aliases(ALIAS_INDEX_NAME, new Alias.Builder().isWriteIndex(true).build())
                .mappings(typeMapping)
                .settings(indexSettings)
                .build();

        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest);
        System.out.println(createIndexResponse.acknowledged());

    }

    @Test
    void getIndexWithMappingsAndSettings() throws IOException {

        GetIndexRequest getIndexRequest = new GetIndexRequest.Builder().index(INDEX_NAME).build();
        GetIndexResponse getIndexResponse = client.indices().get(getIndexRequest);
        IndexState es_index_name = getIndexResponse.get(INDEX_NAME);

        System.out.println(JSON.toJSONString(es_index_name.mappings().properties()));
        System.out.println(JSON.toJSONString(es_index_name.aliases()));
        System.out.println(JSON.toJSONString(es_index_name.settings()));
    }

    /**
     * 删除索引
     *
     * @throws IOException
     */
    @Test
    void deleteIndex() throws IOException {

        List<String> indexList = Collections.singletonList(INDEX_NAME);
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest.Builder().index(indexList).build();
        DeleteIndexResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest);
        System.out.println(deleteIndexResponse.acknowledged());

    }

    @Test
    void getESDataByIndexAndIdTest() throws Exception {

        GetRequest getRequest = new GetRequest.Builder().index(INDEX_NAME).id("1").build();
        GetResponse<Person> personGetResponse = client.get(getRequest, Person.class);
        Person person = personGetResponse.source();

        System.out.println(person);
    }

    @Test
    void existsDocument() throws IOException {

        GetRequest getRequest = new GetRequest.Builder().index(INDEX_NAME).id("4").build();
        GetResponse<Person> personGetResponse = client.get(getRequest, Person.class);

        System.out.println(personGetResponse.source());
        System.out.println(personGetResponse.found());
    }

    @Test
    void saveDocument() throws IOException {

        Person person = new Person();
        person.setNickname("新疆");
        person.setName("邓新疆");
        person.setAge(27);
        person.setSex(true);
        IndexRequest<Person> personIndexRequest = new IndexRequest.Builder<Person>()
                .index(INDEX_NAME).id("1").document(person).build();
        IndexResponse indexResponse = client.index(personIndexRequest);

        // 结果成功为：Created
        System.out.println(indexResponse.result());
    }

    @Test
    void updateDocument() throws IOException {

        Person person = new Person();
        person.setNickname("里斯kitty");
        person.setName("里斯1234");
        person.setSex(false);
        person.setAge(28);
        UpdateRequest<Person, Person> personPersonUpdateRequest = new UpdateRequest.Builder<Person, Person>()
                .index(INDEX_NAME).id("1").doc(person).build();

        UpdateResponse<Person> personUpdateResponse = client.update(personPersonUpdateRequest, Person.class);

        //结果成功为：Updated
        System.out.println(personUpdateResponse.result());
    }

    @Test
    void getDocumentById() throws IOException {

        GetRequest getRequest = new GetRequest.Builder().index(INDEX_NAME).id("1").build();
        GetResponse<Person> personGetResponse = client.get(getRequest, Person.class);

        System.out.println(JSON.toJSONString(personGetResponse.source()));
        System.out.println(personGetResponse.id());
    }

    @Test
    void search() throws IOException {

        SearchRequest searchRequest = new SearchRequest.Builder().index(INDEX_NAME).build();
        SearchResponse<Person> personSearchResponse = client.search(searchRequest, Person.class);
        List<Hit<Person>> hits = personSearchResponse.hits().hits();
        hits.forEach(hit -> {
            System.out.println(JSON.toJSONString(hit.source()));
        });
    }

    @Test
    void searchByPages() throws IOException {
        SearchRequest searchRequest = new SearchRequest.Builder().index(INDEX_NAME).from(0).size(10).build();
        SearchResponse<Person> personSearchResponse = client.search(searchRequest, Person.class);
        List<Hit<Person>> hits = personSearchResponse.hits().hits();
        hits.forEach(hit -> {
            System.out.println(JSON.toJSONString(hit.source()));
        });
    }

}
