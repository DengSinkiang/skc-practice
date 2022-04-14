package com.dxj.skc;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.Time;
import co.elastic.clients.elasticsearch._types.mapping.*;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.CreateOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.json.JsonData;
import com.alibaba.fastjson.JSON;
import com.dxj.skc.es.client.EsAsyncClient;
import com.dxj.skc.es.client.EsClient;
import com.dxj.skc.es.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
@SpringBootTest(classes = ElasticsearchApplication.class)
public class EsTest {

    @Autowired
    private EsClient esClient;
    @Autowired
    private EsAsyncClient esAsyncClient;

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
        propertyMap.put("nickname", new Property(new TextProperty.Builder().index(true).store(true).build()));
        propertyMap.put("age", new Property(new IntegerNumberProperty.Builder().index(true).build()));
        propertyMap.put("sex", new Property(new BooleanProperty.Builder().index(true).build()));
        propertyMap.put("birthday", new Property(new DateProperty.Builder().format("yyyy-MM-dd HH:mm:ss").index(true).build()));
        propertyMap.put("email", new Property(new KeywordProperty.Builder().index(true).build()));

        TypeMapping typeMapping = new TypeMapping.Builder().properties(propertyMap).build();
        IndexSettings indexSettings = new IndexSettings.Builder().numberOfShards(String.valueOf(1)).numberOfReplicas(String.valueOf(0)).build();
        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                .index(INDEX_NAME)
                .aliases(ALIAS_INDEX_NAME, new Alias.Builder().isWriteIndex(true).build())
                .mappings(typeMapping)
                .settings(indexSettings)
                .build();

