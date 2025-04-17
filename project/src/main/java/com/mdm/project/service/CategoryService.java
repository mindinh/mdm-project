package com.mdm.project.service;

import com.mdm.project.entity.CategoryNode;
import com.mdm.project.exception.CategoryAlreadyExistException;
import com.mdm.project.exception.ResourceNotFoundException;
import com.mdm.project.repository.CategoryNodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryNodeRepository categoryNodeRepository;
    public CategoryService(CategoryNodeRepository categoryNodeRepository) {
        this.categoryNodeRepository = categoryNodeRepository;
    }

    public void insertCategory(String code, String name, String parentCode) {
        Optional<CategoryNode> node = categoryNodeRepository.findByCode(code);
        if (node.isPresent()) {
            throw new CategoryAlreadyExistException("Category code already exists");
        }
        CategoryNode categoryNode = new CategoryNode();
        categoryNode.setCode(code);
        categoryNode.setName(name);

        if (parentCode != null) {
            CategoryNode parent = categoryNodeRepository.findByCode(parentCode).orElseThrow(() -> new ResourceNotFoundException("Parent Category", "code", parentCode));
            categoryNode.setParent(parent);
        }
        categoryNodeRepository.save(categoryNode);
    }

    public List<CategoryNode> findAllCategories() {
        return categoryNodeRepository.findAll();

    }

}
