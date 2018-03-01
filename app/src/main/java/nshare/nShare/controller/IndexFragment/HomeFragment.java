package nshare.nShare.controller.IndexFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import nshare.nShare.controller.Main.YaoActivity;
import me.payge.swipecardview.R;


/**
 * Created by Administrator on 2018/1/23.
 */

public class HomeFragment extends Fragment{

    @BindView(R.id.btn)
    Button btn;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,null);
        ButterKnife.bind(this,view);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), YaoActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }






}