        CreateIndexResponse createIndexResponse = esClient.getClient().indices().create(createIndexRequest);
        System.out.println(createIndexResponse.acknowledged());

    }

    @Test
    void getIndexWithMappingsAndSettings() throws IOException {

        GetIndexRequest getIndexRequest = new GetIndexRequest.Builder().index(INDEX_NAME).build();
        GetIndexResponse getIndexResponse = esClient.getClient().indices().get(getIndexRequest);
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
        DeleteIndexResponse deleteIndexResponse = esClient.getClient().indices().delete(deleteIndexRequest);
        System.out.println(deleteIndexResponse.acknowledged());

    }

    @Test
    void getESDataByIndexAndIdTest() throws Exception {

        GetRequest getRequest = new GetRequest.Builder().index(INDEX_NAME).id("1").build();
        GetResponse<Person> personGetResponse = esClient.getClient().get(getRequest, Person.class);
        Person person = personGetResponse.source();

        System.out.println(person);
    }

    @Test
    void existsDocument() throws IOException {

        GetRequest getRequest = new GetRequest.Builder().index(INDEX_NAME).id("4").build();
        GetResponse<Person> personGetResponse = esClient.getClient().get(getRequest, Person.class);

        System.out.println(personGetResponse.source());
        System.out.println(personGetResponse.found());
    }

    @Test
    void saveDocument() throws IOException {
        ElasticsearchClient client = esClient.getClient();

        for (int i = 1; i <= 1000; i++) {
            Person person = new Person();
            person.setNickname("三儿" + "_" + i);
            person.setName("张三" + "_" + i);
            person.setAge(i);
            person.setSex(true);

            person.setBirthday(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            IndexRequest<Person> personIndexRequest = new IndexRequest.Builder<Person>()
                    .index(INDEX_NAME).document(person).build();
            IndexResponse indexResponse = client.index(personIndexRequest);


            // 结果成功为：Created
            System.out.println(indexResponse.result());
        }

    }

    @Test
    void saveBulkDocument() throws ExecutionException, InterruptedException {
        List<Person> personList = new ArrayList<>(3);

        Person person = new Person();
        person.setNickname("三儿");
        person.setName("张三");
        person.setAge(27);
        person.setSex(true);
        person.setBirthday("1993-10-20 00:00:00");
        personList.add(person);
        Person person2 = new Person();
        person2.setNickname("四儿");
        person2.setName("李四");
        person2.setAge(28);
        person2.setSex(false);
        person2.setBirthday("1994-11-24 00:00:00");
        personList.add(person2);
        Person person3 = new Person();
        person3.setNickname("五儿");
        person3.setName("王五");
        person3.setAge(29);
        person3.setSex(true);
        person3.setBirthday("1995-12-28 00:00:00");
        personList.add(person3);
        List<BulkOperation> list = new ArrayList<>();
        for (Person p : personList) {
            CreateOperation<Person> build = new CreateOperation.Builder<Person>().document(p).build();
            BulkOperation bulkOperation = new BulkOperation.Builder().create(build).build();
            list.add(bulkOperation);
        }

        BulkRequest bulkRequest = new BulkRequest.Builder().index(INDEX_NAME).operations(list).build();
        CompletableFuture<BulkResponse> bulk = esAsyncClient.getClient().bulk(bulkRequest);
        System.out.println(bulk.get().errors());
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

        UpdateResponse<Person> personUpdateResponse = esClient.getClient().update(personPersonUpdateRequest, Person.class);

        //结果成功为：Updated
        System.out.println(personUpdateResponse.result());
    }

    @Test
    void getDocumentById() throws IOException {

        GetRequest getRequest = new GetRequest.Builder().index(INDEX_NAME).id("1").build();
        GetResponse<Person> personGetResponse = esClient.getClient().get(getRequest, Person.class);

        System.out.println(JSON.toJSONString(personGetResponse.source()));
        System.out.println(personGetResponse.id());
    }

    @Test
    void search() throws IOException {

        SearchRequest searchRequest = new SearchRequest.Builder().index(INDEX_NAME).build();
        SearchResponse<Person> personSearchResponse = esClient.getClient().search(searchRequest, Person.class);
        List<Hit<Person>> hits = personSearchResponse.hits().hits();
        hits.forEach(hit -> {
            System.out.println(JSON.toJSONString(hit.source()));
        });
    }

    @Test
    void scroll() throws IOException {
        List<Person> list = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest.Builder().index(INDEX_NAME)
                .scroll(new Time.Builder().time("2m").build())
                .size(100)
                .build();
        SearchResponse<Person> personSearchResponse = esClient.getClient().search(searchRequest, Person.class);
        personSearchResponse.hits().hits().forEach(personHit -> {
            list.add(personHit.source());
        });
        String scrollId = personSearchResponse.scrollId();
        ScrollResponse<Person> scroll = esClient.getClient().scroll(new ScrollRequest.Builder().scrollId(scrollId).build(), Person.class);
        List<Hit<Person>> hits = scroll.hits().hits();
        hits.forEach(personHit -> {
            list.add(personHit.source());
        });
        // 清除 scroll
        ClearScrollResponse clearScrollResponse = esClient.getClient().clearScroll(new ClearScrollRequest.Builder().scrollId(scrollId).build());
        System.out.println(clearScrollResponse.succeeded());
        System.out.println(list.size());
    }

    @Test
    void searchByPages() throws IOException {
        List<Query> queryList = new ArrayList<>();
        List<Query> queryShouldList = new ArrayList<>();
        List<Integer> ages = Arrays.asList(20, 21, 22);
        for (Integer age : ages) {
            queryShouldList.add(new Query.Builder().match(new MatchQuery.Builder().field("age").query(age).build()).build());
        }
        queryList.add(new Query.Builder().bool(new BoolQuery.Builder()
                .should(queryShouldList)
                .build()).build());
        queryList.add(new Query.Builder()
                .range(new RangeQuery.Builder().field("birthday").gte(JsonData.of("2022-04-13 19:30:48"))
                        .lte(JsonData.of("2022-04-13 19:33:48")).build()).build());
        queryList.add(new Query.Builder().prefix(new PrefixQuery.Builder().field("name").value("张三").build()).build());
        queryList.add(new Query.Builder().ids(new IdsQuery.Builder().values("sXyxIoABVRsjH5c9H4ay", "sHyxIoABVRsjH5c9H4Zd")
                .build()).build());
        queryList.add(new Query.Builder().match(new MatchQuery.Builder().field("name").query("邓新疆").build()).build());

        SearchRequest searchRequest = new SearchRequest.Builder().index(INDEX_NAME)
                .query(new Query.Builder().bool(new BoolQuery.Builder().must(queryList).build()).build())
                .sort(new SortOptions.Builder().field(new FieldSort.Builder().field("age").order(SortOrder.Desc).build()).build())
                .from(0).size(10).build();
        SearchResponse<Person> personSearchResponse = esClient.getClient().search(searchRequest, Person.class);
        List<Hit<Person>> hits = personSearchResponse.hits().hits();
        hits.forEach(hit -> {
            System.out.println(JSON.toJSONString(hit.source()));
        });
    }

}
