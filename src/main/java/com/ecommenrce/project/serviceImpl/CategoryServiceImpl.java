package com.ecommenrce.project.serviceImpl;

import com.ecommenrce.project.dto.CategoryDTO;
import com.ecommenrce.project.dto.CategoryResponse;
import com.ecommenrce.project.exception.ApiException;
import com.ecommenrce.project.exception.ResourceNotFoundException;
import com.ecommenrce.project.model.Category;
import com.ecommenrce.project.repository.CategoryRepository;
import com.ecommenrce.project.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);


             List<Category> getAll = categoryPage.getContent();
             if(getAll.isEmpty()){
                 throw new ApiException("Categories is empty");
             }
             List<CategoryDTO> categoryDTOS = getAll.stream().map(category ->
                     modelMapper.map(category,CategoryDTO.class))
                     .toList();
              CategoryResponse categoryResponse = new CategoryResponse();
              categoryResponse.setContent(categoryDTOS);
              categoryResponse.setPageNumber(categoryPage.getNumber());
              categoryResponse.setPageSize(categoryPage.getSize());
              categoryResponse.setTotalElements(categoryPage.getTotalElements());
              categoryResponse.setTotalPages(categoryPage.getTotalPages());
              categoryResponse.setLastPage(categoryPage.isLast());



            return categoryResponse;
        }



    @Override
    public CategoryDTO createCategories(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDb = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDb!=null) {
            throw new ApiException("Category with the name "+categoryDTO.getCategoryName()+" already exist");
        }
        Category savedCategory = categoryRepository.save(category);
        CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory,CategoryDTO.class);
  return savedCategoryDTO;
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepository.delete(category);
        CategoryDTO categoryDTO = modelMapper.map(category,CategoryDTO.class);

        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDb= categoryRepository.findById(categoryId).orElseThrow(( )-> new ResourceNotFoundException("Category","categoryId",categoryId));
        category.setCategoryId(categoryId);

        categoryFromDb = categoryRepository.save(category);

        CategoryDTO  savedCategory = modelMapper.map(categoryFromDb,CategoryDTO.class);
        return savedCategory;
    }

}
