package org.neu.alg.practice.lib.utils;

import org.neu.alg.practice.easy.EulerPath;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class PathUtil<T, K> {

  public static String getMockDataPath(String fileName) {
    URL fileUrl = EulerPath.class.getResource("../mock_data/");
    return PathUtil.decodeURI(fileUrl.getFile()) + fileName;
  }

  public static String decodeURI(String value) {
    try {
      return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
    } catch (UnsupportedEncodingException ex) {
      throw new RuntimeException(ex.getCause());
    }
  }

}
