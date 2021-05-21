package org.example.testtask.controllers;

import org.example.testtask.dto.CatDto;
import org.example.testtask.models.Cat;
import org.example.testtask.services.CatService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CatControllerTest extends AbstractControllerTest {

    @Autowired
    private CatService catService;

    @Test
    public void get_CatIsNotExist() throws Exception {
        int invalidId = 390;

        mockMvc.perform(getCatRequest(invalidId)).andExpect(status().is4xxClientError());
    }

    @Test
    public void get_CatIsExist() throws Exception {
        Cat cat = new Cat("Barsik", 10);

        catService.save(cat);

        mockMvc.perform(getCatRequest(cat.getId())).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is(cat.getName())))
                .andExpect(jsonPath("$.age", is(cat.getAge())));
    }

    @Test
    public void create_InvalidName() throws Exception {
        String name = "B";
        int age = 10;

        mockMvc.perform(getCreateRequest(name, age)).andExpect(status().is4xxClientError());
    }

    @Test
    public void create_InvalidAge() throws Exception {
        String name = "Barsik";
        int ageTooBig = 1000;

        mockMvc.perform(getCreateRequest(name, ageTooBig)).andExpect(status().is4xxClientError());
    }

    @Test
    public void create_AllDataIsValid() throws Exception {
        String name = "Barsik";
        int age = 10;

        mockMvc.perform(getCreateRequest(name, age)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.age", is(age)));
    }

    @Test
    public void edit_InvalidNewName() throws Exception {
        String name = "Barsik";
        String invalidName = "B";
        Cat cat = new Cat(name, 0);

        catService.save(cat);
        mockMvc.perform(getEditRequest(Integer.toString(cat.getId()), invalidName, cat.getAge()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void edit_InvalidNewAge() throws Exception {
        String name = "Barsik";
        int ageTooBig = 1000;
        Cat cat = new Cat(name, 0);

        catService.save(cat);

        mockMvc.perform(getEditRequest(Integer.toString(cat.getId()), name, ageTooBig))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void edit_AllDataIsValid() throws Exception {
        String name = "Barsik";
        int age = 10;
        Cat cat = new Cat(name, 0);

        catService.save(cat);

        mockMvc.perform(getEditRequest(Integer.toString(cat.getId()), name, age))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.age", is(age)));
    }

    @Test
    public void delete_IdIsExist() throws Exception {
        Cat cat = new Cat("Barsik", 10);

        catService.save(cat);

        mockMvc.perform(getDeleteRequest(cat.getId())).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(Integer.toString(cat.getId())));
    }

    @Test
    public void delete_IdIsNotExist() throws Exception {
        Cat cat = new Cat("Barsik", 10);
        int notExistId = 390;

        catService.save(cat);

        mockMvc.perform(getDeleteRequest(notExistId)).andExpect(status().is4xxClientError());
    }

    private MockHttpServletRequestBuilder getCreateRequest(String name, int age) {
        return post("/api/cat/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(converter.toJson(new CatDto(name, age)));
    }

    private MockHttpServletRequestBuilder getEditRequest(String id, String name, int age) {
        return patch("/api/cat/edit/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(converter.toJson(new CatDto(name, age)));
    }

    private MockHttpServletRequestBuilder getDeleteRequest(int id) {
        return delete("/api/cat/delete/" + id);
    }

    private MockHttpServletRequestBuilder getCatRequest(int id) {
        return get("/api/cat/" + id);
    }
}
