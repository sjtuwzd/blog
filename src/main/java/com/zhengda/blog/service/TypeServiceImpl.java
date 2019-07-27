package com.zhengda.blog.service;


import com.zhengda.blog.NotFoundException;
import com.zhengda.blog.dao.TypeRepository;
import com.zhengda.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService{


    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        Optional<Type> optionalEntity = typeRepository.findById(id);
        Type type = optionalEntity.get();
        return type;
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }


    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = getType(id);
        if(t == null) {
            throw new NotFoundException("Type with this id not exist!");
        }
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }


    @Transactional
    @Override
    public void deleteType(Long id) {

        typeRepository.deleteById(id);

    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {

        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);

        return typeRepository.findTop(pageable);
    }
}
