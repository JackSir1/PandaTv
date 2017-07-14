package com.example.administrator.pandatv.module.chinaLive;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.pandatv.R;

import java.util.List;

public class MyGridLayout extends GridLayout {
	private View mDragedView;
	public MyGridLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public MyGridLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyGridLayout(Context context) {
		this(context, null);
	}

	private void init() {
		setColumnCount(3);
		setLayoutTransition(new LayoutTransition());
	}

	public void setItems(List<String> list) {
		for (String strItem : list) {
			addItem(strItem);
		}

	}

	private int mMargin = 15;
	private boolean mDragAble;

	private void addItem(String strItem) {
//		LinearLayout linearLayout=new LinearLayout(getContext());
		TextView tv = new TextView(getContext());
		LayoutParams lp = new LayoutParams();
		lp.width = getResources().getDisplayMetrics().widthPixels / 3 - mMargin
				* 4;
		lp.height = 60;
		lp.setMargins(mMargin, mMargin, mMargin, mMargin);

		LayoutParams lp1 = new LayoutParams();
		lp1.width = LayoutParams.WRAP_CONTENT;
		lp1.height = LayoutParams.WRAP_CONTENT;
		lp1.setGravity(Gravity.END);
		tv.setLayoutParams(lp);
		tv.setGravity(Gravity.CENTER);
		tv.setBackground(getResources().getDrawable(R.drawable.shape_white_corner));
		tv.setPadding(10, 10, 10, 10);
		tv.setText(strItem);
		ImageView iv=new ImageView(getContext());
		iv.setImageResource(R.drawable.com_facebook_close);
		iv.setLayoutParams(lp1);
		iv.setVisibility(GONE);

		MyGridLayout.this.addView(tv);
		if (mDragAble) {
			tv.setOnLongClickListener(ocl);
		} else {
			tv.setOnLongClickListener(null);
		}

	}
	// TextView(MyGridLayout的条目)的长按事件
	private OnLongClickListener ocl = new OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {
			mDragedView = v;
			v.startDrag(null, new DragShadowBuilder(v), null, 0);
			v.setEnabled(true);
			return true;
		}
	};
	// 向外界提供是否能拖拽的方法
	public void setDragAble(boolean isDrage) {
		this.mDragAble = isDrage;
		if (mDragAble) {
			this.setOnDragListener(odl);
		} else {
			this.setOnDragListener(null);
		}
	}
	// 拖拽监听器
	private OnDragListener odl = new OnDragListener() {

		@Override
		public boolean onDrag(View arg0, DragEvent event) {

			switch (event.getAction()) {
				// 开始拖拽
			case DragEvent.ACTION_DRAG_STARTED:
				initRect();
				break;
			// 实时监听拖拽事件
			case DragEvent.ACTION_DRAG_LOCATION:
				int index = getIntouchIndex(event);
				if (index > -1 && mDragedView != null
						&& mDragedView != getChildAt(index)) {
					removeView(mDragedView);
					addView(mDragedView, index);
				}
				break;
				// 停止拖拽
			case DragEvent.ACTION_DRAG_ENDED:
				if (mDragedView != null) {
					mDragedView.setEnabled(true);
				}

				break;
			}
			return true;
		}

	};

	// 将所有的条目都封装成矩形然后存入数组
	private Rect[] mRectArr;

	private void initRect() {
		int childViewCount = getChildCount();
		mRectArr = new Rect[childViewCount];
		for (int i = 0; i < childViewCount; i++) {
			View childView = getChildAt(i);
			Rect rect = new Rect(childView.getLeft(), childView.getTop(),
					childView.getRight(), childView.getBottom());
			mRectArr[i] = rect;
		}

	}
	// 实时监听拖拽的点是否进入到了某一个子控件范围内
	private int getIntouchIndex(DragEvent event) {
		for (int i = 0; i < mRectArr.length; i++) {
			if (mRectArr[i].contains((int) event.getX(), (int) event.getY())) {
				return i;
			}
		}
		return -1;
	}
}
