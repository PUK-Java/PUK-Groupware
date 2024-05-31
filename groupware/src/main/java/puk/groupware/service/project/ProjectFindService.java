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
public class ProjectFindService {

    private final Project_info_jpaRepository pRepository;

    @Autowired
    ProjectFindService(Project_info_jpaRepository pRepository){
        this.pRepository = pRepository;
    }

    //조건 없이 아이디만으로 찾기
    public Project_info findById(Long projectNo){
        return pRepository.findById(projectNo).orElseThrow();
    }

    //조건 없이 페이지 모두 찾기
    public Page<Project_info> findAllPage(Pageable page){
        return pRepository.findAll(page);
    }

    //이름을 조건으로 찾기
    public Page<Project_info> findByTitleLike(String title,Pageable page){
        return pRepository.findByTitleContains(title, page);
    }

    //카테고리 별로 찾기
    public Page<Project_info> findByCategory(String category,Pageable page){
        return pRepository.findByCategory(category,page);
    }

    //카테고리와 이름 모든 조건으로 찾기
    public Page<Project_info> findByTitleContainsAndCategory(String title,String category,Pageable page){
        return pRepository.findByTitleContainsAndCategory(title,category,page);
    }


    // 모든 컬럼의 Description의 길이를 확인해서 길이를 줄여주는 작업 작업
    public void descriptionSummary(List<Project_info> projects){
        for(Project_info project: projects){
            String description = project.getDescription();
            if(description.length()> 100){
                description = description.substring(0, 100) + "...";
                project.setDescription(description);
            }
        }
    }

    //Page 객체를 받아서 List으로 변환한 다음 모델에 추가해주는 작업
    public void pagetoListAddtoModel(Model model,Page<Project_info> pageNumber){
        List<Project_info> projects = pageNumber.getContent();
        descriptionSummary(projects);
        model.addAttribute("projectPage", pageNumber.getNumber());
        model.addAttribute("projectTotalPage",pageNumber.getTotalPages());
        model.addAttribute("projects", projects);

    }

    //페이지 설정
    public void paging(int page,String projectName, String projectCategory,Model model){
        PageRequest pageable = PageRequest.of(page,9,Sort.by("startDate").descending());

        //빈 문자열이 들어오면 널로 바꾸시오.
        if(projectName!= null){
            if(projectName.equals("")){
                projectName = null;
            }
        }
        //카테고리 역시 빈 문자열로 들어오면 널로 바꾸시오
        if(projectCategory != null){
            if(projectCategory.equals("")){
                projectCategory = null;
            }
        }

        //프로젝트 이름과 카테고리에 조건이 없다면
        if((projectName == null) && (projectCategory == null)){
            Page<Project_info> pageNumber = findAllPage(pageable);
            pagetoListAddtoModel(model, pageNumber);
        }

        //프로젝트 이름의 조건만 있다면
        if((projectName != null) && (projectCategory == null)){
            Page<Project_info> pageNumber = findByTitleLike(projectName, pageable);
            pagetoListAddtoModel(model, pageNumber);
        }

        //카테고리 조건만 있다면
        if((projectCategory != null) && (projectName == null)){
            Page<Project_info> pageNumber = findByCategory(projectCategory, pageable);
            pagetoListAddtoModel(model, pageNumber);
        }

        //프로젝트 이름과 카테고리 조건이 모두 있다면
        if((projectName != null) && (projectCategory != null)){
            Page<Project_info> pageNumber = findByTitleContainsAndCategory(projectName, projectCategory, pageable);
            pagetoListAddtoModel(model, pageNumber);
        }
    }

}
