package org.umuc.swen.capstone.brewer.model.domain.brewercolor;

/**
 * Created by cwancowicz on 9/29/16.
 */
public enum ScaleType {
  // TODO: add ability to JSON deserialize this regardless of CASE (Case Insensitive)
  SEQ("Sequential"), DIV("Diverging"), QUAL("Qualitative");

  private final String name;
  ScaleType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
