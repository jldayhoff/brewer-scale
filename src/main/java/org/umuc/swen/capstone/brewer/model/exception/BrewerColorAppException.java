package org.umuc.swen.capstone.brewer.model.exception;

/**
 * Created by cwancowicz on 10/21/16.
 */
public abstract class BrewerColorAppException extends RuntimeException {
  public BrewerColorAppException(String message) {
    super(message);
  }
}
