package hello.advanced.app.V2;

public class ServiceV2 {
    private final RepositoryV2 repositoryV2;

    public ServiceV2(RepositoryV2 repositoryV2) {
        this.repositoryV2 = repositoryV2;
    }

    public void orderItem(String itemId) {
        repositoryV2.save(itemId);
    }
}
