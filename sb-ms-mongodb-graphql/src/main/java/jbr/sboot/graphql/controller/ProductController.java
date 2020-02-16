package jbr.sboot.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import jbr.sboot.graphql.util.GraphQLProvider;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

  @Autowired
  private GraphQLProvider graphQLProvider;

  @GetMapping("/greeting")
  public ResponseEntity<Object> greeting() {
    return new ResponseEntity<>("Welcome", HttpStatus.OK);
  }

  @PostMapping(value = "/graphql", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getProducts(@RequestBody String query) {
    log.info("get products");
    ExecutionResult execute = graphQLProvider.getGraphQL()
        .execute(query);
    log.info("query------> "+query);
    return new ResponseEntity<>(execute, HttpStatus.OK);
  }
}
