package cs.app.activity.adapter;

/**
 * Created by shiwx on 2016/1/12.
 */
public class ItemConfig {
    private int titleId;
    private String title;
    private String targetClass;

    /**
     * 构造方法
     * @param titleId   标题String id
     * @param targetClass
     */
    public ItemConfig(int titleId, String targetClass) {
        this.titleId = titleId;
        this.targetClass = targetClass;
    }

    /**
     * 构造方法
     * @param title    标题
     * @param targetClass
     */
    public ItemConfig(String title, String targetClass) {
        this.title = title;
        this.targetClass = targetClass;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
