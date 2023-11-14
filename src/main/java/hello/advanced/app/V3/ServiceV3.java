package hello.advanced.app.V3;

import org.springframework.stereotype.Service;

@Service
public class ServiceV3 {
    private final RepositoryV3 repositoryV3;

    public ServiceV3(RepositoryV3 repositoryV3) {
        this.repositoryV3 = repositoryV3;
    }

    public void orderItem(String itemId) {
        repositoryV3.save(itemId);
    }
}
