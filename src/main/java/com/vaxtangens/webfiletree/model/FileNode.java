package com.vaxtangens.webfiletree.model;

import java.util.ArrayList;
import java.util.List;

public class FileNode {
    private int id;
    private String name;
    private FileNode parent;
    private List<FileNode> children = new ArrayList<>(5);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FileNode getParent() {
        return parent;
    }

    public void setParent(FileNode parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FileNode> getChildren() {
        return children;
    }

    public void setChildren(List<FileNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "FileNode [name=" + name + "]";
    }
}
