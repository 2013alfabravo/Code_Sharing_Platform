package platform.data;

public class SubmittedSnippet {
    private String code;
    private Long time;
    private int views;

    SubmittedSnippet() {  }

    SubmittedSnippet(String code, Long time, int views) {
        this.code = code;
        this.time = time;
        this.views = views;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
