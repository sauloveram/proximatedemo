package com.example.saulovera.proximatedemo;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.example.saulovera.proximatedemo.dao.DaoMaster;
import com.example.saulovera.proximatedemo.dao.DaoSession;
import com.example.saulovera.proximatedemo.view.LoginFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        toolbar = (Toolbar) findViewById(R.id.toolbarDemo);
        setActionBar();
        setTitle("Inicio");

        SQLiteDatabase db;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "local-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        switchFragment(R.layout.login_fragment);
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
    }

    public void switchFragment(int itemId) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        //ckedItem(R.id.nav_home);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        switch (itemId) {

            case R.layout.login_fragment:
                ft.replace(R.id.contentFrame, new LoginFragment()).commit();
                break;
            default:
                break;

        }
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public void setDaoMaster(DaoMaster daoMaster) {
        this.daoMaster = daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }
}
