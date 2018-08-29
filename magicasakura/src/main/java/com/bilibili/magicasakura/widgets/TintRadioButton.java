package com.bilibili.magicasakura.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.bilibili.magicasakura.utils.TintManager;

/**
 * @author xyczero617@gmail.com
 * @time 16/1/19
 */
public class TintRadioButton extends AppCompatRadioButton implements Tintable,
        AppCompatBackgroundHelper.BackgroundExtensible,
        AppCompatCompoundButtonHelper.CompoundButtonExtensible,
        AppCompatTextHelper.TextExtensible {

    private AppCompatTextHelper mTextHelper;
    private AppCompatBackgroundHelper mBackgroundHelper;
    private AppCompatCompoundButtonHelper mCompoundButtonHelper;

    public TintRadioButton(Context context) {
        this(context, null);
    }

    public TintRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.radioButtonStyle);
    }

    public TintRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }
        final TintManager tintManager = TintManager.get(context);

        mTextHelper = new AppCompatTextHelper(this, tintManager);
        mTextHelper.loadFromAttribute(attrs, defStyleAttr);

        mBackgroundHelper = new AppCompatBackgroundHelper(this, tintManager);
        mBackgroundHelper.loadFromAttribute(attrs, defStyleAttr);

        mCompoundButtonHelper = new AppCompatCompoundButtonHelper(this, tintManager);
        mCompoundButtonHelper.loadFromAttribute(attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (ThemeUtils.isSkipAnimatedSelector()) {
            final Drawable drawable = CompoundButtonCompat.getButtonDrawable(this);
            if (drawable != null) {
                if (ThemeUtils.getWrapperDrawable(drawable) instanceof AnimatedStateListDrawable) {
                    drawable.jumpToCurrentState();
                }
            }
        }
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        if (mTextHelper != null) {
            mTextHelper.setTextColor();
        }
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(colors);
        if (mTextHelper != null) {
            mTextHelper.setTextColor();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void setTextAppearance(int resId) {
        super.setTextAppearance(resId);
        if (mTextHelper != null) {
            mTextHelper.setTextAppearanceForTextColor(resId);
        }
    }

    @Override
    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        if (mTextHelper != null) {
            mTextHelper.setTextAppearanceForTextColor(resId);
        }
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
        if (mBackgroundHelper != null) {
            mBackgroundHelper.setBackgroundDrawableExternal(background);
        }
    }

    @Override
    public void setBackgroundResource(int resId) {
        if (mBackgroundHelper != null) {
            mBackgroundHelper.setBackgroundResId(resId);
        } else {
            super.setBackgroundResource(resId);
        }
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        if (mBackgroundHelper != null) {
            mBackgroundHelper.setBackgroundColor(color);
        }
    }

    @Override
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        if (mCompoundButtonHelper != null) {
            mCompoundButtonHelper.setButtonDrawable();
        }
    }

    @Override
    public void setButtonDrawable(@DrawableRes int resId) {
        if (mCompoundButtonHelper != null) {
            mCompoundButtonHelper.setButtonDrawable(resId);
        } else {
            super.setButtonDrawable(resId);
        }
    }

    @Override
    public int getCompoundPaddingLeft() {
        final int value = super.getCompoundPaddingLeft();
        return mCompoundButtonHelper != null
                ? mCompoundButtonHelper.getCompoundPaddingLeft(value)
                : value;
    }

    @Override
    public void setBackgroundTintList(int resId) {
        if (mBackgroundHelper != null) {
            mBackgroundHelper.setBackgroundTintList(resId, null);
        }
    }

    @Override
    public void setBackgroundTintList(int resId, PorterDuff.Mode mode) {
        if (mBackgroundHelper != null) {
            mBackgroundHelper.setBackgroundTintList(resId, mode);
        }
    }

    @Override
    public void setCompoundButtonTintList(int resId) {
        if (mCompoundButtonHelper != null) {
            mCompoundButtonHelper.setButtonDrawableTintList(resId, null);
        }
    }

    @Override
    public void setCompoundButtonTintList(int resId, PorterDuff.Mode mode) {
        if (mCompoundButtonHelper != null) {
            mCompoundButtonHelper.setButtonDrawableTintList(resId, mode);
        }
    }

    @Override
    public void setTextColorById(@ColorRes int colorId) {
        if (mTextHelper != null) {
            mTextHelper.setTextColorById(colorId);
        }
    }

    @Override
    public void tint() {
        if (mTextHelper != null) {
            mTextHelper.tint();
        }
        if (mBackgroundHelper != null) {
            mBackgroundHelper.tint();
        }
        if (mCompoundButtonHelper != null) {
            mCompoundButtonHelper.tint();
        }
    }
}
