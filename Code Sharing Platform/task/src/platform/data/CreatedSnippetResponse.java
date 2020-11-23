package platform.data;

public class CreatedSnippetResponse {
    private String id;

    public CreatedSnippetResponse() {  }

    public CreatedSnippetResponse(String id) {
        this.id = String.valueOf(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
