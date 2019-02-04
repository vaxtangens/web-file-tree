package com.vaxtangens.webfiletree.dao.implementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vaxtangens.webfiletree.dao.FileNodeDao;
import com.vaxtangens.webfiletree.model.FileNode;
import com.vaxtangens.webfiletree.service.DbStubUtil;

@Repository
public class FileNodeDaoImpl implements FileNodeDao {

    private DbStubUtil dataBase;

    public FileNodeDaoImpl(DbStubUtil dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public FileNode insert(FileNode entity) {
        entity.setId(dataBase.getConnection().size() + 1);
        dataBase.getConnection().add(entity);
        return entity;
    }

    @Override
    public FileNode find(Integer id) {
        List<FileNode> list = dataBase.getConnection();
        FileNode searchedNode = null;
        for (FileNode node : list) {
            if (node.getId() == id) {
                searchedNode = node;
                break;
            }
        }
        return searchedNode;
    }

    @Override
    public List<FileNode> findAll() {
        return dataBase.getConnection();
    }

    @Override
    public void update(FileNode entity) {
        delete(entity.getId());
        dataBase.getConnection().add(entity);
    }

    @Override
    public void delete(Integer id) {
        dataBase.getConnection().remove(find(id));
    }

    @Override
    public void deleteChild(FileNode parent, FileNode deleteNode) {
        parent.getChildren().remove(deleteNode);
    }

}
