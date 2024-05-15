package models;

public class Repository {
    private long id;
    private String repositoryName;
    private String repositoryLanguage;
    private String repositoryOwner;
    private String image;

    public Repository(long id, String repositoryName, String repositoryLanguage, String repositoryOwner, String image) {
        this.id = id;
        this.repositoryName = repositoryName;
        this.repositoryLanguage = repositoryLanguage;
        this.repositoryOwner = repositoryOwner;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getRepositoryLanguage() {
        return repositoryLanguage;
    }

    public void setRepositoryLanguage(String repositoryLanguage) {
        this.repositoryLanguage = repositoryLanguage;
    }

    public String getRepositoryOwner() {
        return repositoryOwner;
    }

    public void setRepositoryOwner(String repositoryOwner) {
        this.repositoryOwner = repositoryOwner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
