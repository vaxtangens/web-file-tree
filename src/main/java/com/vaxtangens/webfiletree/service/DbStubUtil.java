package com.vaxtangens.webfiletree.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vaxtangens.webfiletree.model.FileNode;

@Component
public class DbStubUtil {

    private List<FileNode> stubDb;

    private DbStubUtil() {
    }

    public List<FileNode> getConnection() {
        if (stubDb == null) {
            initDb();
        }
        return stubDb;
    }

    private void initDb() {
        stubDb = new ArrayList<>();

        FileNode root = new FileNode();
        root.setName("ROOT");
        root.setId(0);

        FileNode fn1 = new FileNode();
        fn1.setParent(root);
        fn1.setName("NODE 1");
        fn1.setId(1);

        FileNode fn11 = new FileNode();
        fn11.setParent(fn1);
        fn11.setName("NODE 11");
        fn11.setId(11);

        FileNode fn111 = new FileNode();
        fn111.setParent(fn11);
        fn111.setName("NODE 111");
        fn111.setId(111);

        FileNode fn12 = new FileNode();
        fn12.setParent(fn1);
        fn12.setName("NODE 12");
        fn12.setId(12);

        FileNode fn2 = new FileNode();
        fn2.setParent(root);
        fn2.setName("NODE 2");
        fn2.setId(2);

        FileNode fn21 = new FileNode();
        fn21.setParent(fn2);
        fn21.setName("NODE 21");
        fn21.setId(21);

        FileNode fn3 = new FileNode();
        fn3.setParent(root);
        fn3.setName("NODE 3");
        fn3.setId(3);

        root.setParent(root);

        root.getChildren().add(fn1);
        root.getChildren().add(fn2);
        root.getChildren().add(fn3);

        fn1.getChildren().add(fn11);
        fn1.getChildren().add(fn12);

        fn2.getChildren().add(fn21);

        fn11.getChildren().add(fn111);

        stubDb.add(root);
        stubDb.add(fn1);
        stubDb.add(fn2);
        stubDb.add(fn3);
        stubDb.add(fn11);
        stubDb.add(fn12);
        stubDb.add(fn21);
        stubDb.add(fn111);
    }
}
