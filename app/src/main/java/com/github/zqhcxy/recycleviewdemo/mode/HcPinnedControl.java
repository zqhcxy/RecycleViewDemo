package com.github.zqhcxy.recycleviewdemo.mode;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import static java.lang.Math.round;

/**
 * 附件界面的悬浮标题控制类，只给附件面板使用，别的地方不建议使用；如需使用需要：a、Recyclerview需要包在一个相对布局下。
 * b、悬浮布局必须跟recyclerview的xml中的标题布局一个的布局。
 * c、需要实现对应接口。
 * <p/>
 * Created by zqh on 2017/11/9.
 */
public class HcPinnedControl {

    private RecyclerView mRecyclerView;
    private View mHeadView;
    float headViewHeight = 0;
    private HcPinnedControlInterface controlInterface;


    int currentHeadViewPos = -1;//当前第一个位置的headview位置
    int nextHeadViewPos = -1;//下一个headview位置


    public HcPinnedControl(HcPinnedControlInterface inf) {
        controlInterface = inf;
    }


    public void initControl(RecyclerView recyclerView, ViewGroup parentView) {
        mRecyclerView = recyclerView;
        if (controlInterface != null) {
            mHeadView = controlInterface.onCreateView(parentView);
            mHeadView.setVisibility(View.GONE);
            if (mHeadView != null) {
                headViewHeight = mHeadView.getHeight();
            }
            parentView.addView(mHeadView);
        }
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mHeadView == null || controlInterface == null) return;
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int currentFirstPosition = linearLayoutManager.findFirstVisibleItemPosition();
                int currentlastPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (controlInterface.isHeadView(currentFirstPosition)) {//headview再顶部
                    currentHeadViewPos = currentFirstPosition;
                    nextHeadViewPos = controlInterface.getNextHeadViewPosition(currentHeadViewPos);
                    View firstView = controlInterface.getHeadView(currentHeadViewPos);
                    if (firstView == null) return;
                    int firstViewTopPos = firstView.getTop();
                    if (firstViewTopPos == 0) {
                        if (mHeadView.getVisibility() == View.VISIBLE) {
                            mHeadView.setVisibility(View.GONE);
                        }
                    } else {
                        if (mHeadView.getVisibility() == View.GONE) {
                            mHeadView.setVisibility(View.VISIBLE);
                        }
                    }
//                    再向上滚动中
                    if (firstViewTopPos <= 0) {
                        //通知外部进行adapter的headview刷新
                        isnNotifyHeadViewState(mHeadView.getVisibility() == View.VISIBLE);
                    }
                    controlInterface.onBindView(currentHeadViewPos, mHeadView);
                    //进行悬浮偏移
                    headViewOffset(null, 0);
                } else if (controlInterface.getmonthPosition(currentFirstPosition) != controlInterface.getmonthPosition(currentHeadViewPos)) {
                    currentHeadViewPos = controlInterface.getmonthPosition(currentFirstPosition);
                    nextHeadViewPos = controlInterface.getNextHeadViewPosition(currentHeadViewPos);
                    isnNotifyHeadViewState(mHeadView.getVisibility() == View.VISIBLE);
                    controlInterface.onBindView(currentHeadViewPos, mHeadView);
                } else {//recyclerview滚动，headview不在顶部
                    if (currentlastPosition > nextHeadViewPos && nextHeadViewPos != currentHeadViewPos) {//下一个headview出现
                        int headviewHeight = mHeadView.getHeight();
                        View nextView = controlInterface.getHeadView(nextHeadViewPos);
                        if (nextView == null) return;
                        if (nextView.getVisibility() == View.INVISIBLE) {
                            nextView.setVisibility(View.VISIBLE);
                        }
                        //进行悬浮偏移
                        headViewOffset(nextView, headviewHeight);
                    }
                }
                currentHeadViewPos = controlInterface.getmonthPosition(currentFirstPosition);
                if (currentHeadViewPos != currentFirstPosition && mHeadView.getVisibility() == View.GONE) {
                    mHeadView.setVisibility(View.VISIBLE);
                    controlInterface.onBindView(currentHeadViewPos, mHeadView);
                    headViewOffset(null, 0);
                }


                //当处于第一位置划动时，在划动的结束或则开始需要设置外部标题的展示与隐藏,只需在大于或则小于headView高度时做判断
                //其他时候不用做多余的判断&&(firstViewTopPos<0||firstViewTopPos==0)
//                if ((oldFirstPosition == currentFirstPosition) && firstViewTopPos <= 0) {
//                    isnNotifyHeadViewState(mHeadView.getVisibility() == View.VISIBLE);
//                }
//
//                if (firstViewTopPos == 0) {
//                    isNotifyHeadView = true;
//                } else {
//                    isNotifyHeadView = false;
//                }

//                //两次psition不一致就需要进行
//                if (controlInterface != null && oldFirstPosition != currentFirstPosition) {
//                    controlInterface.onBindView(currentFirstPosition, mHeadView);
//                    oldFirstPosition = currentFirstPosition;
//                }
//                headViewOffset(nextView, headviewHeight);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });
    }


    private void isnNotifyHeadViewState(boolean isShow) {
        if (controlInterface != null) {
            controlInterface.showHeadView(currentHeadViewPos, isShow);
        }
    }

    private void headViewOffset(View nextView, int headviewHeight) {
        int measuredHeight = mHeadView.getMeasuredHeight();
        if (nextView != null && nextView.getTop() < headviewHeight) {
            int headerTop = round((nextView.getTop() - headviewHeight) * 1.1f);
            if (mHeadView.getTop() != headerTop || mHeadView.getHeight() != measuredHeight) {
                mHeadView.layout(0, headerTop,
                        mHeadView.getMeasuredWidth(), measuredHeight + headerTop);
            }
        } else {
            mHeadView.layout(0, 0, mHeadView.getMeasuredWidth(), measuredHeight);
        }
    }

    /**
     * 获取悬浮标题布局
     *
     * @return
     */
    public View getmHeadView() {
        return mHeadView;
    }


    public interface HcPinnedControlInterface {
        View onCreateView(ViewGroup parentView);//获取悬浮布局

        void onBindView(int position, View headView);//需要跟新当前标题的时候会调用，机制是，判断当前position跟上次保存的是否一直

        void showHeadView(int position, boolean show);//通知外部，当前headview的暂时展示状态，有点鸡肋，会一直通知，

        boolean isHeadView(int position);//判断当前position是否是headview

        int getNextHeadViewPosition(int position);//获取下一个headview的position（在总的位置）

        View getHeadView(int position);//根据position获取headview

        int getmonthPosition(int position);//跟据position获取月份的位置（在总的位置）
    }
}
