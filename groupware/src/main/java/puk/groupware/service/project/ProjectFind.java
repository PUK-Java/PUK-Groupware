package puk.groupware.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import puk.groupware.model.project.Project_info;
import puk.groupware.repository.project.Project_info_jpaRepository;

@Service
public class ProjectFind {

    private final Project_info_jpaRepository pRepository;

    @Autowired
    ProjectFind(Project_info_jpaRepository pRepository){
        this.pRepository = pRepository;
    }

    public Page<Project_info> findAllPage(Pageable page){
        return pRepository.findAll(page);
    }

    public Page<Project_info> findByTitleLike(String title,Pageable page){
        return pRepository.findByTitleContains(title, page);
    }

    //페이지 설정
    public void paging(int page,String projectName, Model model){
        PageRequest pageable = PageRequest.of(page,9,Sort.by("startDate").descending());
        if(projectName == null){
            Page<Project_info> pageNumber = findAllPage(pageable);
            List<Project_info> projects = pageNumber.getContent();
            model.addAttribute("projectPage",pageNumber.getNumber());
            model.addAttribute("projectTotalPage", pageNumber.getTotalPages());
            model.addAttribute("projects", projects);
        }
        if(projectName != null){
            Page<Project_info> pageNumber = findByTitleLike(projectName, pageable);
            List<Project_info> projects = pageNumber.getContent();
            model.addAttribute("projectPage", pageNumber.getNumber());
            model.addAttribute("projectTotalPage",pageNumber.getTotalPages());
            model.addAttribute("projects", projects);
        }
    }

}
