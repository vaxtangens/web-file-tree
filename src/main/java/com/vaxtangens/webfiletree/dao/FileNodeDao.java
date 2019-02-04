package com.vaxtangens.webfiletree.dao;

import java.util.List;

import com.vaxtangens.webfiletree.model.FileNode;

public interface FileNodeDao extends Dao<FileNode, Integer> {
    FileNode insert(FileNode entity);

    FileNode find(Integer id);

    List<FileNode> findAll();

    void update(FileNode entity);

    void delete(Integer id);

    void deleteChild(FileNode parent, FileNode delete);
}
