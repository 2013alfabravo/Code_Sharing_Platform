package platform.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
public class Code {
    private static final String DATETIME_FORMATTER = "yyyy/MM/dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMATTER);

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @JsonIgnore
    @Column(unique = true, name = "uuid")
    private String uuid;

    @Lob
    @Column
    private String code;

    private String date;

    @JsonIgnore
    @Column
    private LocalDateTime timestamp;

    private Long time;

    @Column
    int views;

    @JsonIgnore
    @Column(name = "view_limit")
    private boolean expiringByViews;

    @JsonIgnore
    @Column(name = "time_limit")
    private boolean expiringByTime;

    @JsonIgnore
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    public Code() {  }

    public Code(String code, Long time, int views) {
        this.code = code;
        this.timestamp = LocalDateTime.now();
        this.uuid = UUID.randomUUID().toString();
        this.date = this.timestamp.format(formatter);
        this.views = views;
        this.expiringByViews = views > 0;
        this.expiringByTime = time > 0;
        this.expirationDate = this.expiringByTime ? this.timestamp.plusSeconds(time) : null;
        this.time = this.expirationDate != null ? ChronoUnit.SECONDS.between(LocalDateTime.now(), this.expirationDate) : time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void updateTime() {
        this.time = this.expirationDate != null ? ChronoUnit.SECONDS.between(LocalDateTime.now(), this.expirationDate) : this.time;;
    }

    public void updateViews() {
        views--;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setDate() {
        this.date = timestamp.format(formatter);
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isExpiringByTime() {
        return expiringByTime;
    }

    public void setExpiringByTime(boolean expiringByTime) {
        this.expiringByTime = expiringByTime;
    }

    public boolean isExpiringByViews() {
        return expiringByViews;
    }

    public void setExpiringByViews(boolean expiringByViews) {
        this.expiringByViews = expiringByViews;
    }

}