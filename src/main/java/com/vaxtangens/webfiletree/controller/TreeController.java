package com.vaxtangens.webfiletree.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vaxtangens.webfiletree.model.FileNode;
import com.vaxtangens.webfiletree.service.FileNodeService;

@Controller
public class TreeController {

    private FileNodeService fileNodeService;

    public TreeController(FileNodeService fileNodeService) {
        this.fileNodeService = fileNodeService;
    }

    @GetMapping("/tree")
    public String loadRoot(Model model) {
        FileNode root = fileNodeService.findRoot();
        model.addAttribute("node", root);
        return "root";
    }

    @PostMapping("/load-children")
    public String loadChildren(@RequestParam Integer parentId, Model model) {
        List<FileNode> children = fileNodeService.findChildren(parentId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("loadChildren interrupted." + e);
        }
        model.addAttribute("children", children);
        return "children";
    }

    @PostMapping("/replace")
    public String replaceNode(@RequestParam Integer targetId, @RequestParam Integer draggedId, Model model) {
        List<FileNode> children = fileNodeService.replace(targetId, draggedId);
        model.addAttribute("children", children);
        return "children";
    }

    @PostMapping("/create")
    public String createNode(@RequestParam Integer parentId, @RequestParam String nodeName, Model model) {
        FileNode newNode = fileNodeService.createNode(parentId, nodeName);
        List<FileNode> children = newNode.getParent().getChildren();
        model.addAttribute("children", children);
        return "children";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Integer deleteId, Model model) {
        List<FileNode> children = fileNodeService.delete(deleteId);
        model.addAttribute("children", children);
        return "children";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id, @RequestParam String name, Model model) {
        List<FileNode> children = fileNodeService.update(id, name);
        model.addAttribute("children", children);
        return "children";
    }
}
