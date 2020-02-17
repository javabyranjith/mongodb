package jbr.mongodb.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import jbr.mongodb.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
