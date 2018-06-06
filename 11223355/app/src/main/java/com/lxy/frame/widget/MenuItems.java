package com.lxy.frame.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxy.frame.R;

/**
 * Created by LXY on 2018/5/16.
 */

public class MenuItems extends RelativeLayout {
    TextView leftetxt;
    ImageView lefticon;
    TextView centreText;
    TextView righttetx;
    ImageView righticon;
    EditText editText;
     TypedArray att;
    public MenuItems(Context context) {
        super(context);
    }

    public MenuItems(Context context, AttributeSet attrs) {
        super(context, attrs);
        att = context.obtainStyledAttributes(
                attrs, R.styleable.MenuItems, 0, 0);
        int lefticonID = att.getResourceId(R.styleable.MenuItems_lefticon, 0);
        if (lefticonID!=0){
            setLefticon(lefticonID);
        }
        int righticonID = att.getResourceId(R.styleable.MenuItems_righticon, 0);
        if (righticonID!=0){
            setRighticon(righticonID);
        }

        if (!TextUtils.isEmpty(att.getString(R.styleable.MenuItems_lefttext))){
            setLeftText(att.getString(R.styleable.MenuItems_lefttext));
        }
        if (!TextUtils.isEmpty(att.getString(R.styleable.MenuItems_righttext))){
            setRighttetx(att.getString(R.styleable.MenuItems_righttext));
        }
        if (!TextUtils.isEmpty(att.getString(R.styleable.MenuItems_centext))){
            setCentreText(att.getString(R.styleable.MenuItems_centext));
        }
    }

    public MenuItems(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       att = context.obtainStyledAttributes(
                attrs, R.styleable.MenuItems, 0, 0);

        if (!TextUtils.isEmpty(att.getString(R.styleable.MenuItems_lefttext))){
            setLeftText(att.getString(R.styleable.MenuItems_lefttext));
        }
        if (!TextUtils.isEmpty(att.getString(R.styleable.MenuItems_righttext))){
            setRighttetx(att.getString(R.styleable.MenuItems_righttext));
        }
        if (!TextUtils.isEmpty(att.getString(R.styleable.MenuItems_centext))){
            setCentreText(att.getString(R.styleable.MenuItems_centext));
        }
        int lefticonID = att.getResourceId(R.styleable.MenuItems_lefticon, 0);
        if (lefticonID!=0){
            setLefticon(lefticonID);
        }
        int righticonID = att.getResourceId(R.styleable.MenuItems_righticon, 0);
        if (righticonID!=0){
            setRighticon(righticonID);
        }
        boolean editext = att.getBoolean(R.styleable.MenuItems_iseditext, false);
//        if (editext){
//            setEditText("","23123123123");
//        }
    }

