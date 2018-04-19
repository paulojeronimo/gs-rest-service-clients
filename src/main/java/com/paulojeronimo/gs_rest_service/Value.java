package com.paulojeronimo.gs_rest_service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {
  private Long id;
  private String content;

  public Value() {}
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }
 
  @Override
  public String toString() {
    return "Value{" +
      "\"id\":" + id +
      ",\"content\"=\"" + content + "\"}";
  }
}
