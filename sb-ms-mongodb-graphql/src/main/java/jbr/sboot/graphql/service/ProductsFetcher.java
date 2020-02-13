package jbr.sboot.graphql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jbr.sboot.graphql.dao.ProductDao;
import jbr.sboot.graphql.model.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductsFetcher implements DataFetcher<List<Product>> {

  @Autowired
  private ProductDao productDao;

  @Override
  public List<Product> get(DataFetchingEnvironment environment) {
    String id = environment.getArgument("id");
    log.info("get product by id", id);
    TextCriteria criteria = TextCriteria.forDefaultLanguage()
        .matchingAny(id);
    return productDao.findAllBy(criteria);
  }
}
