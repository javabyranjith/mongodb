package jbr.sboot.graphql.util;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jbr.sboot.graphql.service.ProductByIdFetcher;
import jbr.sboot.graphql.service.ProductsByTypeFetcher;
import jbr.sboot.graphql.service.ProductsFetcher;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Slf4j
public class GraphQLProvider {
  
  @Value("classpath:graphql/product.graphql")
  private Resource schemaResource;

  private GraphQL graphQL;

  @Autowired
  private ProductsFetcher productsFetcher;

  @Autowired
  private ProductByIdFetcher productByIdFetcher;

  @Autowired
  private ProductsByTypeFetcher productsByTypeFetcher;

  @PostConstruct
  public void loadSchema() throws IOException {
    log.info("Loading Schema.......");
    File schemafile = schemaResource.getFile();
    TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemafile);
    RuntimeWiring wiring = buildRuntimeWiring();
    GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
    this.graphQL = GraphQL.newGraphQL(graphQLSchema)
        .build();
  }

  private RuntimeWiring buildRuntimeWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .type("Query", typeWiring -> typeWiring.dataFetcher("products", productsFetcher)
            .dataFetcher("productById", productByIdFetcher)
            .dataFetcher("productByType", productsByTypeFetcher))
        .build();
  }

}
