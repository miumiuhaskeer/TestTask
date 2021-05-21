package org.example.testtask.services;

import lombok.RequiredArgsConstructor;
import org.example.testtask.exceptions.CatNotFoundException;
import org.example.testtask.mappers.CatMapper;
import org.example.testtask.models.Cat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatMapper mapper;

    public Cat getById(int id) {
        Cat cat = mapper.findById(id);

        if(cat == null)
            throw new CatNotFoundException("Cat with id " + id + " not found!");

        return cat;
    }

    public Cat save(Cat cat) {
        cat.setId(mapper.getNextId());
        mapper.save(cat);

        return cat;
    }

    public void update(Cat cat, int id) {
        mapper.update(cat, id);
    }

    public void delete(int id) {
        mapper.delete(id);
    }

    public List<Cat> getCats() {
        List<Cat> cats = mapper.getCats();

        if(cats == null)
            throw new CatNotFoundException("Cats not found!");

        return cats;
    }

    public boolean isIdExist(int id) {
        Cat cat = mapper.findById(id);

        return cat != null;
    }
}
