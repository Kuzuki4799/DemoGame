package com.shockwave.demogame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class BeautyPanelItemView extends FrameLayout implements ItemView {
    private ImageView imge_abc;
    private View overlay;

    public BeautyPanelItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    public BeautyPanelItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BeautyPanelItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        inflate(context, R.layout.beauty_panel_item, this);
        this.overlay = findViewById(R.id.overlay);
        this.imge_abc = (ImageView) findViewById(R.id.imge_abc);
        this.imge_abc.setClipToOutline(true);
    }

    public void setFocus(boolean z) {
        View view = this.overlay;
        if (view != null) {
            view.setVisibility(z ? 4 : 0);
        }
    }

    public void setImageResource(int i) {
        this.imge_abc.setImageResource(i);
    }
}
