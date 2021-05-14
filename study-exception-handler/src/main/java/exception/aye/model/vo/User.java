package exception.aye.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private String id;
    private String name;
    private String age;
}
