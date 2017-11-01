package cs.app.activity.adapter;

import android.widget.ImageView;

/**
 * Created by shiwx on 2016/3/18.
 */
public class CategoryEntity {
    private String categoryName;
    private ImageView categoryIcon;
    private int imageId;

    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryEntity(String categoryName, int imageId) {
        this.categoryName = categoryName;
        this.imageId = imageId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ImageView getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(ImageView categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
