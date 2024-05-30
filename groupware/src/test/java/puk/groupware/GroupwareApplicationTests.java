package puk.groupware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import puk.groupware.repository.project.Project_info_jpaRepository;
import puk.groupware.service.project.ProjectFindService;

@SpringBootTest
class GroupwareApplicationTests {


	private final ProjectFindService projectFindService;
	private final Project_info_jpaRepository pJpaRepository;

    @Autowired
    GroupwareApplicationTests(ProjectFindService projectFindService,Project_info_jpaRepository pJpaRepository){
        this.projectFindService = projectFindService;
		this.pJpaRepository = pJpaRepository;
    }


}
