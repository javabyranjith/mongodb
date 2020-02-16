package jbr.sboot.graphql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jbr.sboot.graphql.dao.ProductDao;
import jbr.sboot.graphql.model.Product;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductsByTypeFetcher implements DataFetcher<List<Product>> {

  @Autowired
  private ProductDao productDao;

  @Override
  public List<Product> get(DataFetchingEnvironment environment) {
    String type = environment.getArgument("type");
    log.info("get product by id", type);
    return productDao.findByType(type);
  }

}
