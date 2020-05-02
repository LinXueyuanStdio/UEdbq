package pub.xichen.uedbq;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AppActivity;

public class UeActivity extends AppActivity {

    UE spineBoy;
    View spineBoyView;

    Button btnWalk, btnRun, btnJump, btnIdle, btnShoot, btnHit, btnDeath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ue);

        btnWalk = (Button) findViewById(R.id.btn_walk);
        btnWalk.setOnClickListener(vOnClickListener);

        btnRun = (Button) findViewById(R.id.btn_run);
        btnRun.setOnClickListener(vOnClickListener);

        btnJump = (Button) findViewById(R.id.btn_jump);
        btnJump.setOnClickListener(vOnClickListener);

        btnIdle = (Button) findViewById(R.id.btn_idle);
        btnIdle.setOnClickListener(vOnClickListener);

        btnShoot = (Button) findViewById(R.id.btn_shoot);
        btnShoot.setOnClickListener(vOnClickListener);

        btnHit = (Button) findViewById(R.id.btn_hit);
        btnHit.setOnClickListener(vOnClickListener);

        btnDeath = (Button) findViewById(R.id.btn_death);
        btnDeath.setOnClickListener(vOnClickListener);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.r = cfg.g = cfg.b = cfg.a = 8;
        spineBoy = new UE();
        spineBoyView = initializeForView(spineBoy, cfg);
        if (spineBoyView instanceof SurfaceView) {
            SurfaceView glView = (SurfaceView) spineBoyView;
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderOnTop(true);
        }
        addSpineBoy();
    }

    public void addSpineBoy() {
//        final WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

//        spineBoyView.setOnTouchListener(new View.OnTouchListener() {
//
//            float lastX, lastY;
//
//            public boolean onTouch(View v, MotionEvent event) {
//                final int action = event.getAction();
//                float x = event.getRawX();
//                float y = event.getRawY();
//                if (action == MotionEvent.ACTION_DOWN) {
//                    lastX = x;
//                    lastY = y;
//                } else if (action == MotionEvent.ACTION_MOVE) {
//                    layoutParams.x += (int) (x - lastX);
//                    layoutParams.y += (int) (y - lastY);
//                    windowManager.updateViewLayout(spineBoyView, layoutParams);
//                    lastX = x;
//                    lastY = y;
//                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
//                    spineBoy.setAnimate();
//                }
//                return true;
//            }
//        });
//        layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
//        layoutParams.flags = 40;
//        layoutParams.width = dp2Px(144);
//        layoutParams.height = dp2Px(200);
//        layoutParams.format = -3;
//        windowManager.addView(spineBoyView, layoutParams);
        FrameLayout frameLayout = findViewById(R.id.container);
        frameLayout.addView(spineBoyView);
    }

    public int dp2Px(float value) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    View.OnClickListener vOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnWalk) {
                spineBoy.setAnimate("07_walk");
            } else if (view == btnRun) {
                spineBoy.setAnimate("07_run");
            } else if (view == btnJump) {
                spineBoy.setAnimate("07_landing");
            } else if (view == btnIdle) {
                spineBoy.setAnimate("07_idle");
            } else if (view == btnShoot) {
                spineBoy.setAnimate("07_attack");
            } else if (view == btnHit) {
                spineBoy.setAnimate("07_damage");
            } else if (view == btnDeath) {
                spineBoy.setAnimate("07_die");
            }
        }
    };

    @Override
    protected void onDestroy() {
        getWindowManager().removeView(spineBoyView);
        super.onDestroy();
    }
}
