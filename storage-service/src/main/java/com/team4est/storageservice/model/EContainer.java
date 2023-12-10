package com.team4est.storageservice.model;

public enum EContainer {
  PROFILE("profiles"),
  FILE("files");

  private final String containerName;

  EContainer(String containerName) {
    this.containerName = containerName;
  }

  public String getContainerName() {
    return containerName;
  }
}
