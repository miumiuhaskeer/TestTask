package org.example.testtask.controllers;

import lombok.RequiredArgsConstructor;
import org.example.testtask.JsonConverter;
import org.example.testtask.dto.CatDto;
import org.example.testtask.exceptions.CatNotFoundException;
import org.example.testtask.models.Cat;
import org.example.testtask.services.CatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cat")
public class CatController {

    private final CatService service;
    private final JsonConverter converter;

    @GetMapping
    public ResponseEntity<String> getCats() {
        return ResponseEntity.ok(converter.toJson(service.getCats()));
    }

    @GetMapping("/{id}")
    public Cat getCat(@PathVariable("id") int id) {
        return service.getById(id);
    }

    @PostMapping("/create")
    public Cat create(@RequestBody @Valid CatDto catDto) {
        Cat cat = new Cat(catDto.getName(), catDto.getAge());

        return service.save(cat);
    }

    @PatchMapping("/edit/{id}")
    public Cat edit(
            @PathVariable("id") int id,
            @RequestBody @Valid CatDto catDto
    ) {
        if(!service.isIdExist(id))
            throw new CatNotFoundException("Cat with id " + id + " not found!");

        Cat cat = new Cat(catDto.getName(), catDto.getAge());

        service.update(cat, id);

        return cat;
    }

    @DeleteMapping("/delete/{id}")
    public Integer delete(@PathVariable("id") int id) {
        if(!service.isIdExist(id))
            throw new CatNotFoundException("Cat with id " + id + " not found!");

        service.delete(id);

        return id;
    }
}
