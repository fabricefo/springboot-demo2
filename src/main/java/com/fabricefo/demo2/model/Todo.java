package com.fabricefo.demo2.model;

import org.springframework.data.annotation.Id;

public class Todo {
  
  @Id
  private int id;

  private String title;

  private String description;

  public Todo() {

  }

  public Todo(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  @Override
  public String toString() {
    return "Todo [id=" + id + ", title=" + title + ", desc=" + description + "]";
  }
}
