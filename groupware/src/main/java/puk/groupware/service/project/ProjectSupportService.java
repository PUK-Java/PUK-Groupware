package puk.groupware.service.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import puk.groupware.model.project.ProjectSupport;
import puk.groupware.model.user.User_Info;
import puk.groupware.repository.project.ProjectSupportRepository;
import puk.groupware.repository.project.Project_info_jpaRepository;

@Service
public class ProjectSupportService {

    @Autowired
    private ProjectSupportRepository supportRepository;

    public void saveSupport(User_Info userInfo) {
        ProjectSupport support = new ProjectSupport();
        support.setUserId(userInfo.getUserId());
        supportRepository.save(support);
    }
}
