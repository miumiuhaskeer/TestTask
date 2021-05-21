package org.example.testtask.mappers;

import org.apache.ibatis.annotations.Param;
import org.example.testtask.models.Cat;

import java.util.List;

public interface CatMapper {

    Cat findById(int id);

    void save(@Param("cat") Cat cat);

    void update(@Param("cat") Cat cat, @Param("id") int id);

    void delete(Integer id);

    List<Cat> getCats();

    int getNextId();
}
