package com.project.ttotw.dto;

public class PageRequest {

  private int page = 1;
  private int size = 10;
  private final static int DEFAULT_SIZE = 10;
  private final static int MAX_SIZE = 50;

  public void setPage(int page) {
    this.page = page <= 0 ? 1 : page;
  }

  public void setSize(int size) {
    this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
  }

  public org.springframework.data.domain.PageRequest of() {
    return org.springframework.data.domain.PageRequest.of(page -1, size);
  }
}
