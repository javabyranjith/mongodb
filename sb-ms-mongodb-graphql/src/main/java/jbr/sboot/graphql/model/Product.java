package jbr.sboot.graphql.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
public class Product {
  private String id;
  private String name;
  private String type;
  private String price;
}
