package com.dxj.skc;

import com.dxj.skc.es.dao.ItemRepository;
import com.dxj.skc.es.domain.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/7/28 16:52
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkcApplication.class)
public class EsCreateIndexTest {

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testCreateIndex() {
        //创建索引库(数据库)
        esTemplate.createIndex(Item.class);
    }

    @Test
    public void testPutMapping() {
        //创建映射(表)
        esTemplate.putMapping(Item.class);
    }

    @Test
    public void deleteIndex() {
        esTemplate.deleteIndex("item");
    }

    @Test
    public void index() {
        Item item = new Item(999L, "黑马手机", " 手机",
                "黑马", 3499.00, "http://image.leyou.com/13123.jpg");
        itemRepository.save(item);
    }

    @Test
    public void indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }

    @Test
    public void indexListAdd() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }
}
