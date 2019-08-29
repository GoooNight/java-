package enumDemo;

/**
 * @Description
 * @author:awei
 * @date:2019/8/8
 * @ver:1.0
 **/
public enum  EnumDemo  {
    /**
     *红色
     */
    RED("红色"),
    /**
     * 绿色
     */
    Greed("绿色"),
    /**
     * 蓝色
     */
    BLUE("蓝色");
    private String name;

    EnumDemo(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
