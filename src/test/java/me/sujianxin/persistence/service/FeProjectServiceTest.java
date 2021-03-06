package me.sujianxin.persistence.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sujianxin.persistence.model.FeProject;
import me.sujianxin.persistence.model.FeStyle;
import me.sujianxin.persistence.model.FeTree;
import me.sujianxin.persistence.model.FeUser;
import me.sujianxin.spring.config.ApplicationConfig;
import me.sujianxin.spring.domain.FeProjectDomain;
import me.sujianxin.spring.domain.FeProjectForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Created with IDEA
 * <p>Author: sujianxin
 * <p>Date: 2016/3/4
 * <p>Time: 19:49
 * <p>Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
public class FeProjectServiceTest {
    @Autowired
    private IFeProjectService iFeProjectService;
    private SimpleDateFormat sdf;
    private ObjectMapper objectMapper = null;

    @Before
    public void init() {
        this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    }

    @Test
    public void findAllByPage() {
        FeProjectForm feProjectForm = new FeProjectForm();
        feProjectForm.setPage(1);
        feProjectForm.setPageSize(100);
        feProjectForm.setSortCol("createTime");
        feProjectForm.setSortDir("desc");
        // feProjectForm.setName("项目");
        Map<String, Object> result = iFeProjectService.findAll(feProjectForm, 1);
        System.out.println(((List<FeProjectDomain>) result.get("data")).size());
    }

    @Test
    public void save() {
        FeProject feProject = new FeProject();
        feProject.setName("name_" + sdf.format(new Date()));
        feProject.setCreateTime(new Date());
        feProject.setRemark("remark_" + sdf.format(new Date()));
        feProject.setUser(new FeUser(1));
        FeStyle feStyle = new FeStyle();
        feStyle.setName("common.css");
        feStyle.setCode("code");
        FeStyle feStyle1 = new FeStyle();
        feStyle1.setName("common.css");
        feStyle1.setCode("code");

        feProject.addStyle(feStyle);
        feProject.addStyle(feStyle1);

        FeTree feTree = new FeTree();
        feTree.setName("root");
        feTree.setIsFolder("1");
        //feTree.setIconSkin("folder");
        List<FeTree> feTreeList = new ArrayList<>();
        feTreeList.add(feTree);
        feProject.setTrees(feTreeList);

        FeProject tmp = iFeProjectService.save(feProject);
        System.out.println(feProject.hashCode());
        System.out.println(tmp.hashCode());
    }

    @Test
    public void updateById() {
        FeProject project = new FeProject();
        project.setId(3);
        project.setName("update" + sdf.format(new Date()));
        project.setCreateTime(new Date());
        project.setRemark("update" + sdf.format(new Date()));
        project.setUser(new FeUser(1));
        iFeProjectService.save(project);
    }

    @Test
    public void deleteById() {
        iFeProjectService.deleteById(4);
    }

    @Test
    public void findOne() {
        FeProject feProject = iFeProjectService.findOne(28);
        try {
            objectMapper.writeValue(System.out, feProject);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
