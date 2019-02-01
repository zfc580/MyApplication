package com.example.zfc.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zfc.myapplication.entity.User;
import com.example.zfc.myapplication.greendao.DaoMaster;
import com.example.zfc.myapplication.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

public class MainActivity extends AppCompatActivity {

    private static String DB_NAME = "my_application";
    private DaoSession mDaoSession = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(new MyLifecycleObserver());
        setupDataBase();

        mDaoSession.getUserDao().insert(new User(1L, "zfc", 29));

        SharedPreferences myPreference = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        //像SharedPreference中写入数据需要使用Editor
        SharedPreferences.Editor editor = myPreference.edit();
        //存入键值对数据，注意此处的put[type]("key",value);
        editor.putString("STRING_KEY", "string");
        editor.putInt("INT_KEY", 0);
        editor.putBoolean("BOOLEAN_KEY", true);

//提交保存

//editor.apply();
        editor.commit();
    }


    private void setupDataBase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        Database database = helper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }


}
