package com.shockwave.demogame;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.shockwave.demogame.R;
import android.widget.ImageView;

public class BeautyLuckyMonkeyPanelView extends FrameLayout {
    private static final int DEFAULT_SPEED = 150;
    private static final int MIN_SPEED = 50;
    private BeautyPanelItemView beauty1;
    private BeautyPanelItemView beauty2;
    private BeautyPanelItemView beauty3;
    private BeautyPanelItemView beauty4;
    private BeautyPanelItemView beauty6;
    private BeautyPanelItemView beauty7;
    private BeautyPanelItemView beauty8;
    private BeautyPanelItemView beauty9;
    /* access modifiers changed from: private */
    public ItemView[] beautyArr;
    /* access modifiers changed from: private */
    public ImageView bg_1;
    /* access modifiers changed from: private */
    public ImageView bg_2;
    /* access modifiers changed from: private */
    public int currentIndex;
    /* access modifiers changed from: private */
    public int currentSpeed;
    private int currentTotal;
    /* access modifiers changed from: private */
    public boolean isGameRunning;
    /* access modifiers changed from: private */
    public boolean isMarqueeRunning;
    /* access modifiers changed from: private */
    public boolean isTryToStop;
    /* access modifiers changed from: private */
    public int stayIndex;

    static /* synthetic */ int access$508(BeautyLuckyMonkeyPanelView beautyLuckyMonkeyPanelView) {
        int i = beautyLuckyMonkeyPanelView.currentIndex;
        beautyLuckyMonkeyPanelView.currentIndex = i + 1;
        return i;
    }

    public BeautyLuckyMonkeyPanelView(Context context) {
        this(context, (AttributeSet) null);
    }

    public BeautyLuckyMonkeyPanelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BeautyLuckyMonkeyPanelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.beautyArr = new ItemView[8];
        this.currentIndex = 0;
        this.currentTotal = 0;
        this.stayIndex = 0;
        this.isMarqueeRunning = false;
        this.isGameRunning = false;
        this.isTryToStop = false;
        this.currentSpeed = DEFAULT_SPEED;
        inflate(context, R.layout.beauty_panel, this);
        setupView();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startMarquee();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        stopMarquee();
        super.onDetachedFromWindow();
    }

    private void setupView() {
        this.bg_1 = (ImageView) findViewById(R.id.bg_1);
        this.bg_2 = (ImageView) findViewById(R.id.bg_2);
        this.beauty1 = (BeautyPanelItemView) findViewById(R.id.item1);
        this.beauty2 = (BeautyPanelItemView) findViewById(R.id.item2);
        this.beauty3 = (BeautyPanelItemView) findViewById(R.id.item3);
        this.beauty4 = (BeautyPanelItemView) findViewById(R.id.item4);
        this.beauty6 = (BeautyPanelItemView) findViewById(R.id.item6);
        this.beauty7 = (BeautyPanelItemView) findViewById(R.id.item7);
        this.beauty8 = (BeautyPanelItemView) findViewById(R.id.item8);
        BeautyPanelItemView beautyPanelItemView = (BeautyPanelItemView) findViewById(R.id.item9);
        this.beauty9 = beautyPanelItemView;
        ItemView[] itemViewArr = this.beautyArr;
        itemViewArr[0] = this.beauty4;
        itemViewArr[1] = this.beauty1;
        itemViewArr[2] = this.beauty2;
        itemViewArr[3] = this.beauty3;
        itemViewArr[4] = this.beauty6;
        itemViewArr[5] = beautyPanelItemView;
        itemViewArr[6] = this.beauty8;
        itemViewArr[7] = this.beauty7;
        itemViewArr[0].setImageResource(R.drawable.beauty_1);
        this.beautyArr[1].setImageResource(R.drawable.beauty_2);
        this.beautyArr[2].setImageResource(R.drawable.beauty_3);
        this.beautyArr[3].setImageResource(R.drawable.beauty_4);
        this.beautyArr[4].setImageResource(R.drawable.beauty_5);
        this.beautyArr[5].setImageResource(R.drawable.beauty_6);
        this.beautyArr[6].setImageResource(R.drawable.beauty_7);
        this.beautyArr[7].setImageResource(R.drawable.beauty_8);
    }

    private void stopMarquee() {
        this.isMarqueeRunning = false;
        this.isGameRunning = false;
        this.isTryToStop = false;
    }

    private void startMarquee() {
        this.isMarqueeRunning = true;
        new Thread(new Runnable() {
            public void run() {
                while (BeautyLuckyMonkeyPanelView.this.isMarqueeRunning) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    BeautyLuckyMonkeyPanelView.this.post(new Runnable() {
                        public void run() {
                            if (BeautyLuckyMonkeyPanelView.this.bg_1 != null && BeautyLuckyMonkeyPanelView.this.bg_2 != null) {
                                if (BeautyLuckyMonkeyPanelView.this.bg_1.getVisibility() == 0) {
                                    BeautyLuckyMonkeyPanelView.this.bg_1.setVisibility(8);
                                    BeautyLuckyMonkeyPanelView.this.bg_2.setVisibility(0);
                                    return;
                                }
                                BeautyLuckyMonkeyPanelView.this.bg_1.setVisibility(0);
                                BeautyLuckyMonkeyPanelView.this.bg_2.setVisibility(8);
                            }
                        }
                    });
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public long getInterruptTime() {
        int i = this.currentTotal + 1;
        this.currentTotal = i;
        if (this.isTryToStop) {
            int i2 = this.currentSpeed + 10;
            this.currentSpeed = i2;
            if (i2 > DEFAULT_SPEED) {
                this.currentSpeed = DEFAULT_SPEED;
            }
        } else {
            if (i / this.beautyArr.length > 0) {
                this.currentSpeed -= 10;
            }
            if (this.currentSpeed < 50) {
                this.currentSpeed = 50;
            }
        }
        return (long) this.currentSpeed;
    }

    public boolean isGameRunning() {
        return this.isGameRunning;
    }

    public void startGame() {
        this.isGameRunning = true;
        this.isTryToStop = false;
        this.currentSpeed = DEFAULT_SPEED;
        new Thread(new Runnable() {
            public void run() {
                while (BeautyLuckyMonkeyPanelView.this.isGameRunning) {
                    try {
                        Thread.sleep(BeautyLuckyMonkeyPanelView.this.getInterruptTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    BeautyLuckyMonkeyPanelView.this.post(new Runnable() {
                        public void run() {
                            int access$500 = BeautyLuckyMonkeyPanelView.this.currentIndex;
                            BeautyLuckyMonkeyPanelView.access$508(BeautyLuckyMonkeyPanelView.this);
                            if (BeautyLuckyMonkeyPanelView.this.currentIndex >= BeautyLuckyMonkeyPanelView.this.beautyArr.length) {
                                int unused = BeautyLuckyMonkeyPanelView.this.currentIndex = 0;
                            }
                            BeautyLuckyMonkeyPanelView.this.beautyArr[access$500].setFocus(false);
                            BeautyLuckyMonkeyPanelView.this.beautyArr[BeautyLuckyMonkeyPanelView.this.currentIndex].setFocus(true);
                            if (BeautyLuckyMonkeyPanelView.this.isTryToStop && BeautyLuckyMonkeyPanelView.this.currentSpeed == BeautyLuckyMonkeyPanelView.DEFAULT_SPEED && BeautyLuckyMonkeyPanelView.this.stayIndex == BeautyLuckyMonkeyPanelView.this.currentIndex) {
                                boolean unused2 = BeautyLuckyMonkeyPanelView.this.isGameRunning = false;
                            }
                        }
                    });
                }
            }
        }).start();
    }

    public void tryToStop(int i) {
        this.stayIndex = i;
        this.isTryToStop = true;
    }
}
