package com.vaxtangens.webfiletree.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vaxtangens.webfiletree.dao.FileNodeDao;
import com.vaxtangens.webfiletree.model.FileNode;

@Component
public class FileNodeService {

    private FileNodeDao fileNodeDao;

    public FileNodeService(FileNodeDao fileNodeDao) {
        this.fileNodeDao = fileNodeDao;
    }

    public FileNode findRoot() {
        return fileNodeDao.find(0);
    }

    public List<FileNode> findChildren(Integer parentId) {
        FileNode parent = fileNodeDao.find(parentId);
        return parent.getChildren();
    }

    public List<FileNode> replace(Integer targetId, Integer draggedId) {
        FileNode target = fileNodeDao.find(targetId);
        FileNode dragged = fileNodeDao.find(draggedId);

        target.getChildren().add(dragged);
        dragged.getParent().getChildren().remove(dragged);
        dragged.setParent(target);

        return target.getChildren();
    }

    public FileNode createNode(Integer parentId, String nodeName) {
        FileNode parent = fileNodeDao.find(parentId);
        FileNode newNode = new FileNode();
        newNode.setName(nodeName);
        newNode.setParent(parent);
        parent.getChildren().add(newNode);
        return fileNodeDao.insert(newNode);
    }

    public List<FileNode> delete(Integer deleteId) {
        FileNode delete = fileNodeDao.find(deleteId);
        FileNode parent = delete.getParent();

        deleteAllSubNodes(delete);
        fileNodeDao.delete(delete.getId());
        fileNodeDao.deleteChild(parent, delete);

        return fileNodeDao.find(parent.getId()).getChildren();
    }

    private void deleteAllSubNodes(FileNode parent) {
        List<FileNode> children = parent.getChildren();
        for (FileNode node : children) {
            deleteAllSubNodes(node);
            node.getChildren().clear();
        }
    }

    public List<FileNode> update(Integer id, String name) {
        FileNode updateNode = fileNodeDao.find(id);
        updateNode.setName(name);
        fileNodeDao.update(updateNode);
        return updateNode.getParent().getChildren();
    }

}
