package coder.jdev.dto.response.identity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryResponse {

    private Long id;

    private String name;

    private String phoneCode;

    private String capital;

    private String currencyName;

    private String emoji;
}
