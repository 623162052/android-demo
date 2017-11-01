package cs.plugins.storage.realm.json;

import io.realm.RealmObject;

/**
 * 城市实体
 */
public class CityEntity extends RealmObject {

    private String name;
    private long votes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

}
