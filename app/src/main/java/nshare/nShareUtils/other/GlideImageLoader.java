package nshare.nShareUtils.other;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;


/**
 * Created by Administrator on 2018/1/24.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load((String) path).fit().centerCrop().into(imageView);
    }
}
