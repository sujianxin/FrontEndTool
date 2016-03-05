package me.sujianxin.persistence.service;

import me.sujianxin.persistence.model.FeProject;
import me.sujianxin.spring.domain.FeProjectForm;

import java.util.Map;

/**
 * <p>Created with IDEA
 * <p>Author: sujianxin
 * <p>Date: 2016/3/3
 * <p>Time: 22:32
 * <p>Version: 1.0
 */
public interface IFeProjectService {
    void save(FeProject feProject);

    void updateById(FeProject feProject);

    void deleteById(int id);

    Map<String, Object> findAll(FeProjectForm feProjectForm);

    long count();
}