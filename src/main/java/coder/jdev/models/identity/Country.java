package coder.jdev.models.identity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Subselect;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "countries")
//@Subselect("select * from countries")
public class Country {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 3)
    private String iso3;

    @Column(length = 3)
    private String numericCode;

    @Column(length = 2)
    private String iso2;

    @Column(length = 255)
    private String phoneCode;

    @Column(length = 255)
    private String capital;

    @Column(length = 255)
    private String  currency;

    @Column(length = 255)
    private String currencyName;

    @Column(length = 255)
    private String currencySymbol;

    @Column(length = 255)
    private String tld;

    @Column(length = 255)
    private String nativeName;

    @Column(length = 255)
    private String region;

    @Column(length =  255)
    private String  subRegion;

    @Column(length = 5000)
    private String timezones;

    @Column(length = 2000)
    private String translations;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column(length = 191)
    private String emoji;

    @Column(length = 191)
    private String emojiU;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Byte flag;

    @Column(length = 255)
    @JsonProperty("wikiDataId")
    private String wikiDataId;

    @PrePersist
    protected void onCreate() {
        updatedAt = LocalDateTime.now();
        flag = 1;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
