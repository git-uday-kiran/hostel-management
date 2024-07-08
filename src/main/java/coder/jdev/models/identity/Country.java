package coder.jdev.models.identity;

import coder.jdev.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "countries")
//@Subselect("select * from countries")
public class Country extends BaseEntity {

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 3)
	private String iso3;

	@Column(length = 3)
	private String numericCode;

	@Column(length = 2)
	private String iso2;

	private String phoneCode;

	private String capital;

	private String currency;

	private String currencyName;

	private String currencySymbol;

	private String tld;

	private String nativeName;

	private String region;

	private String subRegion;

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

	@Column(nullable = false)
	private Byte flag;

	@Column(length = 255)
	@JsonProperty("wikiDataId")
	private String wikiDataId;

	@Override
	public void prePersist() {
		super.prePersist();
		flag = 1;
	}

}
