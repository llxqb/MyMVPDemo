package com.freemud.app.mvp.demo.presenter.PresenterControl;


import com.freemud.app.mvp.demo.entity.request.MainBannerRequest;
import com.freemud.app.mvp.demo.entity.response.MainBannerResponse;

/**
 * Created by li.liu on 2017/12/13.
 */

public class OneFragmentControl {
    public interface OneFragmentView extends LoadDataView {
        void bannerSuccess(MainBannerResponse response);
    }

    public interface OneFragmentPresenter extends Presenter<OneFragmentView> {
        void onRequestBanner(MainBannerRequest request);
    }

}
