package model;

import java.util.Objects;


public class Tag   {

  private Integer id;
  private String name;

  public Tag() {
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Tag tag = (Tag) o;
    
    return Objects.equals(this.id, tag.id) &&
        Objects.equals(this.name, tag.name);
  }

}