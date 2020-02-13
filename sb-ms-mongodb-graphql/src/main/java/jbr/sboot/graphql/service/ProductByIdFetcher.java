package jbr.sboot.graphql.service;

import org.springframework.beans.factory.annotation.Autowired;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jbr.sboot.graphql.dao.ProductDao;
import jbr.sboot.graphql.model.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductByIdFetcher implements DataFetcher<Product> {

  @Autowired
  private ProductDao productDao;

  @Override
  public Product get(DataFetchingEnvironment environment) {
    String id = environment.getArgument("id");
    log.info("get product by id", id);
    return productDao.findById(id)
        .get();
  }

}
