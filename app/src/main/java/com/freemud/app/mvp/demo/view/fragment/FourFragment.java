package com.freemud.app.mvp.demo.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.freemud.app.mvp.demo.App;
import com.freemud.app.mvp.demo.di.components.DaggerFourFragmentComponent;
import com.freemud.app.mvp.demo.di.modules.FourFragmentModule;
import com.freemud.app.mvp.demo.di.modules.MainModule;
import com.freemud.app.mvp.demo.help.DialogFactory;
import com.freemud.app.mvp.demo.help.photohelp.CompressConfig;
import com.freemud.app.mvp.demo.help.photohelp.CropOptions;
import com.freemud.app.mvp.demo.help.photohelp.InvokeListener;
import com.freemud.app.mvp.demo.help.photohelp.InvokeParam;
import com.freemud.app.mvp.demo.help.photohelp.PermissionManager;
import com.freemud.app.mvp.demo.help.photohelp.TContextWrap;
import com.freemud.app.mvp.demo.help.photohelp.TResult;
import com.freemud.app.mvp.demo.help.photohelp.TakePhoto;
import com.freemud.app.mvp.demo.help.photohelp.TakePhotoImpl;
import com.freemud.app.mvp.demo.help.photohelp.TakePhotoInvocationHandler;
import com.freemud.app.mvp.demo.help.photohelp.TakePhotoOptions;
import com.freemud.app.mvp.demo.mymvpdemo3.R;
import com.freemud.app.mvp.demo.presenter.PresenterControl.FourFragmentControl;
import com.freemud.app.mvp.demo.util.DataCleanManager;
import com.freemud.app.mvp.demo.util.ValueUtil;
import com.freemud.app.mvp.demo.view.activity.LoginActivity;
import com.freemud.app.mvp.demo.view.views.CommonDialog;
import com.jakewharton.rxbinding2.view.RxView;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by li.liu on 2018/1/12.
 * FourFragment
 */

public class FourFragment extends BaseFragment implements FourFragmentControl.FourFragmentView, PhotoChoiceDialog.photoChoiceDialogListener,
        CommonDialog.CommonDialogListener, InvokeListener, TakePhoto.TakeResultListener {
    @BindView(R.id.personal_page_clear_cache)
    TextView personalPageClearCache;
    @BindView(R.id.personal_page_exit)
    Button personalPageExit;
    @BindView(R.id.mime_avator)
    ImageView mMimeAvator;
    private String cacheSize;
    private Uri imageUri;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    @Inject
    FourFragmentControl.FourFragmentPresenter mPresenter;

    public static FourFragment newInstance() {
        return new FourFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_four, container, false);
        ButterKnife.bind(this, view);
        initInjectData();
        initView();
        initData();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void initView() {
        try {
            cacheSize = DataCleanManager.getTotalCacheSize(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //初始化头像存储文件
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        imageUri = Uri.fromFile(file);

        personalPageClearCache.setText("清除缓存（" + cacheSize + "）");
        RxView.clicks(personalPageClearCache).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> clearCache());
        RxView.clicks(personalPageExit).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> exitLogin());
        RxView.clicks(mMimeAvator).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> requestChoicePic());
    }

    @Override
    public void initData() {
    }

    private void requestChoicePic() {
        PhotoChoiceDialog mDialog = PhotoChoiceDialog.newInstance();
        mDialog.setListener(this);
        DialogFactory.showDialogFragment(getActivity().getSupportFragmentManager(), mDialog, PhotoChoiceDialog.TAG);
    }

    private void clearCache() {
        CommonDialog commonDialog = CommonDialog.newInstance();
        commonDialog.setContent("清除缓存");
        commonDialog.setListener(this, 1);
        commonDialog.setIsShowButtonLayout(true);
        DialogFactory.showDialogFragment(getChildFragmentManager(), commonDialog, CommonDialog.TAG);
    }

    private void exitLogin() {
        mBuProcessor.clearLoginUser();
        LoginActivity.start(getActivity());
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initInjectData() {
        DaggerFourFragmentComponent.builder().appComponent(((App) getActivity().getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .fourFragmentModule(new FourFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public void commonDialogBtnOkListener(int type, int position) {
        if (type == 1) {
            DataCleanManager.clearAllCache(getActivity());
            try {
                personalPageClearCache.setText(DataCleanManager.getTotalCacheSize(getActivity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(getActivity(), type, invokeParam, this);
    }

    @Override
    public void photoTakeListener() {
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
    }

    @Override
    public void PhotoDirectListener() {
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
//        takePhoto.onPickMultiple(9);//图片多选
        takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
    }

    @Override
    public void takeSuccess(TResult result) {
        Bitmap bitmap = BitmapFactory.decodeFile(result.getImage().getCompressPath());
        String img_url = ValueUtil.convertIconToString(bitmap);
        mImageLoaderHelper.displayCircularImage(getActivity(), result.getImage().getCompressPath(), mMimeAvator,0);
        //图片上传
    }

    @Override
    public void takeFail(TResult result, String msg) {
        mImageLoaderHelper.displayCircularImage(getActivity(), R.drawable.person_fake_icon, mMimeAvator,0);
    }

    @Override
    public void takeCancel() {

    }

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    private void configCompress(TakePhoto takePhoto) {
        int maxSize = 1024;
        int width = 160;
        int height = 160;
        CompressConfig config = new CompressConfig.Builder()
                .setMaxSize(maxSize)
                .setMaxPixel(width)
                .enableReserveRaw(false).create();
        takePhoto.onEnableCompress(config, false);
    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());
    }

    private CropOptions getCropOptions() {
        int height = 80;
        int width = 80;
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(width).setAspectY(height);
        builder.setWithOwnCrop(true);
        return builder.create();
    }
}
