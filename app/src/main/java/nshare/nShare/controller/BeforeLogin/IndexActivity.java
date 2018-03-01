package nshare.nShare.controller.BeforeLogin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.payge.swipecardview.R;
import nshare.nShare.controller.IndexFragment.HomeFragment;
import nshare.nShare.controller.IndexFragment.MassageFragment;
import nshare.nShare.controller.IndexFragment.MyFragment;
import nshare.nShare.controller.IndexFragment.PublishFragment;


public class IndexActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    @BindView(R.id.btnHome) Button btnHome;
    @BindView(R.id.btnPublish) Button btnPublish;
    @BindView(R.id.btnMassage) Button btnMassage;
    @BindView(R.id.btnMy) Button btnMy;
    @BindView(R.id.initResPb) ProgressBar initResPb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
//        initResPb.setVisibility(View.VISIBLE);
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.context,new HomeFragment());
        transaction.commit();
        initView();
//        intRes();
    }

    public void intRes(){

    }

    private void initView() {
        btnHome.setOnClickListener(this);
        btnPublish.setOnClickListener(this);
        btnMassage.setOnClickListener(this);
        btnMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        transaction = fragmentManager.beginTransaction();
        switch (v.getId())
        {
            case R.id.btnHome:
                transaction.replace(R.id.context,new HomeFragment());
                break;
            case R.id.btnPublish:
                transaction.replace(R.id.context,new PublishFragment());
                break;
            case R.id.btnMassage:
                transaction.replace(R.id.context,new MassageFragment());
                break;
            case R.id.btnMy:
                transaction.replace(R.id.context,new MyFragment());
                break;
        }
        transaction.commit();
    }

}