package hello.advanced.app.V1;

public class ServiceV1Impl implements ServiceV1{
    private final RepositoryV1 repositoryV1;

    public ServiceV1Impl(RepositoryV1 repositoryV1) {
        this.repositoryV1 = repositoryV1;
    }

    @Override
    public void orderItem(String itemId) {
        repositoryV1.save(itemId);
    }
}
