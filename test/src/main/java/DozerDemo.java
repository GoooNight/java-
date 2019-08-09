import dozerClassBean.One;
import dozerClassBean.Three;
import dozerClassBean.Two;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;

/**
 * @Description
 * @author:awei
 * @date:2019/7/30
 * @ver:1.0
 **/
public class DozerDemo {
    public static DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
    private DozerDemo(){}
    public static DozerBeanMapper getInstance(){
        return dozerBeanMapper;
    }
}
class DozerTest{
    public static void main(String[] args) {
        DozerBeanMapper mapper = DozerDemo.getInstance();
        One one = new One(null,12,"23432","sdsas");
        Two two = new Two();
        two.setName("zhangsan");
        mapper.map(one,two);
        Three three = new Three();
        mapper.map(two,three);
        System.out.println(two);
        System.out.println(three);
    }
}