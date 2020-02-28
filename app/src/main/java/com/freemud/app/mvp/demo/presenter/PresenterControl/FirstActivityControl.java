package com.freemud.app.mvp.demo.presenter.PresenterControl;


/**
 * Created by li.liu on 2017/12/13.
 */

public class FirstActivityControl {
    public interface FirstActivityView extends LoadDataView {
        void lookSuccess(int count);

    }

    public interface PresenterFirstActivity extends Presenter<FirstActivityView> {
        void onRequestLookNum(String userId,String shopId);
    }


}
