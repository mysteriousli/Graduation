package com.ligy.util;

import com.ligy.netty.domain.EsEntity;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lgy
 */
@Component
public class EsUtils {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public List<EsEntity> searchEs(String port) {
        // 执行查询
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .build();
        SearchHits<EsEntity> searchHits = elasticsearchRestTemplate.search(query, EsEntity.class, IndexCoordinates.of("index_" + port));

        //封装page对象
        List<EsEntity> accounts = new ArrayList<>();
        for (SearchHit<EsEntity> hit : searchHits) {
            accounts.add(hit.getContent());
        }
        return accounts;
    }

    public void removeIndex(String port) {
        elasticsearchRestTemplate.deleteIndex(IndexCoordinates.of("index_" + port));
    }

    public void save(String msg, String index){
        boolean exists = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(index)).exists();
        if (!exists) {
            elasticsearchRestTemplate.indexOps(IndexCoordinates.of(index)).create();
        }
        EsEntity esEntity = new EsEntity();
        esEntity.setMsg(msg);
        elasticsearchRestTemplate.save(esEntity, IndexCoordinates.of(index));
    }
}
