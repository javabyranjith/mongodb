package jbr.sboot.graphql.model;

import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "product")
@Setter
@Getter
@ToString
public class Product {
  
  @TextIndexed
  private String id;
  private String name;
  private String type;
  private String price;
}
