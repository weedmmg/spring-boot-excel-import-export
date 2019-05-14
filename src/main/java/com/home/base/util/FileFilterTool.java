package com.home.base.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenxf
 * @company ydrx
 * @date 2/2/2018
 * @description
 */
public class FileFilterTool {
  List<String> types;

  public FileFilterTool() {
    types = Arrays.asList("JPG","JPEG","PNG","GIF");
  }

  public FileFilterTool(List<String> types) {
    super();
    this.types = types;
  }

  public boolean accept( String filename) {
    for(String type:types){
      if(filename.toUpperCase().endsWith(type)){
        return true;
      }
    }
    return false;
  }

  public  String extName( String filename) {
    if(filename.contains(".")){
      return filename.substring(filename.lastIndexOf("."),filename.length());
    }
    return ".JPG";
  }



}
