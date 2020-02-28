package com.freemud.app.mvp.demo.help.GlideHelper;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by li.liu on 2018/1/11.
 */

public class BannerImageLoader extends ImageLoader {
    ImageLoaderHelper mImageLoaderHelper;

    public BannerImageLoader(ImageLoaderHelper imageLoaderHelper) {
        mImageLoaderHelper = imageLoaderHelper;
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
//        mImageLoaderHelper.displayImage(context, path, imageView);
        mImageLoaderHelper.displayMatchImage(context, path, imageView,0);
    }

}
