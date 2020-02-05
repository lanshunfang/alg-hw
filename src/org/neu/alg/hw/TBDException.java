package org.neu.alg.hw;

public class TBDException extends Exception {
  public TBDException(String errMsg) {
    throw super("[ERROR] TBD exception: " + errMsg);
  }
}
