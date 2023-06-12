package com.costi.csw9.Model;

import com.costi.csw9.Model.Temp.LightRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Light {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String label;
    @Column(nullable = false, unique = true)
    private String address;
    @Column(nullable = false)
    private LocalDateTime dateAdded;
    @Column(nullable = false)
    private LocalDateTime lastModified;
    private LocalDateTime lastConnected;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String pattern;
    @Column(nullable = false)
    private boolean isFavorite;
    @Column(nullable = false)
    private boolean isPublic;
    @Column(nullable = false)
    private boolean isEnabled;
    @OneToMany(mappedBy = "light", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LightLog> logs = new ArrayList<>();

    @Transient
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/y hh:mm a");

    public Light(String address, String label, String color, String pattern) {
        this.address = address;
        this.label = label;
        this.dateAdded = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.status = "New";
        this.color = color;
        this.pattern = pattern;
        this.isEnabled = false;
        this.isFavorite = false;
        this.isPublic = false;
    }

    public Light(LightRequest request){
        this.label = request.getLabel();
        this.dateAdded = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.color = request.getColor();
        this.pattern = request.getPattern();
        this.isEnabled = false;
        this.isFavorite = false;
        this.isPublic = false;
        this.status = "New";
    }

    public LightRequest getRequest(){
        return new LightRequest(address, label, color, pattern);
    }

    public void setValues(LightRequest request){
        this.label = request.getLabel();
        this.color = request.getColor();
        this.pattern = request.getPattern();
        this.address = address;
        this.status = "Recently Edited";
    }

    public String getlastModified(){
        return lastModified.getMonthValue() + "/" + lastModified.getDayOfMonth() + "/" + lastModified.getYear();
    }

    @Transient
    public String getFormattedLastConnected(){
        return (lastConnected != null) ? lastConnected.format(formatter) : LocalDateTime.MIN.format(formatter);
    }

    @Transient
    public String getFormattedModified(){
        return (lastModified != null) ? lastModified.format(formatter) : LocalDateTime.MIN.format(formatter);
    }

    @Transient
    public String getFormattedAdded(){
        return (dateAdded != null) ? dateAdded.format(formatter) : LocalDateTime.MIN.format(formatter);
    }

    @Transient
    public String getTimeSinceLastConnected() {
        if(lastConnected == null){
            return "never used";
        }

        String unit = "";
        LocalDateTime now = LocalDateTime.now();
        long diff;
        if((diff = ChronoUnit.SECONDS.between(lastConnected,now)) < 60){
            unit = "seconds";
        } else if ((diff = ChronoUnit.MINUTES.between(lastConnected,now)) < 60) {
            unit = "minutes";
        } else if ((diff = ChronoUnit.HOURS.between(lastConnected,now)) < 24) {
            unit = "hours";
        } else if ((diff = ChronoUnit.DAYS.between(lastConnected,now)) < 30) {
            unit = "days";
        } else if ((diff = ChronoUnit.MONTHS.between(lastConnected,now)) < 12) {
            unit = "months";
        } else{
            diff = ChronoUnit.YEARS.between(lastConnected,now);
        }
        return String.format("%d %s",diff,unit);
    }

    public String getCurrentStatus() {
        RestTemplate restTemplate = new RestTemplate();
        String url = address + "/api/status";

        try {
            ResponseEntity<LightRequest> response = restTemplate.exchange(url, HttpMethod.GET, null, LightRequest.class);
            LightRequest lightRequest = response.getBody();
            lastConnected = LocalDateTime.now();
            status = "Active";
            setValues(lightRequest);
            return "Connection Successful: " + lightRequest.toString();
        } catch (RestClientException e) {
            e.printStackTrace();
            status = "Error";
            return "Error updating status of light: " + e.getMessage();
        }
    }
}
