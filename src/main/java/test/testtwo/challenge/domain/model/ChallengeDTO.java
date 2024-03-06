package test.testtwo.challenge.domain.model;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "challenge") // For XML
public class ChallengeDTO {
    @NotNull(message = "{common.field.required}")
    private Integer x;

    @NotNull(message = "{common.field.required}")
    private Integer y;

    @NotNull(message = "{common.field.required}")
    private Integer n;
}
