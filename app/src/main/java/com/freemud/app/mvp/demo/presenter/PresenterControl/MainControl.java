package com.freemud.app.mvp.demo.presenter.PresenterControl;


import com.freemud.app.mvp.demo.entity.request.MainBannerRequest;
import com.freemud.app.mvp.demo.entity.response.MainBannerResponse;

/**
 * Created by li.liu on 2017/12/13.
 */

public class MainControl {
    public interface MainView extends LoadDataView {
        void bannerSuccess(MainBannerResponse response);

    }

    public interface PresenterMain extends Presenter<MainView> {
        void onRequestBanner(MainBannerRequest request);
    }

}
