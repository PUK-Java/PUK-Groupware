package puk.groupware.service.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import puk.groupware.model.project.Project_info;
import puk.groupware.repository.project.Project_info_jpaRepository;

@Service
public class ProejctFindAllService {

    private final Project_info_jpaRepository pRepository;

    @Autowired
    ProejctFindAllService(Project_info_jpaRepository pRepository){
        this.pRepository = pRepository;
    }

    public Page<Project_info> findAll(Pageable page){
        return pRepository.findAll(page);
    }

}
