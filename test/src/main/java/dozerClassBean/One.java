package dozerClassBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

/**
 * @Description
 * @author:awei
 * @date:2019/7/30
 * @ver:1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class One {
    private String name;
    private int age;
    private String password;
    @Mapping("names")
    private String realname;

}
