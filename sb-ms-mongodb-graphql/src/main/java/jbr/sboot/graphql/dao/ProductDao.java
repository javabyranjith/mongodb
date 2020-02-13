package jbr.sboot.graphql.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import jbr.sboot.graphql.model.Product;

@Repository
public interface ProductDao extends MongoRepository<Product, String> {

  List<Product> findProducts();

  Optional<Product> findById(String id);

  List<Product> findByType(String type);
}