    public void setLeftText(String text) {
        RelativeLayout.LayoutParams layoutParams = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        if (lefticon != null) {
            layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.lefticon);
            layoutParams.setMargins(20, 0, 0, 0);
        } else {
            layoutParams.setMargins(40, 0, 0, 0);
        }
        if (leftetxt == null) {
            leftetxt = new TextView(getContext());
            leftetxt.setId(R.id.leftetxt);
            leftetxt.setText(text);
            leftetxt.setLayoutParams(layoutParams);
            leftetxt.setTextSize(att.getInt(R.styleable.MenuItems_lefttextsize,12));
            leftetxt.setTextColor(att.getColor(R.styleable.MenuItems_lefttextcolock,getResources().getColor(R.color.black)));
            this.addView(leftetxt);
        } else {
            leftetxt.setText(text);
            leftetxt.setLayoutParams(layoutParams);
        }
    }


    public void setLefticon(@DrawableRes int icon) {
        RelativeLayout.LayoutParams layoutParams;

            layoutParams    = new
                    RelativeLayout.LayoutParams(att.getInt(R.styleable.MenuItems_lefticonwidth,RelativeLayout.LayoutParams.WRAP_CONTENT)
                    , att.getInt(R.styleable.MenuItems_lefticonwidth,RelativeLayout.LayoutParams.WRAP_CONTENT));


        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.setMargins(40, 0, 0, 0);
        if (lefticon == null) {
            lefticon = new ImageView(getContext());
            lefticon.setImageResource(icon);
            lefticon.setLayoutParams(layoutParams);
            lefticon.setId(R.id.lefticon);
            this.addView(lefticon);
        } else {
            lefticon.setImageResource(icon);
            lefticon.setLayoutParams(layoutParams);
        }
    }

    public void setCentreText(String text) {
        RelativeLayout.LayoutParams layoutParams = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        if (centreText == null) {
            centreText = new TextView(getContext());
            centreText.setId(R.id.centtext);
            centreText.setText(text);
            centreText.setLayoutParams(layoutParams);
            centreText.setTextColor(att.getColor(R.styleable.MenuItems_centextcolock,getResources().getColor(R.color.black)));
            centreText.setTextSize(att.getInt(R.styleable.MenuItems_centextsize,12));
            this.addView(centreText);
        } else {
            centreText.setText(text);
            centreText.setLayoutParams(layoutParams);
        }
    }

    public void setRighttetx(String text) {
        RelativeLayout.LayoutParams layoutParams = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        if (righticon != null) {
            layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.righticon);
            layoutParams.setMargins(0, 0, 20, 0);
        } else {
            layoutParams.setMargins(0, 0, 40, 0);
        }
        if (righttetx == null) {
            righttetx = new TextView(getContext());
            righttetx.setId(R.id.righttext);
            righttetx.setText(text);
            righttetx.setLayoutParams(layoutParams);
            righttetx.setTextColor(att.getColor(R.styleable.MenuItems_righttextcolock,getResources().getColor(R.color.black)));
            righttetx.setTextSize(att.getInt(R.styleable.MenuItems_righttextsize,12));
            this.addView(righttetx);
        } else {
            righttetx.setText(text);
            righttetx.setLayoutParams(layoutParams);
        }
    }

    public void setRighticon(@DrawableRes int icon) {
        RelativeLayout.LayoutParams layoutParams;
        layoutParams    = new
                RelativeLayout.LayoutParams(att.getInt(R.styleable.MenuItems_righticonwidth,RelativeLayout.LayoutParams.WRAP_CONTENT), att.getInt(R.styleable.MenuItems_righticonwidth,RelativeLayout.LayoutParams.WRAP_CONTENT));

        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL  );
        layoutParams.setMargins(0, 0, 40, 0);
        if (righticon == null) {
            righticon = new ImageView(getContext());
            righticon.setImageResource(icon);
            righticon.setLayoutParams(layoutParams);
            righticon.setId(R.id.righticon);
            this.addView(righticon);
        } else {
            righticon.setImageResource(icon);
            righticon.setLayoutParams(layoutParams);
        }
    }

//    public void setEditText(String hindext ,String text) {
//        RelativeLayout.LayoutParams layoutParams;
//            layoutParams    = new
//                    RelativeLayout.LayoutParams(att.getInt(R.styleable.MenuItems_righticonwidth,RelativeLayout.LayoutParams.WRAP_CONTENT), att.getInt(R.styleable.MenuItems_righticonwidth,RelativeLayout.LayoutParams.WRAP_CONTENT));
//
//        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//        if (righticon != null) {
//            layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.righticon);
//            layoutParams.setMargins(0, 0, 20, 0);
//        } else {
//            layoutParams.setMargins(0, 0, 40, 0);
//        }
//        if (editText == null) {
//            editText = new EditText(getContext());
//            editText.setId(R.id.editext);
//            editText.setText(text);
//            editText.setLayoutParams(layoutParams);
//            editText.setHint(hindext);
//            this.addView(editText);
//        } else {
//            editText.setHint(hindext);
//            editText.setText(text);
//            editText.setLayoutParams(layoutParams);
//        }
//    }
}
