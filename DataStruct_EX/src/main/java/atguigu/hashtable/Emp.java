package atguigu.hashtable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Emp {
    private int id;
    private String name;
    public Emp next;
}
