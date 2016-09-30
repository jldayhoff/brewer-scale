package org.umuc.swen.capstone.brewer.model.exception;

/**
 * Created by cwancowicz on 9/29/16.
 */
public class NoBrewerColorFoundException extends RuntimeException {

  public static final String MESSAGE = "Found value that could not be mapped to a Brewer Color.";

  public NoBrewerColorFoundException() {
    super(MESSAGE);
  }
}
