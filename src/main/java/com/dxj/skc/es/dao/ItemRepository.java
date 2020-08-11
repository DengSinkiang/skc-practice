package com.dxj.skc.es.dao;

import com.dxj.skc.es.domain.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @AUTHOR: sinkiang
 * @DATE: 2019-07-16 22:52
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

    List<Item> findByPriceBetween(double price1, double price2);
}