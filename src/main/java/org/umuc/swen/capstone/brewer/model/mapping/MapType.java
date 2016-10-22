package org.umuc.swen.capstone.brewer.model.mapping;

/**
 * Created by cwancowicz on 10/16/16.
 */
public enum MapType {
  DISCRETE("Discrete Mapper"), CONTINUOUS("Continuous Mapper"), DIVERGING("Diverging Mapper");

  private String mapName;

  MapType(String mapName) {
    this.mapName = mapName;
  }

  public String getMapName() {
    return this.mapName;
  }
}
