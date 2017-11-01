package cs.plugins.storage.realm.json;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs.app.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * This example demonstrates how to import RealmObjects as JSON. Realm supports JSON represented
 * as Strings, JSONObject, JSONArray or InputStreams (from API 11+)
 */
public class RealmActivity extends Activity {

    private GridView mGridView;
    private CityAdapter mAdapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_example);

        // Create a RealmConfiguration which is to locate Realm file in package's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        // Get a Realm instance for this thread
        realm = Realm.getInstance(realmConfig);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Load data
        if(mAdapter == null) {
            List<CityEntity> cities = null;
            try {
                cities = loadCities();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //This is the GridView adapter
            mAdapter = new CityAdapter(this);
            mAdapter.setData(cities);

            //This is the GridView which will display the list of cities
            mGridView = (GridView) findViewById(R.id.cities_list);
            mGridView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            mGridView.invalidate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public List<CityEntity> loadCities() throws IOException {
//        loadJsonFromStream();
        loadJsonFromJsonObject();
        loadJsonFromString();

        // 查出City.class的所有数据
        return realm.allObjects(CityEntity.class);
    }

    /**
     * Json Stream
     */
    private void loadJsonFromStream() throws IOException {
        InputStream stream = getAssets().open("cities.json");

        // Open a transaction to store items into the realm
        realm.beginTransaction();
        try {
            realm.createAllFromJson(CityEntity.class, stream);
            realm.commitTransaction();
        } catch (IOException e) {
            // Remember to cancel the transaction if anything goes wrong.
            realm.cancelTransaction();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    /**
     * JSONObject
     */
    private void loadJsonFromJsonObject() {
        Map<String, String> city = new HashMap<String, String>();
        city.put("name", "København");
        city.put("votes", "9");
        JSONObject json = new JSONObject(city);

        realm.beginTransaction();
        realm.createObjectFromJson(CityEntity.class, json);
        realm.commitTransaction();
    }

    /**
     * Json String
     */
    private void loadJsonFromString() {
        String json = "{ name: \"Aarhus\", votes: 99 }";
        realm.beginTransaction();
        realm.createObjectFromJson(CityEntity.class, json);
        realm.commitTransaction();
    }
}